package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.Country;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String emailAdress;
    private String phoneNumber;
    private String street;
    private String city;
    private String postalCode;
    private Country country;
}
