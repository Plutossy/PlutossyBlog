// 导入 Vue Router
import { createRouter, createWebHistory } from 'vue-router'

// 创建路由实例并传递 `routes` 配置
const routes = [
  { path: '/', redirect: '/index' },
  { path: '/index', name: 'Index', component: () => import('../pages/Index.vue') },
  { path: '/type', name: 'Type', component: () => import('../pages/Type.vue') },
  { path: '/tag', name: 'Tag', component: () => import('../pages/Tag.vue') },
  { path: '/clone', name: 'Clone', component: () => import('../pages/Clone.vue') },
  { path: '/music', name: 'Music', component: () => import('../pages/Music.vue') },
  { path: '/picture', name: 'Picture', component: () => import('../pages/Picture.vue') },
  { path: '/about', name: 'About', component: () => import('../pages/About.vue') }
]

const router = createRouter({
  // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: VueRouter.createWebHashHistory(),
  history: createWebHistory(),
  routes, // `routes: routes` 的缩写
})

export default router