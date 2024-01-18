/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-17 16:14:03
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-17 16:16:16
 * @FilePath: \blog-manage\src\api\http.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import axios from 'axios'
import router from '../router'
axios.defaults.timeout = 10000 // 超时时间是10秒
axios.defaults.withCredentials = true // 允许跨域
// Content-Type 响应头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
// 基础url
axios.defaults.baseURL = 'http://localhost:8888'
// 响应拦截器
axios.interceptors.response.use(response => {
  // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
  if (response.status === 200) {
    return Promise.resolve(response)
  } else {
    // 否则的话抛出错误
    return Promise.reject(response)
  }
}, error => {
  if (error.response.status) {
    switch (error.response.status) {
      case 401:
        // 未登录
        router.replace({
          path: '/',
          query: {
            redirect: router.currentRoute.fullPath
          }
        })
        break
      case 404:
        // 未找到
        break
    }
    return Promise.reject(error.response)
  }
})

// 封装get方法
export function get (url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: params
    }).then(response => {
      resolve(response.data)
    }).catch(err => {
      reject(err)
    })
  })
}

// 封装post方法
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data).then(response => {
      resolve(response.data)
    }).catch(err => {
      reject(err)
    })
  })
}
