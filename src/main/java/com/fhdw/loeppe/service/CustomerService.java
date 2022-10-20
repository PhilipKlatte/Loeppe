package com.fhdw.loeppe.service;

import com.fhdw.loeppe.Entity.CustomerEntity;
import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    private final CustomerRepository repository;
    private final Mapper mapper;

    @Autowired
    public CustomerService(CustomerRepository repository, Mapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        mapper.map(customer, entity);

        repository.saveAndFlush(entity);
    }
    //Brauchen wir eine save All?
    /*public void saveAll(List<Customer> customers){
        List<CustomerEntity> entitys = ;
        mapper.map(customers, entitys);

        repository.saveAll(entitys);
    }*/

    public Optional<Customer> getCustomer(Integer id) {
        Optional<CustomerEntity> entity = repository.findById(id);
        Optional<Customer> customer = Optional.of(new Customer());
        mapper.map(entity, customer);

        return customer;
    }

    public List<Customer> getAllCustomer(){
        List<CustomerEntity> entities = repository.findAll();
        List<Customer> customers = new ArrayList<>();
        mapper.map(entities, customers);

        return customers;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    //Eine Delete all klingt riskant xD
    public void deleteAll() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
//TODO: Mapper einbauen um zwischen Entity und Dto zu wechseln um nur Objekte raus zu geben
