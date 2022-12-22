package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;


public class LoeppeLayout extends AppLayout {
    private final SecurityService securityService;

    public LoeppeLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Image img = new Image("images/Logo_gut.png", "loeppe logo");
        img.setHeight("50px");
        img.getStyle().set("margin", "10px");
        DrawerToggle drawerToggle = new DrawerToggle();
        drawerToggle.getStyle().set("margin", "0");
        Button logout = createLogoutButton();

        HorizontalLayout header = new HorizontalLayout(
                drawerToggle,
                img,
                logout
        );

        logout.getStyle().set("margin-left", "auto")
                         .set("margin-right", "20px");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("header-1", "px-m");

        addToNavbar(header);
    }

    private Button createLogoutButton() {
        return new Button("Ausloggen", e -> securityService.logout());
    }



    private void createDrawer() {
        RouterLink startLink = new RouterLink("Startseite", MainView.class);

        addToDrawer(new VerticalLayout(
                startLink,
                new RouterLink("Kunden", CustomerListView.class),
                new RouterLink("Artikel", ArticleListView.class),
                new RouterLink("Aufträge", OrderListView.class)));
    }
}
