package com.example.eshop.rest;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.mapper.ProductMapper;
import com.example.eshop.model.product.Product;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRest {

    private final ProductService service;

    @GetMapping
    public List<Product> getAll(@RequestParam(value = "count", required = false, defaultValue = "0") int count) {
        if (count == 0) {
            return service.getAll();
        }
        return service.getAll(count);
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) {
        return ProductMapper.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product add(@RequestBody ProductDto productDto) {
        return service.save(ProductMapper.toProduct(productDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public Product edit(@RequestBody ProductDto productDto) {
        Product product = ProductMapper.toProduct(productDto);
        return service.edit(product);
    }

}
