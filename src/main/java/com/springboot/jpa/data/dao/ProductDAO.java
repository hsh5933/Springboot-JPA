package com.springboot.jpa.data.dao;

import com.springboot.jpa.data.entity.Product;

public interface ProductDAO {
    //CRUD를 다루기위해 메서드를 정의

    //create
    Product insertProduct(Product product);

    //read
    Product selectProduct(Long number);

    //update
    Product updateProductName(Long number, String name) throws Exception;

    //delete
    public void deleteProduct(Long number) throws Exception;
}
