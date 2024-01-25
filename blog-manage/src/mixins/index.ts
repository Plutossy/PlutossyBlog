import { ElMessage } from "element-plus"
import type { UploadProps } from 'element-plus'

/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-22 14:59:59
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-23 17:57:40
 * @FilePath: \blog-manage\src\mixins\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export const handleSex = val => {
  return val === 1 ? '男' : val === 0 ? '女' : '未知'
}

// 上传图片之前的校验
export const beforeAvatarUpload: UploadProps['beforeUpload'] = file => {
  const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png') || (file.type === 'image/gif')
  const isLt2M = (file.size / 1024 / 1024) < 2
  if (!isJPG) {
    ElMessage.error('上传头像图片只能是 JPG、PNG 或 GIF 格式！')
    return false
  }
  if (!isLt2M) {
    ElMessage.error('上传头像图片大小不能超过 2MB！')
    return false
  }
  return true
}
