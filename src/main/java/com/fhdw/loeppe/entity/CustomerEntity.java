package com.fhdw.loeppe.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String address;
}
