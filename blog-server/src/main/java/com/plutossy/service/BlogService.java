package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Blog;

import java.util.List;

// 博客Service接口
public interface BlogService {
    // 添加博客
    /**
     * @param title
     * @param content
     * @param description
     * @param picture
     * @param flag
     * @param recommend
     * @param published
     * @param commentabled
     * @param shared
     * @param typeId
     * @param userId
     * @return
     */
    public Boolean insertBlog(Long id, String title,String content,String description,String picture,Integer flag,Boolean recommend,Integer published,Boolean commentabled,Boolean shared,Long typeId,Long userId);

    /**
     * @param id
     * @return
     */
    public Boolean deleteBlogById(Long id);

    /**
     * @param id
     * @param title
     * @param content
     * @param description
     * @param picture
     * @param flag
     * @param recommend
     * @param published
     * @param commentabled
     * @param shared
     * @param typeId
     * @param userId
     * @return
     */
    public Boolean updateBlog(Long id, String title,String content,String description,String picture,Integer flag,Boolean recommend,Integer published,Boolean commentabled,Boolean shared,Long typeId,Long userId);

    /**
     * @param id
     * @return
     */
    public Blog selectBlogById(Long id);

    /**
     * @param published
     * @return
     */
    public PageInfo<Blog> selectBlogByPublished(Integer pageNum, Integer pageSize, Integer published);

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    public PageInfo<Blog> selectBlogByQuery(Integer pageNum, Integer pageSize, String queryParam);

    /**
     * @param id
     * @param recommend
     * @return
     */
    public Boolean updateBlogRecommend(Long id, Boolean recommend);

    /**
     * @param id
     * @param commentabled
     * @return
     */
    public Boolean updateBlogCommentabled(Long id, Boolean commentabled);

    /**
     * @param id
     * @param shared
     * @return
     */
    public Boolean updateBlogShared(Long id, Boolean shared);
}
