/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-22 14:59:59
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-30 09:11:54
 * @FilePath: \blog-manage\src\mixins\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { deepClone } from '@/plugins/modules/common';

import { ElMessage } from 'element-plus';
import type { UploadProps } from 'element-plus';

export const handleSex = (val: number) => {
  return val === 1 ? '男' : val === 0 ? '女' : '未知';
};

// 上传图片之前的校验
export const beforeImgUpload: UploadProps['beforeUpload'] = file => {
  const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/gif';
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isJPG) {
    ElMessage.error('上传图片只能是 JPG、PNG 或 GIF 格式！');
    return false;
  }
  if (!isLt2M) {
    ElMessage.error('上传图片大小不能超过 2MB！');
    return false;
  }
  return true;
};

// 歌词换行
export const formatLyric = (lyric: string) => {
  // const newLyric0 = lyric.replace(/[[]/g, '\n[') // 将[换成\n[
  // const newLyric1 = newLyric0.replace(/]/g, '] ') // 在]后面加空格
  // const newLyric2 = newLyric1.replace('\n', '') // 去掉第一个换行符
  return lyric.replace(/[[]/g, '\n[').replace(/]/g, '] ').replace('\n', '').split('\n'); // 以换行符分割成数组
};

interface FormType {
  [key: string]: any;
}
// 清空表单
export const clearForm = (form: FormType) => {
  for (const key in form) {
    form[key] = '';
  }
};

// 清空属性值为空的属性
export const clearEmptyProperty = (obj: FormType) => {
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
export const initData = (obj: FormType, dataObj: FormType) => {
  if (!dataObj) return obj;
  let initData = deepClone(obj);
  for (const key in initData) {
    if (Object.hasOwnProperty.call(dataObj, key)) {
      initData[key] = dataObj[key];
    }
  }
  return initData;
};
