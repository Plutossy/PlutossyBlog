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
import org.springframework.web.bind.annotation.RequestParam;
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
            // 生成一个有效期为24小时的token
            String token = JwtUtil.generateToken(nickname, MD5Utils.code(password), (long) (60*60*24));
            jsonObject.put(Consts.TOKEN, token);
            session.setAttribute(Consts.NICKNAME, nickname);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 400);
        jsonObject.put(Consts.MSG, "用户名或密码错误！");
        return jsonObject;
    }

    /* 退出登录 */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(HttpSession session) {
        session.removeAttribute(Consts.NICKNAME);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "退出成功！");
        return jsonObject;
    }

    /* 查询所有用户信息 */
    @RequestMapping(value = "/manage/allUserInfo", method = RequestMethod.POST)
    public Object selectAllUser(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        // 如果type为空或者为false，则只查询当前用户信息
        if (!Boolean.parseBoolean(type) || type == null || "".equals(type)) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.DATA, userService.selectUserByIdAndType(Long.parseLong(id), Boolean.parseBoolean(type)));
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.TOTAL, userService.selectAllUser(pageNum, pageSize).getTotal());
            jsonObject.put(Consts.PAGES, userService.selectAllUser(pageNum, pageSize).getPages());
            jsonObject.put(Consts.PAGE_NUM, userService.selectAllUser(pageNum, pageSize).getPageNum());
            jsonObject.put(Consts.PAGE_SIZE, userService.selectAllUser(pageNum, pageSize).getPageSize());
            jsonObject.put(Consts.DATA, userService.selectAllUser(pageNum, pageSize).getList());
            return jsonObject;
        }
    }
}
