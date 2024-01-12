const state = {
  showBackTop: true // 是否显示回到顶部按钮
}

const getters = {
  showBackTop: state => {
    let showBackTop = state.showBackTop
    if (!showBackTop) {
      showBackTop = JSON.parse(window.sessionStorage.getItem('showBackTop') || null)
    }
    return showBackTop
  }
}

const mutations = {
  // 设置状态
  setShowBackTop (state, showBackTop) {
    state.showBackTop = showBackTop
    window.sessionStorage.setItem('showBackTop', JSON.stringify(showBackTop))
  }
}

const actions = {}

export default {
  /* 为当前模块开启命名空间 */
  namespaced: true,
  state,
  getters,
  mutations,
  actions
}