/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 18:37:55
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-10 14:09:31
 * @FilePath: \blog-manage\vite.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { defineConfig } from 'vite'
// 引入path
import { resolve } from 'path'
import vue from '@vitejs/plugin-vue'

// 导入commonjs模块
import commonjs from '@rollup/plugin-commonjs'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    commonjs(), // 将 CommonJS 转换成 ES2015 模块供 Rollup 处理
    vue()
  ],
  base: './',
  server: {
    port: 3001, // 端口号
    open: true, // 自动打开浏览器
    cors: true, // 允许跨域
    // proxy: {
    //   // 选项写法
    //   '/api': 'http://localhost:3000',
    //   // 函数写法
    //   '/foo': {
    //     target: '<other_url>'
    //   }
    // }
  },
  /* 路径配置 */
  resolve: {
    alias: [
      {
        find: '@',
        replacement: resolve(__dirname, './src'),
      }
    ]
  }
})

