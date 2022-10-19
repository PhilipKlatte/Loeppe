package com.fhdw.loeppe.views.main;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Loeppe | Artikel")
@Route(value = "item", layout = LoeppeLayout.class)
public class ItemListView extends VerticalLayout {

    final private TextField itemID;
    final private TextField itemName;
    final private ComboBox<String> itemType;
    final private TextField itemPrice;

    public ItemListView() {
        H2 headline = new H2("Artikelliste");
        headline.getStyle().set("margin-top", "10px");

        itemID = new TextField();
        itemName = new TextField();
        itemType = new ComboBox<>();
        itemPrice = new TextField();
        setComboBox();

        add(headline, createItemSearch());
    }

    private void setComboBox() {
        itemType.setItems("Computer",
                "Smartphones",
                "Drucker",
                "Tablets",
                "Gaming",
                "usw.");
    }

    private FormLayout createItemSearch() {
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(itemID, "Artikelnummer");
        formLayout.addFormItem(itemName, "Artikelname");
        formLayout.addFormItem(itemType, "Artikelart");
        formLayout.addFormItem(itemPrice, "Preis");
        return formLayout;
    }


}
