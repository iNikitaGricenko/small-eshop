package com.example.eshop.controller.rest;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.mapper.ProductMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Cacheable(cacheNames = "product")
    @GetMapping
    public Page<ProductDto> getAll(Pageable pageable) {
        return productService.getAll(pageable)
                .map(productMapper::toDto);
    }

    @Cacheable(cacheNames = "product", key = "#id")
    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable("id") String id) throws ObjectNotFoundException {
        return productMapper.toDto(productService.get(id));
    }

}
