/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-05 15:20:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-28 11:01:35
 * @FilePath: \blog-client\src\store\modules\song.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
const state = {
  playList: [], //播放列表
  songContent: {},   //当前播放的歌曲信息
  currentTime: "00:00",  //当前歌曲的播放进度
  currentSeconds: 0, //当前歌曲的播放进度(秒)，用于歌词滚动
}

const getters = {}

const mutations = {
  // 定义存储到本地的 mutations 方法
  saveToStroage(state) {
    window.sessionStorage.setItem('songContent', JSON.stringify(state.songContent))
    window.sessionStorage.setItem('playList', JSON.stringify(state.playList))
    window.sessionStorage.setItem('currentTime', JSON.stringify(state.currentTime))
    window.sessionStorage.setItem('currentSeconds', JSON.stringify(state.currentSeconds))
  },
  setSongContent(state, payload) {
    state.songContent = payload
    state.playList.indexOf(payload) == -1 ? state.playList.push(payload) : ""
    this.commit('m_song/saveToStroage')
  },
  setLyric(state, payload) {
    state.songContent.lyric = payload
    this.commit('m_song/saveToStroage')
  },
  setCurrentTime(state, payload) {
    state.currentTime = payload
    this.commit('m_song/saveToStroage')
  },
  setCurrentSeconds(state, payload) {
    state.currentSeconds = payload
    this.commit('m_song/saveToStroage')
  },
  removeSong(state, payload) {
    const index = state.playList.indexOf(payload)
    state.playList.splice(index, 1)
    this.commit('m_song/saveToStroage')
  },
  addSong(state, payload) {
    const index = state.playList.indexOf(payload)
    state.playList.indexOf(payload) == -1 ? state.playList.push(payload) : ""
    this.commit('m_song/saveToStroage')
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