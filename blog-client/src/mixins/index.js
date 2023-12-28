/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-27 16:51:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-28 15:09:19
 * @FilePath: \blog-client\src\mixins\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export const mixin = {
  methods: {
    // 提示信息
    notify(message, type) {
      this.$notify({
        title: message,
        type: type
      })
    },
    // 格式化时间
    formatSeconds(val) {
      let result = ''
      const theTime = parseFloat(val)
      const hour = parseInt(theTime / 3600) // 小时
      const minute = parseInt((theTime / 60) % 60) // 分钟
      const second = parseInt(theTime % 60) // 秒
      if (hour > 0) {
        if (hour < 10) {
          result = '0' + hour + ':'
        } else {
          result = hour + ':'
        }
      }
      if (minute > 0) {
        if (minute < 10) {
          result += '0' + minute + ':'
        } else {
          result += minute + ':'
        }
      } else {
        result += '00:'
      }
      if (second) {
        if (second < 10) {
          result += '0' + second
        } else {
          result += second
        }
      } else {
        result += '00'
      }
      return result
    },
    // 解析歌词
    parseLyric(lyric) {
      const newLyric0 = lyric.replace(/[[]/g, '\n[') // 将[换成\n[
      const newLyric = newLyric0.replace('\n', '') // 去掉第一个换行符
      let lines = newLyric.split('\n') // 将文本按行分割，存入数组
      const pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g // [00:00.00]或者[00:00.000]的正则表达式
      let result = [] // 返回值
      // 对于歌词格式不对的直接返回
      if (!(/\[.+\]/.test(newLyric))) {
        return [[0, newLyric]]
      }
      while (!pattern.test(lines[0])) {
        lines = lines.slice(1)
      }
      // 遍历每一行，提取歌词和对应的时间
      for (let item of lines) {
        let time = item.match(pattern) // 存前面的时间段
        let value = item.replace(pattern, '') // 存后面的歌词
        for (let item1 of time) {
          let t = item1.slice(1, -1).split(':') // 分钟和秒
          if (value !== '') {
            result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]) // 转化为秒
          }
        }
      }
      // 根据第一个元素大小排序
      result.sort(function (a, b) {
        return a[0] - b[0]
      })
      return result
    }
  },
}
