/*
 * @Description: 将所有api集成为一个模块
 * @Author: Plutossy
 * @Date: 2024-04-10 09:05:44
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 14:57:14
 */
const modulesFiles = import.meta.glob('./modules/*.js'); // eager: true 表示在加载时就执行,不懒加载
const modules = Object.keys(modulesFiles).reduce((modules, modulePath) => {
  // 获取 modules 文件夹下的所有文件名
  const moduleName = modulePath.replace(/^\.\/modules\/(.*)\.\w+$/, '$1'); // $1表示正则表达式中第一个小括号匹配的内容
  const value = modulesFiles[modulePath];
  modules[moduleName] = value;
  return modules;
}, {});

export default modules;
