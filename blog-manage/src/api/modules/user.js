/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 11:16:32
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 16:43:56
 * @FilePath: \blog-manage\src\api\modules\user.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request';

export function login(data) {
  return request({
    url: '/api/login',
    method: 'post',
    data,
  });
}
