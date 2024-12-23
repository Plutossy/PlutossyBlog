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

    // 注册
    public Integer register(User user);

    // 查询所有用户nickname
    public List<String> selectAllNickname();

    // 查询所有用户信息
    @Select("select " + Base_Columns + " from t_user where (nickname like CONCAT('%', #{queryParam}, '%')) or (username like CONCAT('%', #{queryParam}, '%')) order by create_time")
    public List<User> selectAllUserByCondition(String queryParam);
    default PageInfo<User> selectAllUser(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("queryParam") String queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = selectAllUserByCondition(queryParam);
        return new PageInfo<>(users);
    }

    // 根据用户id、queryParam和type查询用户信息
    @Select("select " + Base_Columns + " from t_user where id = #{id} and type = #{type} and ((nickname like CONCAT('%', #{queryParam}, '%')) or (username like CONCAT('%', #{queryParam}, '%'))) order by create_time")
    public List<User> selectUserByQueryByIdAndTypeByCondition(Long id,String queryParam, Boolean type);
    default PageInfo<User> selectUserByQueryByIdAndType(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("id") Long id, @Param("queryParam") String queryParam, @Param("type") Boolean type) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = selectUserByQueryByIdAndTypeByCondition(id, queryParam, type);
        return new PageInfo<>(users);
    }

    // 根据用户id查询用户信息
    public User selectUserById(Long id);

    // 查询验证码
    public String selectCaptcha(Long id, String email);

    // 更新验证码
    public Integer updateCaptcha(String captcha, Long id, String email);

    // 更新密码
    public Integer updatePwd(String newPassword, Long id, String password, String email, String captcha);

    // 更新用户信息
    public Integer updateUser(User user);

    // 新增用户
    public Integer insertUser(User user);

    // 删除用户
    public Integer deleteUserById(Long id);
}
