import request from '@/utils/request';

// ================歌曲相关========================
// 查询所有歌曲
export const getSongList = params => {
  return request({
    url: 'song/getSongList',
    method: 'get',
    params,
  });
};

// 下载音乐
export const downloadSong = params => {
  return request({
    url: 'song/downloadSong',
    method: 'get',
    params,
    responseType: 'blob',
  });
};
