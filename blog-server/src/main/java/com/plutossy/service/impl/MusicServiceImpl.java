package com.plutossy.service.impl;

import com.github.pagehelper.PageInfo;
import com.plutossy.dao.MusicMapper;
import com.plutossy.domain.Music;
import com.plutossy.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 音乐service实现类
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;

    /**
     * @param name
     * @param url
     * @param singer
     * @param lyric
     * @return
     */
    @Override
    public Boolean insertMusic(String name, String url, String singer, String lyric) {
        String img = "http://localhost:8080/img/music.jpg";
        return musicMapper.insertMusic(name, url, singer, img, lyric) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteMusicById(Long id) {
        return musicMapper.deleteMusicById(id) > 0;
    }

    /**
     * @param id
     * @param name
     * @param url
     * @param singer
     * @param lyric
     * @return
     */
    @Override
    public Boolean updateMusic(Long id, String name, String url, String singer, String lyric) {
        return musicMapper.updateMusic(id, name, url, singer, lyric) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Music selectMusicById(Long id) {
        return musicMapper.selectMusicById(id);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Music> selectAllMusic(Integer pageNum, Integer pageSize) {
        return musicMapper.selectAllMusic(pageNum, pageSize);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param queryParam
     * @return
     */
    @Override
    public PageInfo<Music> selectMusicByQuery(Integer pageNum, Integer pageSize, String queryParam) {
        return musicMapper.selectMusicByQuery(pageNum, pageSize, queryParam);
    }
}
