package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.util.OrderStatus;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class OrderInputForm extends FormLayout {

    private Order order;

    final private ComboBox<OrderStatus> statusBox = new ComboBox<>();
    final private ComboBox<Customer> customerBox = new ComboBox<>();
    private final Button save = new Button("Speichern");
    private final Button delete = new Button("Löschen");
    private final Button cancel = new Button("Abbruch");

    private final Button openArticleQuantityDialogButton = new Button("Artikel bearbeiten");
    final private Binder<Order> binder = new Binder<>(Order.class);
    CustomerService service;

    public OrderInputForm(CustomerService service) {
        this.service = service;

        statusBox.setItems(OrderStatus.values());
        customerBox.setItems(service.getAllCustomer());

        configureCustomerBox();

        binder.forField(statusBox).bind(Order::getOrderStatus, Order::setOrderStatus);
        binder.forField(customerBox).bind(Order::getCustomer, Order::setCustomer);

        openArticleQuantityDialogButton.addClickListener(event -> openArticleQuantityDialog());

        add(statusBox, customerBox, openArticleQuantityDialogButton, createButtonLayout());
    }

    private void configureCustomerBox() {
        customerBox.setItemLabelGenerator(customer -> customer.getId()
                + ". "
                + customer.getFirstname()
                + " " +
                customer.getLastname());
    }

    private void openArticleQuantityDialog(){
        ArticleQuantityDialog articleQuantityDialog = new ArticleQuantityDialog(this.order);
        articleQuantityDialog.open();
    }

    private HorizontalLayout createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, order)));
        cancel.addClickListener(event -> fireEvent(new CancleEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    public void setOrder(Order order) {
        this.order = order;
        binder.readBean(order);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(order);
            fireEvent(new SaveEvent(this, order));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class OrderFormEvent extends ComponentEvent<OrderInputForm> {
        private Order order;

        protected OrderFormEvent(OrderInputForm source, Order order) {
            super(source, false);
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }

    public static class SaveEvent extends OrderFormEvent {
        SaveEvent(OrderInputForm source, Order order) {
            super(source, order);
        }
    }

    public static class DeleteEvent extends OrderFormEvent {
        DeleteEvent(OrderInputForm source, Order order) {
            super(source, order);
        }
    }

    public static class CancleEvent extends OrderFormEvent {
        CancleEvent(OrderInputForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
