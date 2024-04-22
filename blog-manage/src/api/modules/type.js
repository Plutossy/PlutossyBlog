import request from '@/utils/request';

export function selectTypeList(data) {
  return request({
    url: '/manage/typeList',
    method: 'post',
    data,
  });
}

export function selectTypeByName(data) {
  return request({
    url: '/manage/selectTypeByName',
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
