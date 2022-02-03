package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final Long id;
    @NotNull(message = "{msg_name_notnull}")
    private final String name;
    @NotNull(message = "{msg_price_notnull}")
    @Min(value = 1, message = "{msg_price_min}")
    private final int price;
    private final int count;
}
