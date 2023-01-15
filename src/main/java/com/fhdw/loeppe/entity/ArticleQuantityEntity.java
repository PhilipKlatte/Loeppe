package com.fhdw.loeppe.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class ArticleQuantityEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "ID")
    private UUID id;

    @ManyToOne
    private OrderEntity orderEntity;

    @ManyToOne
    private ArticleEntity articleEntity;

    private int quantity;
}
