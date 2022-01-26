package com.example.eshop.service;

import com.example.eshop.model.product.Order;
import com.example.eshop.repository.product.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order editOrder(Order order) {
        Long id = order.getOrders_id();
        if(!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order with " + id + " is not exists");
        }

        return orderRepository.save(order);
    }

    public Order getById(Long id) {
        return orderRepository.getById(id);
    }
}
