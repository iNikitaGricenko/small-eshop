package com.example.eshop.controller.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.user.CustomUserDetails;
import com.example.eshop.model.order.Order;
import com.example.eshop.model.user.User;
import com.example.eshop.service.OrderService;
import com.example.eshop.validator.UserOrdersConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;
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
    public ResponseEntity<OrderDto> getUserOne(
            @PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
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
    public ResponseEntity<Object> delete(
            @PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        orderService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @ResponseStatus(OK)
    @UserOrdersConstraint
    public OrderDto edit(
            @Valid @RequestBody OrderDto dto, Authentication authentication) throws ObjectNotFoundException {
        Order order = orderMapper.toOrder(dto);
        order = orderService.edit(order);
        return orderMapper.toDto(order);
    }
}
