package com.example.eshop.controller.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.model.Order;
import com.example.eshop.model.User;
import com.example.eshop.service.CustomUserDetailsService;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomUserDetailsService userService;
    private final OrderMapper orderMapper;

    @GetMapping("/user")
    public List<OrderDto> getUserOrder(Authentication authentication, Pageable pageable) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return orderMapper.toDtos(orderService.getAll(pageable, userDetails.getUser()).toList()); /*TODO: realize returning Page*/
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getUserOne(@PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (!orderService.existUser(userDetails.getUser())) {
            throw new AccessDeniedException("Access denied");
        }
        Order order = orderService.getById(id);

        OrderDto orderDto = orderMapper.toDto(order);
        return ResponseEntity.ok().body(orderDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderDto add(@Valid @RequestBody OrderDto dto) {
        Order order = orderMapper.toOrder(dto);
        return orderMapper.toDto(orderService.save(order));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id, Authentication authentication) throws ObjectNotFoundException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (!orderService.existUser(userDetails.getUser())) {
            throw new AccessDeniedException("Access denied");
        }
        orderService.remove(id);

        return new ResponseEntity<>(OK);
    }

    @PatchMapping
    @ResponseStatus(OK)
    public OrderDto edit(@Valid @RequestBody OrderDto dto, Authentication authentication) throws ObjectNotFoundException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (!orderService.existUser(userDetails.getUser())) {
            throw new AccessDeniedException("Access denied");
        }
        Order order = orderMapper.toOrder(dto);
        order = orderService.edit(order);

        return orderMapper.toDto(order);
    }
}
