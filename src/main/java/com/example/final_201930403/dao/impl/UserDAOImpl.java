package com.example.final_201930403.dao.impl;

import com.example.final_201930403.dao.UserDAO;
import com.example.final_201930403.entity.User;
import com.example.final_201930403.repository.UserRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User userById(String uId) {
        User user = userRepository.getByUid(uId);
        return user;
    }
}
