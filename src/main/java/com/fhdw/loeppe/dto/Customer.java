package com.fhdw.loeppe.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class Customer {

    private long id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
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
    private String country;
}
