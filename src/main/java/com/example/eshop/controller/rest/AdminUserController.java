package com.example.eshop.controller.rest;

import com.example.eshop.dto.UserDto;
import com.example.eshop.dto.mapper.UserMapper;
import com.example.eshop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final CustomUserDetailsService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAll(Pageable pageable) {
        return userMapper.toDtos(userService.getAll(pageable).toList()); /*TODO: realize returning Page*/
    }
}
