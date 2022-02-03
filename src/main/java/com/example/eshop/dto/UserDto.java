package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    @NotNull(message = "{msg_email_notnull}")
    @Email(message = "{msg_email}")
    @Size(max = 345,
            message = "{msg_email_size}")
    private final String email;
}
