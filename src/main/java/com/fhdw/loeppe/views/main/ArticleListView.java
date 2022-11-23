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
public class ArticleListView extends VerticalLayout {

    final private Grid<Article> grid = new Grid<>(Article.class);
    ArticleInputForm form;
    final private IntegerField id = new IntegerField();
    final private TextField name = new TextField();
    final private TextField description = new TextField();
    final private NumberField price = new NumberField();
    final private ArticleService service;
    private final FormLayout articleSearch;
    private final FormLayout buttonLayout;

    public ArticleListView(ArticleService service) {
        setSizeFull();
        this.service = service;
        articleSearch = createArticleSearch();
        buttonLayout = createButtons();
        configurePriceField();
        configureGrid();
        configureForm();
        add(getHeader(), getTop(), getContent());
        updateList();
        closeForm();
    }

    private FormLayout createArticleSearch() {
        FormLayout articleSearch = new FormLayout();
        articleSearch.setSizeFull();
        id.setSizeFull();
        name.setSizeFull();
        description.setSizeFull();
        price.setSizeFull();
        articleSearch.addFormItem(id, "Artikelnummer");
        articleSearch.addFormItem(name, "Artikelname");
        articleSearch.addFormItem(description, "Beschreibung");
        articleSearch.addFormItem(price, "Preis");

        return articleSearch;
    }

    private FormLayout createButtons() {
        FormLayout buttonLayout = new FormLayout();
        Button search = new Button("Artikel Hinzufügen");
        search.addClickListener(click -> addArticle());
        buttonLayout.add(search);
        buttonLayout.setWidth("25em");
        return buttonLayout;
    }

    private Component getTop() {
        HorizontalLayout top = new HorizontalLayout(articleSearch, buttonLayout);
        top.setFlexGrow(2, articleSearch);
        top.setFlexGrow(1, buttonLayout);
        return top;
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
        grid.asSingleSelect().addValueChangeListener(event -> editArticle(event.getValue()));
    }

    private void editArticle(Article article) {
        if(article == null) {
            closeForm();
        } else {
            form.setArticle(article);
            form.setVisible(true);
        }
    }

    private void configureForm() {
        form = new ArticleInputForm();
        form.setWidth("25em");
        form.addListener(ArticleInputForm.SaveEvent.class, this::saveArticle);
        form.addListener(ArticleInputForm.DeleteEvent.class, this::deleteArticle);
        form.addListener(ArticleInputForm.CancleEvent.class, e -> closeForm());
    }

    private void saveArticle(ArticleInputForm.SaveEvent event) {
        service.saveArticle(event.getArticle());
        updateList();
        closeForm();
    }

    private void deleteArticle(ArticleInputForm.DeleteEvent event) {
        service.deleteArticle(event.getArticle());
        updateList();
        closeForm();
    }

    private void addArticle() {
        grid.asSingleSelect().clear();
        editArticle(new Article());
    }

    private void updateList() {
        grid.setItems(service.getAllArticles());
    }

    private void closeForm() {
        form.setVisible(false);
    }

}
