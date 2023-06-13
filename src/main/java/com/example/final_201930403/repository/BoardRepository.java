package com.example.final_201930403.repository;

import com.example.final_201930403.entity.Board;
import com.example.final_201930403.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByIdAsc();
    List<Board> findAllByOrderByCreatedAtDesc();
    List<Board> findByUserId(String userId);
}
