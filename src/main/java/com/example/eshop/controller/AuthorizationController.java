package com.example.eshop.controller;

import com.example.eshop.model.user.User;
import com.example.eshop.service.OrderService;
import com.example.eshop.service.ProductService;
import com.example.eshop.service.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

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

    @GetMapping("/login")
    @PreAuthorize(value = "!isAuthenticated()")
    public String getLoginPage() { return "login"; }

    @GetMapping("/register")
    @PreAuthorize(value = "!isAuthenticated()")
    public String getRegisterPage() { return "register"; }

    @PostMapping("/register")
    @PreAuthorize(value = "!isAuthenticated()")
    public String registrate(@ModelAttribute("User") User user) {
        userService.add(user);
        return "/login";
    }

    @GetMapping("/")
    @PostAuthorize(value = "isAuthenticated()")
    public String getHomePage(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAll(pageable));
        return "index";
    }

    @GetMapping("/my-orders")
    @PreAuthorize(value = "isAuthenticated()")
    public String getOrderPage(Model model, Principal principal, Pageable pageable) {
        User user = userService.get(principal.getName());
        model.addAttribute("orders", orderService.getAll(pageable, user));
        return "my_orders";
    }

    @GetMapping("/all-orders")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String getAllOrdersPage(Model model, Pageable pageable) {
        model.addAttribute("orders", orderService.getAll(pageable));
        model.addAttribute("deletedOrders", orderService.getDeleted(pageable));
        model.addAttribute("deletedProducts", productService.getDeleted(pageable));
        return "all_orders";
    }

    @GetMapping("/add-product")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String getProductCreatorPage() {
        return "add_product";
    }
}
