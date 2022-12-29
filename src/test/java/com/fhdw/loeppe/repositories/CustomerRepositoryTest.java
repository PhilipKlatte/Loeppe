package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.util.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    private UUID entityID;

    //@BeforeEach
    public void setUp(){
        CustomerEntity entity = new CustomerEntity();
        entity.setFirstname("John");
        entity.setLastname("Doe");
        entity.setEmailAdress("johndoe@email.com");
        entity.setPhoneNumber("123456789");
        entity.setCity("Berlin");
        entity.setStreet("Gute Straße 15");
        entity.setPostalCode("12345");
        entity.setCountry(Country.GERMANY);

        entity = repository.save(entity);

        entityID = entity.getId();
    }

    //@Test
    public void saveCustomerSuccess(){
        var result = repository.findById(entityID);

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(entityID);
        assertThat(result.get().getFirstname()).isEqualTo( "John");
        assertThat(result.get().getLastname()).isEqualTo( "Doe");
        assertThat(result.get().getCity()).isEqualTo( "Berlin");
    }

    //@AfterEach
    public void tearDown(){
        repository.deleteAll();
    }
}
