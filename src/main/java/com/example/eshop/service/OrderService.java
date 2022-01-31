package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Order;
import com.example.eshop.model.user.User;
import com.example.eshop.repository.product.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order); }

    public List<Order> getAll(Pageable pageable) {
        return orderRepository
                .findAll(pageable)
                .toList();
    }

    public List<Order> getAll(Pageable pageable, User user) {
        Long id = user.getId();
        return orderRepository.findAllByUser(pageable, id).toList();
    }

    public List<Order> getDeleted(Pageable pageable) {
        return orderRepository
                .findAllDeleted(pageable)
                .toList();
    }

    public Order getById(Long id) throws ObjectNotFoundException {
        return orderRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public Order edit(Order order) throws ObjectNotFoundException {
        Long id = order.getId();
        orderRepository.checkIdOrThrow(id)
                .orElseThrow(ObjectNotFoundException::new);
        return orderRepository.save(order);
    }

    public void remove(Long id) { orderRepository.deleteById(id); }
}
