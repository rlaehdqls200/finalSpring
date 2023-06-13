package com.example.final_201930403.dto;

import com.example.final_201930403.entity.User;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String uid;

    private String name;

    private String email;


    public UserResponseDto(User user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
