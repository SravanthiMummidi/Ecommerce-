package com.spring.project.repository;

import com.spring.project.entity.UserDetail;
import com.spring.project.entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<cart, Long> {

    List<cart> findByUser(UserDetail userDetail);


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM cart c WHERE c.user.id = :userId AND c.product.id = :productId")
    boolean check(@Param("userId") int userId, @Param("productId") Long productId);



    @Query("SELECT c from cart c where c.user.id= :id")
    List<cart> findProductsByUserId(int id);


}
