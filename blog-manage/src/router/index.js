/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 18:52:14
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-25 16:24:30
 * @FilePath: \PlutossyBlog\blog-manage\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// 导入 Vue Router
import { createRouter, createWebHistory } from 'vue-router'

// 创建路由实例并传递 `routes` 配置
const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'Login', component: () => import('../pages/Login.vue') },
  {
    path: '/index', name: 'Index', component: () => import('../pages/Index.vue'), children: [
      { path: '/info', name: 'Info', component: () => import('../pages/Info.vue') },
      { path: '/blog', name: 'Blog', component: () => import('../pages/Blog.vue') },
      { path: '/comment', name: 'Comment', component: () => import('../pages/Comment.vue') },
      { path: '/type', name: 'Type', component: () => import('../pages/Type.vue') },
      { path: '/tag', name: 'Tag', component: () => import('../pages/Tag.vue') },
      { path: '/music', name: 'Music', component: () => import('../pages/Music.vue') },
      { path: '/picture', name: 'Picture', component: () => import('../pages/Picture.vue') },
      { path: '/user', name: 'User', component: () => import('../pages/User.vue'), children: [
        { path: '', name: 'UserList', component: () => import('../pages/user/UserList.vue') },
        { path: 'collect', name: 'Collect', component: () => import('../pages/user/Collect.vue') },
      ]},
    ]
  },
]

const router = createRouter({
  // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: createWebHashHistory(),
  history: createWebHistory(),
  routes,
})

// 路由守卫
// router.beforeEach((to, from, next) => {
//   if (Arrrouter.indexOf(to.path) !== -1) {
//     const token = localStorage.getItem('token')
//     const username = sessionStorage.getItem('userName')
//     if (token === 'Bearer ' + username + '-1') {
//       // 有 token，放行
//       next()
//     } else {
//       // 没有 token，强制跳转到 登录界面
//       next('/')
//     }
//   } else {
//     // 不是 /main，直接放行
//     next()
//   }
// })

export default router