package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Blog;

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
    public Boolean insertBlog(String title,String content,String description,String picture,Integer flag,Boolean recommend,Integer published,Boolean commentabled,Boolean shared,Long typeId,Long userId);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Blog> selectAllBlog(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    public PageInfo<Blog> selectBlogByQuery(Integer pageNum, Integer pageSize, String queryParam);
}
