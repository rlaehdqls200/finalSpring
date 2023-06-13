package com.example.final_201930403.service.impl;

import com.example.final_201930403.dao.BoardDAO;
import com.example.final_201930403.dao.OrderDAO;
import com.example.final_201930403.dto.*;
import com.example.final_201930403.entity.Board;
import com.example.final_201930403.entity.Order;
import com.example.final_201930403.service.BoardService;
import com.example.final_201930403.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }
    @Override
    public OrderResponseDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUserName());
        order.setProductId(orderDto.getProductId());
        order.setProductName(orderDto.getProductName());
        order.setPrice(orderDto.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order saveOrder = orderDAO.insertOrder(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(saveOrder.getId());
        orderResponseDto.setProductId(saveOrder.getProductId());
        orderResponseDto.setProductName(saveOrder.getProductName());
        orderResponseDto.setUserId(saveOrder.getUserId());
        orderResponseDto.setUserName(saveOrder.getUserName());
        orderResponseDto.setPrice(saveOrder.getPrice());

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> listAll() {
        List<Order> order = orderDAO.listAll();
        List<OrderResponseDto> orderResponseDtoList =
                order.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }
    @Override
    public List<OrderResponseDto> getOrderByUserId(String userId) {
        List<Order> order = orderDAO.listUserId(userId);
        List<OrderResponseDto> orderResponseDtoList =
                order.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }
    @Override
    public List<OrderResponseDto> getOrderProductId(Long productId) {
        List<Order> order = orderDAO.listProductId(productId);
        List<OrderResponseDto> orderResponseDtoList =
                order.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }
    @Override
    public OrderResponseDto orderById(Long id) {
        Order order = orderDAO.orderById(id);
        OrderResponseDto orderResponseDto = new OrderResponseDto(order);
        return orderResponseDto;
    }
}
