package com.example.final_201930403.repository;

import com.example.final_201930403.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);

}
