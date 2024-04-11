package com.plutossy.service;

// 用户Service接口
public interface UserService {
    //    验证用户密码是否正确
    public boolean verifyPassword(String nickname, String password);

}
