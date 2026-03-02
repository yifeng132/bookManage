package com.cn.bookmanager.controller;



import com.cn.bookmanager.common.Result;
import com.cn.bookmanager.domain.dto.LoginDto;
import com.cn.bookmanager.domain.entity.User;
import com.cn.bookmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@Valid @RequestBody LoginDto dto) {
        User user = userService.login(dto);
        if (user == null) {
            return Result.error("账号或密码错误");
        }
        return Result.success(user);
    }
}
