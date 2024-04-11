package com.plutossy.dao;

import org.apache.ibatis.annotations.Mapper;
//import org.springframework.stereotype.Repository;

// 用户Dao
//@Repository
@Mapper
public interface UserMapper {
    // 验证用户密码是否正确
    public int verifyPassword(String nickname, String password);
}
