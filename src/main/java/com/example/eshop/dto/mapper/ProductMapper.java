package com.example.eshop.dto.mapper;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);
    Product toProduct(ProductDto product);
    List<ProductDto> toDtos(List<Product> product);

}
