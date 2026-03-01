package com.cn.bookmanager.service;



import com.cn.bookmanager.entity.User;




public interface UserService {

    public User login(String username, String password);
}
