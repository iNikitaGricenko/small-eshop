package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserVerificationDto {
    @NotNull
    private final Long id;
    @NotNull @Size(max = 345) @Email
    private final String email;

    @NotNull @Size(min = 8)
    private final String password;

    @NotNull @Size(max = 150, min = 5)
    private final String firstName;

    @NotNull @Size(max = 165, min = 5)
    private final String secondName;

    @NotNull @Size(max = 150, min = 5)
    private final String surname;

    @NotNull @Size(max = 1024, min = 5)
    private final String address;
}
