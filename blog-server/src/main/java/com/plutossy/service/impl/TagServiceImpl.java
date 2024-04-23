package com.plutossy.service.impl;

import com.github.pagehelper.PageInfo;
import com.plutossy.dao.TagMapper;
import com.plutossy.domain.Tag;
import com.plutossy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标签service实现类
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    /**
     * @param name
     * @return
     */
    @Override
    public Boolean insertTag(String name) {
        return tagMapper.insertTag(name) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteTagById(Long id) {
        return tagMapper.deleteTagById(id) > 0;
    }

    /**
     * @param id
     * @param name
     * @return
     */
    @Override
    public Boolean updateTag(Long id, String name) {
        return tagMapper.updateTag(id, name) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Tag selectTagById(Long id) {
        return tagMapper.selectTagById(id);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Tag> selectAllTag(Integer pageNum, Integer pageSize) {
        return tagMapper.selectAllTag(pageNum, pageSize);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageInfo<Tag> selectTagByName(Integer pageNum, Integer pageSize, String name) {
        return tagMapper.selectTagByName(pageNum, pageSize, name);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Boolean selectNameCount(String name) {
        return tagMapper.selectNameCount(name) > 0;
    }
}
