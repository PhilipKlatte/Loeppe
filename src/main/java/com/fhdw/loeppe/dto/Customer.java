package com.fhdw.loeppe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private Integer id;
    private String firstname;
    private String lastname;
    private String address;
}
