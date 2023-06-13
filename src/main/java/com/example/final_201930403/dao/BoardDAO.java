package com.example.final_201930403.dao;

import com.example.final_201930403.dto.ChangeBoardDto;
import com.example.final_201930403.entity.Board;

import java.util.List;


public interface BoardDAO {

    Board insertBoard(Board board);
    Board updateBoard(ChangeBoardDto changeBoardDto) throws Exception;
    void deleteBoard(Long id) throws Exception;
    List<Board> listAll();
    List<Board> listOrderByCreatedAt();
    List<Board> selectBoardByUserId(String userId);
    Board boardById(Long id);

//    Board updateBoard(ChangeBoardDto changeBoardDto, String userId) throws Exception;
}
