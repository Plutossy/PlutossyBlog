/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-27 16:51:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-28 10:46:00
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
  },
}
