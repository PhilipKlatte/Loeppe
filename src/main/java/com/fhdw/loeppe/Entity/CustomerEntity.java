package com.fhdw.loeppe.Entity;

import org.springframework.context.annotation.Primary;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Table(name = "CUSTOMER")
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String address;
}
