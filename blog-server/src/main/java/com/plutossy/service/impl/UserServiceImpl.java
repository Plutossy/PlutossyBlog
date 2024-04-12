package com.plutossy.service.impl;

import com.github.pagehelper.PageInfo;
import com.plutossy.dao.UserMapper;
import com.plutossy.domain.User;
import com.plutossy.service.UserService;
import com.plutossy.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> selectUserByIdAndType(Long id, Boolean type) {
        return userMapper.selectUserByIdAndType(id, type);
    }
}
