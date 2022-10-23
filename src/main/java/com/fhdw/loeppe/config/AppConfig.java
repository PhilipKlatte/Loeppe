package com.fhdw.loeppe.config;

import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    CustomerRepository customerRepository;

    @Bean
    public CustomerService customerService(){
        return new CustomerService(customerRepository);
    }
}
