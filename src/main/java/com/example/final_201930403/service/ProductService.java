package com.example.final_201930403.service;

import com.example.final_201930403.dto.ProductDto;
import com.example.final_201930403.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto changeProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;
    List<ProductResponseDto> listAll();
    List<ProductResponseDto> listOrderByPrice();
    List<ProductResponseDto> getProductByName(String name);
    ProductResponseDto getProduct(Long number);
    ProductResponseDto changeProductStock(Long number, int stock) throws Exception;
    ProductResponseDto changeProduct(Long number, String name, int price, int stock) throws Exception;
}
