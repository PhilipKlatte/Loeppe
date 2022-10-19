package com.fhdw.loeppe.views.main;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Loeppe | Kunden")
@Route(value = "customer", layout = LoeppeLayout.class)
public class CustomerListView extends VerticalLayout {

    private final TextField custId;
    private final TextField custLastName;
    private final TextField custFirstName;
    private final TextField custPhone;

    public CustomerListView() {
        H2 headline = new H2("Kundenliste");
        headline.getStyle().set("margin-top", "10px");

        custId = new TextField();
        custLastName = new TextField();
        custFirstName = new TextField();
        custPhone = new TextField();

        add(headline, createCustSearch());
    }

    public FormLayout createCustSearch() {
        FormLayout custSearch = new FormLayout();
        custSearch.addFormItem(custId, "Kunden-ID");
        custSearch.addFormItem(custLastName, "Nachname");
        custSearch.addFormItem(custFirstName, "Vorname");
        custSearch.addFormItem(custPhone, "Festnetz");
        return custSearch;
    }

}
