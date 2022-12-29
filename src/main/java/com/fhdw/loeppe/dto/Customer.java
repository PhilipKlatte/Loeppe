package com.fhdw.loeppe.dto;

import com.fhdw.loeppe.util.Country;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Customer {

    private long id;

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

    public Customer(long id, String firstname, String lastname, String address) {
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
