package com.plutossy.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//import org.springframework.stereotype.Repository;

// 用户Dao
//@Repository
@Mapper
public interface UserMapper {
    String Base_Columns = "id, nickname, avatar, username, type, create_time, update_time, sex, phone, email, github, csdn, qq, wechat, birth, address, introduction";

    // 验证用户密码是否正确
    public Long verifyPassword(String nickname, String password);

    // 查询所有用户信息
    @Select("select " + Base_Columns + " from t_user order by create_time")
    public List<User> selectAllUserByCondition();
    default PageInfo<User> selectAllUser(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = selectAllUserByCondition();
        return new PageInfo<>(users);
    }

    // 根据用户id和type查询用户信息
    @Select("select " + Base_Columns + " from t_user where id = #{id} and type = #{type} order by create_time")
    public List<User> selectAllUserByIdAndTypeByCondition(Long id, Boolean type);
    default PageInfo<User> selectUserByIdAndType(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("id") Long id, @Param("type") Boolean type) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = selectAllUserByIdAndTypeByCondition(id, type);
        return new PageInfo<>(users);
    }

    // 根据用户id查询用户信息
    public User selectUserById(Long id);

    // 更新密码
    public Integer updatePwd(String newPassword, Long id, String password, String email);
}
