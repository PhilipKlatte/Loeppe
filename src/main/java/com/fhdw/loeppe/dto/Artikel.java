package com.fhdw.loeppe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Artikel {
    private Integer id;
    private String name;
    private String description;
    private Double price;
}
