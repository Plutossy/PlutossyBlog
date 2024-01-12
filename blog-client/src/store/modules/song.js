/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-05 15:20:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-12 15:23:51
 * @FilePath: \blog-client\src\store\modules\song.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
const state = {
  playStatus: false, //播放状态, 默认为false
  playList: [], //播放列表
  songContent: {
    id: -1,
    name: '暂无歌曲',
    singer: '',
    url: '',
    img: '',
    lyric: ''
  },   //当前播放的歌曲信息
  currentTime: "00:00",  //当前歌曲的播放进度
  currentSeconds: 0, //当前歌曲的播放进度(秒)，用于歌词滚动
}

const getters = {
  // 获取播放状态
  playStatus: state => {
    let playStatus = state.playStatus
    // if (!playStatus) {
    //   playStatus = JSON.parse(window.sessionStorage.getItem('playStatus') || null)
    // }
    return playStatus
  },
  // 获取播放列表
  playList: state => {
    let playList = state.playList
    if (!playList.length) {
      playList = JSON.parse(window.sessionStorage.getItem('playList') || null) || []
    }
    return playList
  },
  // 获取当前播放歌曲信息
  songContent: state => {
    let songContent = state.songContent
    const defaultSongContent = {
        id: -1,
        name: '暂无歌曲',
        singer: '暂无歌手',
        url: '',
        img: '',
        lyric: ''
    }
    if (!songContent.url) {
      songContent = JSON.parse(window.sessionStorage.getItem('songContent')) || defaultSongContent
    }
    return songContent
  },
  // 获取当前歌曲播放进度
  currentTime: state => {
    let currentTime = state.currentTime
    // if (!currentTime) {
    //   currentTime = JSON.parse(window.sessionStorage.getItem('currentTime') || null)
    // }
    return currentTime
  },
  // 获取当前歌曲播放进度(秒)
  currentSeconds: state => {
    let currentSeconds = state.currentSeconds
    if (!currentSeconds) {
      currentSeconds = JSON.parse(window.sessionStorage.getItem('currentSeconds') || null)
    }
    return currentSeconds
  }
}

const mutations = {
  // 定义存储到本地的 mutations 方法
  saveToStroage(state) {
    window.sessionStorage.setItem('playStatus', JSON.stringify(state.playStatus))
    window.sessionStorage.setItem('songContent', JSON.stringify(state.songContent))
    window.sessionStorage.setItem('playList', JSON.stringify(state.playList))
    window.sessionStorage.setItem('currentTime', JSON.stringify(state.currentTime))
    window.sessionStorage.setItem('currentSeconds', JSON.stringify(state.currentSeconds))
  },
  setPlayStatus(state, status) {
    state.playStatus = status
    this.commit('m_song/saveToStroage')
  },
  setSongContent(state, payload) {
    state.songContent = payload
    // 判断当前播放列表是否已经存在该歌曲 如果不存在则 开头追加 到播放列表
    const index = state.playList.indexOf(payload)
    if(index == -1){
      state.playList.push(payload)
    } else {
      state.playList.splice(index, 1)
      state.playList.push(payload)
    }
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