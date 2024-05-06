/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-27 16:51:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-06 16:52:30
 * @FilePath: \blog-client\src\mixins\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { deepClone } from '@/plugins/modules/common';
export const mixin = {
  methods: {
    // 提示信息
    notify(message, type) {
      this.$notify({
        title: message,
        type: type,
        duration: 1000,
        showClose: true,
      });
    },
    // 格式化时间
    formatSeconds(val) {
      let result = '';
      const theTime = parseFloat(val);
      const hour = parseInt(theTime / 3600); // 小时
      const minute = parseInt((theTime / 60) % 60); // 分钟
      const second = parseInt(theTime % 60); // 秒
      if (hour > 0) {
        if (hour < 10) {
          result = '0' + hour + ':';
        } else {
          result = hour + ':';
        }
      }
      if (minute > 0) {
        if (minute < 10) {
          result += '0' + minute + ':';
        } else {
          result += minute + ':';
        }
      } else {
        result += '00:';
      }
      if (second) {
        if (second < 10) {
          result += '0' + second;
        } else {
          result += second;
        }
      } else {
        result += '00';
      }
      return result;
    },
    // 解析歌词
    parseLyric(lyric) {
      const newLyric0 = lyric.replace(/[[]/g, '\n['); // 将[换成\n[
      const newLyric = newLyric0.replace('\n', ''); // 去掉第一个换行符
      let lines = newLyric.split('\n'); // 将文本按行分割，存入数组
      const pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g; // [00:00.00]或者[00:00.000]的正则表达式
      let result = []; // 返回值
      // 对于歌词格式不对的直接返回
      if (!/\[.+\]/.test(newLyric)) {
        return [[0, newLyric]];
      }
      while (!pattern.test(lines[0])) {
        lines = lines.slice(1);
      }
      // 遍历每一行，提取歌词和对应的时间
      for (let item of lines) {
        let time = item.match(pattern); // 存前面的时间段
        let value = item.replace(pattern, ''); // 存后面的歌词
        for (let item1 of time) {
          let t = item1.slice(1, -1).split(':'); // 分钟和秒
          if (value !== '') {
            result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]); // 转化为秒
          }
        }
      }
      // 根据第一个元素大小排序
      result.sort(function (a, b) {
        return a[0] - b[0];
      });
      return result;
    },
    // 返回顶部的动画
    //性能优化，利用对象给不同的元素添加不同的定时器
    animate(obj, target, callback) {
      // 不断点击按钮，动画效果会越来越快，因为开启太多定时器了
      // 解决方案，让元素只有一个定时器执行

      // 清楚以前的定时器，只保留当前的
      clearInterval(obj.timer);
      obj.timer = setInterval(() => {
        //步长公式
        // 把步长值改为整数，避免小数问题
        // var step = Math.ceil((target - obj.scrollTop) / 10); //进一法，向上取整
        let step = (target - obj.scrollTop) / 10;
        // 判断步长是否大于0，小于0向下取整
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (obj.scrollTop == target) {
          // 停止动画定时器
          clearInterval(obj.timer);
          // 回调函数写到定时器结束位置
          if (callback) {
            // 调用函数
            callback();
          }
        }
        obj.scrollTop += step;
      }, 15);
    },
  },
};

// 清空表单
export const clearForm = form => {
  for (const key in form) {
    form[key] = '';
  }
};

// 清空属性值为空的属性
export const clearEmptyProperty = obj => {
  const params = JSON.parse(JSON.stringify(obj));

  for (const key in params) {
    if (Object.hasOwnProperty.call(params, key)) {
      const element = params[key];
      const eleType = typeof element;
      let isDel = false;

      if (eleType === 'string') {
        element === '' && (isDel = true);
      } else if (eleType === 'object') {
        if (element === null) {
          isDel = true;
        } else if (Array.isArray(element)) {
          element.length === 0 && (isDel = true);
        }
      }

      isDel && delete params[key];
    }
  }

  return params;
};

// 初始化数据
export const initData = (obj, dataObj) => {
  if (!dataObj) return obj;
  let initData = deepClone(obj);
  for (const key in initData) {
    if (Object.hasOwnProperty.call(dataObj, key)) {
      initData[key] = dataObj[key];
    }
  }
  return initData;
};
