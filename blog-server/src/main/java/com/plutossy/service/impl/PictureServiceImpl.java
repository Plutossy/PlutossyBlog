package com.plutossy.service.impl;

import com.github.pagehelper.PageInfo;
import com.plutossy.dao.PictureMapper;
import com.plutossy.domain.Picture;
import com.plutossy.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 照片service实现类
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    /**
     * @param url
     * @param name
     * @param description
     * @return
     */
    @Override
    public Boolean insertPicture(String url,String name,  String description) {
        return pictureMapper.insertPicture(url, name, description) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deletePictureById(Long id) {
        return pictureMapper.deletePictureById(id) > 0;
    }

    /**
     * @param id
     * @param url
     * @param name
     * @param description
     * @return
     */
    @Override
    public Boolean updatePicture(Long id, String url,String name,  String description) {
        return pictureMapper.updatePicture(id, url, name, description) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Picture selectPictureById(Long id) {
        return pictureMapper.selectPictureById(id);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    @Override
    public PageInfo<Picture> selectPictureByQuery(Integer pageNum, Integer pageSize, String queryParam) {
        return pictureMapper.selectPictureByQuery(pageNum, pageSize, queryParam);
    }
}
