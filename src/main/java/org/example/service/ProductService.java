package org.example.service;


import org.example.model.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByUserId(Long id);
}
