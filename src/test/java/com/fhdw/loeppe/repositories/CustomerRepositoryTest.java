package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.repo.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    public void setUp(){
        CustomerEntity entity = new CustomerEntity();
        entity.setFirstname("John");
        entity.setLastname("Doe");
        entity.setAddress("Berlin");
        
        repository.save(entity);
    }

    @Test
    public void saveCustomerSuccess(){
        var result = repository.findById(2L); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo( 2L); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben
        assertThat(result.get().getFirstname()).isEqualTo( "John");
        assertThat(result.get().getLastname()).isEqualTo( "Doe");
        assertThat(result.get().getAddress()).isEqualTo( "Berlin");
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
    }
}
