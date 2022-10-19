package com.fhdw.loeppe.data;

import com.fhdw.loeppe.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private Integer id;
    private Customer customer;
    private List<Artikel> artikels;
    private Boolean bezahlt;
    private OrderStatus orderStatus;
}
