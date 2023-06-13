package com.example.final_201930403.service;

import com.example.final_201930403.dto.UserResponseDto;
import com.example.final_201930403.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDto userById(String uId);
}
