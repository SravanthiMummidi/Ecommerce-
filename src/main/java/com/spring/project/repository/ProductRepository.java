package com.spring.project.repository;

import com.spring.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p from Product p where p.id=:id")
    public Product findByProductId(@Param("id") Long id);
}
