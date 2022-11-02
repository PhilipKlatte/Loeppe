package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.service.ArticleService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Loeppe | Artikel")
@Route(value = "item", layout = LoeppeLayout.class)
public class ItemListView extends VerticalLayout {

    final private Grid<Article> grid = new Grid<>(Article.class);
    final private IntegerField itemID;
    final private TextField itemName;
    final private TextField itemDes;
    final private NumberField itemPrice;
    final private ArticleService service;

    public ItemListView(ArticleService service) {
        this.service = service;
        service.saveArticle(new Article(1,"Weniger Torben",  "Erzeugt weniger Torben", 25.00));

        H2 headline = new H2("Artikelliste");
        headline.getStyle().set("margin-top", "10px");

        itemID = new IntegerField();
        itemName = new TextField();
        itemDes = new TextField();
        itemPrice = new NumberField();

        configurePriceField();
        configureGrid();

        add(headline, createItemSearch(), grid);

        updateGrid();
    }

    private void configurePriceField() {
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        itemPrice.setSuffixComponent(euroSuffix);
    }

    private void configureGrid() {
        grid.setColumns("id", "name", "description", "price");
    }

    private FormLayout createItemSearch() {
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(itemID, "Artikelnummer");
        formLayout.addFormItem(itemName, "Artikelname");
        formLayout.addFormItem(itemDes, "Beschreibung");
        formLayout.addFormItem(itemPrice, "Preis");
        return formLayout;
    }

    private void updateGrid() {
        grid.setItems(service.getAllArticles());
    }
}
