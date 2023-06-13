package com.example.final_201930403.service.impl;

import com.example.final_201930403.dao.BoardDAO;
import com.example.final_201930403.dto.*;
import com.example.final_201930403.entity.Board;
import com.example.final_201930403.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }
    @Override
    public BoardResponseDto createBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(boardDto.getUserId());
        board.setUserName(boardDto.getUserName());
        board.setCreatedAt(LocalDateTime.now());

        Board saveBoard = boardDAO.insertBoard(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setTitle(saveBoard.getTitle());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());

        return boardResponseDto;
    }



    @Override
    public BoardResponseDto updateBoard(ChangeBoardDto changeBoardDto) throws Exception {
        Board updateBoard = boardDAO.updateBoard(changeBoardDto);
        return new BoardResponseDto(updateBoard);
    }


    @Override
    public void deleteBoard(Long id) throws Exception {
        boardDAO.deleteBoard(id);
    }

    @Override
    public List<BoardResponseDto> listAll() {
        List<Board> board = boardDAO.listAll();
        List<BoardResponseDto> boardResponseDtoList =
                board.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtoList;
    }
    @Override
    public List<BoardResponseDto> listOrderByCreatedAt() {
        List<Board> board = boardDAO.listOrderByCreatedAt();
        List<BoardResponseDto> boardResponseDtoList =
                board.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtoList;
    }
    @Override
    public List<BoardResponseDto> getBoardById(String userId) {
        List<Board> board = boardDAO.selectBoardByUserId(userId);
        List<BoardResponseDto> boardResponseDtoList =
                board.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtoList;
    }
    @Override
    public BoardResponseDto boardById(Long id) {
        Board board = boardDAO.boardById(id);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContents(board.getContents());
        boardResponseDto.setUserName(board.getUserName());
        boardResponseDto.setUserId(board.getUserId());

        return boardResponseDto;
    }
}
