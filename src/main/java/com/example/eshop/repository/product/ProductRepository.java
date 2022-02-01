package com.example.eshop.repository.product;

import com.example.eshop.model.product.Product;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products e WHERE e.deleted = true",
            nativeQuery = true)
    Page<Product> findAllDeleted(Pageable pageable);

    @Override
    @Query(value = "SELECT * FROM products e WHERE e.deleted = false",
            nativeQuery = true )
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT product_id FROM products e WHERE e.product_id = ?",
            nativeQuery = true )
    Optional<Boolean> existById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE products e SET deleted = false where e.product_id = ?",
            nativeQuery = true)
    void returnToSale(Long id);
}
