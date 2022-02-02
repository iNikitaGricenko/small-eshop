package com.example.eshop.controller.rest;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.mapper.ProductMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Product;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRest {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAll(Pageable pageable) {
        return productMapper.toDtos(productService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return productMapper.toDto(productService.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@RequestBody ProductDto dto) {
        Product product = productMapper.toProduct(dto);
        return productMapper.toDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws ObjectNotFoundException {
        productService.remove(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto edit(@RequestBody ProductDto dto) throws ObjectNotFoundException {
        Product product = productMapper.toProduct(dto);
        return productMapper.toDto(productService.edit(product));
    }

    @PostMapping("/return/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> backToSale(@PathVariable Long id) throws ObjectNotFoundException {
        productService.backToSale(id);
        return ResponseEntity.ok().build();
    }

}
