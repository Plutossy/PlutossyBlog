package com.plutossy.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "pluto_blog_ssy_token";

    public static String generateToken(String nickname, Long expiration) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + expiration * 1000);

        JwtBuilder builder = Jwts.builder()
                .setId(nickname)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, SECRET);
        return builder.compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

