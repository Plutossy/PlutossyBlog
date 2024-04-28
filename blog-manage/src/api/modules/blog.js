/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-28 11:32:43
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-28 11:43:56
 * @FilePath: \blog-manage\src\api\modules\blog.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function getBlogList(data) {
  return request({
    url: '/manage/blogList',
    method: 'post',
    data,
  });
}

export function selectBlogByQuery(data) {
  return request({
    url: '/manage/selectBlogByQuery',
    method: 'post',
    data,
  });
}

export function selectBlogById(params) {
  return request({
    url: '/manage/selectBlogById',
    method: 'get',
    params,
  });
}

export function addBlog(data) {
  return request({
    url: '/manage/addBlog',
    method: 'post',
    data,
  });
}

export function deleteBlog(data) {
  return request({
    url: '/manage/deleteBlog',
    method: 'post',
    data,
  });
}

export function deleteBlogs(data) {
  return request({
    url: '/manage/deleteBlogs',
    method: 'post',
    data,
  });
}

export function updateBlog(data) {
  return request({
    url: '/manage/updateBlog',
    method: 'post',
    data,
  });
}
