package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Article;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class ItemInputForm extends FormLayout {

    final private TextField name = new TextField("Name");
    final private TextField description = new TextField("Beschreibung");
    final private NumberField price = new NumberField("Preis");
    private final Button save = new Button("Speichern");
    private final Button delete = new Button("Löschen");
    private final Button cancel = new Button("Abbruch");
    Binder<Article> binder = new BeanValidationBinder<>(Article.class);
    Article article;

    public ItemInputForm() {
        configurePriceField();
        binder.bindInstanceFields(this);
        add(name, description, price, creatButtonLayout());
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

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, article)));
        cancel.addClickListener(event -> fireEvent(new CancleEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    public void setArticle(Article article) {
        this.article = article;
        binder.readBean(article);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(article);
            fireEvent(new SaveEvent(this, article));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class ItemFormEvent extends ComponentEvent<ItemInputForm> {
        private Article article;

        protected ItemFormEvent(ItemInputForm source, Article article) {
            super(source, false);
            this.article = article;
        }

        public Article getArticle() {
            return article;
        }
    }

    public static class SaveEvent extends ItemFormEvent {
        SaveEvent(ItemInputForm source, Article article) {
            super(source, article);
        }
    }

    public static class DeleteEvent extends ItemFormEvent {
        DeleteEvent(ItemInputForm source, Article article) {
            super(source, article);
        }
    }

    public static class CancleEvent extends ItemFormEvent {
        CancleEvent(ItemInputForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
