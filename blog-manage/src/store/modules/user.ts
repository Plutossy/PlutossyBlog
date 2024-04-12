/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-10 11:37:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-12 18:18:29
 * @FilePath: \blog-manage\src\store\modules\user.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export default {
  namespaced: true,
  state: {
    token: '',
    userInfo: {
      id: '',
    },
  },
  getters: {
    token: (state: { token: any }) => JSON.parse(localStorage.getItem('token') || JSON.stringify(state.token)),
    userInfo: (state: { userInfo: any }) => JSON.parse(sessionStorage.getItem('userInfo') || JSON.stringify(state.userInfo)),
  },
  mutations: {
    setToken(state: { token: any }, token: any) {
      state.token = token;
      // 保存到本地缓存
      localStorage.setItem('token', JSON.stringify(state.token));
    },
    setUserInfo(state: { userInfo: any }, userInfo: any) {
      state.userInfo = userInfo;
      // 保存到本地缓存
      sessionStorage.setItem('userInfo', JSON.stringify(state.userInfo));
    },
    setUserInfoId(state: { userInfo: { id: String } }, id: String) {
      state.userInfo.id = id;
      // 保存到本地缓存
      sessionStorage.setItem('userInfo', JSON.stringify(state.userInfo));
    },
  },
  actions: {
    removeToken(context: { commit: (arg0: string, arg1: string) => void }) {
      context.commit('setToken', '');
    },
  },
};
