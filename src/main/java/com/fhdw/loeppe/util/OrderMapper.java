package com.fhdw.loeppe.util;

import com.fhdw.loeppe.entity.OrderEntity;
import com.fhdw.loeppe.dto.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void map(Object entityFrom, Object entityTo) {
        modelMapper.map(entityFrom, entityTo);
    }
    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /*public <D, T> List<D> mapAll(final Collection<T> list, Class<D> outClass) {
        return list.stream()
                .map(entity ->map(entity, outClass))
                .collect(Collectors.toList());
    }*/

    public ArrayList<Order> mapAll(List<OrderEntity> entities) {
        ArrayList<Order> orders = new ArrayList<>();
        Order order = new Order();

        for (OrderEntity entity : entities) {
            map(entity, order);
            orders.add(order);
        }

        return orders;
    }
}
