package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Tag;

// 标签Service接口
public interface TagService {
    /**
     * @param name
     * @return
     */
    public Boolean insertTag(String name);

    /**
     * @param id
     * @return
     */
    public Boolean deleteTagById(Long id);

    /**
     * @param id
     * @param name
     * @return
     */
    public Boolean updateTag(Long id, String name);

    /**
     * @param id
     * @return
     */
    public Tag selectTagById(Long id);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Tag> selectAllTag(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    public PageInfo<Tag> selectTagByName(Integer pageNum, Integer pageSize, String name);

    /**
     * @param name
     * @return
     */
    public Boolean selectNameCount(String name);
}
