package com.spring.project.service;

import com.spring.project.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product save(Product product);
    public void delete(Long id);

    public Product finProductById(Long id);
}
