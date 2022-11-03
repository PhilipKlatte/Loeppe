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

    public void createOrder(Order order){
        OrderEntity entity = new OrderEntity();
        mapper.map(order, entity);
        repository.saveAndFlush(entity);
    }

    public void saveAllOrders(List<Order> orders){
        List<OrderEntity> entitys = new ArrayList<>();
        mapper.map(orders, entitys);
        repository.saveAll(entitys);
    }

    public Optional<Order> readOrder(Integer id) {
        Optional<OrderEntity> entity = repository.findById(id);
        Optional<Order> order = Optional.of(new Order());
        mapper.map(entity, order);

        return order;
    }

    public List<Order> readAllOrder(){
        List<OrderEntity> entities = repository.findAll();

        return mapper.mapAllOrders(entities);
    }

    public void updateOrder(Order order) {
        createOrder(order);
    }

    public void deleteOrder(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAllOrders() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
