package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.service.TestService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("Loeppe | Startseite")
@Route(value = "", layout = LoeppeLayout.class)
public class MainView extends VerticalLayout {

    private final TextField userId;
    private final TextField userName;

    private final TestView testView = new TestView(new TestService());

    public MainView() {
        H1 welcome = new H1("Willkommen in der Loeppe Auftagsverwaltung");
        welcome.getStyle().set("margin-top", "10px")
                          .set("margin-bottom", "10px");
        H2 user = new H2("Angemeldet als:");
        user.getStyle().set("margin-top", "10px");
        userId = new TextField();
        userName = new TextField();

        configureTextFields();

        add(welcome, user, createLoggedInAs(), testView);
    }

    private void configureTextFields() {
        userId.setValue("000000");
        userId.setReadOnly(true);
        userName.setValue("Max Mustermann");
        userName.setReadOnly(true);
    }

    private FormLayout createLoggedInAs() {
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(userId, "User-ID");
        formLayout.addFormItem(userName, "User-Name");
        return formLayout;
    }
}
