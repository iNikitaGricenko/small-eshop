package com.example.eshop.dto.mapper;

import com.example.eshop.model.user.User;
import com.example.eshop.dto.UserDto;
import com.example.eshop.dto.UserCreatorDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(user.getUser_id(), user.getLogin());
    }

    public static User toUser(UserDto dto) {
        User user = new User();
        user.setUser_id(dto.getUser_id());
        user.setLogin(dto.getLogin());
        return user;
    }

    public static User toUser(UserCreatorDto dto) {
        User user = new User();
        user.setUser_id(dto.getUser_id());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

}
