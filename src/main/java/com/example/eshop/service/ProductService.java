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

    public Page<Product> getDeleted(Pageable pageable) {
        return productRepository
                .findAllDeleted(pageable);
    }

    public Product get(Long id) throws ObjectNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public Product edit(Product product) throws ObjectNotFoundException {
        Long id = product.getId();
        productRepository.existById(id)
                .orElseThrow(ObjectNotFoundException::new);
        return productRepository.save(product);
    }

    public void backToSale(Long id) throws ObjectNotFoundException{
        productRepository.existById(id)
                .orElseThrow(ObjectNotFoundException::new);
        productRepository.returnToSale(id);
    }
}
