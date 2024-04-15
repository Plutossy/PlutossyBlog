package com.plutossy.utils;

import com.plutossy.domain.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String SECRET = "pluto_blog_ssy_token";

    public static String generateToken(String nickname, String password, Long expiration) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + expiration * 1000); // System.currentTimeMillis() + expiration * 1000

        JwtBuilder builder = Jwts.builder()
                //header -- 设置头
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")//加密算法

                //payload----载荷
                .claim("nickname", nickname)
                .claim("password", MD5Utils.code(password)) // MD5加密后的密码
//                //主题
//                .setSubject("weifeng-test")
                //有效时间
                .setExpiration(expireTime)//从当前开始+有效时间
                //jwt id
                .setId(UUID.randomUUID().toString()) // 生成一个随机的UUID
                //签名 signature
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .setIssuedAt(now); // 签发时间
        return builder.compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

