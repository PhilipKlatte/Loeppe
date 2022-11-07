package com.fhdw.loeppe.service;

import com.fhdw.loeppe.entity.OrderEntity;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.repo.OrderRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final Mapper mapper;

    public void saveOrder(Order order){
        repository.saveAndFlush(mapper.map(order, OrderEntity.class));
    }

    public void saveAllOrders(List<Order> orders){
        repository.saveAll(mapper.mapAll(orders, OrderEntity.class));
    }

    public Order getOrder(long id) {
        return mapper.map(repository.findById(id), Order.class);
    }

    public List<Order> getAllOrders(){
        return mapper.mapAll(repository.findAll(), Order.class);
    }

    public void updateOrder(Order order) {
        saveOrder(order);
    }

    public void deleteOrder(long id) {
        repository.deleteById(id);
    }

    public void deleteAllOrders() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
