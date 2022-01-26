package com.example.eshop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final Long product_id;
    private final String name;
    private final int price;
    private final int count;
}
