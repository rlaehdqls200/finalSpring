package com.example.final_201930403.dao.impl;


import com.example.final_201930403.dao.BoardDAO;
import com.example.final_201930403.dto.ChangeBoardDto;
import com.example.final_201930403.entity.Board;
import com.example.final_201930403.repository.BoardRepository;
import com.example.final_201930403.repository.QBoardRespository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class BoardDAOImpl implements BoardDAO {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board insertBoard(Board board) {
        Board createBoard = boardRepository.save(board);
        //memberDao에 sql date를 insert하는 쿼리문을 이 코드로 대체
        return createBoard;
    }

    @Override
    public Board updateBoard(ChangeBoardDto changeBoardDto) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(changeBoardDto.getId());

        Board updateBoard;
        if(selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            board.setTitle(changeBoardDto.getTitle());
            board.setContents(changeBoardDto.getContents());
            board.setUpdatedAt(LocalDateTime.now());
            updateBoard = boardRepository.save(board);
        } else throw new Exception();
        return updateBoard;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(id);
        if(selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            boardRepository.delete(board);
        } else throw new Exception();
    }


    @Override
    public List<Board> listAll()  {
        return boardRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Board> listOrderByCreatedAt() {
        List<Board> selectBoard =
                boardRepository.findAllByOrderByCreatedAtDesc();
        return selectBoard;
    }
    @Override
    public List<Board> selectBoardByUserId(String userId) {
        List<Board> selectBoard =
                boardRepository.findByUserId(userId);
        return selectBoard;
    }
    @Override
    public Board boardById(Long id) {
        Board selectBoard = boardRepository.getReferenceById(id);
        return selectBoard;
    }
}
