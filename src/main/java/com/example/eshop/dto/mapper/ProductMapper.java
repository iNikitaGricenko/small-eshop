package com.example.eshop.dto.mapper;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.model.product.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductMapper {

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getProduct_id(),
                product.getName(),
                product.getPrice(),
                product.getCount()
        );
    }

    public static Product toProduct(ProductDto dto) {
        Product product = new Product();
        product.setProduct_id(dto.getProduct_id());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCount(dto.getCount());

        return product;
    }

}
