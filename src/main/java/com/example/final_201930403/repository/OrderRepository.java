package com.example.final_201930403.repository;

import com.example.final_201930403.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByProductId(Long productId);
    List<Order> findByUserId(String userId);
}
