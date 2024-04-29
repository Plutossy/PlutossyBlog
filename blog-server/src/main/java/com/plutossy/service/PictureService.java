package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Picture;

// 图片Service接口
public interface PictureService {
    /**
     * @param url
     * @param name
     * @param description
     * @return
     */
    public Boolean insertPicture(String url, String name, String description);

    /**
     * @param id
     * @return
     */
    public Boolean deletePictureById(Long id);

    /**
     * @param id
     * @param url
     * @param name
     * @param description
     * @return
     */
    public Boolean updatePicture(Long id, String url, String name, String description);

    /**
     * @param id
     * @return
     */
    public Picture selectPictureById(Long id);

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    public PageInfo<Picture> selectPictureByQuery(Integer pageNum, Integer pageSize, String queryParam);

}
