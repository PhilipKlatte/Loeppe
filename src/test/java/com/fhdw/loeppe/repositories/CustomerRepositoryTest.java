package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.util.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    //@BeforeEach
    public void setUp(){
        CustomerEntity entity = new CustomerEntity();
        entity.setFirstname("John");
        entity.setLastname("Doe");
        entity.setCity("Berlin");
        entity.setCountry(Country.ANGOLA);
        entity.setPostalCode("12345");
        entity.setEmailAdress("johndoe@mail.com");
        entity.setPhoneNumber("12345 / 4321");
        
        repository.save(entity);
    }

    //@Test
    public void saveCustomerSuccess(){
        var result = repository.findById(2L); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo( 2L); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben
        assertThat(result.get().getFirstname()).isEqualTo("John");
        assertThat(result.get().getLastname()).isEqualTo("Doe");
        assertThat(result.get().getCity()).isEqualTo("Berlin");
        assertThat(result.get().getPostalCode()).isEqualTo("12345");
        assertThat(result.get().getCountry()).isEqualTo(Country.ALBANIA);
        assertThat(result.get().getEmailAdress()).isEqualTo("johndoe@mail.com");
        assertThat(result.get().getPhoneNumber()).isEqualTo("12345 / 4321");
    }

    //@AfterEach
    public void tearDown(){
        repository.deleteAll();
    }
}
