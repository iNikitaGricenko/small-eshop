package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Product;
import com.example.eshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getAll(Pageable pageable) {
        return productRepository
                .findAll(pageable);
    }

    public Product get(String id) throws ObjectNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public void remove(String id) {
        productRepository.deleteById(id);
    }

    public Product edit(Product product) throws ObjectNotFoundException {
        String id = product.getId();
        if (productRepository.existsById(id)) {
                throw new ObjectNotFoundException();
        }
        return productRepository.save(product);
    }
}
