package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.dto.Customer;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class CustomerInputForm extends FormLayout {
   private Customer customer;
   final private TextField firstname = new TextField("Vorname");
   final private TextField lastname = new TextField("Nachname");
   final private TextField address = new TextField("Adresse");
   private final Button save = new Button("Speichern");
   private final Button delete = new Button("Löschen");
   private final Button cancel = new Button("Abbruch");
   private final Binder<Customer> binder = new Binder<>(Customer.class);


   public CustomerInputForm() {
       binder.bindInstanceFields(this);
       add(firstname, lastname, address, creatButtonLayout());
   }

   private HorizontalLayout creatButtonLayout() {
       save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
       delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
       cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

       save.addClickListener(event -> validateAndSave());
       delete.addClickListener(event -> fireEvent(new DeleteEvent(this, customer)));
       cancel.addClickListener(event -> fireEvent(new CancleEvent(this)));

       return new HorizontalLayout(save, delete, cancel);
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
      binder.readBean(customer);
   }

   private void validateAndSave() {
       try {
           binder.writeBean(customer);
           fireEvent(new SaveEvent(this,customer));
       } catch (ValidationException e) {
           e.printStackTrace();
       }
   }

    public static abstract class CustomerFormEvent extends ComponentEvent<CustomerInputForm> {
        private final Customer customer;

        protected CustomerFormEvent(CustomerInputForm source, Customer customer) {
            super(source, false);
            this.customer = customer;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    public static class SaveEvent extends CustomerFormEvent {
        SaveEvent(CustomerInputForm source, Customer customer) {
            super(source, customer);
        }
    }

    public static class DeleteEvent extends CustomerFormEvent {
        DeleteEvent(CustomerInputForm source, Customer customer) {
            super(source, customer);
        }
    }

    public static class CancleEvent extends CustomerFormEvent {
        CancleEvent(CustomerInputForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
