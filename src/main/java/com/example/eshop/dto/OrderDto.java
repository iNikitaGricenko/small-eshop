package com.example.eshop.dto;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.UserDto;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class OrderDto implements Serializable {
    private final Long orders_id;
    private final String address;
    private final String description;
    private final int count;
    private final Date date;
    private final UserDto user;
    private final List<ProductDto> products;
}
