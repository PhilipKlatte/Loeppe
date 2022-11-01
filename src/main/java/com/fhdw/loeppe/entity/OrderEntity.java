package com.fhdw.loeppe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToMany
    @JoinTable(
            name = "ORDERS_ARTICLES",
            joinColumns = @JoinColumn(name = "ORD_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ART_ID", referencedColumnName = "ID")
    )
    private List<ArticleEntity> articles;

    //private OrderStatus orderStatus;

    //@OneToOne
    //private CustomerEntity customerEntity;
}
