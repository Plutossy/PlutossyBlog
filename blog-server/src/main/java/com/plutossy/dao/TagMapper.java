package com.plutossy.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper {
    // 添加标签
    /**
     * @param name
     * @return
     */
    public int insertTag(String name);

    // 删除标签
    /**
     * @param id
     * @return
     */
    public int deleteTagById(Long id);

    // 修改标签
    /**
     * @param id
     * @param name
     * @return
     */
    public int updateTag(Long id, String name);

    // 根据主键id查询标签
    /**
     * @param id
     * @return
     */
    public Tag selectTagById(Long id);

    // 查询所有标签
    /**
     * @return
     */
    @Select("select * from t_tag")
    public List<Tag> selectAllTagByCondition();
    default PageInfo<Tag> selectAllTag(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> tags = selectAllTagByCondition();
        return new PageInfo<>(tags);
    }
    // 根据标签名称模糊查询标签
    /**
     * @param name
     * @return
     */
    @Select("select * from t_tag where name like CONCAT('%', #{name}, '%')")
    public List<Tag> selectTagByNameByCondition(String name);
    default PageInfo<Tag> selectTagByName(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> tags = selectTagByNameByCondition(name);
        return new PageInfo<>(tags);
    };

    public Integer selectNameCount(String name);
}
