/*
 * @Author: sunshuangyin sunshuangyin@seewintech.com
 * @Date: 2024-04-10 10:38:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 11:12:16
 * @FilePath: \blog-manage\src\plugins\common.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export function deepClone(obj) {
  if (obj === null) return null;
  if (typeof obj === 'function') return cloneFunction(obj);
  if (typeof obj !== 'object') return obj;
  if (obj instanceof RegExp) return new RegExp(obj);
  if (obj instanceof Date) return new Date(obj);
  const obj2 = new obj.constructor();
  for (const key in obj) {
    if (Object.prototype.hasOwnProperty.call(obj, key)) {
      obj2[key] = deepClone(obj[key]);
    }
  }
  return obj2;
}

// 封装function拷贝方法
function cloneFunction() {
  const obj = [].shift.call(arguments);
  const temp = function temporary() {
    return obj.apply(this, arguments);
  };
  for (const key in obj) {
    if (Object.prototype.hasOwnProperty.call(obj, key)) {
      temp[key] = obj[key];
    }
  }
  return temp;
}

let timeout = null;
/**
 * 防抖原理：一定时间内，只有最后一次操作，再过wait毫秒后才执行函数
 *
 * @param {Function} func 要执行的回调函数
 * @param {Number} wait 延时的时间
 * @param {Boolean} immediate 是否立即执行
 * @return null
 */
export function debounce(func, wait = 500, immediate = false) {
  // 清除定时器
  if (timeout !== null) clearTimeout(timeout);
  // 立即执行，此类情况一般用不到
  if (immediate) {
    var callNow = !timeout;
    timeout = setTimeout(function () {
      timeout = null;
    }, wait);
    if (callNow) typeof func === 'function' && func();
  } else {
    // 设置定时器，当最后一次操作后，timeout不会再被清除，所以在延时wait毫秒后执行func回调方法
    timeout = setTimeout(function () {
      typeof func === 'function' && func();
    }, wait);
  }
}

let timer, flag;
/**
 * 节流原理：在一定时间内，只能触发一次
 *
 * @param {Function} func 要执行的回调函数
 * @param {Number} wait 延时的时间
 * @param {Boolean} immediate 是否立即执行
 * @return null
 */
export async function throttle(func, wait = 500, immediate = true) {
  if (immediate) {
    if (!flag) {
      flag = true;
      // 如果是立即执行，则在wait毫秒内开始时执行
      typeof func === 'function' && (await func());
      timer = setTimeout(() => {
        flag = false;
        clearTimeout(timer);
      }, wait);
    }
  } else {
    if (!flag) {
      flag = true;
      // 如果是非立即执行，则在wait毫秒内的结束处执行
      timer = setTimeout(async () => {
        flag = false;
        typeof func === 'function' && (await func());
        clearTimeout(timer);
      }, wait);
    }
  }
}

const commonPlugin = {
  install: function (app) {
    app.config.globalProperties.$deepClone = deepClone;
    app.config.globalProperties.$debounce = debounce;
    app.config.globalProperties.$throttle = throttle;
  },
};

export default commonPlugin;
