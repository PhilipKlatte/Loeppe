package events;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.views.main.CustomerInputForm;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.shared.Registration;

public class CustomerEvents extends FormLayout {

   public static abstract class CustomerFormEvent extends ComponentEvent<CustomerInputForm> {
        private Customer customer;

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

