package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    private long id;

    @NonNull
    private Customer customer;

    @NonNull
    private List<Article> articles;

    @NonNull
    private OrderStatus orderStatus;
}
