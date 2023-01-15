package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.dto.ArticleQuantity;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.service.ArticleQuantityService;
import com.fhdw.loeppe.service.ArticleService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class ArticleQuantityDialog extends Dialog {

    private Order order;

    private final ArticleQuantityService articleQuantityService;
    private final ArticleService articleService;

    private final VerticalLayout layout = new VerticalLayout();
    private final H2 orderLabel = new H2();
    private final Button addArticleButton = new Button("Artikel hinzufügen");
    private final Button closeDialogButton = new Button("Dialog schließen");
    private final Grid<ArticleQuantity> grid = new Grid<>(ArticleQuantity.class, false);

    public ArticleQuantityDialog(Order order, ArticleQuantityService articleQuantityService, ArticleService articleService){
        this.order = order;
        this.articleQuantityService = articleQuantityService;
        this.articleService = articleService;
        this.orderLabel.setText("Artikel für Auftrag " + order.getId());

        addArticleButton.addClickListener(event -> openSelectArticleDialog());
        closeDialogButton.addClickListener(event -> close());
        configureGrid();
        setSizeFull();

        add(configureLayout());
        updateList();
    }

    public void addArticle(Article article){
        var articalQuantity = new ArticleQuantity();
        articalQuantity.setOrder(this.order);
        articalQuantity.setArticle(article);

        articleQuantityService.saveArticleQuantity(articalQuantity);
        updateList();
    }

    private void openSelectArticleDialog(){
        Article article = new Article();
        new SelectArticleDialog(article, articleService, this);
    }

    private void configureGrid() {
        grid.setSizeFull();

        //grid.addColumn(ArticleQuantity::getQuantity).setHeader("Quantity1");

        grid.addComponentColumn(articleQuantity -> {
            TextField textField = new TextField();
            textField.setValue(String.valueOf(articleQuantity.getQuantity()));
            textField.addValueChangeListener(event -> {
                articleQuantity.setQuantity(Integer.parseInt(event.getValue()));
                articleQuantityService.saveArticleQuantity(articleQuantity);
            });
            textField.setSizeFull();


            return textField;
        }).setHeader("Quantity");

        grid.addColumn(articleQuantity -> articleQuantity.getArticle().getName()).setHeader("Artikel");

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, articleQuantity) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.deleteArticleQuantity(articleQuantity));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage");

        grid.getColumns().forEach(e -> e.setResizable(true));
        grid.getColumns().forEach(e -> e.setAutoWidth(true));
    }

    private void updateList() {
        var orderArticles = articleQuantityService.getAllArticleQuantities();
        grid.setItems(orderArticles);
    }

    private void deleteArticleQuantity(ArticleQuantity articleQuantity){
        articleQuantityService.deleteArticleQuantity(articleQuantity);
        updateList();
    }

    private VerticalLayout configureLayout(){
        layout.add(orderLabel);
        layout.add(addArticleButton);
        layout.add(grid);
        layout.add(closeDialogButton);

        layout.setSizeFull();

        return layout;
    }
}
