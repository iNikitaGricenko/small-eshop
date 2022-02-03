package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    @NotNull(message = "email is not present")
    @Email(message = "email have to be like example 'example@yourmail.com'")
    @Size(max = 345,
            message = "email is to big, required maximum 345 characters")
    private final String email;
}
