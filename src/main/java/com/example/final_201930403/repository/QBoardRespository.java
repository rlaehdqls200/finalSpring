package com.example.final_201930403.repository;

import com.example.final_201930403.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QBoardRespository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

}
