package com.example.eshop.repository.product;

import com.example.eshop.model.product.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDeletedIsFalse(Pageable pageable);
    List<Order> findAllByDeletedIsTrue(Pageable pageable);

}
