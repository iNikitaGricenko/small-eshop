package com.example.eshop.controller.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Order;
import com.example.eshop.model.User;
import com.example.eshop.service.OrderService;
import com.example.eshop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRest {

    private final OrderService orderService;
    private final CustomUserDetailsService userService;
    private final OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto> getAll(Pageable pageable) {
        return orderMapper.toDtos(orderService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return orderMapper.toDto(orderService.getById(id));
    }

    @GetMapping("/user/{id}")
    public List<OrderDto> getUserOrder(@PathVariable("id") Long id, Pageable pageable) throws ObjectNotFoundException {
        User user = userService.get(id);
        return orderMapper.toDtos(orderService.getAll(pageable, user));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto add(@Valid @RequestBody OrderDto dto) {
        Order order = orderMapper.toOrder(dto);
        return orderMapper.toDto(orderService.save(order));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        orderService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderDto edit(@Valid @RequestBody OrderDto dto) throws ObjectNotFoundException {
        Order order = orderMapper.toOrder(dto);
        order = orderService.edit(order);

        return orderMapper.toDto(order);
    }
}
