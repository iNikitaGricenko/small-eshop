package com.example.eshop.controller;

import com.example.eshop.model.User;
import com.example.eshop.service.OrderService;
import com.example.eshop.service.ProductService;
import com.example.eshop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticatedController {

    private final ProductService productService;
    private final OrderService orderService;
    private final CustomUserDetailsService userService;

    @ModelAttribute("User")
    public User getUser(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userService.get(principal.getName());
    }

    @GetMapping
    public String getHomePage(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAll(pageable));
        return "index";
    }

    @GetMapping("/my-orders")
    public String getOrderPage(Model model, Principal principal, Pageable pageable) {
        User user = userService.get(principal.getName());
        model.addAttribute("orders", orderService.getAll(pageable, user));
        return "my_orders";
    }
}
