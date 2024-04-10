/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-17 16:14:03
 * @LastEditors: sunshuangyin sunshuangyin@seewintech.com
 * @LastEditTime: 2024-04-10 11:02:03
 * @FilePath: \blog-manage\src\api\http.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';
// ================歌曲相关========================
// 查询所有歌曲
export const getSongList = () => get(`song/getSongList`);

// 下载音乐
export const downloadSong = url =>
  request({
    method: 'get',
    url: url,
    responseType: 'blob',
  });
