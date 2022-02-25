package com.example.eshop.controller;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.model.User;
import com.example.eshop.service.OrderService;
import com.example.eshop.service.ProductService;
import com.example.eshop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;
    private final OrderService orderService;
    private final CustomUserDetailsService userService;

    @ModelAttribute("User")
    public User getUser(Authentication authentication) {
        if (authentication == null) {
            return new User();
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    @GetMapping
    public String getHomePage(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAll(pageable));
        return "index";
    }

    @GetMapping("/my/orders")
    public String getOrderPage(Model model, Authentication authentication, Pageable pageable) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("orders", orderService.getAll(pageable, userDetails.getUser()));
        return "my_orders";
    }

    @GetMapping("/user/activate")
    public String activatePage(Model model, @RequestParam("key") String code) throws ObjectNotFoundException {
        User user = userService.findByActivationCode(code);
        model.addAttribute("email", user.getEmail());
        return "activate";
    }
}
