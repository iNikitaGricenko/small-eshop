package com.example.eshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    @GetMapping("/login")
    public String getLoginPage() { return "login"; }

    @GetMapping("/register")
    public String getRegisterPage() { return "register"; }
}
