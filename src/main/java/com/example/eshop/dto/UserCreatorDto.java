package com.example.eshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserCreatorDto implements Serializable {

    @NotNull(message = "{msg_email_notnull}")
    @Email(message = "{msg_email}")
    @Size(max = 345,
            message = "{msg_email_size}")
    private final String email;

    @NotNull(message = "{msg_first_name_notnull}")
    @Size(max = 150,
            min = 5,
            message = "{msg_first_name_size}")
    private final String firstName;

    @NotNull(message = "{msg_second_name_notnull}")
    @Size(max = 165,
            min = 5,
            message = "{msg_second_name_size}")
    private final String secondName;

    @NotNull(message = "{msg_surname_notnull}")
    @Size(max = 150,
            min = 5,
            message = "{msg_surname_size}")
    private final String surname;

    @NotNull(message = "{msg_address_notnull}")
    @Size(max = 1024,
            min = 5,
            message = "{msg_address_size}")
    private final String address;
}
