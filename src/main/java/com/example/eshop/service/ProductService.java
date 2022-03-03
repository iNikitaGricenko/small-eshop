package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Product;
import com.example.eshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Cacheable(cacheNames = "product", key = "#product.id")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(cacheNames = "product")
    public Page<Product> getAll(Pageable pageable) {
        return productRepository
                .findAll(pageable);
    }

    @Cacheable(cacheNames = "product", key = "#ids")
    public Iterable<Product> getAllById(Iterable<String> ids) {
        return productRepository
                .findAllById(ids);
    }

    @SneakyThrows
    @Cacheable(cacheNames = "product", key = "#id")
    public Product get(String id) {
        return productRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    public void remove(String id) {
        productRepository.deleteById(id);
    }

    @CachePut(cacheNames = "product", key = "#product.id")
    public Product edit(Product product) throws ObjectNotFoundException {
        String id = product.getId();
        if (productRepository.existsById(id)) {
                throw new ObjectNotFoundException();
        }
        return productRepository.save(product);
    }
}
