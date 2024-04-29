/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-23 10:17:00
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-28 18:17:34
 * @FilePath: \blog-manage\src\api\modules\tag.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function selectTagByQuery(data) {
  return request({
    url: '/manage/selectTagByQuery',
    method: 'post',
    data,
  });
}

export function selectTagById(params) {
  return request({
    url: '/manage/selectTagById',
    method: 'get',
    params,
  });
}

export function addTag(data) {
  return request({
    url: '/manage/addTag',
    method: 'post',
    data,
  });
}

export function deleteTag(data) {
  return request({
    url: '/manage/deleteTag',
    method: 'post',
    data,
  });
}

export function deleteTags(data) {
  return request({
    url: '/manage/deleteTags',
    method: 'post',
    data,
  });
}

export function updateTag(data) {
  return request({
    url: '/manage/updateTag',
    method: 'post',
    data,
  });
}
