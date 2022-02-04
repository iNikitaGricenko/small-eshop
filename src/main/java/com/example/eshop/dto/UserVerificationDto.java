package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserVerificationDto {
    @NotNull(message = "{msg_email_notnull}")
    @Email(message = "{msg_email}")
    @Size(max = 345,
            message = "{msg_email_size}")
    private final String email;

    @NotNull(message = "{msg_password_notnull}")
    @Size(min = 8,
            message = "{msg_password_size}")
    private final String password;
}
