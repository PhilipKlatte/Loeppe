package com.fhdw.loeppe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Article {

    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Double price;

    public Article(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Article(String name) {
        this.name = name;
    }
}
