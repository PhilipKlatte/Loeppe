package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.Country;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Customer {

    private UUID id;

    private String firstname;

    private String lastname;

    private String emailAdress;

    private String phoneNumber;

    private String street;

    private String city;

    private String postalCode;

    private Country country;

    public Customer(String firstname, String lastname, String emailAdress, String phoneNumber,
                    String street, String city, String postalCode, Country country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
}
