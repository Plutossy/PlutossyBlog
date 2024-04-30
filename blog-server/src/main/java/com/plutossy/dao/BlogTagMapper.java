package com.plutossy.dao;

import com.plutossy.domain.BlogTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogTagMapper {
    // 添加博客-标签
    /**
     * @param blogId
     * @param tagId
     * @return
     */
    public int insertBlogTag(Long blogId, Long tagId);

    // 根据博客id删除博客标签
    /**
     * @param blogId
     * @return
     */
    public int deleteBlogTagByBlogId(Long blogId);

    // 根据标签id删除博客标签
    /**
     * @param tagId
     * @return
     */
    public int deleteBlogTagByTagId(Long tagId);

    // 根据博客id和标签id更新博客标签
    /**
     * @param blogId
     * @return
     */
    public int updateBlogTag(Long blogId, Long tagId);

    // 根据博客id查询博客标签
    /**
     * @param blogId
     * @return
     */
    public List<BlogTag> selectBlogTagByBlogId(Long blogId);

    // 根据标签id查询博客标签
    /**
     * @param tagId
     * @return
     */
    public List<BlogTag> selectBlogTagByTagId(Long tagId);
}
