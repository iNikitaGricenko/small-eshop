package com.example.eshop.repository.product;

import com.example.eshop.model.product.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders e WHERE e.deleted = true", nativeQuery = true)
    Page<Order> findAllDeleted(Pageable pageable);

    @Override
    @Query(value = "SELECT * FROM orders e WHERE e.deleted = false", nativeQuery = true )
    Page<Order> findAll(Pageable pageable);
}
