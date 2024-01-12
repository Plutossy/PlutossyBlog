/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-28 13:56:32
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-12 15:15:58
 * @FilePath: \blog-client\src\store\store.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// VUE2: 导入 Vue 和 Vuex
// import Vue from 'vue'
// import Vuex from 'vuex'

// VUE2: 将 Vuex 安装为 Vue 的插件
// Vue.use(Vuex) new Vue.Store

import {
  createStore
} from "vuex";


// 导入音乐 vuex 模块
import moduleSong from './modules/song.js'
// 回到顶部
import moduleBack from './modules/back.js'

// 创建 Store 的实例对象
const store = createStore({
  // 挂载 store 模块
  modules: {
    // 挂载音乐的 vuex 模块，模块内成员的访问路径被调整为 m_song，例如：
    // 音乐模块中 cart 数组的访问路径是 m_song/song
    m_song: moduleSong,
    // 回到顶部
    m_back: moduleBack
  }
})

// 向外共享 Store 的实例对象
export default store