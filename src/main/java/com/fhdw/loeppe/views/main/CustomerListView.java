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
    CustomerInputForm form;
    private final IntegerField id = new IntegerField();
    private final TextField lastname = new TextField();
    private final TextField firstname = new TextField();
    private final TextField address = new TextField();
    private final CustomerService service;
    private final FormLayout custSearch;
    private final FormLayout buttonLayout;

    public CustomerListView(CustomerService service) {
        setSizeFull();
        this.service = service;

        custSearch = createCustSearch();
        buttonLayout = createButtons();

        configureGrid();
        configureForm();
        add(getHeader(), getTop(), getContent());
        updateList();
        closeForm();
    }

    public FormLayout createCustSearch() {
        FormLayout custSearch = new FormLayout();
        custSearch.setSizeFull();
        id.setSizeFull();
        firstname.setSizeFull();
        lastname.setSizeFull();
        address.setSizeFull();
        custSearch.addFormItem(id, "Kunden-ID");
        custSearch.addFormItem(firstname, "Vorname");
        custSearch.addFormItem(lastname, "Nachname");
        custSearch.addFormItem(address, "Adresse");

        return custSearch;
    }

    private FormLayout createButtons() {
        FormLayout buttonLayout = new FormLayout();
        Button search = new Button("Kunden Hinzufügen");
        search.addClickListener(click -> addCustomer());
        buttonLayout.add(search);
        buttonLayout.setWidth("25em");
        return buttonLayout;
    }

    private H2 getHeader() {
        H2 headline = new H2("Kundenliste");
        headline.getStyle().set("margin-top", "10px");
        return headline;
    }

    private Component getTop() {
        HorizontalLayout top = new HorizontalLayout(custSearch, buttonLayout);
        top.setFlexGrow(2, custSearch);
        top.setFlexGrow(1, buttonLayout);
        return top;
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
        grid.setColumns("id", "firstname", "lastname", "address");
        grid.asSingleSelect().addValueChangeListener(event -> editCustomer(event.getValue()));
    }

    private void editCustomer(Customer customer) {
        if(customer == null) {
            closeForm();
        } else {
            form.setCustomer(customer);
            form.setVisible(true);
        }
    }

    private void configureForm() {
        form = new CustomerInputForm();
        form.setWidth("25em");
        form.addListener(CustomerInputForm.SaveEvent.class, this::saveCustomer);
        form.addListener(CustomerInputForm.DeleteEvent.class, this::deleteCustomer);
        form.addListener(CustomerInputForm.CancleEvent.class, e -> closeForm());
    }

    private void saveCustomer(CustomerInputForm.SaveEvent event) {
        service.saveCustomer(event.getCustomer());
        updateList();
        closeForm();
    }

    private void deleteCustomer(CustomerInputForm.DeleteEvent event) {
        service.deleteCustomer(event.getCustomer());
        updateList();
        closeForm();
    }

    private void addCustomer() {
        grid.asSingleSelect().clear();
        editCustomer(new Customer());
    }

    private void updateList() {
        grid.setItems(service.getAllCustomer());
    }

    private void closeForm() {
        form.setVisible(false);
    }
}
