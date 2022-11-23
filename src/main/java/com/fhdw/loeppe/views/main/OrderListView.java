package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.service.OrderService;
import com.fhdw.loeppe.util.OrderStatus;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Loeppe | Aufträge")
@Route(value = "auftrag", layout = LoeppeLayout.class)
public class OrderListView extends VerticalLayout {

    private final Grid<Order> grid = new Grid<>(Order.class);
    OrderInputForm form;
    final private IntegerField orderID = new IntegerField();
    final private IntegerField customerID = new IntegerField();
    final private TextField customerFirstname = new TextField();
    final private TextField customerLastname = new TextField();
    final private TextField customerAddress = new TextField();
    final private ComboBox<OrderStatus> orderStatus = new ComboBox<>();
    private final OrderService service;
    private final CustomerService customerService;
    private final FormLayout custSearch;
    private final FormLayout buttonLayout;

    public OrderListView(OrderService service, CustomerService customerService) {
        setSizeFull();
        this.service = service;
        this.customerService = customerService;

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
        customerID.setSizeFull();
        customerFirstname.setSizeFull();
        customerLastname.setSizeFull();
        customerAddress.setSizeFull();
        orderStatus.setSizeFull();
        layout.addFormItem(orderID, "Auftrags ID");
        layout.addFormItem(customerID, "Kundennummer");
        layout.addFormItem(customerFirstname, "Vorname");
        layout.addFormItem(customerLastname, "Nachname");
        layout.addFormItem(customerAddress, "Adresse");
        layout.addFormItem(orderStatus, "Auftagsstatus");

        return layout;
    }

    private FormLayout createButtons() {
        FormLayout layout = new FormLayout();
        Button search = new Button("Auftrag Hinzufügen");
        search.addClickListener(click -> addCustomer());
        layout.add(search);
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
        grid.setColumns("id", "orderStatus");

        grid.addColumn(order -> order.getCustomer().getId()).setHeader("Kunden ID");
        grid.addColumn(order -> order.getCustomer().getFirstname()).setHeader("Vorname");
        grid.addColumn(order -> order.getCustomer().getLastname()).setHeader("Nachname");
        grid.addColumn(order -> order.getCustomer().getAddress()).setHeader("Adresse");
        grid.asSingleSelect().addValueChangeListener(event -> editOrder(event.getValue()));
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
        form = new OrderInputForm(customerService);
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
        service.deleteOrder(event.getOrder().getId());
        updateList();
        closeForm();
    }

    private void addCustomer() {
        grid.asSingleSelect().clear();
        editOrder(new Order());
    }

    private void updateList()
    {
        service.getAllOrders().forEach(System.out::println);
        grid.setItems(service.getAllOrders());
    }

    private void closeForm() {
        form.setVisible(false);
    }
}
