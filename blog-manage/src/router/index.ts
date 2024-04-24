/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 18:52:14
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-24 12:25:00
 * @FilePath: \PlutossyBlog\blog-manage\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// 导入 Vue Router
import { createRouter, createWebHistory } from 'vue-router';

import store from '@/store/store';

// 创建路由实例并传递 `routes` 配置
const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'Login', component: () => import('../pages/Login.vue') },
  {
    path: '/index',
    name: 'Index',
    component: () => import('../pages/Index.vue'),
    meta: { requiresAuth: true }, // 需要校验登录状态
    children: [
      { path: '', redirect: '/info', name: 'Index0', meta: { requiresAuth: true } },
      { path: '/info', name: 'Info', component: () => import('../pages/Info.vue'), meta: { requiresAuth: true } },
      { path: '/blog', name: 'Blog', component: () => import('../pages/Blog.vue'), meta: { requiresAuth: true } },
      {
        path: '/type',
        name: 'Type',
        component: () => import('../pages/Type.vue'),
        meta: { requiresAuth: true },
        children: [
          { path: '', name: 'TypeList', component: () => import('../pages/type/TypeList.vue'), meta: { requiresAuth: true } },
          { path: 'typeBlog', name: 'TypeBlog', component: () => import('../pages/type/TypeBlog.vue'), meta: { requiresAuth: true } },
        ],
      },
      {
        path: '/tag',
        name: 'Tag',
        component: () => import('../pages/Tag.vue'),
        meta: { requiresAuth: true },
        children: [
          { path: '', name: 'TagList', component: () => import('../pages/tag/TagList.vue'), meta: { requiresAuth: true } },
          { path: 'tagBlog', name: 'TagBlog', component: () => import('../pages/tag/TagBlog.vue'), meta: { requiresAuth: true } },
        ],
      },
      { path: '/music', name: 'Music', component: () => import('../pages/Music.vue'), meta: { requiresAuth: true } },
      { path: '/picture', name: 'Picture', component: () => import('../pages/Picture.vue'), meta: { requiresAuth: true } },
      {
        path: '/user',
        name: 'User',
        component: () => import('../pages/User.vue'),
        meta: { requiresAuth: true },
        children: [
          { path: '', name: 'UserList', component: () => import('../pages/user/UserList.vue'), meta: { requiresAuth: true } },
          { path: 'collect', name: 'Collect', component: () => import('../pages/user/Collect.vue'), meta: { requiresAuth: true } },
        ],
      },
    ],
  },
];

const router = createRouter({
  // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: createWebHashHistory(),
  history: createWebHistory(),
  routes,
});

// 路由守卫
router.beforeEach(async (to, _from, next) => {
  // const ArrRouter = ['/', '/login', '/error'];
  // if (ArrRouter.indexOf(to.path) === -1) {
  const token = store.getters['user/token'] || JSON.parse(localStorage.getItem('token') || '');
  // 编辑to.matched，判断是否需要登录；matched 是一个数组，包含当前路由的所有嵌套路径片段所对应的配置参数对象
  // record.meta.requiresAuth 为 true 时，表示该路由需要登录
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!token) {
      // 没有 token
      ElMessage.error('请先登录');
      next({
        path: '/login',
        query: { redirect: to.fullPath }, // 将目标路由作为重定向参数传递
      });
    } else {
      next();
      /* try {
        // 如果token存在，可以将token发送到服务器进行验证
        // const { code } = await validateToken(token);
        const code = 200;
        if (code == 200) {
          // 如果token有效，继续导航
          next();
        } else {
          ElMessage.error('token无效，请重新登录!');
          // 如果token无效，跳转到登录页面
          next({ path: '/login' });
        }
      } catch (error) {
        // 处理错误情况，例如网络问题，可以跳转到错误页面或重试
        // next({ path: '/error' });
        console.log(error, 'error');
      } */
    }
  } else {
    next();
  }
  // }
});

export default router;
