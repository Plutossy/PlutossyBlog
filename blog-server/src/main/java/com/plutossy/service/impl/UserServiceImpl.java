package com.plutossy.service.impl;

import com.plutossy.dao.UserMapper;
import com.plutossy.service.UserService;
import com.plutossy.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 用户service实现类
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 验证密码是否正确
     *
     * @param nickname
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String nickname, String password) {
        return userMapper.verifyPassword(nickname, MD5Utils.code(password)) > 0;
    }
}
