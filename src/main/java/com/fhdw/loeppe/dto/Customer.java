package com.fhdw.loeppe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    private long id;
    private String firstname;
    private String lastname;
    private String address;
}
