package com.fhdw.loeppe.entity;

import com.fhdw.loeppe.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "ORDER")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private CustomerEntity customerEntity;

    @OneToMany
    private List<ArticleEntity> articles; //TODO: Mapping
    private OrderStatus orderStatus;
}
