package com.example.eshop.repository.product;

import com.example.eshop.model.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
