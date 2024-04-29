package com.plutossy.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Blog;
import com.plutossy.domain.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface BlogMapper {
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
    public int insertBlog(String title,String content,String description,String picture,Integer flag,Boolean recommend,Integer published,Boolean commentabled,Boolean shared,Long typeId,Long userId);

    // 删除博客
    /**
     * @param id
     * @return
     */
    public int deleteBlogById(Long id);

    // 修改博客
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
     * @param updateTime
     * @param typeId
     * @param userId
     * @return
     */
    public int updateBlog(Long id, String title, String content, String description, String picture, Integer flag, Boolean recommend, Integer published, Boolean commentabled, Boolean shared, Date updateTime, Long typeId, Long userId);

    // 根据主键id查询博客信息
    /**
     * @param id
     * @return
     */
    public Blog selectBlogById(Long id);

    // 根据博客标题和博客描述模糊查询博客
    /**
     * @param queryParam
     * @return
     */
    @Select("select * from t_blog where (title like CONCAT('%', #{queryParam}, '%')) or (description like CONCAT('%', #{queryParam}, '%')) order by update_time desc")
    public List<Blog> selectBlogByQueryByCondition(String queryParam);
    default PageInfo<Blog> selectBlogByQuery(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, String queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = selectBlogByQueryByCondition(queryParam);
        return new PageInfo<>(blogs);
    };

    // 更新博客推荐状态
    /**
     * @param id
     * @param recommend
     * @return
     */
    public int updateBlogRecommend(Long id, Boolean recommend);

    // 更新博客评论状态
    /**
     * @param id
     * @param commentabled
     * @return
     */
    public int updateBlogCommentabled(Long id, Boolean commentabled);

    // 更新博客分享状态
    /**
     * @param id
     * @param shared
     * @return
     */
    public int updateBlogShared(Long id, Boolean shared);
}
