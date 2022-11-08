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
    private final Mapper customerMapper;

    public void saveCustomer(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        customerMapper.map(customer, entity);
        repository.saveAndFlush(entity);
    }

    public void saveAllCustomers(List<Customer> customers){
        List<CustomerEntity> entitys = new ArrayList<>();
        customerMapper.map(customers, entitys);
        repository.saveAll(entitys);
    }

    public Optional<Customer> getCustomer(long id) {
        Optional<CustomerEntity> entity = repository.findById(id);
        Optional<Customer> customer = Optional.of(new Customer());
        customerMapper.map(entity, customer);

        return customer;
    }

    public List<Customer> getAllCustomer(){
        List<CustomerEntity> entities = repository.findAll();

        return customerMapper.mapAllCustomers(entities);
    }

    public void updateCustomer(Customer customer) {
        saveCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        customerMapper.map(customer, entity);
        repository.delete(entity);
    }

    public void deleteAllCustomers() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
