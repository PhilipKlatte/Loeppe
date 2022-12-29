package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.Country;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    private String firstname;
    private String lastname;
    private String emailAdress;
    private String phoneNumber;
    private String street;
    private String city;
    private String postalCode;
    private Country country;
}
