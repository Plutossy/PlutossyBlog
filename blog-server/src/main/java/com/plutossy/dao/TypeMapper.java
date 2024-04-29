package com.plutossy.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TypeMapper {
    // 添加类型
    /**
     * @param name
     * @return
     */
    public int insertType(String name);

    // 删除类型
    /**
     * @param id
     * @return
     */
    public int deleteTypeById(Long id);

    // 修改类型
    /**
     * @param id
     * @param name
     * @return
     */
    public int updateType(Long id, String name);

    // 根据主键id查询类型
    /**
     * @param id
     * @return
     */
    public Type selectTypeById(Long id);

    // 根据类型名称模糊查询类型
    /**
     * @param name
     * @return
     */
    @Select("select * from t_type where name like CONCAT('%', #{name}, '%')")
    public List<Type> selectTypeByQueryByCondition(String name);
    default PageInfo<Type> selectTypeByQuery(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> types = selectTypeByQueryByCondition(name);
        return new PageInfo<>(types);
    };

    public Integer selectNameCount(String name);
}
