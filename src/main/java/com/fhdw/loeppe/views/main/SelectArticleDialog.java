package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.service.ArticleService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SelectArticleDialog extends Dialog {

    private Article article;

    private final ArticleService articleService;
    private final ArticleQuantityDialog articleQuantityDialog;

    private final VerticalLayout layout = new VerticalLayout();
    private final ComboBox<Article> comboBox = new ComboBox<>();
    private final Button confirmButton = new Button("Artikel auswählen");

    public SelectArticleDialog(Article article, ArticleService articleService, ArticleQuantityDialog articleQuantityDialog){
        this.article = article;
        this.articleService = articleService;
        this.articleQuantityDialog = articleQuantityDialog;

        confirmButton.addClickListener(event -> confirm());

        comboBox.setItemLabelGenerator(Article::getName);
        comboBox.setItems(articleService.getAllArticles());
        comboBox.setSizeFull();

        add(configureLayout());
        open();
    }

    private void confirm(){
        article = comboBox.getValue();
        articleQuantityDialog.addArticle(article);
        close();
    }

    private VerticalLayout configureLayout(){
        layout.add(comboBox);
        layout.add(confirmButton);

        return layout;
    }
}
