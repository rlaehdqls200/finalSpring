package com.example.final_201930403.dao.impl;


import com.example.final_201930403.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.final_201930403.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.example.final_201930403.repository.ProductRepository;
import com.example.final_201930403.repository.QProductRespository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        //memberDao에 sql date를 insert하는 쿼리문을 이 코드로 대체
        return saveProduct;
    }
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number); //타입의 맞게 받을것 여기선 Optional임
        Product updateProduct;
        if(selectProduct.isPresent()){
            // update
            Product product = selectProduct.get();
            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        }else{
            throw  new Exception();
        }
        return updateProduct;
    }
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        // delete
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else throw new Exception();
    }

    @Override
    public List<Product> listAll()  {
        return productRepository.findAllByOrderByNumberAsc();
    }

    @Override
    public List<Product> listOrderByPrice() {
        List<Product> selectProduct =
                productRepository.findAllByOrderByPriceDesc();
        return selectProduct;
    }

    @Override
    public List<Product> selectProductByName(String name) {
        List<Product> selectProduct =
                productRepository.findByName(name, Sort.by(Sort.Order.asc("price")));
        return selectProduct;
    }
    @Override
    public Product selectProduct(Long number) {
        Product selectProduct = productRepository.getReferenceById(number);
        return selectProduct;
    }

    @Override
    public Product updateProduct(Long number, String name, int price, int stock) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updateProduct;

        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUpdatedAt(LocalDateTime.now());

            updateProduct = productRepository.save(product);
        } else throw new Exception();

        return updateProduct;
    }

    @Override
    public Product updateProductStock(Long number, int stock) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        Product updateProduct;

        if(selectProduct.isPresent()) {
            Product product = selectProduct.get();
            product.setStock(stock-1);
            product.setUpdatedAt(LocalDateTime.now());

            updateProduct = productRepository.save(product);
        } else throw new Exception();

        return updateProduct;
    }
}
