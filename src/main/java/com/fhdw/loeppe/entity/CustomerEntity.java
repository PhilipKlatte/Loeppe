package com.fhdw.loeppe.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "CUSTOMER")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String address;
}
