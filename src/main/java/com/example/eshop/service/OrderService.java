package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Order;
import com.example.eshop.model.User;
import com.example.eshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order); }

    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Page<Order> getAll(Pageable pageable, User user) {
        Long id = user.getId();
        return orderRepository.findAllByUser(pageable, id);
    }

    public Page<Order> getDeleted(Pageable pageable) {
        return orderRepository.findAllDeleted(pageable);
    }

    public Order getById(Long id) throws ObjectNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public Order edit(Order order) throws ObjectNotFoundException {
        Long id = order.getId();
        orderRepository.checkIdOrThrow(id)
                .orElseThrow(ObjectNotFoundException::new);
        return orderRepository.save(order);
    }

    public boolean isUserOrder(Long id, User user) {
        return orderRepository.existsOrderByIdAndUserEquals(id, user);
    }

    public void remove(Long id) { orderRepository.deleteById(id); }
}
