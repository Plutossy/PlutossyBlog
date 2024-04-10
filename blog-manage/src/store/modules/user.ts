export default {
  namespaced: true,
  state: {
    token: '',
    userInfo: {},
  },
  getters: {
    token: state => JSON.parse(sessionStorage.getItem('token') || JSON.stringify(state.token) || ''),
    userInfo: state => JSON.parse(sessionStorage.getItem('userInfo') || JSON.stringify(state.userInfo) || '{}'),
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      // 保存到本地缓存
      sessionStorage.setItem('token', JSON.stringify(state.token));
    },
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo;
      // 保存到本地缓存
      sessionStorage.setItem('userInfo', JSON.stringify(state.userInfo));
    },
  },
  actions: {
    async login(context, loginForm) {
      // 模拟登录
      const { username, password } = loginForm;
      if (username === 'admin' && password === '123456') {
        // 登录成功，保存 token
        context.commit('setToken', 'token123456');
        // 获取用户信息
        const userInfo = {
          name: '小明',
          age: 18,
        };
        // 保存用户信息
        context.commit('setUserInfo', userInfo);
        return true;
      }
      return false;
    },
  },
};
