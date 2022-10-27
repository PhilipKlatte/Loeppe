package com.fhdw.loeppe.dto;

import lombok.Data;

@Data
public class Article {
    private Integer id;
    private String name;
    private String description;
    private Double price;
}
