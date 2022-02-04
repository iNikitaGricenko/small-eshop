package com.example.eshop.controller.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Order;
import com.example.eshop.model.User;
import com.example.eshop.service.CustomUserDetailsService;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final CustomUserDetailsService userService;
    private final OrderMapper orderMapper;

    @GetMapping("/user")
    public List<OrderDto> getUserOrder(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderMapper.toDtos(orderService.getAll(pageable, user));
    }

    @GetMapping("/my/{id}")
    public ResponseEntity<OrderDto> getUserOne(@PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        Order order = orderService.getById(id);
        User user = (User) authentication.getPrincipal();
        User orderUser = order.getUser();

        if (isUserOder(orderUser, user)) {
            OrderDto orderDto = orderMapper.toDto(order);
            return ResponseEntity.ok().body(orderDto);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto add(@Valid @RequestBody OrderDto dto) {
        Order order = orderMapper.toOrder(dto);
        return orderMapper.toDto(orderService.save(order));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        User user = (User) authentication.getPrincipal();
        User orderUser = orderService.getById(id).getUser();

        HttpStatus status = HttpStatus.FORBIDDEN;
        if (isUserOder(orderUser, user)) {
            orderService.remove(id);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(status);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderDto edit(@Valid @RequestBody OrderDto dto, Authentication authentication) throws ObjectNotFoundException {
        Order order = orderMapper.toOrder(dto);
        User user = (User) authentication.getPrincipal();
        User orderUser = order.getUser();

        if (isUserOder(orderUser, user)) {
            order = orderService.edit(order);
        }
        return orderMapper.toDto(order);
    }

    private boolean isUserOder(User orderUser, User user) {
        return orderUser.equals(user);
    }
}
