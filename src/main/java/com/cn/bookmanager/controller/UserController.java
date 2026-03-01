package com.cn.bookmanager.controller;



import com.cn.bookmanager.common.Result;
import com.cn.bookmanager.entity.User;
import com.cn.bookmanager.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String username,
                              @RequestParam String password) {
        User user = userService.login(username, password);
        if (user == null) {
            return Result.error("账号或密码错误");
        }
        return Result.success(user);
    }
}
