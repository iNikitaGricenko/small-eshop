package com.example.eshop.repository.product;

import com.example.eshop.model.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByDeletedIsFalse(Pageable pageable);
    List<Product> findAllByDeletedIsTrue(Pageable pageable);

}
