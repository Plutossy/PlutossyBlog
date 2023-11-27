import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import '../node_modules/element-plus/dist/index.css'
import './index.css'

//导入 Element Plus 的图标库
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 导入路由实例
import router from './router'

const app = createApp(App)

// 通过 `use` 方法安装 Element Plus
app.use(ElementPlus)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// 通过 `use` 方法安装路由实例
app.use(router)

app.mount('#app')
