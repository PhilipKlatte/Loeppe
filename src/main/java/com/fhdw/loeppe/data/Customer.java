package com.fhdw.loeppe.data;

import com.fhdw.loeppe.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Customer extends AbstractEntity {
    private String vorname;
    private String nachname;
    private String adresse;
}
