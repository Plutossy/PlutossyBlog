/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 18:37:55
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-16 19:29:20
 * @FilePath: \blog-manage\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createApp } from 'vue'
import './style.scss'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store/store'
import mitt from 'mitt'
// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 导入全局动画样式表
import 'animate.css';

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(ElementPlus)

// 通过 `use` 方法安装路由实例
app.use(router)

// 通过 `use` 方法安装 Vuex 的 store 实例
app.use(store)

// 挂载 EventBus 实例
app.config.globalProperties.emitter = mitt()

app.mount('#app')
