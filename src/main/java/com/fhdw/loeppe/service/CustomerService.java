package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public List<Customer> saveAll(List<Customer> customers){
        return repository.saveAll(customers);
    }

    public Optional<Customer> get(UUID id) {
        return repository.findById(id);
    }

    public List<Customer> getAll(){ return repository.findAll(); }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest