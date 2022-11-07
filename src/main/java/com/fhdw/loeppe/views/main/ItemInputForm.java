package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.dto.Customer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class ItemInputForm extends FormLayout {

    final private IntegerField id = new IntegerField("Artikel-ID");
    final private TextField name = new TextField("Name");
    final private TextField description = new TextField("Beschreibung");
    final private NumberField price = new NumberField("Preis");
    private final Button save = new Button("Speichern");
    private final Button delete = new Button("Löschen");
    private final Button cancel = new Button("Abbruch");


    public ItemInputForm() {
        configurePriceField();
        add(id, name, description, price, creatButtonLayout());
    }

    private void configurePriceField() {
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        price.setSuffixComponent(euroSuffix);
    }

    private HorizontalLayout creatButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        return new HorizontalLayout(save, delete, cancel);
    }

    public void setContent(Article article) {
        id.setValue(article.getId());
        name.setValue(article.getName());
        description.setValue(article.getDescription());
        price.setValue(article.getPrice());
    }

}
