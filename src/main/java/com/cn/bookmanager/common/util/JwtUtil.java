package com.cn.bookmanager.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//功能：生成 token、校验 token、从 token 拿用户信息
@Component
public class JwtUtil {

    // 密钥
    private static final String FIXED_BASE64_KEY = "CdnjhjX9AHFL928Z4ITSxEBAhGsUf32iiT3FQnekjYfajapn4fb8WHhZy1OtY9Ae+DmFcCQmRirGBS6VSSX5Tg==";

    // 3. 转换为 SecretKey 对象
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(FIXED_BASE64_KEY));

    // 加在 SECRET_KEY 定义后，启动时执行
    static {
        String FIXED_BASE64_KEY = "CdnjhjX9AHFL928Z4ITSxEBAhGsUf32iiT3FQnekjYfajapn4fb8WHhZy1OtY9Ae+DmFcCQmRirGBS6VSSX5Tg==";
        byte[] keyBytes = Base64.getDecoder().decode(FIXED_BASE64_KEY);
        System.out.println("密钥字节长度：" + keyBytes.length); // 必须等于64！
    }

    // 过期时间：2小时
    private final long EXPIRATION_TIME = 7200000L;

    // 生成 token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 校验 token 是否合法
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.err.println("Token校验失败：已过期 → " + e.getMessage());
            return false;
        }  catch (io.jsonwebtoken.MalformedJwtException e) {
            System.err.println("Token校验失败：格式错误（非标准JWT/被篡改） → " + e.getMessage());
            return false;
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.err.println("Token校验失败：不支持的格式 → " + e.getMessage());
            return false;
        }  catch (Exception e) {
            System.err.println("Token校验失败：其他异常 → " + e.getMessage());
            return false;
        }
    }

    // 从 token 取出用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
