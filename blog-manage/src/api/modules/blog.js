/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-28 11:32:43
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-29 15:15:18
 * @FilePath: \blog-manage\src\api\modules\blog.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function selectBlogByQuery(data) {
  return request({
    url: '/manage/selectBlogByQuery',
    method: 'post',
    data,
  });
}

export function selectBlogById(data) {
  return request({
    url: '/manage/selectBlogById',
    method: 'post',
    data,
  });
}

export const selectBlogByPublished = data => {
  return request({
    url: '/manage/selectBlogByPublished',
    method: 'post',
    data,
  });
};

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

export const updateRecommend = data => {
  return request({
    url: '/manage/updateBlogRecommend',
    method: 'post',
    data,
  });
};

export const updateCommentabled = data => {
  return request({
    url: '/manage/updateBlogCommentabled',
    method: 'post',
    data,
  });
};

export const updateShared = data => {
  return request({
    url: '/manage/updateBlogShared',
    method: 'post',
    data,
  });
};
