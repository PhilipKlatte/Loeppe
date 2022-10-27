package com.fhdw.loeppe.util;

import com.fhdw.loeppe.Entity.CustomerEntity;
import com.fhdw.loeppe.dto.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper() {
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
    
    public ArrayList<Customer> mapAll(List<CustomerEntity> entities) {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();

        for (CustomerEntity entity : entities) {
            map(entity, customer);
            customers.add(customer);
        }

        return customers;
    }
}
