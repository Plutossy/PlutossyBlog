/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-05 15:20:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-27 17:58:07
 * @FilePath: \blog-client\src\store\modules\song.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
const state = {
  playList: [], //播放列表
  songContent: {},   //当前播放的歌曲信息
  currentTime: ""  //当前歌曲的播放进度
}

const getters = {}

const mutations = {
  setSongContent(state, payload) {
    state.songContent = payload
    state.playList.indexOf(payload) == -1 ? state.playList.push(payload) : ""
  },
  setLyric(state, payload) {
    state.songContent.lyric = payload
  },
  setCurrentTime(state, payload) {
    state.currentTime = payload
  },
  removeSong(state, payload) {
    const index = state.playList.indexOf(payload)
    state.playList.splice(index, 1)
  },
  addSong(state, payload) {
    const index = state.playList.indexOf(payload)
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