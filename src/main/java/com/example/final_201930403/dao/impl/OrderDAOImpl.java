package com.example.final_201930403.dao.impl;

import com.example.final_201930403.dao.OrderDAO;
import com.example.final_201930403.entity.Board;
import com.example.final_201930403.entity.Order;
import com.example.final_201930403.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderDAOImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public Order insertOrder(Order order){
        Order createOrder = orderRepository.save(order);
        return createOrder;
    }
    @Override
    public List<Order> listAll(){
        return orderRepository.findAll();
    }
    @Override
    public List<Order> listUserId(String userId){
        List<Order> selectOrder =
                orderRepository.findByUserId(userId);
        return selectOrder;
    }
    @Override
    public List<Order> listProductId(Long productId){
        List<Order> selectOrder =
                orderRepository.findByProductId(productId);
        return selectOrder;
    }
    @Override
    public Order orderById(Long id){
        return orderRepository.getReferenceById(id);
    }
}
