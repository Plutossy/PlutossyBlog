/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:56:57
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-18 19:48:51
 * @FilePath: \blog-client\vite.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { defineConfig } from 'vite'
// 引入path
import { resolve } from 'path'
import vue from '@vitejs/plugin-vue'

// 导入commonjs模块
import commonjs from '@rollup/plugin-commonjs'

// 按需导入element-plus
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// 热更新，开发环境下使用
import ViteRestart from 'vite-plugin-restart'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    commonjs(), // 将 CommonJS 转换成 ES2015 模块供 Rollup 处理
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
    ViteRestart({
      restart: [
        'vite.config.js',
      ]
    }),
  ],
  base: './', // 设置打包路径
  server: {
    port: 3000, // 端口号
    open: true, // 自动打开浏览器
    cors: true, // 允许跨域
    // proxy: {
    //   // 选项写法
    //   '/api': 'http://localhost:3000',
    //   // 函数写法
    //   '/foo': {
    //     target: '<other_url>'
    //   }
    //   '/api': {
    //     target: 'http://jsonplaceholder.typicode.com',
    //     changeOrigin: true,
    //     rewrite: (path) => path.replace(/^/api/, '')
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
  },
  build: {
    minify: 'terser', // 启用 terser 压缩
    terserOptions: {
      compress: {
        drop_console: true, // 删除所有 console
        drop_debugger: true,// 删除 debugger
      }
    }
  },
})
