package com.spring.project.service;

import com.spring.project.entity.Product;
import com.spring.project.entity.UserDetail;
import com.spring.project.entity.cart;
import com.spring.project.repository.CartRepository;
import com.spring.project.repository.ProductRepository;
import com.spring.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProductRepository productRepository;

//    @Override
//    public List<cart> findProductsByUser(String username) {
//        System.out.println("user is :"+username);
//        UserDetail userDetail1=userRepository.findByEmail(username);
//        System.out.println(cartRepository.findByUser(userDetail1));
//        return cartRepository.findByUser(userDetail1);
//    }

    @Override
    public void addItemToCart(String username, Long id) {
        UserDetail user = userRepository.findByEmail(username);
        Product product=productRepository.findByProductId(id);
        cart cartItem = new cart();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartRepository.save(cartItem);
    }

    @Override
    public boolean check(int id, Long productId) {
        return cartRepository.check(id,productId);
    }

    @Override
    public List<cart> findProductsByUserId(int id) {
        return cartRepository.findProductsByUserId(id);
    }

    @Override
    public void deleteItem(Long itemId) {

        Optional<cart> cart=cartRepository.findById(itemId);
        cartRepository.deleteById(itemId);
    }


}
