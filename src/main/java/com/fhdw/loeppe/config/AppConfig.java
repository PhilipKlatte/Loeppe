package com.fhdw.loeppe.config;

import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.service.CustomerService;
import com.fhdw.loeppe.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

//@Configuration
public class AppConfig {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Mapper mapper;

    @Bean
    public CustomerService customerService(){
        return new CustomerService(customerRepository, mapper);
    }
}
