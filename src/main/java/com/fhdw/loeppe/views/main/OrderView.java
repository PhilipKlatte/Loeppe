package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.service.ArticleService;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.service.OrderService;
import com.fhdw.loeppe.util.OrderStatus;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Loeppe | Aufträge")
@Route(value = "auftrag", layout = LoeppeLayout.class)
public class OrderView extends VerticalLayout {

    private final Grid<Order> grid = new Grid<>(Order.class);
    final private IntegerField orderID;
    final private IntegerField customerID;
    final private TextField customerFirstname;
    final private TextField customerLastname;
    final private TextField customerAddress;
    final private ComboBox<OrderStatus> orderStatus;
    private final OrderService orderService;

    public OrderView(OrderService orderService, ArticleService articleService, CustomerService customerService) {
        this.orderService = orderService;

        H2 headline = new H2("Auftragsliste");
        headline.getStyle().set("margin-top", "10px");

        orderID = new IntegerField();
        customerID = new IntegerField();
        customerFirstname = new TextField();
        customerLastname = new TextField();
        customerAddress = new TextField();
        orderStatus = new ComboBox<>();
        orderStatus.setItems(OrderStatus.values());

        configureGrid();

        add(headline, createSearchForm(), grid);

        updateList();
    }

    private FormLayout createSearchForm() {
        FormLayout layout = new FormLayout();
        layout.addFormItem(orderID, "Auftrags ID");
        layout.addFormItem(customerID, "Kundennummer");
        layout.addFormItem(customerFirstname, "Vorname");
        layout.addFormItem(customerLastname, "Nachname");
        layout.addFormItem(customerAddress, "Adresse");
        layout.addFormItem(orderStatus, "Auftagsstatus");

        return layout;
    }

    private void configureGrid() {
        grid.setColumns("id", "orderStatus");

        grid.addColumn(order -> order.getCustomer().getId()).setHeader("Kunden ID");
        grid.addColumn(order -> order.getCustomer().getFirstname()).setHeader("Vorname");
        grid.addColumn(order -> order.getCustomer().getLastname()).setHeader("Nachname");
        grid.addColumn(order -> order.getCustomer().getAddress()).setHeader("Adresse");
    }

    private void updateList() {
        grid.setItems(orderService.getAllOrders());
    }
}
