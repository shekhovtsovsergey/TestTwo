package org.example.dao;

import org.example.model.Products;

import java.util.List;

public interface ProductDao {

    void createProduct(Products product);
    Products getProductById(Long id);
    List<Products> getAllProducts();
    void updateProduct(Products product);
    void deleteProduct(Long id);
}
