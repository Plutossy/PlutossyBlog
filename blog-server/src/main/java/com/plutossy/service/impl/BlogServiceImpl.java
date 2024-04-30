package com.plutossy.service.impl;

import com.github.pagehelper.PageInfo;
import com.plutossy.dao.BlogMapper;
import com.plutossy.dao.BlogTagMapper;
import com.plutossy.domain.Blog;
import com.plutossy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 博客service实现类
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;

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
     * @param views
     * @param typeId
     * @param tagIds
     * @param userId
     * @return
     */
    @Override
    public Boolean insertBlog(Long id, String title, String content, String description, String picture, Integer flag, Boolean recommend, Integer published, Boolean commentabled, Boolean shared, Integer views, Long typeId, Long[] tagIds, Long userId) {
        if (published == 0) {
            PageInfo<Blog> pageData = blogMapper.selectBlogByPublished(1, -1, 0);
            boolean dataFlag = pageData.getList().isEmpty();
            if (dataFlag) {
                blogMapper.insertBlog(title, content, description, picture, flag, recommend, published, commentabled, shared, typeId, userId);
                return true;
            } else {
                Long cid = pageData.getList().getFirst().getId();
                return blogMapper.updateBlog(cid, title, content, description, picture, flag, recommend, published, commentabled, shared, views, typeId, userId) > 0;
            }
        }
        if (published == 1 && id == -1) {
            boolean inBFlag = blogMapper.insertBlog(title, content, description, picture, flag, recommend, published, commentabled, shared, typeId, userId) > 0;
            // 获取这条新增数据的id
            Blog blog = blogMapper.selectBlogByTPC(title, content, published, userId);
            Long blogId = blog.getId();
            // 循环插入博客-标签
            for (Long tagId : tagIds) {
                inBFlag = blogTagMapper.insertBlogTag(blogId, tagId)>0;
            }
            return inBFlag;
        }
        if (published == 1) {
            boolean upBFlag = blogMapper.updateBlog(id, title, content, description, picture, flag, recommend, published, commentabled, shared, views, typeId, userId) > 0;
            // 删除博客-标签
            blogTagMapper.deleteBlogTagByBlogId(id);
            // 循环插入博客-标签
            for (Long tagId : tagIds) {
                upBFlag = blogTagMapper.insertBlogTag(id, tagId) > 0;
            }
            return upBFlag;
        }
        if (published == 2 && id == -1) {
            boolean inBFlag = blogMapper.insertBlog(title, content, description, picture, flag, recommend, published, commentabled, shared, typeId, userId) > 0;
            // 获取这条新增数据的id
            Blog blog = blogMapper.selectBlogByTPC(title, content, published, userId);
            Long blogId = blog.getId();
            // 循环插入博客-标签
            for (Long tagId : tagIds) {
                inBFlag = blogTagMapper.insertBlogTag(blogId, tagId) > 0;
            }
            return inBFlag;
        }
        if (published == 2) {
            boolean upBFlag = blogMapper.updateBlog(id, title, content, description, picture, flag, recommend, published, commentabled, shared, views, typeId, userId) > 0;
            // 删除博客-标签
            blogTagMapper.deleteBlogTagByBlogId(id);
            // 循环插入博客-标签
            for (Long tagId : tagIds) {
                upBFlag = blogTagMapper.insertBlogTag(id, tagId) > 0;
            }
            return upBFlag;
        }
        return false;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteBlogById(Long id) {
        return blogMapper.deleteBlogById(id) > 0;
    }

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
     * @param views
     * @param typeId
     * @param userId
     * @return
     */
    @Override
    public Boolean updateBlog(Long id, String title, String content, String description, String picture, Integer flag, Boolean recommend, Integer published, Boolean commentabled, Boolean shared, Integer views, Long typeId, Long userId) {
        return blogMapper.updateBlog(id, title, content, description, picture, flag, recommend, published, commentabled, shared, views, typeId, userId) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Blog selectBlogById(Long id) {
        return blogMapper.selectBlogById(id);
    }

    /**
     * @param published
     * @return
     */
    @Override
    public PageInfo<Blog> selectBlogByPublished(Integer pageNum, Integer pageSize,Integer published) {
        return blogMapper.selectBlogByPublished(pageNum, pageSize, published);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    @Override
    public PageInfo<Blog> selectBlogByQuery(Integer pageNum, Integer pageSize, String queryParam) {
        Integer notPublished = 0;
        return blogMapper.selectBlogByQuery(pageNum, pageSize, queryParam, notPublished);
    }

    /**
     * @param id
     * @param recommend
     * @return
     */
    @Override
    public Boolean updateBlogRecommend(Long id, Boolean recommend) {
        return blogMapper.updateBlogRecommend(id, recommend) > 0;
    }

    /**
     * @param id
     * @param commentabled
     * @return
     */
    @Override
    public Boolean updateBlogCommentabled(Long id, Boolean commentabled) {
        return blogMapper.updateBlogCommentabled(id, commentabled) > 0;
    }

    /**
     * @param id
     * @param shared
     * @return
     */
    @Override
    public Boolean updateBlogShared(Long id, Boolean shared) {
        return blogMapper.updateBlogShared(id, shared) > 0;
    }
}
