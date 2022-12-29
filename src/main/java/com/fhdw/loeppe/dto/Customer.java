package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.Country;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Customer {

    private UUID id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String address;

    private String phoneNumber;

    @NonNull
    private String emailAdress;

    @NonNull
    private String street;

    @NonNull
    private String postalCode;

    @NonNull
    private String city;

    @NonNull
    private Country country;

    public Customer(UUID id, String firstname, String lastname, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public Customer(String firstname, String lastname, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

}
