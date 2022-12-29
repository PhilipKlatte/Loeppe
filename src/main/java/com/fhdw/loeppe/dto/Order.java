package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Order {

    private UUID id;

    @NonNull
    private Customer customer;

    @NonNull
    private List<Article> articles;

    @NonNull
    private OrderStatus orderStatus;

    public Order(UUID id, Customer customer, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        orderStatus = status;
    }

    public Order(Customer customer, OrderStatus status) {
        this.customer = customer;
        orderStatus = status;
    }

}
