/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 18:37:55
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-31 19:11:58
 * @FilePath: \blog-manage\vite.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { defineConfig } from 'vite';
// 引入path
import { resolve } from 'path';
import vue from '@vitejs/plugin-vue';

// 导入commonjs模块
import commonjs from '@rollup/plugin-commonjs';

// 按需导入element-plus
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';

// 热更新，开发环境下使用
import ViteRestart from 'vite-plugin-restart';

import pxToViewport from 'postcss-px-to-viewport';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    commonjs(), // 将 CommonJS 转换成 ES2015 模块供 Rollup 处理
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
      imports: ['vue', 'vue-router', 'vuex', '@vueuse/head'],
      // 可以选择auto-import.d.ts生成的位置，使用ts建议设置为'src/auto-import.d.ts'
      dts: 'src/auto-import.d.ts',
    }),
    Components({
      dit: true, // 自动导入组件，默认为false
      dirs: ['src/components'], // 组件存放的文件夹，默认为 src/components
      resolvers: [ElementPlusResolver()],
    }),
    ViteRestart({
      restart: ['vite.config.js'],
    }),
  ],
  // css: {
  //   postcss: {
  //     plugins: [
  //       pxToViewport({
  //         // 要转换的单位
  //         unitToConvert: 'px',
  //         // 设计稿的宽度
  //         viewportWidth: 1326,
  //       })
  //     ]
  //   }
  // },
  base: './', // 设置打包路径
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
      },
    ],
  },
  build: {
    minify: 'terser', // 启用 terser 压缩
    terserOptions: {
      compress: {
        drop_console: true, // 删除所有 console
        drop_debugger: true, // 删除 debugger
      },
    },
  },
});
