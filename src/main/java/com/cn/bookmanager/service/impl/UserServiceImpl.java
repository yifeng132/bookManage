package com.cn.bookmanager.service.impl;

import com.cn.bookmanager.entity.User;
import com.cn.bookmanager.mapper.UserMapper;
import com.cn.bookmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }
}
