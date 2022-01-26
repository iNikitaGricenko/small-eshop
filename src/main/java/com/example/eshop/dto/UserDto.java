package com.example.eshop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long user_id;
    private final String login;
}
