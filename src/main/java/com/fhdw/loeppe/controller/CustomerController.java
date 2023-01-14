package com.fhdw.loeppe.controller;

import com.fhdw.loeppe.dto.Customer;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.util.Mapper;
import com.fhdw.loeppe.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerById(@PathVariable UUID id) {
        var customer = customerService.getCustomer(id);
        if (customer == null) throw new ResourceNotFoundException();

        return customer;
    }

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @PostMapping(path = "/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer postCustomer(@RequestBody Customer customer){
        return mapper.map(customerService.saveCustomer(customer), Customer.class);
    }

    @PutMapping(path = "/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putCustomer(@RequestBody Customer customer){
        return mapper.map(customerService.updateCustomer(customer), Customer.class);
    }

    @DeleteMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomerById(@PathVariable UUID id) {
        var customer = customerService.getCustomer(id);
        if (customer == null) throw new ResourceNotFoundException();

        customerService.deleteCustomer(customer);
    }
}
