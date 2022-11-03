package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void saveCustomerSuccess(){
        CustomerEntity entity = new CustomerEntity("John", "Doe", "Berlin");
        repository.save(entity);

        var result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
        assertEquals(result.get().getFirstname(), "John");
        assertEquals(result.get().getLastname(), "Doe");
        assertEquals(result.get().getAddress(), "Berlin");
    }
}
