package com.fhdw.loeppe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {

    private Integer id;
    private String name;
    private String description;
    private Double price;
}
