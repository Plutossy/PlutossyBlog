package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.plutossy.service.UserService;
import com.plutossy.utils.Consts;
import com.plutossy.utils.JwtUtil;
import com.plutossy.utils.MD5Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /* 判断是否登陆成功 */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        boolean flag = userService.verifyPassword(nickname, password);
        if (flag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "登陆成功！");
            session.setAttribute(Consts.NICKNAME, nickname);
            // 生成一个有效期为24小时的token
            String token = JwtUtil.generateToken(nickname, MD5Utils.code(password), (long) (60*60*24));
            jsonObject.put(Consts.TOKEN, token);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 400);
        jsonObject.put(Consts.MSG, "用户名或密码错误！");
        return jsonObject;
    }
}
