package com.example.final_201930403.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String contents;
    private String userId;
    private String userName;

    public BoardDto() {

    }
}
