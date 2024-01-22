/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-22 17:26:00
 * @FilePath: \blog-client\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createApp } from 'vue'
import App from './App.vue'
import store from './store/store'
// import ElementPlus from 'element-plus' //已经配置按需导入
import 'element-plus/dist/index.css'
import './index.css'
// 导入全局动画样式表
import 'animate.css';

//导入 Element Plus 的图标库
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 导入路由实例
import router from './router'

const app = createApp(App)

// 通过 `use` 方法安装 Element Plus
// app.use(ElementPlus)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// 通过 `use` 方法安装路由实例
app.use(router)

// 通过 `use` 方法安装 Vuex 的 store 实例
app.use(store)

app.mount('#app')
