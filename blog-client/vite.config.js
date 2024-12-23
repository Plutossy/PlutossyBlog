/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:56:57
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-11-21 10:18:09
 * @FilePath: \blog-client\vite.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { defineConfig, loadEnv } from 'vite';
import vueSetupExtend from 'vite-plugin-vue-setup-extend';
// 引入path
import { resolve } from 'path';
import vue from '@vitejs/plugin-vue';
import Icons from 'unplugin-icons/vite';
import IconsResolver from 'unplugin-icons/resolver';

// 导入commonjs模块
import commonjs from '@rollup/plugin-commonjs';

// 按需导入element-plus
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';

// 导入svg插件
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons';

// 热更新，开发环境下使用
import ViteRestart from 'vite-plugin-restart';

const pathSrc = resolve(__dirname, 'src');

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd());
  return {
    plugins: [
      vueSetupExtend(),
      commonjs(), // 将 CommonJS 转换成 ES2015 模块供 Rollup 处理
      vue(),
      AutoImport({
        resolvers: [
          ElementPlusResolver(), // 自动导入图标组件
          IconsResolver({
            prefix: 'Icon',
          }),
        ],
        imports: ['vue', '@vueuse/core', 'vue-router', 'vuex', '@vueuse/head'],
        // 指定自动导入函数TS类型声明文件路径 (false:关闭自动生成)
        dts: false,
        // 是否在 vue 模板中自动导入
        vueTemplate: true,
        eslintrc: {
          // 是否自动生成 eslint 规则，建议生成之后设置 false
          enabled: true,
          // 指定自动导入函数 eslint 规则的文件
          filepath: './.eslintrc-auto-import.json',
          globalsPropValue: true,
        },
      }),
      Components({
        resolvers: [
          // 自动注册图标组件
          IconsResolver({
            // element-plus图标库，其他图标库 https://icon-sets.iconify.design/
            enabledCollections: ['ep'],
          }),
          // 自动导入 Element Plus 组件
          ElementPlusResolver(),
        ],
        dts: false, // 指定自动导入函数TS类型声明文件路径 (false:关闭自动生成)
        dirs: ['src/components'], // 自动导入组件存放的文件夹，默认为 src/components
      }),
      Icons({
        // 自动安装图标库
        autoInstall: true,
      }),
      // svg图标
      createSvgIconsPlugin({
        // 指定需要缓存的图标文件夹
        iconDirs: [resolve(process.cwd(), 'src/assets/img/svg')],
        // 指定symbolId格式
        symbolId: 'icon-[dir]-[name]',
      }),
      ViteRestart({
        restart: ['vite.config.js'],
      }),
    ],
    base: './', // 设置打包路径
    server: {
      host: '0.0.0.0',
      port: Number(env.VITE_APP_PORT), // 端口号 3000
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
      proxy: {
        /** 代理前缀为 /api 的请求  */
        [env.VITE_APP_BASE_API]: {
          // 接口地址
          target: env.VITE_APP_API_URL,
          // 是否重写请求
          changeOrigin: true,
          // 重写路径
          rewrite: path => path.replace(new RegExp('^' + env.VITE_APP_BASE_API), ''),
          bypass(req, res, options) {
            const realUrl = options.target + (options.rewrite ? options.rewrite(req.url) : '');
            res.setHeader('A-Real-Url', realUrl); // 添加响应标头(A-Real-Url为自定义命名)，在浏览器中显示
          },
        },
      },
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
  };
});
