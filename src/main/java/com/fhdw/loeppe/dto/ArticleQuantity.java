package com.fhdw.loeppe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ArticleQuantity {

    private UUID id;

    private Order order;
    private Article article;
    private int quantity;
}
