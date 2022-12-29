package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Customer;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.UUID;

@PermitAll
@PageTitle("Loeppe | Aufträge")
@Route(value = "auftrag", layout = LoeppeLayout.class)
public class OrderListView extends VerticalLayout {

    private final Grid<Order> grid = new Grid<>(Order.class, false);
    OrderInputForm form;
    final private TextField orderID = new TextField();
    final private TextField custID = new TextField();
    final private TextField custFirstname = new TextField();
    final private TextField custLastname = new TextField();
    final private TextField custAddress = new TextField();
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
        custID.setSizeFull();
        custFirstname.setSizeFull();
        custLastname.setSizeFull();
        custAddress.setSizeFull();
        orderStatus.setSizeFull();
        layout.addFormItem(orderID, "Auftragsnummer");
        layout.addFormItem(custID, "Kundennummer");
        layout.addFormItem(custFirstname, "Vorname");
        layout.addFormItem(custLastname, "Nachname");
        layout.addFormItem(custAddress, "Adresse");
        layout.addFormItem(orderStatus, "Auftagsstatus");

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

    private void searchOrder() {
        if(orderID.isEmpty() && custID.isEmpty() && custFirstname.isEmpty() &&
        custLastname.isEmpty() && custAddress.isEmpty() && orderStatus.isEmpty()) {
            grid.setItems(service.getAllOrders());
        } else {
            if (!orderID.isEmpty() && !custID.isEmpty()) {
                UUID oID = UUID.fromString(orderID.getValue());
                UUID cID = UUID.fromString(custID.getValue());
                Customer cust = new Customer(cID, custFirstname.getValue(), custLastname.getValue(), custAddress.getValue());
                grid.setItems(service.searchOrderWithOrderIDAndCustID(new Order(oID,
                        cust, orderStatus.getValue())));
            } else if (orderID.isEmpty() && !custID.isEmpty()) {
                UUID cID = UUID.fromString(custID.getValue());
                Customer cust = new Customer(cID, custFirstname.getValue(), custLastname.getValue(), custAddress.getValue());
                grid.setItems(service.searchOrderWithoutOrderIDAndWithCustID((new Order(
                        cust, orderStatus.getValue()))));
            } else if(!orderID.isEmpty() && custID.isEmpty()) {
                UUID oID = UUID.fromString(orderID.getValue());
                Customer cust = new Customer(custFirstname.getValue(), custLastname.getValue(), custAddress.getValue());
                grid.setItems(service.searchOrderWithOrderIDAndWithoutCustID(new Order(oID,
                        cust, orderStatus.getValue())));
            } else if(orderID.isEmpty() && custID.isEmpty()){
                Customer cust = new Customer(custFirstname.getValue(), custLastname.getValue(), custAddress.getValue());
                grid.setItems(service.searchOrderWithoutOrderIDAndWithoutCustID(new Order(
                        cust, orderStatus.getValue())));
            }
        }
    }


    private void closeForm() {
        form.setVisible(false);
    }
}
