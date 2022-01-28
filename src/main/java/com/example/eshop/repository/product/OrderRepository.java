package com.example.eshop.repository.product;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.product.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders e WHERE e.deleted = true",
            nativeQuery = true)
    Page<Order> findAllDeleted(Pageable pageable);

    @Override
    @Query(value = "SELECT * FROM orders e WHERE e.deleted = false",
            nativeQuery = true )
    Page<Order> findAll(Pageable pageable);

    @Query(value = "SELECT orders_id FROM orders e WHERE e.orders_id = ?",
            nativeQuery = true )
    Optional<Boolean> checkIdOrThrow(Long id) throws ObjectNotFoundException;
}
