/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 11:16:32
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-29 09:03:55
 * @FilePath: \blog-manage\src\api\modules\user.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data,
  });
}

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data,
  });
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get',
  });
}

export function getUserInfo(params) {
  return request({
    url: '/manage/userInfoById',
    method: 'get',
    params,
  });
}

export function sendEmail(data) {
  return request({
    url: '/manage/send-email',
    method: 'post',
    data,
  });
}

export function verifyPwd(data) {
  return request({
    url: '/manage/verify-pwd',
    method: 'post',
    data,
  });
}

export function updatePwd(data) {
  return request({
    url: '/manage/update-pwd',
    method: 'post',
    data,
  });
}

export function editUser(data) {
  return request({
    url: '/manage/updateUser',
    method: 'post',
    data,
  });
}

export function addUser(data) {
  return request({
    url: '/manage/insertUser',
    method: 'post',
    data,
  });
}

export function deleteUser(data) {
  return request({
    url: '/manage/deleteUser',
    method: 'post',
    data,
  });
}

export function deleteUsers(data) {
  return request({
    url: '/manage/deleteUsers',
    method: 'post',
    data,
  });
}

export function selectUserByQuery(data) {
  return request({
    url: '/manage/selectUserByQuery',
    method: 'post',
    data,
  });
}
