/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-25 10:14:07
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-25 10:14:31
 * @FilePath: \blog-manage\src\api\modules\picture.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function selectPictureList(data) {
  return request({
    url: '/manage/pictureList',
    method: 'post',
    data,
  });
}

export function selectPictureByQuery(data) {
  return request({
    url: '/manage/selectPictureByQuery',
    method: 'post',
    data,
  });
}

export function selectPictureById(params) {
  return request({
    url: '/manage/selectPictureById',
    method: 'get',
    params,
  });
}

export function addPicture(data) {
  return request({
    url: '/manage/addPicture',
    method: 'post',
    data,
  });
}

export function deletePicture(data) {
  return request({
    url: '/manage/deletePicture',
    method: 'post',
    data,
  });
}

export function deletePictures(data) {
  return request({
    url: '/manage/deletePictures',
    method: 'post',
    data,
  });
}

export function updatePicture(data) {
  return request({
    url: '/manage/updatePicture',
    method: 'post',
    data,
  });
}
