package com.example.eshop.repository.product;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products e WHERE e.is_deleted = true",
            nativeQuery = true)
    Page<Product> findAllDeleted(Pageable pageable);

    @Override
    @Query(value = "SELECT * FROM products e WHERE e.is_deleted = false",
            nativeQuery = true )
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT product_id FROM products e WHERE e.product_id = ?",
            nativeQuery = true )
    Optional<Boolean> checkIdOrThrow(Long id) throws ObjectNotFoundException;
}
