package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.Country;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
