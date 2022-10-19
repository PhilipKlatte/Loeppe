package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.OrderStatus;
import com.fhdw.loeppe.dto.Artikel;
import com.fhdw.loeppe.dto.Customer;
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
