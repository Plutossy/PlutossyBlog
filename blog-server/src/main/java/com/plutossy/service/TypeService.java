package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Type;

// 类型Service接口
public interface TypeService {
    /**
     * @param name
     * @return
     */
    public Boolean insertType(String name);

    /**
     * @param id
     * @return
     */
    public Boolean deleteTypeById(Long id);

    /**
     * @param id
     * @param name
     * @return
     */
    public Boolean updateType(Long id, String name);

    /**
     * @param id
     * @return
     */
    public Type selectTypeById(Long id);

    /**
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    public PageInfo<Type> selectTypeByQuery(Integer pageNum, Integer pageSize, String name);

    /**
     * @param name
     * @return
     */
    public Boolean selectNameCount(String name);
}
