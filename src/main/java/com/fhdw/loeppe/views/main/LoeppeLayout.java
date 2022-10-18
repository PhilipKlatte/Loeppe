package com.fhdw.loeppe.views.main;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class LoeppeLayout extends AppLayout {

    public LoeppeLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Image img = new Image("Logo_gut.png", "loeppe logo");
        img.setHeight("50px");

        HorizontalLayout header = new HorizontalLayout(
                img,
                new DrawerToggle()

        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("header-1", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink startLink = new RouterLink("Startseite", MainView.class);

        addToDrawer(new VerticalLayout(
                startLink,
                new RouterLink("Kunden", CustomerView.class)));
    }

}
