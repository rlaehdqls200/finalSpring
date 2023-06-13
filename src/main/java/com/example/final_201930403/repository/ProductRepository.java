package com.example.final_201930403.repository;

import com.example.final_201930403.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByNumberAsc();
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findByName(String name, Sort sort);
    //orderBy는 데이터가 하나론 안되고 여러개가 필요해서 리턴을 리스트 사용.
    @Query("SELECT p FROM Product AS p WHERE p.stock = :stock")//DB에 prodyct 테이블명이 아닌 엔티피에 정의해준 이름이다.
    List<Product> listByStock(@Param("stock") int stock);
    // ?1 은 파라미터를 받기 위한 인자 (1은 첫번째 파라미터 의미)
}
