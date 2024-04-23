package com.plutossy.service.impl;

import com.github.pagehelper.PageInfo;
import com.plutossy.dao.TypeMapper;
import com.plutossy.domain.Type;
import com.plutossy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类型service实现类
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    /**
     * @param name
     * @return
     */
    @Override
    public Boolean insertType(String name) {
        return typeMapper.insertType(name) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteTypeById(Long id) {
        return typeMapper.deleteTypeById(id) > 0;
    }

    /**
     * @param id
     * @param name
     * @return
     */
    @Override
    public Boolean updateType(Long id, String name) {
        return typeMapper.updateType(id, name) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Type selectTypeById(Long id) {
        return typeMapper.selectTypeById(id);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Type> selectAllType(Integer pageNum, Integer pageSize) {
        return typeMapper.selectAllType(pageNum, pageSize);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageInfo<Type> selectTypeByName(Integer pageNum, Integer pageSize, String name) {
        return typeMapper.selectTypeByName(pageNum, pageSize, name);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Boolean selectNameCount(String name) {
        return typeMapper.selectNameCount(name) > 0;
    }
}
