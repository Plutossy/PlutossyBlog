/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-03-04 10:53:37
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 11:14:52
 * @FilePath: \blog-manage\src\main.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createApp } from 'vue'
import './style.scss'
// import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css';
import App from './App.vue';
import router from './router';
import store from './store/store.ts';
import mitt from 'mitt';
// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
// 导入全局动画样式表
import 'animate.css';

// custom自定义插件（plugins目录）
import apiPlugin from '@/plugins/apis.js' // 全局注册请求API
import commonPlugin from '@/plugins/common.js' // 全局注册公共方法

// 导入markdown编辑器
import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

// highlightjs
import hljs from 'highlight.js';

// codemirror 编辑器的相关资源
import Codemirror from 'codemirror';

VMdEditor.Codemirror = Codemirror;
VMdEditor.use(vuepressTheme, {
  Hljs: hljs,
});

const app = createApp(App);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

// app.use(ElementPlus)
// 引入自定义插件
app.use(apiPlugin).use(commonPlugin)

// 通过 `use` 方法安装路由实例
app.use(router);

// 通过 `use` 方法安装 Vuex 的 store 实例
app.use(store);

// 挂载md编辑器
app.use(VMdEditor);

// 挂载 EventBus 实例
app.config.globalProperties.emitter = mitt();

app.mount('#app');
