package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.plutossy.utils.PageDefault;
import com.plutossy.domain.User;
import com.plutossy.service.UserService;
import com.plutossy.utils.Consts;
import com.plutossy.utils.JwtUtil;
import com.plutossy.utils.MD5Utils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    /* 判断是否登陆成功 */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody Map<String, Object> jsonData, HttpSession session) {
        String nickname = (String) jsonData.get("nickname");
        String password = (String) jsonData.get("password");
        Long id = userService.verifyPassword(nickname, password);
        JSONObject jsonObject = new JSONObject();
        if (id != null) {
            // 封装返回数据 id，封装到data中
            JSONObject data = new JSONObject();
            data.put(Consts.ID, id);
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "登陆成功！");
            jsonObject.put(Consts.DATA, data);
            // 生成一个有效期为24小时的token
            String token = JwtUtil.generateToken(nickname, password, (long) (60 * 60 * 24));
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

    /* 查询用户信息 */
    @RequestMapping(value = "/manage/userList", method = RequestMethod.POST)
    public Object selectUserInfo(@RequestBody Map<String, Object> jsonData) {
        Long id = Long.valueOf((String) jsonData.get("id"));
        // 通过PageDefault为pageNum和pageSize设置默认值
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        Boolean type = (Boolean) jsonData.get("type");
        JSONObject jsonObject = new JSONObject();
        // 如果type为空或者为false，则只查询当前用户信息
        if (!type) {
            // 查询当前用户信息
            PageInfo<User> pageData = userService.selectUserByIdAndType(pageNum, pageSize, id, false);
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.TOTAL, pageData.getTotal());
            jsonObject.put(Consts.PAGES, pageData.getPages());
            jsonObject.put(Consts.PAGE_NUM, pageData.getPageNum());
            jsonObject.put(Consts.PAGE_SIZE, pageData.getPageSize());
            jsonObject.put(Consts.DATA, pageData.getList());
            return jsonObject;
        } else {
            // 查询所有用户信息
            PageInfo<User> pageData = userService.selectAllUser(pageNum, pageSize);
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.TOTAL, pageData.getTotal());
            jsonObject.put(Consts.PAGES, pageData.getPages());
            jsonObject.put(Consts.PAGE_NUM, pageData.getPageNum());
            jsonObject.put(Consts.PAGE_SIZE, pageData.getPageSize());
            jsonObject.put(Consts.DATA, pageData.getList());
            return jsonObject;
        }
    }

    /* 根据id查询用户信息 */
    @RequestMapping(value = "/manage/userInfoById", method = RequestMethod.GET)
    public Object selectUserInfoById(@RequestParam("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        User userInfo = userService.selectUserById(id);
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "查询成功！");
        jsonObject.put(Consts.DATA, userInfo);
        return jsonObject;
    }

    @RequestMapping(value = "/manage/send-email", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, Object> jsonData) throws MessagingException {
        String email = (String) jsonData.get("email");
        // 生成随机验证码
        String verificationCode = RandomStringUtils.randomNumeric(6);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 配置邮件
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom(email);
        message.setTo(email);
        message.setSubject("Email Verification");
        message.setText("Your PlutoBlog verification code is: " + verificationCode);

        // 发送邮件
        mailSender.send(message.getMimeMessage());

        // 存储验证码到数据库（示例代码，实际实现根据需求）
        // ...

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "发送成功！");
        return ResponseEntity.ok(jsonObject);
    }

    @RequestMapping(value = "/manage/verify-pwd", method = RequestMethod.POST)
    public Object verifyPwd(@RequestBody Map<String, Object> jsonData) {
        String nickname = (String) jsonData.get("nickname");
        String password = (String) jsonData.get("password");
        Long id = userService.verifyPassword(nickname, password);
        JSONObject jsonObject = new JSONObject();
        if (id != null) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "密码正确！");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 400);
        jsonObject.put(Consts.MSG, "密码错误！");
        return jsonObject;
    }

    @RequestMapping(value = "/manage/update-pwd", method = RequestMethod.POST)
    public Object updatePwd(@RequestBody Map<String, Object> jsonData) throws Exception {
        Long id = Long.valueOf((Integer) jsonData.get("id"));
        String password = (String) jsonData.get("password");
        String newPassword = (String) jsonData.get("newPassword");
        String email = (String) jsonData.get("email");
        Boolean flag = userService.updatePwd(newPassword, id, password, email); // MD5Utils加密密码
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "修改成功！");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 400);
        jsonObject.put(Consts.MSG, "修改失败！");
        return jsonObject;
    }
}
