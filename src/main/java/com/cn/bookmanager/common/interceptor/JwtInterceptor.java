package com.cn.bookmanager.common.interceptor;

import com.cn.bookmanager.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//JWT 拦截器（鉴权核心）
//作用：所有需要登录的接口，先校验 token
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 从请求头取 token：约定 header 名为 Authorization
        String token = request.getHeader("Authorization");

        // 2. 处理Bearer前缀：如果有就去掉
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉"Bearer "（注意有空格，长度固定7）
        }

        // 没有 token → 未登录
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"未登录\"}");
            return false;
        }

        // token 无效/过期
        if (!jwtUtil.validateToken(token)) {
            // 新增：打印具体失败原因
            try {
                jwtUtil.getUsernameFromToken(token); // 主动解析，触发具体异常
            } catch (Exception e) {
                System.err.println("Token校验失败详情：" + e.getMessage());
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"token无效或过期\"}");
            return false;
        }

        // 放行
        return true;
    }
}
