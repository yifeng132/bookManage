package com.cn.bookmanager.service;



import com.cn.bookmanager.domain.dto.LoginDto;
import com.cn.bookmanager.domain.entity.User;




public interface UserService {

    public User login(LoginDto dto);
}
