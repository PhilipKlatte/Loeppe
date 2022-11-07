package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.service.CustomerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Loeppe | Kunden")
@Route(value = "customer", layout = LoeppeLayout.class)
public class CustomerListView extends VerticalLayout {

    private final Grid<Customer> grid = new Grid<>(Customer.class);
    private final IntegerField custId;
    private final TextField custLastName;
    private final TextField custFirstName;
    private final TextField custAddress;
    private final CustomerService service;
    private final FormLayout custSearch;
    private final FormLayout buttonLayout;

    public CustomerListView(CustomerService service) {
        this.service = service;

        H2 headline = new H2("Kundenliste");
        headline.getStyle().set("margin-top", "10px");

        custId = new IntegerField();
        custLastName = new TextField();
        custFirstName = new TextField();
        custAddress = new TextField();
        custSearch = createCustSearch();
        buttonLayout = createButtons();

        configureGrid();

        add(headline, getContent(), grid);

        updateList();
    }

    public FormLayout createCustSearch() {
        FormLayout custSearch = new FormLayout();
        custSearch.addFormItem(custId, "Kunden-ID");
        custSearch.addFormItem(custLastName, "Nachname");
        custSearch.addFormItem(custFirstName, "Vorname");
        custSearch.addFormItem(custAddress, "Adresse");
        return custSearch;
    }

    public FormLayout createButtons() {
        FormLayout layout = new FormLayout();
        Button search = new Button("Suche");
        createBListener(search);
        layout.add(search);
        layout.setWidth("25em");
        return layout;
    }

    public Component getContent() {
        HorizontalLayout content = new HorizontalLayout(custSearch, buttonLayout);
        content.setFlexGrow(2, custSearch);
        content.setFlexGrow(1, buttonLayout);
        return content;
    }

    private void configureGrid() {
        grid.setColumns("id", "firstname", "lastname", "address");
    }

    private void updateList() {
        grid.setItems(service.getAllCustomer());
    }

    private void createBListener(Button b) {
        b.addClickListener(clickEvent -> {

        });
    }
}
