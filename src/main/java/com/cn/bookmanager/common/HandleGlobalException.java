package com.cn.bookmanager.common;



import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * 极简实现：捕获所有异常，统一返回错误响应
 */
@RestControllerAdvice // 全局拦截@RestController的异常，自动返回JSON
public class HandleGlobalException {

    /**
     * 捕获所有未手动处理的异常（兜底处理）
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleGlobalException(Exception e) {
        // 打印异常栈（便于排查问题）
        e.printStackTrace();
        // 返回标准化错误响应：500码 + 异常提示
        return Result.error("服务器内部错误：" + e.getMessage());
    }

    /**
     * 可选：针对特定异常（如参数校验异常）做精细化处理（按需添加）
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgException(IllegalArgumentException e) {
        return Result.error("参数错误：" + e.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return Result.error("请求参数格式错误");
    }


}
