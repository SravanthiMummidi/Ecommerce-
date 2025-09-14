package com.spring.project.service;

import com.spring.project.entity.UserDetail;
import com.spring.project.entity.cart;

import java.util.List;

public interface CartService {


    public  void addItemToCart(String username,Long product_id);


    boolean check(int id, Long productId);


    List<cart> findProductsByUserId(int id);


    void deleteItem(Long itemId);
}
