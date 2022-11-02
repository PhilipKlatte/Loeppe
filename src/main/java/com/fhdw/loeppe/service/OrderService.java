package com.fhdw.loeppe.service;

import com.fhdw.loeppe.entity.OrderEntity;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.repo.OrderRepository;
import com.fhdw.loeppe.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper orderMapper;

    public void saveOrder(Order order){
        OrderEntity entity = new OrderEntity();
        orderMapper.map(order, entity);
        repository.saveAndFlush(entity);
    }

    public void saveAllOrders(List<Order> orders){
        List<OrderEntity> entitys = new ArrayList<>();
        orderMapper.map(orders, entitys);
        repository.saveAll(entitys);
    }

    public Optional<Order> getOrder(long id) {
        Optional<OrderEntity> entity = repository.findById(id);
        Optional<Order> order = Optional.of(new Order());
        orderMapper.map(entity, order);

        return order;
    }

    public List<Order> getAllOrders(){
        List<OrderEntity> entities = repository.findAll();

        return orderMapper.mapAll(entities);
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
