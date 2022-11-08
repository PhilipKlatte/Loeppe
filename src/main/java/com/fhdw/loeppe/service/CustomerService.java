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

    public void saveCustomer(Customer customer){
        repository.saveAndFlush(mapper.map(customer, CustomerEntity.class));
    }

    public void saveAllCustomers(List<Customer> customers){
        List<CustomerEntity> entitys = mapper.mapAll(customers, CustomerEntity.class);
        repository.saveAll(entitys);
    }

    public Customer getCustomer(long id) {
        return mapper.map(repository.findById(id), Customer.class);
    }

    public List<Customer> getAllCustomer(){
        return mapper.mapAll(repository.findAll(), Customer.class);
    }

    public void updateCustomer(Customer customer) {
        saveCustomer(customer);
    }

    public void deleteCustomer(long id) {
        repository.deleteById(id);
    }

    public void deleteAllCustomers() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
