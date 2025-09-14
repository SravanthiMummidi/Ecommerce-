package com.spring.project.service;

import com.spring.project.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> findAll();
    public Optional<Category> findById(Long id);
    public Category save(Category category);
    public void delete(Long id);

    
}
