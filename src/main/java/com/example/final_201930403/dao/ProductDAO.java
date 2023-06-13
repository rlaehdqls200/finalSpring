package com.example.final_201930403.dao;

import com.example.final_201930403.entity.Product;

import java.util.List;


public interface ProductDAO {

    Product insertProduct(Product product);
    Product updateProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;
    List<Product> listAll();
    List<Product> listOrderByPrice();
    List<Product> selectProductByName(String name);
    Product selectProduct(Long number);
    Product updateProduct(Long number, String name, int price, int stock) throws Exception;
    Product updateProductStock(Long number, int stock) throws Exception;
}
