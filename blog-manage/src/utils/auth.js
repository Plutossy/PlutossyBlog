/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 13:58:27
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 16:41:09
 * @FilePath: \blog-manage\src\utils\auth.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
/* import Vue from 'vue';
import VueCookies from 'vue-cookies';
Vue.use(VueCookies, { expires: '7d' });

const $cookies = inject < VueCookies > '$cookies'; */

const TokenKey = 'pluto_blog_ssy_token';
export function setToken() {
  this.$store.commit('user/setToken', TokenKey);
}
export function getToken() {
  return 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzeWtqX2FkbWluIiwiYWNjZXNzVHlwZSI6MTEsInVzZXJUeXBlIjoyLCJ1c2VySWQiOjE3NjUyNjc2MjU1OTk3Mzc4NTksImV4cCI6MTcxMDgzMjEyOSwianRpIjoiMTk1ZjM2ODAtNTU1ZC00NTRhLTg1OTUtODMyYWMyNTIyZmJlIn0.2uFIx6kLi8JS0nDTXx7tSRAodEnDTip1Jwt3qY5H81c';
}
