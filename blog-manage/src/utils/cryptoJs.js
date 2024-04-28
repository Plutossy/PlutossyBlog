/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-28 16:42:25
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-28 16:54:15
 * @FilePath: \blog-manage\src\utils\jsencrypt.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import CryptoJS from 'crypto-js';
const key = 'pluto_blog_ssy_token';

export const encrypt = text => {
  return CryptoJS.AES.encrypt(text, CryptoJS.enc.Utf8.parse(key), {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  });
};

export const decrypt = text => {
  let result = CryptoJS.AES.decrypt(text, CryptoJS.enc.Utf8.parse(key), {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  });
  return result.toString(CryptoJS.enc.Utf8);
};
