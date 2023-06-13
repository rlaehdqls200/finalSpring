package com.example.final_201930403.dto;


import com.example.final_201930403.entity.Board;
import com.example.final_201930403.entity.Product;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;
    private LocalDateTime createAt;
    public BoardResponseDto() {
    }
    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
        this.createAt = board.getCreatedAt();
    }
    public BoardResponseDto(ChangeBoardDto changeBoardDto) {
        this.id = changeBoardDto.getId();
        this.title = changeBoardDto.getTitle();
        this.contents = changeBoardDto.getContents();
    }
    public BoardResponseDto(Long id, String title, String contents, String userId, String userName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.userName = userName;
    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}