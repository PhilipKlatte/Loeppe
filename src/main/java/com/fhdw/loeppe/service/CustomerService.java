package com.fhdw.loeppe.service;

import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository repository;
    private final Mapper mapper;

    public void createCustomer(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        mapper.map(customer, entity);
        repository.saveAndFlush(entity);
    }

    public void saveAllCustomers(List<Customer> customers){
        List<CustomerEntity> entitys = new ArrayList<>();
        mapper.map(customers, entitys);
        repository.saveAll(entitys);
    }

    public Optional<Customer> readCustomer(Integer id) {
        Optional<CustomerEntity> entity = repository.findById(id);
        Optional<Customer> customer = Optional.of(new Customer());
        mapper.map(entity, customer);

        return customer;
    }

    public List<Customer> readAllCustomer(){
        List<CustomerEntity> entities = repository.findAll();
        ArrayList<Customer> customers = mapper.mapAll(entities);

        return customers;
    }

    public void updateCustomer(Customer customer) {
        createCustomer(customer);
    }

    public void deleteCustomer(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAllCustomers() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
