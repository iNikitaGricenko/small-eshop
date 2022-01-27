package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Order;
import com.example.eshop.repository.product.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable).toList();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order editOrder(Order order) throws ObjectNotFoundException {
        Long id = order.getId();
        if(!orderRepository.existsById(id)) {
            throw new ObjectNotFoundException("Order with " + id + " not found");
        }

        return orderRepository.save(order);
    }

    public Order getById(Long id) throws ObjectNotFoundException {
        return orderRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }
}
