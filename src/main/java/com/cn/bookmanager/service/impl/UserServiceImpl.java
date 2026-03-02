package com.cn.bookmanager.service.impl;

import com.cn.bookmanager.domain.dto.LoginDto;
import com.cn.bookmanager.domain.entity.User;
import com.cn.bookmanager.mapper.UserMapper;
import com.cn.bookmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginDto dto) {
        return userMapper.selectByUsernameAndPassword(dto);
    }
}
