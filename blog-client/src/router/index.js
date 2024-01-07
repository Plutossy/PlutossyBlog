/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-05 17:21:14
 * @FilePath: \blog-client\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// 导入 Vue Router
import { createRouter, createWebHistory } from 'vue-router'

// 创建路由实例并传递 `routes` 配置
const routes = [
  { path: '/', redirect: '/home' },
  { path: '/home', name: 'Index', component: () => import('../pages/Home.vue') },
  { path: '/type', name: 'Type', component: () => import('../pages/Type.vue') },
  { path: '/tag', name: 'Tag', component: () => import('../pages/Tag.vue') },
  { path: '/clone', name: 'Clone', component: () => import('../pages/Clone.vue') },
  { path: '/music', name: 'Music', component: () => import('../pages/Music.vue') },
  { path: '/picture', name: 'Picture', component: () => import('../pages/Picture.vue') },
  { path: '/about', name: 'About', component: () => import('../pages/About.vue') },
  { path: '/search', name: 'Search', component: () => import('../pages/Search.vue')}
]

const router = createRouter({
  // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: createWebHashHistory(),
  history: createWebHistory(),
  routes, // `routes: routes` 的缩写
})

export default router