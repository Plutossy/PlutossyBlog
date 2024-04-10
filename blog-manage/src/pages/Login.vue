<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-09 08:56:06
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-10 18:32:53
 * @FilePath: \blog-manage\src\pages\Login.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="container">
    <div class="tips">
      <div class="container-title">
        <h1>欢迎来到 SSY_Blog 后台管理</h1>
        <p>快来定制你的专属博客吧！</p>
      </div>
      <div class="container-img">
        <img src="@/assets/img/love.gif" alt="欢迎来到 SSY_Blog" />
      </div>
      <div class="toLogin">
        <p>已有账号？</p>
        <el-button @click="loginToogle">去登录</el-button>
      </div>
    </div>
    <div class="tips">
      <div class="container-title">
        <h1>欢迎来到 SSY_Blog 后台管理</h1>
        <p>快来定制你的专属博客吧！</p>
      </div>
      <div class="container-img">
        <img src="@/assets/img/love.gif" alt="欢迎来到 SSY_Blog" />
      </div>
      <div class="toRegister">
        <p>没有账号？</p>
        <el-button @click="loginToogle">去注册</el-button>
      </div>
    </div>

    <div class="login-register-wrap" :style="{ transform: tologin ? 'translate(-55.88%, -50%)' : 'translate(55.88%, -50%)' }">
      <div class="main">
        <div v-if="tologin" class="title">LOGIN</div>
        <div v-else class="title">REGISTER</div>
        <div class="content">
          <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="100px" size="large">
            <el-form-item v-if="!tologin" label="用户名：" prop="username" size="large">
              <el-input v-model="ruleForm.username" placeholder="请输入用户名" clearable></el-input>
            </el-form-item>
            <el-form-item label="昵&nbsp;&nbsp;&nbsp;&nbsp;称：" prop="nickname" size="large" class="animate__animated animate__bounceIn">
              <el-input v-model="ruleForm.nickname" placeholder="请输入昵称" clearable></el-input>
            </el-form-item>
            <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码：" prop="password" size="large">
              <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
            <el-form-item v-if="!tologin" label="确认密码：" prop="confirmPwd" size="large" class="animate__animated animate__bounceIn">
              <el-input type="password" v-model="ruleForm.confirmPwd" placeholder="请再次输入密码" show-password></el-input>
            </el-form-item>
            <div class="btn">
              <el-button v-if="tologin" type="primary" @click="submitForm(ruleFormRef)" size="large">登录</el-button>
              <el-button v-else type="primary" @click="submitForm(ruleFormRef)" size="large">注册</el-button>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { login } from '@/api/modules/user';
import { FormInstance, FormRules, ElNotification } from 'element-plus';
interface RuleForm {
  // 表单数据类型
  username: string;
  nickname: string;
  password: string;
  confirmPwd: string;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例

let tologin = ref(true);
let ruleForm = reactive<RuleForm>({
  // 表单数据
  nickname: 'admin',
  username: 'PlutoSSY',
  password: '123456',
  confirmPwd: '123456',
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' },
    { pattern: /^\S*$/, message: '密码不能包含空格', trigger: 'blur' },
  ],
  nickname: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  ],
  confirmPwd: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' },
    { pattern: /^\S*$/, message: '密码不能包含空格', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value === ruleForm.password) {
          callback();
        } else {
          callback(new Error('两次输入密码不一致'));
        }
      },
      trigger: 'blur',
    },
  ],
});
const router = useRouter();
/* const instance = getCurrentInstance();
const appContext = instance?.appContext;
const apis = appContext?.config.globalProperties.$apis;
 */
// 切换登录注册
const loginToogle = () => {
  tologin.value = !tologin.value;
};

// 提交表单
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('success!');
    } else {
      console.log('error submit!', fields);
    }
  });
  // alert('登录成功')
  // URLSearchParams 是一个对象，它主要用来处理 URL 的查询字符串
  // let params = new URLSearchParams()
  // params.append('name', ruleForm.username)
  // params.append('password', ruleForm.password)
  if (tologin.value) {
    // login() // 登录
    login({ nickname: ruleForm.nickname, password: ruleForm.password })
      .then(res => {
        console.log(res);
        if (res.code === 200) {
          sessionStorage.setItem('nickname', ruleForm.nickname);
          const token = 'Bearer ' + ruleForm.username + '-' + res.code;
          window.localStorage.setItem('token', token);
          router.push('/index');
          ElNotification({
            message: '登录成功！',
            type: 'success',
          });
        } else {
          ElNotification({
            message: '登录失败，请重试！',
            type: 'warning',
          });
        }
      })
      .catch(err => {
        console.log(err);
      });
  } else {
    // register() // 注册
    if (ruleForm.username === 'admin') {
      ElNotification({
        message: '用户名重复，请尝试修改用户名！',
        type: 'info',
      });
      // 聚焦到用户名输入框
      ruleFormRef.value.validateField('username');
    } else if (ruleForm.nickname === 'PlutoSSY') {
      ElNotification({
        message: '昵称重复，请尝试修改昵称！',
        type: 'info',
      });
      // 聚焦到昵称输入框
      ruleFormRef.value.validateField('nickname');
    } else {
      // 清空表单数据
      // ruleForm.username = '' // 用户名不清空
      ruleForm.nickname = '';
      ruleForm.password = '';
      ruleForm.confirmPwd = '';
      ElNotification({
        message: '注册成功，请登录吧！',
        type: 'success',
      });
      tologin.value = true;
    }
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
};
</script>

<style lang="scss" scoped>
@import url('@/assets/scss/loginAnimate.scss');

.container {
  position: absolute;
  display: flex;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  justify-content: center;
  align-items: center;

  .tips:first-child {
    border-radius: 10px 0 0 10px;
  }

  .tips:nth-child(2) {
    border-radius: 0 10px 10px 0;
  }

  .tips {
    width: 340px;
    height: 500px;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-around;

    .container-title {
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-around;

      h1 {
        font-family: 'STXingkai';
        font-size: 26px;
        font-weight: 700;
        color: #ef5508;
        margin-bottom: 5px;
      }

      p {
        font-family: '宋体';
        font-style: italic;
        font-size: 12px;
        font-weight: 400;
        color: #000;
      }
    }

    .container-img {
      width: calc(100% - 10px);

      img {
        width: 100%;
        object-fit: cover;
      }
    }

    .toLogin,
    .toRegister {
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      p {
        font-size: 16px;
        font-weight: 400;
        color: rgb(0, 136, 255);
      }

      .el-button {
        width: 100px;
        height: 36px;
        margin-top: 10px;
      }
    }
  }

  .login-register-wrap {
    position: absolute;
    top: 50%;
    transform: translate(-55.88%, -50%);
    width: 340px;
    height: 600px;
    // background-color: rgba(0, 0, 0, 0.9);
    border-radius: 10px;
    box-shadow: 0 5px 25px rgba(0, 0, 0, 0.5);
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: transform 0.5s ease-in-out;

    &::before {
      content: '';
      position: absolute;
      width: 100%;
      height: 100%;
      background: repeating-conic-gradient(from var(--a), transparent 0%, transparent 80%, #45f3ff 90%, #45f3ff 99.5%, transparent 100%);
      border-radius: 10px;
      // 动画种类有：ease、linear、ease-in、ease-out、ease-in-out、step-start、step-end、steps、cubic-bezier
      animation: animate 2.5s linear infinite;
    }

    .main {
      position: absolute;
      inset: 5px;
      overflow: hidden;
      border-radius: 10px;
      background-color: rgb(142, 224, 61);
      // background: url(@/assets/img/bg2.png);
      // background-attachment: fixed;
      // background-size: cover;
      // background-position: center;

      // &::before {
      //   content: '';
      //   position: absolute;
      //   inset: 5px;
      //   // background: url(@/assets/img/bg1.png);
      //   // background-attachment: fixed;
      //   // background-size: cover;
      //   // background-position: center;
      //   filter: blur(10px);
      // }
      .title {
        width: 100%;
        font-size: 30px;
        font-weight: 600;
        color: #fff;
        text-align: center;
        margin: 50px auto;
      }

      .content {
        // width: 100%;
        height: 250px;
        display: flex;
        align-items: center;
        border-radius: 5px;
        padding: 0 15px;

        .el-form {
          width: 100%;
          margin: 0 10px;

          .btn button {
            width: 100%;
            height: 36px;
          }
        }
      }
    }
  }
}
</style>
