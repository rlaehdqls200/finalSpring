package com.example.final_201930403.service;

import com.example.final_201930403.dto.SignInResultDto;
import com.example.final_201930403.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String email, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}

