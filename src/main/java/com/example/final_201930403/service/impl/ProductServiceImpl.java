package com.example.final_201930403.service.impl;

import com.example.final_201930403.service.ProductService;
import com.example.final_201930403.dao.ProductDAO;
import com.example.final_201930403.dto.ProductDto;
import com.example.final_201930403.dto.ProductResponseDto;
import com.example.final_201930403.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());
        return productResponseDto;
    }
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changeProduct = productDAO.updateProductName(number, name);
        return new ProductResponseDto(changeProduct);
    }
    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
    @Override
    public List<ProductResponseDto> listAll() {
        List<Product> products = productDAO.listAll();
        List<ProductResponseDto> productResponseDtoList =
                products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }
    @Override
    public List<ProductResponseDto> listOrderByPrice() {
        List<Product> product = productDAO.listOrderByPrice();
        List<ProductResponseDto> productResponseDtoList =
                product.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }
    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        List<Product> product = productDAO.selectProductByName(name);
        List<ProductResponseDto> productResponseDtoList =
                product.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }
    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }
    @Override
    public ProductResponseDto changeProductStock(Long number, int stock) throws Exception {
        Product changeProduct = productDAO.updateProductStock(number, stock);
        return new ProductResponseDto(changeProduct);
    }
    @Override
    public ProductResponseDto changeProduct(Long number, String name, int price, int stock) throws Exception {
        Product changeProduct = productDAO.updateProduct(number, name, price, stock);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());

        return productResponseDto;
    }

}
