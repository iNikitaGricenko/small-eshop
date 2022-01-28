package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Product;
import com.example.eshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
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

    public List<Product> getAll(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .toList();
    }

    public List<Product> getDeleted(Pageable pageable) {
        return productRepository
                .findAllDeleted(pageable)
                .toList();
    }

    public Product get(Long id) throws ObjectNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public void remove(Long id) throws ObjectNotFoundException {
        productRepository.checkIdOrThrow(id);

        productRepository.deleteById(id);
    }

    public Product edit(Product product) throws ObjectNotFoundException {
        Long id = product.getId();
        productRepository.checkIdOrThrow(id);

        return productRepository.save(product);
    }

}
