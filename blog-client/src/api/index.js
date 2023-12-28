import Axios from 'axios'
import { get, post } from './http'
// ================歌曲相关========================
// 查询所有歌曲
export const getSongList = () => get(`song/getSongList`)

// 下载音乐
export const downloadSong = url => Axios({
  method: 'get',
  url: url,
  responseType: 'blob'
})

