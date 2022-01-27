package com.example.eshop.controller;

import com.example.eshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    private final ProductRepository productRepository;

    @GetMapping("/login")
     public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

}
