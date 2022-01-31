package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final Long id;
    @NotNull
    private final String name;
    @NotNull @Min(1)
    private final int price;
    private final int count;
}
