package com.example.final_201930403.controller;

import com.example.final_201930403.dto.ChangeProductDto;
import com.example.final_201930403.dto.ProductDto;
import com.example.final_201930403.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.final_201930403.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductDto changeProductDto) throws Exception{
        ProductResponseDto productResponseDto = productService.changeProductName(changeProductDto.getNumber(), changeProductDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> listAll(){
        List<ProductResponseDto> productResponseDto = productService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>> listOrderByPrice(){
        List<ProductResponseDto> productResponseDto = productService.listOrderByPrice();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> getProductByName(String name){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(name));
    }
    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> getProduct(Long number){
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
}