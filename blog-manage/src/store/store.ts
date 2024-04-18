/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:37:20
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-18 17:52:07
 * @FilePath: \PlutossyBlog\blog-manage\src\store\store.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// VUE2: 导入 Vue 和 Vuex
// import Vue from 'vue'
// import Vuex from 'vuex'

// VUE2: 将 Vuex 安装为 Vue 的插件
// Vue.use(Vuex) new Vue.Store

import { createStore } from 'vuex';

// 导入其他 vuex 模块
import moduleUser from './modules/user';
import moduleTable from './modules/table';

// 创建 Store 的实例对象
const store = createStore({
  // 挂载 store 模块
  modules: {
    // 挂载用户的 vuex 模块，模块内成员的访问路径被调整为 user，例如：
    // 用户模块中 token 的访问路径是 user/token
    user: moduleUser,
    table: moduleTable,
  },
});

// 向外共享 Store 的实例对象
export default store;
