package com.example.eshop.controller.rest;

import com.example.eshop.dto.UserCreatorDto;
import com.example.eshop.dto.UserDto;
import com.example.eshop.dto.mapper.UserMapper;
import com.example.eshop.model.user.User;
import com.example.eshop.service.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRest {

    private final CustomUserDetailsService service;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDto> getAll(Pageable pageable) {
        return mapper.toDtos(service.getAll(pageable));
    }

    @PostMapping
    public RedirectView add(@ModelAttribute("user")User user) {
        service.add(user);

        return new RedirectView("/login");
    }

}
