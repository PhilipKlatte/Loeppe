package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private Integer id;
    private Customer customer;
    private Boolean paid;
    private OrderStatus orderStatus;
}
