package com.fhdw.loeppe.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Data
public class ArticleEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "ID")
    private UUID id;

    private String name;
    private String description;
    private Double price;
}
