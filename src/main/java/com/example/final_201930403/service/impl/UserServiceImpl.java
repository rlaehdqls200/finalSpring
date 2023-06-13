package com.example.final_201930403.service.impl;

import com.example.final_201930403.dao.UserDAO;
import com.example.final_201930403.dto.UserResponseDto;
import com.example.final_201930403.entity.User;
import com.example.final_201930403.repository.UserRepository;
import com.example.final_201930403.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponseDto userById(String uId) {
        User user = userDao.userById(uId);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }
}
