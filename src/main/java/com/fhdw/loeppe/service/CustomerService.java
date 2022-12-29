package com.fhdw.loeppe.service;

import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.helpers.SearchHelper;
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
        repository.saveAllAndFlush(entitys);
    }

    public List<Customer> searchCustomerWithID(Customer cust) {
        final List<Customer> repoID = SearchHelper.searchCustID(getAllCustomer(), cust.getId());
        final List<Customer> repoFirstName = SearchHelper.searchCustFirstN(repoID, cust.getFirstname());
        final List<Customer> repoLastName = SearchHelper.searchCustLarstN(repoFirstName, cust.getLastname());

        return SearchHelper.searchCustAddress(repoLastName, cust.getAddress());
    }

    public List<Customer> searchCustomerWithoutID(Customer cust) {
        final List<Customer> repoFirstName = SearchHelper.searchCustFirstN(getAllCustomer(), cust.getFirstname());
        final List<Customer> repoLastName = SearchHelper.searchCustLarstN(repoFirstName, cust.getLastname());

        return SearchHelper.searchCustAddress(repoLastName,cust.getAddress());
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

    public void deleteCustomer(Customer customer) {
        repository.delete(mapper.map(customer, CustomerEntity.class));
    }

    public void deleteAllCustomers() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
