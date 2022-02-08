package com.example.eshop.controller.rest;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.dto.mapper.OrderMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderService.getAll(pageable)
                .map(orderMapper::toDto);
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return orderMapper.toDto(orderService.getById(id));
    }

}
