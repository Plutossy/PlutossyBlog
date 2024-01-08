/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 18:52:14
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-08 19:18:43
 * @FilePath: \PlutossyBlog\blog-manage\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// 导入 Vue Router
import { createRouter, createWebHistory } from 'vue-router'

// 创建路由实例并传递 `routes` 配置
const routes = [
  { path: '/', redirect: '/index' },
  { path: '/index', name: 'Index', component: () => import('../pages/Index.vue') },
  { path: '/blog', name: 'Blog', component: () => import('../pages/Blog.vue') },
  { path: '/comment', name: 'Comment', component: () => import('../pages/Comment.vue') },
  { path: '/type', name: 'Type', component: () => import('../pages/Type.vue') },
  { path: '/tag', name: 'Tag', component: () => import('../pages/Tag.vue') },
  { path: '/music', name: 'Music', component: () => import('../pages/Music.vue') },
  { path: '/picture', name: 'Picture', component: () => import('../pages/Picture.vue') },
]

const router = createRouter({
  // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: createWebHashHistory(),
  history: createWebHistory(),
  routes, // `routes: routes` 的缩写
})

export default router