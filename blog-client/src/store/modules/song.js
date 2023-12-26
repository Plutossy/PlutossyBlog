/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-05 15:20:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-26 18:13:17
 * @FilePath: \blog-client\src\store\modules\song.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
const state = {
  playList: [], //播放列表
  playContent: {},   //当前播放的歌曲信息
  current: ""  //当前歌曲的播放进度
}

const getters = {}

const mutations = {
  setContent(state, payload) {
    state.playContent = payload
    state.playList.indexOf(payload) == -1 ? state.playList.push(payload) : ""
  },
  setLyric(state, payload) {
    state.playContent.lyric = payload
  },
  setCurrent(state, payload) {
    state.current = Math.ceil(payload)
  },
  removeSong(state, payload) {
    let index = state.playList.indexOf(payload)
    state.playList.splice(index, 1)
  },
  addSong(state, payload) {
    let index = state.playList.indexOf(payload)
    state.playList.indexOf(payload) == -1 ? state.playList.push(payload) : ""
  }
}

const actions = {}

export default {
  // 为当前模块开启命名空间
  namespaced: true,
  // 模块的 state 数据
  state,
  getters,
  mutations,
  actions
}