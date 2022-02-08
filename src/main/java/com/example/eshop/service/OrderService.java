package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Order;
import com.example.eshop.model.Product;
import com.example.eshop.model.User;
import com.example.eshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public Order save(Order order) {
        Set<Product> products = getOrderProducts(order);
        order.setProducts(products);
        return orderRepository.save(order);
    }

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
        Order order = orderRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        List<String> products = List.copyOf(order.getProducts()).stream().map(Product::getId).collect(toList());
        productService.getAll(null, products);
        return order;
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

    private Set<Product> getOrderProducts(Order order) {
        List<String> productIds = order.getProducts()
                .stream()
                .map(Product::getId)
                .collect(toList());

        return productIds.stream()
                .map(productService::get)
                .collect(toSet());
    }
}
