package com.spring.project.service;

import com.spring.project.entity.UserDetail;

public interface UserService {
    public UserDetail createUser(UserDetail userDetail);
    public boolean checkEmail(String email);
}
