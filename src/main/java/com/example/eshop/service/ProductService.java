package com.example.eshop.service;

import com.example.eshop.model.product.Product;
import com.example.eshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).toList();
    }

    public Product getById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with " + id + " is not exists");
        }

        productRepository.deleteById(id);
    }

    public Product edit(Product product) {
        Long id = product.getId();
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with " + id + " is not exists");
        }
        return productRepository.save(product);
    }

}
