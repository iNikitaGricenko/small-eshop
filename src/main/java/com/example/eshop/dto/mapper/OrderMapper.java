package com.example.eshop.dto.mapper;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.UserDto;
import com.example.eshop.model.product.Order;
import com.example.eshop.dto.OrderDto;
import com.example.eshop.model.product.Product;
import com.example.eshop.model.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class OrderMapper {

    public static OrderDto toDto(Order order) {
        List<ProductDto> products = order.getProducts()
                .stream()
                .map(ProductMapper::toDto)
                .collect(toList());
        UserDto userDto = UserMapper.toDto(order.getUser());

        return new OrderDto(
                order.getOrders_id(),
                order.getAddress(),
                order.getDescription(),
                order.getCount(),
                order.getDate(),
                userDto,
                products
        );
    }

    public static Order toOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrders_id(orderDto.getOrders_id());
        order.setAddress(orderDto.getAddress());
        order.setDescription(orderDto.getDescription());
        order.setCount(orderDto.getCount());
        order.setDate(orderDto.getDate());

        List<Product> products = orderDto.getProducts()
                .stream()
                .map(ProductMapper::toProduct)
                .collect(toList());

        User user = UserMapper.toUser(orderDto.getUser());

        order.setUser(user);
        order.setProducts(products);

        return order;
    }

}
