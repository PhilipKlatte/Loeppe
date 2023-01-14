package com.fhdw.loeppe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Article {

    private UUID id;

    private String name;
    private String description;
    private Double price;

    public Article(String name) {
        this.name = name;
    }
}
