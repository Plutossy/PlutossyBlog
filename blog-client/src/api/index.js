import Axios from 'axios'
import { get, post } from './http'
// ================歌曲相关========================
// 查询所有歌曲
export const getAllSong = () => get(`song/allSong`)

// 下载音乐
export const downloadSong = url => Axios({
  method: 'get',
  url: url,
  responseType: 'blob'
})

