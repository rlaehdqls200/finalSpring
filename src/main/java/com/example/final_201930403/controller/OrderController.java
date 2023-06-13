package com.example.final_201930403.controller;

import com.example.final_201930403.config.JwtTokenProvider;
import com.example.final_201930403.dto.*;
import com.example.final_201930403.service.BoardService;
import com.example.final_201930403.service.OrderService;
import com.example.final_201930403.service.ProductService;
import com.example.final_201930403.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final BoardService boardService;
    private final UserService userService;
    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;

    public OrderController(OrderService orderService, BoardService boardService, UserService userService, ProductService productService, JwtTokenProvider jwtTokenProvider) {
        this.orderService = orderService;
        this.boardService = boardService;
        this.userService = userService;
        this.productService = productService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<OrderResponseDto> insertOrder(HttpServletRequest request, @RequestParam Long productId, @RequestParam String productName) throws Exception {
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserResponseDto userResponseDto = userService.userById(userId);
        ProductResponseDto productResponseDto = productService.getProduct(productId);

        System.out.println("[OrderController] userId" + userId);

        if(productResponseDto.getStock() > 0) {
            OrderDto orderDto = new OrderDto();
            orderDto.setProductId(productId);
            orderDto.setProductName(productName);
            orderDto.setUserId(userResponseDto.getUid());
            orderDto.setUserName(userResponseDto.getName());
            orderDto.setPrice(productResponseDto.getPrice());

            OrderResponseDto orderResponseDto = orderService.createOrder(orderDto);
            productService.changeProductStock(productResponseDto.getNumber(), productResponseDto.getStock());
            return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
        } else return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDto>> listAll(){
        // 주문 리스트 - 어드민
        List<OrderResponseDto> orderList = orderService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/listByUserId")
    public ResponseEntity<List<OrderResponseDto>> listByUserId(String userId){
        // 상품별 리스트 - 어드민,구매자 아이디 통해
        List<OrderResponseDto> orderList = orderService.getOrderByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/listByProductId")
    public ResponseEntity<List<OrderResponseDto>> listOrderByCreatedAt(Long productId){
        // 상품별 리스트 - 어드민, 상품 아이디 통해
        List<OrderResponseDto> orderList = orderService.getOrderProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<OrderResponseDto> getOrderId(Long id){
        // 주문 정보 - 어드민, 아이디를 통해 가져오기
        OrderResponseDto order = orderService.orderById(id);
        return ResponseEntity.ok(order);
    }
}
