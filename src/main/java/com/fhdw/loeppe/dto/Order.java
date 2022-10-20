package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private Integer id;
    private Customer customer;
    private List<Artikel> artikels;
    private Boolean paid;
    private OrderStatus orderStatus;
}
