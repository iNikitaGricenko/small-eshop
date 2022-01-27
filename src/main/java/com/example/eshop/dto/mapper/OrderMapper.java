package com.example.eshop.dto.mapper;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.model.product.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);
    Order toOrder(OrderDto dto);
    List<OrderDto> toDtos(List<Order> orders);

}
