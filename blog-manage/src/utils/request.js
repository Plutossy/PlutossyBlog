/*
 * @Author: sunshuangyin sunshuangyin@seewintech.com
 * @Date: 2024-04-10 10:57:18
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-11 09:42:37
 * @FilePath: \blog-manage\src\utils\request.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import axios from 'axios';
import router from '@/router';
import { ElMessage } from 'element-plus';

import store from '@/store/store';
import { getToken } from '@/utils/auth';

// 创建axios实例
const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API, // 请求地址代理 /api 从.env文件中获取
  // withCredentials: true, // 发送请求时携带cookie
  timeout: 10000, // 请求超时时间
  headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8' },
});

// request 请求拦截器
service.interceptors.request.use(
  config => {
    if (store.getters['user/token']) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['token'] = getToken();
      /* config.headers.Authorization = accessToken; */
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// response 响应拦截器
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data;
    // 20000是mock的code要不然登陆不进去
    if (res.code === null || res.code === undefined || res.code === 0 || res.code === 200) {
      // 请求成功，返回数据成功
      return res;
    } else {
      // 响应数据为二进制流处理(Excel导出)
      if (response.data instanceof ArrayBuffer) {
        return response;
      }
      if (res.msg) {
        ElMessage({
          message: res.msg,
          type: 'error',
          duration: 1500,
          forbidClick: true,
        });
      }
      return Promise.reject(res);
    }
  },
  error => {
    // console.log('err' + error); // for debug
    if (error.response && error.response.status) {
      switch (error.response.status) {
        // 401: 未登录
        // 未登录则跳转登录页面，并携带当前页面的路径
        // 在登录成功后返回当前页面，这一步需要在登录页操作。
        case 401:
          store.dispatch('user/removeToken');
          setTimeout(() => {
            router.replace({
              path: '/login',
            });
          }, 0);
          break;
        // 403 token过期
        // 登录过期对用户进行提示
        // 清除本地token和清空vuex中token对象
        // 跳转登录页面
        case 403:
          // 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
          store.dispatch('user/removeToken');
          setTimeout(() => {
            router.replace({
              path: '/login',
            });
          }, 0);
          break;
        // 404请求不存在
        case 404:
          ElMessage({
            message: '网络请求不存在',
            duration: 1500,
            forbidClick: true,
          });
          break;
        // 其他错误，直接抛出错误提示
        default:
          ElMessage({
            message: error.response.data.message,
            duration: 1500,
            forbidClick: true,
          });
      }
    } else {
      if (error.message) {
        ElMessage({
          message: error.message,
          type: 'error',
          duration: 1500,
          forbidClick: true,
        });
      }
    }
    return Promise.reject(error.response);
  }
);

// axios.defaults.timeout = 10000; // 超时时间是10秒
// axios.defaults.withCredentials = true; // 允许跨域
// Content-Type 响应头
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
// 基础url
// axios.defaults.baseURL = 'http://localhost:3002';

// // 响应拦截器
// axios.interceptors.response.use(
//   response => {
//     // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
//     if (response.status === 200) {
//       return Promise.resolve(response);
//     } else {
//       // 否则的话抛出错误
//       return Promise.reject(response);
//     }
//   },
//   error => {
//     if (error.response.status) {
//       switch (error.response.status) {
//         case 401:
//           // 未登录
//           router.replace({
//             path: '/',
//             query: {
//               redirect: router.currentRoute.fullPath,
//             },
//           });
//           break;
//         case 404:
//           // 未找到
//           break;
//       }
//       return Promise.reject(error.response);
//     }
//   }
// );

// // 封装get方法
// export function get(url, params = {}) {
//   return new Promise((resolve, reject) => {
//     axios
//       .get(url, {
//         params: params,
//       })
//       .then(response => {
//         resolve(response.data);
//       })
//       .catch(err => {
//         reject(err);
//       });
//   });
// }

// // 封装post方法
// export function post(url, data = {}) {
//   return new Promise((resolve, reject) => {
//     axios
//       .post(url, data)
//       .then(response => {
//         resolve(response.data);
//       })
//       .catch(err => {
//         reject(err);
//       });
//   });
// }

export default service;
