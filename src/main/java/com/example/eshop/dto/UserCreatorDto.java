package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserCreatorDto implements Serializable {

    @NotEmpty(message = "email is not present")
    @Email(message = "email have to be like example 'example@yourmail.com'")
    @Size(max = 345,
            message = "email is to big, required maximum 345 characters")
    private final String email;

    @NotNull(message = "first name is not present")
    @Size(max = 150,
            min = 5,
            message = "first name length have to be less then 150 and bigger then 5 characters")
    private final String firstName;

    @NotNull(message = "second name is not present")
    @Size(max = 165,
            min = 5,
            message = "second name length have to be less then 165 and bigger then 5 characters")
    private final String secondName;

    @NotNull(message = "surname is not present")
    @Size(max = 150,
            min = 5,
            message = "surname length have to be less then 150 and bigger then 5 characters")
    private final String surname;

    @NotNull(message = "address is not present")
    @Size(max = 1024,
            min = 5,
            message = "address length have to be less then 1024 and bigger then 5 characters")
    private final String address;
}
