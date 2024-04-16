package com.plutossy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.plutossy.dao.UserMapper;
import com.plutossy.domain.User;
import com.plutossy.service.UserService;
import com.plutossy.utils.MD5Utils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// 用户service实现类
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 验证密码是否正确
     *
     * @param nickname
     * @param password
     * @return
     */
    @Override
    public Long verifyPassword(String nickname, String password) {
        return userMapper.verifyPassword(nickname, MD5Utils.code(password));
    }

    /**
     * 查询所有用户信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> selectAllUser(Integer pageNum, Integer pageSize) {
        return userMapper.selectAllUser(pageNum, pageSize);
    }

    /**
     * 根据用户id和type查询用户信息
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public PageInfo<User> selectUserByIdAndType(Integer pageNum, Integer pageSize,Long id, Boolean type) {
        return userMapper.selectUserByIdAndType(pageNum, pageSize, id, type);
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public String selectCaptcha(Long id, String email) {
        return userMapper.selectCaptcha(id, email);
    }

    @Override
    public Boolean updateCaptcha(String captcha, Long id, String email) throws MessagingException{
        User user = userMapper.selectUserById(id);
        if (user == null) {
            return false;
        }
        // 创建邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 配置邮件
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom(email);
        message.setTo(email);
        message.setSubject("PlutoBlog Email 验证码");
        message.setText("你的 PlutoBlog 验证码是: " + captcha+ "，本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n" + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
        // 发送邮件
        mailSender.send(message.getMimeMessage());
        // 设置验证码时效为5分钟
        return userMapper.updateCaptcha(captcha, id, email) > 0;
    }

    @Override
    public Boolean updatePwd(String newPassword, Long id,  String password, String email, String captcha) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            return false;
        }
        return userMapper.updatePwd(MD5Utils.code(newPassword), id, MD5Utils.code(password), email, captcha) > 0;
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user) > 0;
    }
}
