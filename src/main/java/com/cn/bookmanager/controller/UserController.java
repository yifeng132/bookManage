package com.cn.bookmanager.controller;



import com.cn.bookmanager.common.Result;
import com.cn.bookmanager.common.util.JwtUtil;
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
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<String> login(@Valid @RequestBody LoginDto dto) {
        User user = userService.login(dto);

        // 2. 判断用户是否存在 + 密码是否正确（真实项目要加密：BCrypt）
        if (user == null || !user.getPassword().equals(dto.getPassword())) {
            return Result.error("账号或密码错误");
        }

        // 3. 生成 JWT token 返回
        String token = jwtUtil.generateToken(user.getUsername());

        System.out.println("生成的Token：" + token); // 新增打印
        return Result.success(token);
    }
}
