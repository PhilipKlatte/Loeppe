package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    private CustomerEntity customerEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ORDERS_ARTICLES",
            joinColumns = @JoinColumn(name = "ORD_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ART_ID", referencedColumnName = "ID")
    )
    private List<ArticleEntity> articles;

    private OrderStatus orderStatus;
}
