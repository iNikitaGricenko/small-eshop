package com.example.eshop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreatorDto implements Serializable {
    private final Long user_id;
    private final String login;
    private final String password;
}
