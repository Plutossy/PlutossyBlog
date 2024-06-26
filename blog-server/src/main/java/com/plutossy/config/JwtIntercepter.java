package com.plutossy.config;

import com.plutossy.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtIntercepter implements HandlerInterceptor {

//    @Autowired
//    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization: " + authorization);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try {
                Claims claims = JwtUtil.parseToken(token);
                request.setAttribute("nickname", claims.get("nickname"));
                System.out.println("nickname: " + claims.get("nickname"));
                return true;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
                return false;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "请求头未携带token！");
            return false;
        }
    }
}

