package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.service.ArticleService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
    ItemInputForm form;
    final private IntegerField itemID = new IntegerField();
    final private TextField itemName = new TextField();
    final private TextField itemDes = new TextField();
    final private NumberField itemPrice = new NumberField();
    final private ArticleService service;

    public ItemListView(ArticleService service) {
        setSizeFull();
        this.service = service;
        service.saveArticle(new Article(1,"Weniger Torben",  "Erzeugt weniger Torben", 25.00));
        configurePriceField();
        configureGrid();
        configureForm();
        add(getHeader(), createItemSearch(), getContent());
        updateGrid();
        closeForm();
    }

    private FormLayout createItemSearch() {
        FormLayout formLayout = new FormLayout();
        itemID.setSizeFull();
        itemName.setSizeFull();
        itemDes.setSizeFull();
        itemPrice.setSizeFull();
        formLayout.addFormItem(itemID, "Artikelnummer");
        formLayout.addFormItem(itemName, "Artikelname");
        formLayout.addFormItem(itemDes, "Beschreibung");
        formLayout.addFormItem(itemPrice, "Preis");
        return formLayout;
    }

    private void configurePriceField() {
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        itemPrice.setSuffixComponent(euroSuffix);
    }

    public H2 getHeader() {
        H2 headline = new H2("Artikelliste");
        headline.getStyle().set("margin-top", "10px");
        return headline;
    }

    public Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name", "description", "price");
        grid.asSingleSelect().addValueChangeListener(event -> editItem(event.getValue()));
    }

    private void editItem(Article article) {
        if(article == null) {
            closeForm();
        } else {
            form.setContent(article);
            form.setVisible(true);
        }
    }

    private void configureForm() {
        form = new ItemInputForm();
        form.setWidth("25em");
    }

    private void updateGrid() {
        grid.setItems(service.getAllArticles());
    }

    private void closeForm() {
        form.setVisible(false);
    }

}
