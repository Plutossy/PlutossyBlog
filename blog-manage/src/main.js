import { createApp } from 'vue'
import './style.scss'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store/store'
// 导入全局动画样式表
import 'animate.css';

const app = createApp(App)

app.use(ElementPlus)

// 通过 `use` 方法安装路由实例
app.use(router)

// 通过 `use` 方法安装 Vuex 的 store 实例
app.use(store)

app.mount('#app')
