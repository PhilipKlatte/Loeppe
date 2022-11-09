package com.fhdw.loeppe.dto;

import lombok.*;

@RequiredArgsConstructor
@Data
public class Article {

    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Double price;
}
