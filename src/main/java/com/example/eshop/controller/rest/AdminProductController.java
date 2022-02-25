package com.example.eshop.controller.rest;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.mapper.ProductMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Product;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @CacheEvict(cacheNames = "product", key = "#id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) throws ObjectNotFoundException {
        productService.remove(id);
    }

    @CachePut(cacheNames = "product", key = "#id")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto edit(@Valid @RequestBody ProductDto dto) throws ObjectNotFoundException {
        Product product = productMapper.toProduct(dto);
        return productMapper.toDto(productService.edit(product));
    }

    @Cacheable(cacheNames = "product", key = "#product.id")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@Valid @RequestBody ProductDto dto) {
        Product product = productMapper.toProduct(dto);
        return productMapper.toDto(productService.save(product));
    }

}
