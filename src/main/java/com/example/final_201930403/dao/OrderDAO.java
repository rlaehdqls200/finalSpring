package com.example.final_201930403.dao;

import com.example.final_201930403.entity.Board;
import com.example.final_201930403.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order insertOrder(Order order);
    List<Order> listAll();
    List<Order> listUserId(String userId);
    List<Order> listProductId(Long productId);
    Order orderById(Long id);

}
