package com.fhdw.loeppe.data;

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
