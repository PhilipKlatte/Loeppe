package com.fhdw.loeppe.data;

import com.fhdw.loeppe.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@AllArgsConstructor
@Entity
@Data
public class Customer extends AbstractEntity {
    private String vorname;
    private String nachname;
    private String adresse;

    public Customer() {

    }
}
