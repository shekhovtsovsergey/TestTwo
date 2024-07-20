package org.example.service;

import org.example.dao.ProductDao;
import org.example.model.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProduct(Products product) {
        productDao.createProduct(product);
    }

    @Override
    public Products getProductById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Products> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void updateProduct(Products product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
