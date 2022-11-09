package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.service.ArticleService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
    final private IntegerField id = new IntegerField();
    final private TextField name = new TextField();
    final private TextField description = new TextField();
    final private NumberField price = new NumberField();
    final private ArticleService service;
    private final FormLayout itemSearch;
    private final FormLayout buttonLayout;

    public ItemListView(ArticleService service) {
        setSizeFull();
        this.service = service;
        itemSearch = createItemSearch();
        buttonLayout = createButtons();
        configurePriceField();
        configureGrid();
        configureForm();
        add(getHeader(), getTop(), getContent());
        updateList();
        closeForm();
    }

    private FormLayout createItemSearch() {
        FormLayout formLayout = new FormLayout();
        formLayout.setSizeFull();
        id.setSizeFull();
        name.setSizeFull();
        description.setSizeFull();
        price.setSizeFull();
        formLayout.addFormItem(id, "Artikelnummer");
        formLayout.addFormItem(name, "Artikelname");
        formLayout.addFormItem(description, "Beschreibung");
        formLayout.addFormItem(price, "Preis");

        return formLayout;
    }

    private FormLayout createButtons() {
        FormLayout layout = new FormLayout();
        Button search = new Button("Kunden Hinzufügen");
        search.addClickListener(click -> addItem());
        layout.add(search);
        layout.setWidth("25em");
        return layout;
    }

    private Component getTop() {
        HorizontalLayout content = new HorizontalLayout(itemSearch, buttonLayout);
        content.setFlexGrow(2, itemSearch);
        content.setFlexGrow(1, buttonLayout);
        return content;
    }

    private void configurePriceField() {
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        price.setSuffixComponent(euroSuffix);
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
            form.setArticle(article);
            form.setVisible(true);
        }
    }

    private void configureForm() {
        form = new ItemInputForm();
        form.setWidth("25em");
        form.addListener(ItemInputForm.SaveEvent.class, this::saveItem);
        form.addListener(ItemInputForm.DeleteEvent.class, this::deleteItem);
        form.addListener(ItemInputForm.CancleEvent.class, e -> closeForm());
    }

    private void saveItem(ItemInputForm.SaveEvent event) {
        service.saveArticle(event.getArticle());
        updateList();
        closeForm();
    }

    private void deleteItem(ItemInputForm.DeleteEvent event) {
        service.deleteArticle(event.getArticle());
        updateList();
        closeForm();
    }

    private void addItem() {
        grid.asSingleSelect().clear();
        editItem(new Article());
    }

    private void updateList() {
        grid.setItems(service.getAllArticles());
    }

    private void closeForm() {
        form.setVisible(false);
    }

}
