<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-09 08:56:06
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-16 20:14:29
 * @FilePath: \blog-manage\src\pages\Login.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="login-wrap">
    <div class="ms-title">SSY_Blog 后台管理登录</div>
    <div class="ms-login">
      <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="80px" size="large">
        <el-form-item label="用户名：" prop="username" size="large">
          <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码：" prop="password" size="large">
          <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm(ruleFormRef)" size="large">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { FormInstance, FormRules, ElNotification } from 'element-plus'
interface RuleForm {
  username: string
  password: string
}
const ruleFormRef = ref<FormInstance>()

let ruleForm = reactive<RuleForm>({
  username: 'admin',
  password: '123456'
})
const rules = reactive<FormRules<RuleForm>>({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
  ]
})
const router = useRouter();

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('login success!')
    } else {
      console.log('error submit!', fields)
    }
  })
  // alert('登录成功')
  // URLSearchParams 是一个对象，它主要用来处理 URL 的查询字符串
  // let params = new URLSearchParams()
  // params.append('name', ruleForm.username)
  // params.append('password', ruleForm.password)
  if (ruleForm.username === 'admin' && ruleForm.password === '123456') {
    router.push('/index')
  } else {
    ElNotification({
      message: '登录失败，请重试',
      type: 'warning',
    })
  }
  // getLoginStatus(params).then(res => {
  //   if (res.code === 1) {
  //     sessionStorage.setItem('userName', this.ruleForm.username)
  //     const token = 'Bearer ' + this.ruleForm.username + '-' + res.code
  //     window.localStorage.setItem('token', token)
  //     this.$router.push('/index')
  //     this.notify('登录成功', 'success')
  //   } else {
  //     this.notify('登录失败', 'error')
  //   }
  // })
}
</script>

<style lang="scss" scoped>
.login-wrap {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 50%;
  left: 75%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .ms-title {
    width: 100%;
    font-size: 30px;
    font-weight: 600;
    color: #f08047;
    text-align: center;
    margin-bottom: 5px;
  }

  .ms-login {
    width: 450px;
    height: 250px;
    display: flex;
    align-items: center;
    border-radius: 5px;
    background-color: #fff;
    padding: 0 1rem;

    .el-form {
      width: 100%;
      margin: 0 10px;

      .login-btn button {
        width: 100%;
        height: 36px;
      }
    }

  }
}
</style>