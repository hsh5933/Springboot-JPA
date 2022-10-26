package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    //데이터베이스에 접근하기위해 리포지토리 인터페이스사용 의존성주입
    //생성자를 통해
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.findById(number).orElse(null);

        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        //id값으로 selectedProduct에 객체 찾아넣는다.
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        //isPresent메서드 = 객체가 값을 가지고있다면 true 아니면 false
        if(selectedProduct.isPresent()){
            //product에 가져온 객체를 넣는다
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            productRepository.delete(product);
        }else{
            throw new Exception();
        }
    }
}
