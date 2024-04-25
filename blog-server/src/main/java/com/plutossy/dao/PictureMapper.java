package com.plutossy.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PictureMapper {
    // 添加图片
    /**
     * @param url
     * @param name
     * @param description
     * @return
     */
    public int insertPicture(String url,String name,String description);

    // 删除图片
    /**
     * @param id
     * @return
     */
    public int deletePictureById(Long id);

    // 修改图片
    /**
     * @param id
     * @param url
     * @param name
     * @param description
     * @return
     */
    public int updatePicture(Long id, String url,String name,String description);

    // 根据主键id查询图片
    /**
     * @param id
     * @return
     */
    public Picture selectPictureById(Long id);

    // 查询所有图片
    /**
     * @return
     */
    @Select("select * from t_picture order by create_time desc")
    public List<Picture> selectAllPictureByCondition();
    default PageInfo<Picture> selectAllPicture(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = selectAllPictureByCondition();
        return new PageInfo<>(pictures);
    }

    // 根据图片名称和歌手名称模糊查询图片
    /**
     * @param queryParam
     * @return
     */
    @Select("select * from t_picture where (name like CONCAT('%', #{queryParam}, '%')) order by create_time desc")
    public List<Picture> selectPictureByQueryByCondition(String queryParam);
    default PageInfo<Picture> selectPictureByQuery(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, String queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = selectPictureByQueryByCondition(queryParam);
        return new PageInfo<>(pictures);
    };

}
