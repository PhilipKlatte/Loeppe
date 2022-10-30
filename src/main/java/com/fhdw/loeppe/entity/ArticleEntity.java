package com.fhdw.loeppe.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "ARTICLE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Double price;
}
