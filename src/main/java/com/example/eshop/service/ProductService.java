package com.example.eshop.service;

import com.example.eshop.model.product.Product;
import com.example.eshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAll(int count) {
        Pageable pageable = PageRequest.ofSize(count);
        return productRepository.findAll(pageable).toList();
    }

    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with " + id + " is not exists");
        }

        productRepository.deleteById(id);
    }

    public Product edit(Product product) {
        Long id = product.getProduct_id();
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with " + id + " is not exists");
        }
        return productRepository.save(product);
    }

}
