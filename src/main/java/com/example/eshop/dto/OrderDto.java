package com.example.eshop.dto;

import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Data
public class OrderDto implements Serializable {

    private final Long id;

    @NotNull @Size(max = 200)
    private final String address;

    @Size(max = 1024)
    private final String description;

    @NotNull
    private final String status;

    @Min(1)
    private final int count;

    @NotNull
    private final Date date;

    @NotNull
    private final UserDto user;
    private final Set<ProductDto> products;
}
