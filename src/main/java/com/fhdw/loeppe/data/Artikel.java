package com.fhdw.loeppe.data;

import lombok.Data;

@Data
public class Artikel {
    private Integer id;
    private String name;
    private String description;
    private Double price;
}
