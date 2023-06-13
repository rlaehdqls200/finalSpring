package com.example.final_201930403.service;

import com.example.final_201930403.dto.*;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardDto boardDto);
    BoardResponseDto updateBoard(ChangeBoardDto changeBoardDto) throws Exception;
    void deleteBoard(Long id) throws Exception;
    List<BoardResponseDto> listAll();
    List<BoardResponseDto> listOrderByCreatedAt();
    List<BoardResponseDto> getBoardById(String userId);
    BoardResponseDto boardById(Long id);
}
