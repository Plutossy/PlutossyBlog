package com.plutossy.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper {
    // 添加歌曲
    /**
     * @param name
     * @param url
     * @param singer
     * @param img
     * @param lyric
     * @return
     */
    public int insertMusic(String name,String url,String singer,String img,String lyric);

    // 删除歌曲
    /**
     * @param id
     * @return
     */
    public int deleteMusicById(Long id);

    // 修改歌曲
    /**
     * @param id
     * @param name
     * @param url
     * @param singer
     * @param img
     * @param lyric
     * @return
     */
    public int updateMusic(Long id, String name, String url, String singer, String img, String lyric);

    // 根据主键id查询歌曲
    /**
     * @param id
     * @return
     */
    public Music selectMusicById(Long id);

    // 查询所有歌曲
    /**
     * @return
     */
    @Select("select * from t_music")
    public List<Music> selectAllMusicByCondition();
    default PageInfo<Music> selectAllMusic(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Music> musics = selectAllMusicByCondition();
        return new PageInfo<>(musics);
    }

    // 根据歌曲名称和歌手名称模糊查询歌曲
    /**
     * @param queryParam
     * @return
     */
    @Select("select * from t_music where (name like CONCAT('%', #{queryParam}, '%')) or (singer like CONCAT('%', #{queryParam}, '%'))")
    public List<Music> selectMusicByQueryByCondition(String queryParam);
    default PageInfo<Music> selectMusicByQuery(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, String queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        List<Music> musics = selectMusicByQueryByCondition(queryParam);
        return new PageInfo<>(musics);
    };

}
