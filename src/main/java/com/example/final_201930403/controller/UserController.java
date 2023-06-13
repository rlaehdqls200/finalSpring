package com.example.final_201930403.controller;

import com.example.final_201930403.dto.ProductResponseDto;
import com.example.final_201930403.entity.User;
import com.example.final_201930403.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.function.Function;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String> listALl() {
        List<User> userList = userRepository.findAll();
        List<String> uidList = new ArrayList<>();
        for (User user : userList) {
            uidList.add(user.getUid());
        }
        return uidList;
    }
    @GetMapping("/listOrderByName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String> listOrderByName() {
        List<User> userList = userRepository.findAll();
        userList.sort(Comparator.comparing(User::getName));
        List<String> uidList = new ArrayList<>();
        for (User user : userList) {
            uidList.add(user.getUid());
        }
        return uidList;
    }
}
