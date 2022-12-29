package com.fhdw.loeppe.views.main;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
@CssImport(value = "./themes/vaadin-login-overlay.css", themeFor = "vaadin-login-overlay-wrapper")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginOverlay login = new LoginOverlay();

    public LoginView() {
        login.setI18n(createCustomLogin());
        configureLogin();
        addClassName("login-view");
        setSizeFull();
        login.setAction("login");
        add(login);
        login.setOpened(true);
    }

    private LoginI18n createCustomLogin() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setForm(createCustomLoginForm(i18n));
        i18n.setErrorMessage(creatCustomLoginErroMessage(i18n));
        return i18n;
    }

    private LoginI18n.Form createCustomLoginForm(LoginI18n i18n) {
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Einloggen");
        i18nForm.setUsername("Benutzername");
        i18nForm.setPassword("Passwort");
        i18nForm.setSubmit("Einloggen");
        return i18nForm;
    }

    private LoginI18n.ErrorMessage creatCustomLoginErroMessage(LoginI18n i18n) {
        LoginI18n.ErrorMessage i18nError = i18n.getErrorMessage();
        i18nError.setTitle("Fehler beim Einloggen");
        i18nError.setMessage("Der eingegebene Benutzername und / oder das eingegebene Passwort ist falsch");
        return  i18nError;
    }

    private void configureLogin() {
        login.setTitle(new Image("images/Logo_gut.png", "loeppe logo"));

        login.setDescription(null);
        login.setForgotPasswordButtonVisible(false);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
