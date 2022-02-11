package com.example.eshop.controller;

import com.example.eshop.service.OrderService;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/orders")
    public String getAllOrdersPage(Model model, Pageable pageable) {
        model.addAttribute("orders", orderService.getAll(pageable));
        model.addAttribute("deletedOrders", orderService.getDeleted(pageable));
        return "all_orders";
    }

    @GetMapping("/product/add")
    public String getProductCreatorPage() {
        return "add_product";
    }

}
