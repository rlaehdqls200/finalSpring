package com.example.final_201930403.service;

import com.example.final_201930403.dto.*;
import com.example.final_201930403.repository.OrderRepository;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderDto orderDto);
    List<OrderResponseDto> listAll();
    List<OrderResponseDto> getOrderByUserId(String userId);
    List<OrderResponseDto> getOrderProductId(Long productId);
    OrderResponseDto orderById(Long id);

}
