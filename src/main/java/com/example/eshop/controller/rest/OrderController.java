package com.example.eshop.controller.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.model.Order;
import com.example.eshop.model.User;
import com.example.eshop.service.CustomUserDetailsService;
import com.example.eshop.service.OrderService;
import com.example.eshop.validator.UserOrdersConstraint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderController {

    private final OrderService orderService;
    private final CustomUserDetailsService userService;
    private final OrderMapper orderMapper;

    @GetMapping("/user")
    public Page<OrderDto> getUserOrder(Authentication authentication, Pageable pageable) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        return orderService.getAll(pageable, user)
                .map(orderMapper::toDto);
    }

    @GetMapping("/{id}")
    @UserOrdersConstraint
    public ResponseEntity<OrderDto> getUserOne(@PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        Order order = orderService.getById(id);
        OrderDto orderDto = orderMapper.toDto(order);
        return ResponseEntity.ok().body(orderDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderDto add(@Valid @RequestBody OrderDto dto) throws ObjectNotFoundException {
        Order order = orderMapper.toOrder(dto);
        return orderMapper.toDto(orderService.save(order));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    @UserOrdersConstraint
    public ResponseEntity<Object> delete(@PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        orderService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @ResponseStatus(OK)
    @UserOrdersConstraint
    public OrderDto edit(@Valid @RequestBody OrderDto dto, Authentication authentication) throws ObjectNotFoundException {
        Order order = orderMapper.toOrder(dto);
        order = orderService.edit(order);
        return orderMapper.toDto(order);
    }
}
