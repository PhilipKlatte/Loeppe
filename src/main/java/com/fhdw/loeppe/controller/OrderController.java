package com.fhdw.loeppe.controller;

import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.service.OrderService;
import com.fhdw.loeppe.util.Mapper;
import com.fhdw.loeppe.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById(@PathVariable UUID id) {
        var order = orderService.getOrder(id);
        if (order == null) throw new ResourceNotFoundException();

        return order;
    }

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping(path = "/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order postOrder(@RequestBody Order order){
        var orderDto = mapper.map(orderService.saveOrder(order), Order.class);
        orderDto.setCustomer(order.getCustomer());

        return orderDto;
    }

    @PutMapping(path = "/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order putOrder(@RequestBody Order order){
        return mapper.map(orderService.saveOrder(order), Order.class);
    }

    @DeleteMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteOrderById(@PathVariable UUID id) {
        var order = orderService.getOrder(id);
        if (order == null) throw new ResourceNotFoundException();

        orderService.deleteOrder(order);
    }
}
