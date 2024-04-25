package com.plutossy.service;

import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Music;

// 歌曲Service接口
public interface MusicService {
    /**
     * @param name
     * @param url
     * @param singer
     * @param lyric
     * @return
     */
    public Boolean insertMusic(String name, String url, String singer, String lyric);

    /**
     * @param id
     * @return
     */
    public Boolean deleteMusicById(Long id);

    /**
     * @param id
     * @param name
     * @param url
     * @param singer
     * @param lyric
     * @return
     */
    public Boolean updateMusic(Long id, String name, String url, String singer, String lyric);

    /**
     * @param id
     * @return
     */
    public Music selectMusicById(Long id);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Music> selectAllMusic(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    public PageInfo<Music> selectMusicByQuery(Integer pageNum, Integer pageSize, String queryParam);

}
