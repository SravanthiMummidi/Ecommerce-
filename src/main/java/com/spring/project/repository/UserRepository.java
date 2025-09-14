package com.spring.project.repository;

import com.spring.project.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDetail,Integer> {
    public boolean existsByEmail(String email);
    public UserDetail findByEmail(String email);


    @Query("SELECT u from UserDetail u where u.email=:email")
    UserDetail findByUserEmail(String email);
}
