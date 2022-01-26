package com.example.eshop.rest;

import com.example.eshop.model.product.Order;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.dto.OrderDto;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRest {

    private final OrderService service;

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable("id") Long id) {
        return OrderMapper.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order add(@RequestBody OrderDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        return service.save(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderDto edit(@RequestBody OrderDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        order = service.editOrder(order);

        return OrderMapper.toDto(order);
    }
}
