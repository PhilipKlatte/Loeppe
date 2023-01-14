package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.ArticleQuantity;
import com.fhdw.loeppe.dto.Order;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ArticleQuantityDialog extends Dialog {

    private Order order;

    private final VerticalLayout layout = new VerticalLayout();
    private final Label orderLabel = new Label();
    private final Button addArticle = new Button("Artikel hinzufügen");
    private final Grid<ArticleQuantity> grid = new Grid<>(ArticleQuantity.class, false);


    public ArticleQuantityDialog(Order order){
        this.order = order;
        this.orderLabel.setText("Artikel für Auftrag " + order.getId());

        addArticle.addClickListener(event -> addArticle());

        add(configureLayout());
    }

    private void addArticle(){

    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.addColumn(ArticleQuantity::getQuantity).setHeader("Quantity");
        grid.addColumn(ArticleQuantity::getArticle).setHeader("Artikel");

        grid.getColumns().forEach(e -> e.setResizable(true));
        grid.getColumns().forEach(e -> e.setAutoWidth(true));
    }

    private VerticalLayout configureLayout(){
        layout.add(orderLabel);
        layout.add(grid);

        return layout;
    }
}
