package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @NotEmpty
    private CustomerEntity customerEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ORDERS_ARTICLES",
            joinColumns = @JoinColumn(name = "ORD_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ART_ID", referencedColumnName = "ID")
    )
    @NotEmpty
    private List<ArticleEntity> articles;

    @NotEmpty
    private OrderStatus orderStatus;
}
