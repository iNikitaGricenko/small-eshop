package com.example.eshop.controller.rest;

import com.example.eshop.dto.ProductDto;
import com.example.eshop.dto.mapper.ProductMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Product;
import com.example.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRest {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    public List<ProductDto> getAll(Pageable pageable) {
        return mapper.toDtos(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return mapper.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@RequestBody ProductDto dto) {
        Product product = mapper.toProduct(dto);
        return mapper.toDto(service.save(product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws ObjectNotFoundException {
        service.deleteById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto edit(@RequestBody ProductDto dto) throws ObjectNotFoundException {
        Product product = mapper.toProduct(dto);
        return mapper.toDto(service.edit(product));
    }

}
