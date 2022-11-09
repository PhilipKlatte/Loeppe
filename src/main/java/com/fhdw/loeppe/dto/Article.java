package com.fhdw.loeppe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Article {

    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Double price;
}
