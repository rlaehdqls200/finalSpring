package com.example.final_201930403.service.impl;
import com.example.final_201930403.entity.User;
import com.example.final_201930403.repository.UserRepository;
import com.example.final_201930403.service.SignService;
import com.example.final_201930403.config.JwtTokenProvider;
import com.example.final_201930403.dto.CommonResponse;
import com.example.final_201930403.dto.SignInResultDto;
import com.example.final_201930403.dto.SignUpResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;
    @Autowired
    public SignServiceImpl(UserRepository userRepository
            ,JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public SignUpResultDto signUp(String id, String password, String name, String email, String role) {
        System.out.println("[signUp] 회원가입");
        User user;
        if(role.equalsIgnoreCase("admin")){
            user = User.builder().uid(id).name(name).email(email).password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN")).build();
        } else {
            user = User.builder().uid(id).name(name).email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER")).build();
        }
        User saveUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();
        if(!saveUser.getName().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }
    private void setSuccessResult(SignUpResultDto signUpResultDto){
        signUpResultDto.setSuccess(true);
        signUpResultDto.setCode(CommonResponse.SUCCESS.getCode());
        signUpResultDto.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    private void setFailResult(SignUpResultDto signUpResultDto){
        signUpResultDto.setSuccess(false);
        signUpResultDto.setCode(CommonResponse.FAIL.getCode());
        signUpResultDto.setMsg(CommonResponse.FAIL.getMsg());
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        User user = userRepository.getByUid(id);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new RuntimeException();
        }
//        String token = JwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles(), user);
//        SignInResultDto signInResultDto = SignInResultDto.builder().token(token).build();
//        return null;
        SignInResultDto signInResultDto = SignInResultDto.builder().token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles())).build();
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }
}
