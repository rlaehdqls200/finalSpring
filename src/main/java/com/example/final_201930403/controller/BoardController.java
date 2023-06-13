package com.example.final_201930403.controller;

import com.example.final_201930403.config.JwtTokenProvider;
import com.example.final_201930403.dto.*;
import com.example.final_201930403.entity.Board;
import com.example.final_201930403.entity.User;
import com.example.final_201930403.service.BoardService;
import com.example.final_201930403.service.ProductService;
import com.example.final_201930403.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardResponseDto> createBoard(HttpServletRequest request, @RequestParam String title, @RequestParam String contents) {
        BoardDto boardDto = new BoardDto();
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserResponseDto userResponseDto = userService.userById(userId);
        boardDto.setTitle(title);
        boardDto.setContents(contents);
        boardDto.setUserId(userId);
        boardDto.setUserName(userResponseDto.getName());

        System.out.println("userId :" + userId);
        BoardResponseDto boardResponseDto = boardService.createBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardResponseDto> changeBoard(HttpServletRequest request, @RequestBody ChangeBoardDto changeBoardDto) throws Exception {
        BoardResponseDto selectBoard = boardService.boardById(changeBoardDto.getId());
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));

        System.out.println("Id: " + id);
        System.out.println("userId: " + selectBoard.getUserId());
        if(selectBoard.getUserId().equals(id)) {
            BoardResponseDto boardResponseDto = boardService.updateBoard(changeBoardDto);
            return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
        } else {
            throw new Exception();
        }
    }
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> deleteBoard(HttpServletRequest request, @RequestParam Long boardId) throws Exception {
        BoardResponseDto selectBoard = boardService.boardById(boardId);
        String token = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));

        if (selectBoard.getUserId().equals(token)) {
            boardService.deleteBoard(boardId);
            return ResponseEntity.status(HttpStatus.OK).body("게시글이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("권한이 없습니다.");
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> listAll(){
        // 게시판 리스트 - 누구나
        List<BoardResponseDto> boardList = boardService.listAll();
        boardList.forEach(b-> {
            System.out.println("board : " + b);
        });
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }

    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardResponseDto>> listOrderByCreatedAt(){
        // 게시글 리스트 - 작성일시(내림차순)(누구나, 작성자 중복 등록 가능)
        List<BoardResponseDto> sortedBoardList = boardService.listOrderByCreatedAt();
        return ResponseEntity.ok(sortedBoardList);
    }

    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDto>> getBoardById(String userId){
        // 게시글 리스트 - 작성자를 통해 가져오기(누구나, 작성자 중복 등록 가능)
        List<BoardResponseDto> boardListByUser = boardService.getBoardById(userId);
        return ResponseEntity.ok(boardListByUser);
    }
    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> getBoard(Long id){
        // 게시글 정보 - 아이디를 통해 가져오기(누구나)
        BoardResponseDto board = boardService.boardById(id);
        return ResponseEntity.ok(board);
    }
}
