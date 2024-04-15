package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.User;

import java.util.List;

// 用户Service接口
public interface UserService {
    //    验证用户密码是否正确
    public Long verifyPassword(String nickname, String password);

    //    查询所有用户信息
    public PageInfo<User> selectAllUser(Integer pageNum, Integer pageSize);

    //    根据用户id和type查询用户信息
    public PageInfo<User> selectUserByIdAndType(Integer pageNum, Integer pageSize, Long id, Boolean type);

    //    根据用户id查询用户信息
    public User selectUserById(Long id);

    //    更新验证码
    public Boolean updateCaptcha(String captcha, Long id, String email);

    //    更新密码
    public Boolean updatePwd(String newPassword, Long id, String password, String email, String captcha);
}
