/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 11:37:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-11 14:02:25
 * @FilePath: \blog-manage\src\store\modules\user.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export default {
  namespaced: true,
  state: {
    token: '',
    userInfo: {},
  },
  getters: {
    token: state => JSON.parse(localStorage.getItem('token') || JSON.stringify(state.token) || ''),
    userInfo: state => JSON.parse(sessionStorage.getItem('userInfo') || JSON.stringify(state.userInfo) || '{}'),
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      // 保存到本地缓存
      localStorage.setItem('token', JSON.stringify(state.token));
    },
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo;
      // 保存到本地缓存
      sessionStorage.setItem('userInfo', JSON.stringify(state.userInfo));
    },
  },
  actions: {
    removeToken(context) {
      context.commit('setToken', '');
    },
  },
};
