/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 10:38:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 15:10:18
 * @FilePath: \blog-manage\src\plugins\apis.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import apis from '@/api';
export function apiPlugin(app) {
  app.config.globalProperties.$apis = apis;
}
