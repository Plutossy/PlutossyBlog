/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-24 18:23:29
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-24 18:24:26
 * @FilePath: \blog-manage\src\api\modules\music.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function selectMusicList(data) {
  return request({
    url: '/manage/musicList',
    method: 'post',
    data,
  });
}

export function selectMusicByQuery(data) {
  return request({
    url: '/manage/selectMusicByQuery',
    method: 'post',
    data,
  });
}

export function selectMusicById(params) {
  return request({
    url: '/manage/selectMusicById',
    method: 'get',
    params,
  });
}

export function addMusic(data) {
  return request({
    url: '/manage/addMusic',
    method: 'post',
    data,
  });
}

export function deleteMusic(data) {
  return request({
    url: '/manage/deleteMusic',
    method: 'post',
    data,
  });
}

export function deleteMusics(data) {
  return request({
    url: '/manage/deleteMusics',
    method: 'post',
    data,
  });
}

export function updateMusic(data) {
  return request({
    url: '/manage/updateMusic',
    method: 'post',
    data,
  });
}
