package com.example.eshop.controller;

import com.example.eshop.model.user.User;
import com.example.eshop.service.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    private final CustomUserDetailsService userService;

    @GetMapping("/login")
    public String getLoginPage() { return "login"; }

    @GetMapping("/register")
    public String getRegisterPage() { return "register"; }

    @PostMapping("/register")
    public String registrate(@ModelAttribute("User") User user) {
        userService.add(user);
        return "/login";
    }

}
