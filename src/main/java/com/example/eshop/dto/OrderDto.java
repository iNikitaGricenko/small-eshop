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

    @NotNull(message = "{msg_address_notnull}")
    @Size(max = 200)
    private final String address;

    @Size(max = 1024, message = "{msg_description_size}")
    private final String description;

    @NotNull(message = "{msg_status_notnull}")
    private final String status;

    @Min(value = 1, message = "{msg_count_min}")
    private final int count;

    @NotNull(message = "{msg_user_notnull}")
    private final UserDto user;
    private final Set<ProductDto> products;
}
