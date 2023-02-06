package com.liaojacky.springbootmenu.dao;


import com.liaojacky.springbootmenu.dto.UserRegisterRequest;
import com.liaojacky.springbootmenu.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
