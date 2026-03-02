package com.cn.bookmanager.mapper;



import com.cn.bookmanager.domain.dto.LoginDto;
import com.cn.bookmanager.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByUsernameAndPassword(LoginDto dto);
}
