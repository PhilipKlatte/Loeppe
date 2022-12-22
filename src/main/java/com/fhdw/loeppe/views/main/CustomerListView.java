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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("Loeppe | Kunden")
@Route(value = "customer", layout = LoeppeLayout.class)
public class CustomerListView extends VerticalLayout {

    private final Grid<Customer> grid = new Grid<>(Customer.class, false);
    private CustomerInputForm form;
    private final TextField idSearch = new TextField();
    private final TextField firstnameSearch = new TextField();
    private final TextField lastnameSearch = new TextField();
    private final TextField addressSearch = new TextField();
    private final CustomerService service;
    private final FormLayout customerSearch;
    private final FormLayout buttonLayout;


    public CustomerListView(CustomerService service) {
        setSizeFull();
        this.service = service;
        customerSearch = createCustomerSearch();
        buttonLayout = createButtons();
        configureGrid();
        configureForm();
        add(getHeader(), getTop(), getContent());
        updateList();
        closeForm();
    }

    private FormLayout createCustomerSearch() {
        FormLayout customerSearch = new FormLayout();
        customerSearch.setSizeFull();
        idSearch.setSizeFull();
        firstnameSearch.setSizeFull();
        lastnameSearch.setSizeFull();
        addressSearch.setSizeFull();
        customerSearch.addFormItem(idSearch, "Kudennummer");
        customerSearch.addFormItem(firstnameSearch, "Vorname");
        customerSearch.addFormItem(lastnameSearch, "Nachname");
        customerSearch.addFormItem(addressSearch, "Adresse");

        return customerSearch;
    }

    private FormLayout createButtons() {
        FormLayout buttonLayout = new FormLayout();
        Button search = new Button("Suchen");
        search.addClickListener(click -> searchCustomer());
        Button add = new Button("Kunden Hinzufügen");
        add.addClickListener(click -> addCustomer());
        buttonLayout.add(search, add);
        buttonLayout.setWidth("25em");
        return buttonLayout;
    }

    private Component getTop() {
        HorizontalLayout top = new HorizontalLayout(customerSearch, buttonLayout);
        top.setFlexGrow(2, customerSearch);
        top.setFlexGrow(1, buttonLayout);
        return top;
    }

    private H2 getHeader() {
        H2 headline = new H2("Kundenliste");
        headline.getStyle().set("margin-top", "10px");
        return headline;
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
        grid.addColumn(Customer::getId).setHeader("Kundennummer");
        grid.addColumn(Customer::getFirstname).setHeader("Vorname");
        grid.addColumn(Customer::getLastname).setHeader("Nachname");
        grid.addColumn(Customer::getAddress).setHeader("Adresse");
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

    private void searchCustomer() {
        if(idSearch.isEmpty() && firstnameSearch.isEmpty() && lastnameSearch.isEmpty() && addressSearch.isEmpty()) {
            grid.setItems(service.getAllCustomer());
        } else {
            if (!idSearch.isEmpty()) {
                try {
                    long id = Long.parseLong(idSearch.getValue());
                    grid.setItems(service.searchCustomerWithID(new Customer(id,
                                  firstnameSearch.getValue(), lastnameSearch.getValue(),
                                  addressSearch.getValue())));
                } catch (NumberFormatException e) {
                    System.out.println("HEY");
                }
            } else {
                grid.setItems(service.searchCustomerWithoutID(new Customer(
                        firstnameSearch.getValue(), lastnameSearch.getValue(),
                        addressSearch.getValue())));
            }
        }
    }

    private void closeForm() {
        form.setVisible(false);
    }
}
