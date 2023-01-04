package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.util.Country;
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
@PageTitle("Loeppe | Kunden")
@Route(value = "customer", layout = LoeppeLayout.class)
public class CustomerListView extends VerticalLayout {

    private final Grid<Customer> grid = new Grid<>(Customer.class, false);
    private CustomerInputForm form;
    private final TextField idSearch = new TextField();
    private final TextField firstnameSearch = new TextField();
    private final TextField lastnameSearch = new TextField();
    private final TextField emailSearch = new TextField();
    private final TextField phoneSearch = new TextField();
    private final TextField streetSearch = new TextField();
    private final TextField citySearch = new TextField();
    private final TextField postalSearch = new TextField();
    private final ComboBox<Country> countrySearch = new ComboBox<>();
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
        emailSearch.setSizeFull();
        phoneSearch.setSizeFull();
        streetSearch.setSizeFull();
        citySearch.setSizeFull();
        postalSearch.setSizeFull();
        countrySearch.setSizeFull();
        countrySearch.setItems(Country.values());
        customerSearch.addFormItem(idSearch, "Kudennummer");
        customerSearch.addFormItem(firstnameSearch, "Vorname");
        customerSearch.addFormItem(lastnameSearch, "Nachname");
        customerSearch.addFormItem(emailSearch, "Email");
        customerSearch.addFormItem(phoneSearch, "Telefon");
        customerSearch.addFormItem(streetSearch, "Straße");
        customerSearch.addFormItem(citySearch, "Stadt");
        customerSearch.addFormItem(postalSearch, "Postleitzahl");
        customerSearch.addFormItem(countrySearch, "Land");

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
        grid.addColumn(Customer::getEmailAdress).setHeader("Email");
        grid.addColumn(Customer::getPhoneNumber).setHeader("Telefonnummer");
        grid.addColumn(Customer::getStreet).setHeader("Straße");
        grid.addColumn(Customer::getCity).setHeader("Stadt");
        grid.addColumn(Customer::getPostalCode).setHeader("Postleitzahl");
        grid.addColumn(Customer::getCountry).setHeader("Land");
        grid.asSingleSelect().addValueChangeListener(event -> editCustomer(event.getValue()));
        grid.getColumns().forEach(e -> e.setResizable(true));
        grid.getColumns().forEach(e -> e.setAutoWidth(true));
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
        if(idSearch.isEmpty() && firstnameSearch.isEmpty() && lastnameSearch.isEmpty() &&
                emailSearch.isEmpty() && phoneSearch.isEmpty() && streetSearch.isEmpty() &&
                citySearch.isEmpty() && postalSearch.isEmpty() && countrySearch.isEmpty()) {
            grid.setItems(service.getAllCustomer());
        } else {
            grid.setItems(service.searchCustomer(idSearch.getValue(), new Customer(
                    firstnameSearch.getValue(), lastnameSearch.getValue(), emailSearch.getValue(),
                    phoneSearch.getValue(), streetSearch.getValue(), citySearch.getValue(),
                    postalSearch.getValue(), countrySearch.getValue())));
        }
    }

    private void closeForm() {
        form.setVisible(false);
    }
}
