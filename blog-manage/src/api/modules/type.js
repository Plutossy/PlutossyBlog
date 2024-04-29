/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-26 09:50:42
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-28 18:08:33
 * @FilePath: \blog-manage\src\api\modules\type.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function selectTypeList(data) {
  return request({
    url: '/manage/typeList',
    method: 'post',
    data,
  });
}

export function selectTypeByQuery(data) {
  return request({
    url: '/manage/selectTypeByQuery',
    method: 'post',
    data,
  });
}

export function selectTypeById(params) {
  return request({
    url: '/manage/selectTypeById',
    method: 'get',
    params,
  });
}

export function addType(data) {
  return request({
    url: '/manage/addType',
    method: 'post',
    data,
  });
}

export function deleteType(data) {
  return request({
    url: '/manage/deleteType',
    method: 'post',
    data,
  });
}

export function deleteTypes(data) {
  return request({
    url: '/manage/deleteTypes',
    method: 'post',
    data,
  });
}

export function updateType(data) {
  return request({
    url: '/manage/updateType',
    method: 'post',
    data,
  });
}
