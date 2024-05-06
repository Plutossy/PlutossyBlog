/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 11:37:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-06 10:36:09
 * @FilePath: \blog-manage\src\store\modules\user.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { getCookiesToken, setCookiesToken, removeCookiesToken } from '@/utils/auth';
export default {
  namespaced: true,
  state: {
    token: '',
    userInfo: {},
  },
  getters: {
    token: (state: { token: string }) => {
      if (!state.token) {
        state.token = getCookiesToken() || JSON.parse(localStorage.getItem('token') || '"Bearer "');
      }
      return state.token;
    },
    userInfo: (state: { userInfo: any }) => {
      if (!state.userInfo.id) {
        state.userInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
      }
      return state.userInfo;
    },
  },
  mutations: {
    setToken(state: { token: string }, token: string) {
      state.token = token;
      setCookiesToken(token);
      // 保存到本地缓存
      localStorage.setItem('token', JSON.stringify(state.token));
    },
    setUserInfo(state: { userInfo: any }, userInfo: any) {
      state.userInfo = userInfo;
      // 保存到本地缓存
      sessionStorage.setItem('userInfo', JSON.stringify(state.userInfo));
    },
  },
  actions: {
    removeToken(context: { commit: (arg0: string, arg1: any) => void }) {
      context.commit('setToken', '');
      removeCookiesToken();
    },
    removeUserInfo(context: { commit: (arg0: string, arg1: any) => void }) {
      context.commit('setUserInfo', {});
    },
  },
};
