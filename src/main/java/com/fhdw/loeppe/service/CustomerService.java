package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.helpers.SearchHelper;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository repository;
    private final Mapper mapper;

    public CustomerEntity saveCustomer(Customer customer){
        return repository.saveAndFlush(mapper.map(customer, CustomerEntity.class));
    }

    public void saveAllCustomers(List<Customer> customers){
        List<CustomerEntity> entitys = mapper.mapAll(customers, CustomerEntity.class);
        repository.saveAllAndFlush(entitys);
    }

    public List<Customer> searchCustomer(String id, Customer cust) {
        final List<Customer> repoID = SearchHelper.searchCustID(getAllCustomer(), id);
        final List<Customer> repoFirstName = SearchHelper.searchCustFirstN(repoID, cust.getFirstname());
        final List<Customer> repoLastName = SearchHelper.searchCustLarstN(repoFirstName, cust.getLastname());
        final List<Customer> repoEmail = SearchHelper.searchCustEmail(repoLastName, cust.getEmailAdress());
        final List<Customer> repoPhone = SearchHelper.searchCustPhone(repoEmail, cust.getPhoneNumber());
        final List<Customer> repoStreet = SearchHelper.searchCustStreet(repoPhone, cust.getStreet());
        final List<Customer> repoCity = SearchHelper.searchCustCity(repoStreet, cust.getCity());
        final List<Customer> repoPostal = SearchHelper.searchCustPostal(repoCity, cust.getPostalCode());

        return SearchHelper.searchCustCountry(repoPostal, cust.getCountry());
    }

    public Customer getCustomer(UUID id) {
        return mapper.map(repository.findById(id), Customer.class);
    }

    public List<Customer> getAllCustomer(){
        return mapper.mapAll(repository.findAll(), Customer.class);
    }

    public CustomerEntity updateCustomer(Customer customer) {
        return saveCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        repository.delete(mapper.map(customer, CustomerEntity.class));
    }

    public void deleteAllCustomers() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
