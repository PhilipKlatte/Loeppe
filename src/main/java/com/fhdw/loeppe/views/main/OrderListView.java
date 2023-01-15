package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.service.ArticleQuantityService;
import com.fhdw.loeppe.service.ArticleService;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.service.OrderService;
import com.fhdw.loeppe.util.Country;
import com.fhdw.loeppe.util.OrderStatus;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("Loeppe | Aufträge")
@Route(value = "auftrag", layout = LoeppeLayout.class)
public class OrderListView extends VerticalLayout {

    private final Grid<Order> grid = new Grid<>(Order.class, false);
    OrderInputForm form;
    final private TextField orderID = new TextField();
    final private ComboBox<OrderStatus> orderStatus = new ComboBox<>();
    final private TextField custID = new TextField();
    final private TextField custFirstname = new TextField();
    final private TextField custLastname = new TextField();
    final private TextField custEmail = new TextField();
    final private TextField custPhone = new TextField();
    final private TextField custStreet = new TextField();
    final private TextField custCity = new TextField();
    final private TextField custPostal = new TextField();
    final private ComboBox<Country> custCountry = new ComboBox<>();
    private final OrderService service;
    private final CustomerService customerService;
    private final ArticleQuantityService articleQuantityService;
    private final ArticleService articleService;
    private final FormLayout custSearch;
    private final FormLayout buttonLayout;

    public OrderListView(
            OrderService service,
            CustomerService customerService,
            ArticleQuantityService articleQuantityService,
            ArticleService articleService) {
        setSizeFull();
        this.service = service;
        this.customerService = customerService;
        this.articleQuantityService = articleQuantityService;
        this.articleService = articleService;

        custSearch = createSearchForm();
        buttonLayout = createButtons();
        orderStatus.setItems(OrderStatus.values());
        configureGrid();
        configureForm();
        add(getHeader(), getTop(), getContent());
        updateList();
        closeForm();
    }

    private FormLayout createSearchForm() {
        FormLayout layout = new FormLayout();
        layout.setSizeFull();
        orderID.setSizeFull();
        orderStatus.setSizeFull();
        custID.setSizeFull();
        custFirstname.setSizeFull();
        custLastname.setSizeFull();
        custEmail.setSizeFull();
        custPhone.setSizeFull();
        custStreet.setSizeFull();
        custCity.setSizeFull();
        custPostal.setSizeFull();
        custCountry.setSizeFull();
        custCountry.setItems(Country.values());
        layout.addFormItem(orderID, "Auftragsnummer");
        layout.addFormItem(orderStatus, "Auftagsstatus");
        layout.addFormItem(custID, "Kundennummer");
        layout.addFormItem(custFirstname, "Vorname");
        layout.addFormItem(custLastname, "Nachname");
        layout.addFormItem(custEmail, "Email");
        layout.addFormItem(custPhone, "Telefon");
        layout.addFormItem(custStreet, "Straße");
        layout.addFormItem(custCity, "Stadt");
        layout.addFormItem(custPostal, "Postleitzahl");
        layout.addFormItem(custCountry, "Land");

        return layout;
    }

    private FormLayout createButtons() {
        FormLayout layout = new FormLayout();
        Button search = new Button("Suche");
        search.addClickListener(click -> searchOrder());
        Button add = new Button("Auftrag Hinzufügen");
        add.addClickListener(click -> addCustomer());
        layout.add(search, add);
        layout.setWidth("25em");
        return layout;
    }

    private H2 getHeader() {
        H2 headline = new H2("Auftragsliste");
        headline.getStyle().set("margin-top", "10px");
        return headline;
    }

    private Component getTop() {
        HorizontalLayout content = new HorizontalLayout(custSearch, buttonLayout);
        content.setFlexGrow(2, custSearch);
        content.setFlexGrow(1, buttonLayout);
        return content;
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(Order::getId).setHeader("Auftragsnummer");
        grid.addColumn(Order::getOrderStatus).setHeader("Auftragsstatus");
        grid.addColumn(order -> order.getCustomer().getId()).setHeader("Kundennummer");
        grid.addColumn(order -> order.getCustomer().getFirstname()).setHeader("Vorname");
        grid.addColumn(order -> order.getCustomer().getLastname()).setHeader("Nachname");
        grid.asSingleSelect().addValueChangeListener(event -> editOrder(event.getValue()));
        grid.getColumns().forEach(e -> e.setResizable(true));
        grid.getColumns().forEach(e -> e.setAutoWidth(true));
    }
    
    private void editOrder(Order order) {
        if(order == null) {
            closeForm();
        } else {
            form.setOrder(order);
            form.setVisible(true);
        }
    }
    
    private void configureForm() {
        form = new OrderInputForm(customerService, articleQuantityService, articleService);
        form.setWidth("25em");
        form.addListener(OrderInputForm.SaveEvent.class, this::saveOrder);
        form.addListener(OrderInputForm.DeleteEvent.class, this::deleteOrder);
        form.addListener(OrderInputForm.CancleEvent.class, e -> closeForm());
    }
    
    private void saveOrder(OrderInputForm.SaveEvent event) {
        service.saveOrder(event.getOrder());
        updateList();
        closeForm();
    }

    private void deleteOrder(OrderInputForm.DeleteEvent event) {
        service.deleteOrder(event.getOrder());
        updateList();
        closeForm();
    }

    private void addCustomer() {
        grid.asSingleSelect().clear();
        editOrder(new Order());
    }

    private void updateList()
    {
        grid.setItems(service.getAllOrders());
    }

    private void searchOrder() {
        if(orderID.isEmpty() && orderStatus.isEmpty() && custID.isEmpty() && custFirstname.isEmpty() &&
        custLastname.isEmpty() && custEmail.isEmpty() && custPhone.isEmpty() &&
        custStreet.isEmpty() && custCity.isEmpty() && custPostal.isEmpty() && custCountry.isEmpty()) {
            grid.setItems(service.getAllOrders());
        } else {
            Customer cust = new Customer(custFirstname.getValue(), custLastname.getValue(), custEmail.getValue(),
                    custPhone.getValue(), custStreet.getValue(), custCity.getValue(),
                    custPostal.getValue(), custCountry.getValue());
            grid.setItems(service.searchOrder(orderID.getValue(), custID.getValue(),
                    new Order(cust, orderStatus.getValue())));
        }
    }

    private void closeForm() {
        form.setVisible(false);
    }
}
