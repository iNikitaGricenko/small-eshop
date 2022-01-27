package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserCreatorDto implements Serializable {
    @NotNull
    private final Long id;
    @NotNull @Size(max = 345) @Email
    private final String login;
    @NotNull
    private final String password;
}
