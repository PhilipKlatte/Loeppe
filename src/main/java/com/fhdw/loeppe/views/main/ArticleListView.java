package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.service.ArticleService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.UUID;

@PageTitle("Loeppe | Artikel")
@Route(value = "item", layout = LoeppeLayout.class)
public class ArticleListView extends VerticalLayout {

    final private Grid<Article> grid = new Grid<>(Article.class, false);
    ArticleInputForm form;
    final private TextField idSearch = new TextField();
    final private TextField nameSearch = new TextField();
    final private ArticleService service;
    private final FormLayout articleSearch;
    private final FormLayout buttonLayout;

    public ArticleListView(ArticleService service) {
        setSizeFull();
        this.service = service;
        articleSearch = createArticleSearch();
        buttonLayout = createButtons();
        configureGrid();
        configureForm();
        add(getHeader(), getTop(), getContent());
        updateList();
        closeForm();
    }

    private FormLayout createArticleSearch() {
        FormLayout articleSearch = new FormLayout();
        articleSearch.setSizeFull();
        idSearch.setSizeFull();
        nameSearch.setSizeFull();
        articleSearch.addFormItem(idSearch, "Artikelnummer");
        articleSearch.addFormItem(nameSearch, "Artikelname");
        return articleSearch;
    }

    private FormLayout createButtons() {
        FormLayout buttonLayout = new FormLayout();
        Button search = new Button("Suchen");
        search.addClickListener(click -> searchArticle());
        Button add = new Button("Artikel Hinzufügen");
        add.addClickListener(click -> addArticle());
        buttonLayout.add(search, add);
        buttonLayout.setWidth("25em");
        return buttonLayout;
    }

    private Component getTop() {
        HorizontalLayout top = new HorizontalLayout(articleSearch, buttonLayout);
        top.setFlexGrow(2, articleSearch);
        top.setFlexGrow(1, buttonLayout);
        return top;
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
        grid.addColumn(Article::getId).setHeader("Artikelnummer");
        grid.addColumn(Article::getName).setHeader("Artikelname");
        grid.addColumn(Article::getDescription).setHeader("Beschreibung");
        grid.addColumn(Article::getPrice).setHeader("Preis");
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

    private void searchArticle() {
        if(idSearch.isEmpty() && nameSearch.isEmpty()) {
            grid.setItems(service.getAllArticles());
        } else {
            if (!idSearch.isEmpty()) {
                try {
                    UUID id = UUID.fromString(idSearch.getValue());
                    grid.setItems(service.searchArticleWithID(new Article(id,
                            nameSearch.getValue())));
                } catch (NumberFormatException e) {
                    System.out.println("HEY");
                }
            } else {
                grid.setItems(service.searchArticleWithoutID(new Article(
                        nameSearch.getValue())));
            }
        }
    }

    private void closeForm() {
        form.setVisible(false);
    }

}
