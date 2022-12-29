package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class OrderEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "ID")
    private UUID id;

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
