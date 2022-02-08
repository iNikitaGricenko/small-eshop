package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final String id;
    @NotNull(message = "{msg_name_notnull}")
    private final String title;
    @NotNull(message = "{msg_price_notnull}")
    @Min(value = 1, message = "{msg_price_min}")
    private final float price;
    private final String available;
    private String category;
    private String productCategory;
    private String articleNumber;
    private int vatRate;
    private String description;
    private String color;
}
