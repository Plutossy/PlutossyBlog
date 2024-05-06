/*
 * @Author: sunshuangyin sunshuangyin@seewintech.com
 * @Date: 2024-04-10 10:38:38
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 15:20:43
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

export function commonPlugin(app) {
  app.config.globalProperties.$deepClone = deepClone;
}
