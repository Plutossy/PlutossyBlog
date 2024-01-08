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

