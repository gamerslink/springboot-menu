package com.liaojacky.springbootmenu.service;


import com.liaojacky.springbootmenu.dto.UserLoginRequest;
import com.liaojacky.springbootmenu.dto.UserRegisterRequest;
import com.liaojacky.springbootmenu.model.User;

public interface UserService {

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

    Integer register(UserRegisterRequest userRegisterRequest);
}
