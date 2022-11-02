package com.fhdw.loeppe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {

    private long id;
    private String name;
    private String description;
    private Double price;
}
