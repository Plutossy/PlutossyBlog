<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-09 08:56:06
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-28 16:49:56
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
        <el-button @click="loginToggle">去登录</el-button>
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
        <el-button @click="loginToggle">去注册</el-button>
      </div>
    </div>

    <div class="login-register-wrap" :style="{ transform: tologin ? 'translate(-55.88%, -50%)' : 'translate(55.88%, -50%)' }">
      <div class="main">
        <div v-if="tologin" class="title login">LOGIN</div>
        <div v-else class="title register">REGISTER</div>
        <div class="content">
          <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" :label-width="tologin ? '70px' : '100px'" :label-position="tologin ? 'left' : 'right'" size="large">
            <el-form-item v-if="!tologin" label="用户名：" prop="username" size="large" class="animate__animated animate__bounceIn">
              <el-input v-model="ruleForm.username" placeholder="请输入用户名" clearable></el-input>
            </el-form-item>
            <el-form-item label="昵称：" prop="nickname" size="large">
              <el-input v-model="ruleForm.nickname" placeholder="请输入昵称" clearable></el-input>
            </el-form-item>
            <el-form-item v-if="!tologin" label="邮箱：" prop="email" size="large" class="animate__animated animate__bounceIn">
              <el-input v-model="ruleForm.email" clearable placeholder="请输入邮箱" />
            </el-form-item>
            <el-form-item label="密码：" prop="password" size="large">
              <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
            <el-form-item v-if="!tologin" label="确认密码：" prop="confirmPwd" size="large" class="animate__animated animate__bounceIn">
              <el-input type="password" v-model="ruleForm.confirmPwd" placeholder="请再次输入密码" show-password></el-input>
            </el-form-item>
            <div class="login-tools" v-if="tologin">
              <el-checkbox v-model="ruleForm.remember">记住密码</el-checkbox>
              <!-- <el-button type="primary" link style="font-weight: 400">忘记密码？</el-button> -->
            </div>
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
import Cookies from 'js-cookie';
import { encrypt, decrypt } from '@/utils/cryptoJs';
import { login, register } from '@/api/modules/user';
import store from '@/store/store';
import { FormInstance, FormRules } from 'element-plus';
import { clearForm } from '@/mixins';

interface RuleForm {
  // 表单数据类型
  username: string;
  nickname: string;
  email: string;
  password: string;
  confirmPwd: string;
  remember?: boolean;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例

let tologin = ref(true);
let ruleForm = reactive<RuleForm>({
  // 表单数据
  nickname: '',
  username: '',
  email: '',
  password: '',
  confirmPwd: '',
  remember: false,
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },
    { pattern: /^\S*$/, message: '密码不能包含空格', trigger: 'blur' },
  ],
  nickname: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { pattern: /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/, message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] },
  ],
  confirmPwd: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },
    { pattern: /^\S*$/, message: '密码不能包含空格', trigger: 'blur' },
    {
      validator: (_rule, value, callback) => {
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

onMounted(() => {
  getCookie();
});
// 切换登录注册
const loginToggle = () => {
  tologin.value = !tologin.value;
  if (tologin.value) {
    ruleFormRef.value.resetFields();
    clearForm(ruleForm);
  }
};

// 提交表单
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (tologin.value) {
        toLogin();
      } else {
        toRegister();
      }
      console.log('success!');
    } else {
      console.log('error submit!', fields);
      return false;
    }
  });
};

const toLogin = async () => {
  // URLSearchParams 是一个对象，它主要用来处理 URL 的查询字符串
  // let params = new URLSearchParams()
  // params.append('nickname', ruleForm.nickname)
  // params.append('password', ruleForm.password)
  try {
    const res = await login({ nickname: ruleForm.nickname, password: ruleForm.password });
    if (res.code === 200) {
      const token = 'Bearer ' + res.token;
      store.commit('user/setToken', token);
      setCookie();
      store.commit('user/setUserInfo', { id: res.data.id });
      ElNotification({
        message: '登录成功！',
        type: 'success',
        duration: 1000,
        showClose: true,
      });
      router.push('/index');
    } else {
      ElNotification({
        message: '登录失败，请重试！',
        type: 'warning',
        duration: 1000,
        showClose: true,
      });
    }
  } catch (err) {
    console.log(err);
  }
};
const toRegister = async () => {
  const { code, msg } = await register(ruleForm); // 注册
  if (code === 200) {
    ElNotification({
      message: '注册成功，请登录吧！',
      type: 'success',
      duration: 1000,
      showClose: true,
    });
    tologin.value = true;
  } else if (code === 400 && msg === '用户昵称已存在！') {
    ElNotification({
      message: msg,
      type: 'warning',
      duration: 1000,
      showClose: true,
    });
    // 聚焦到昵称输入框
    ruleFormRef.value.validateField('nickname');
  } else {
    ElNotification({
      message: '注册失败，请重试！',
      type: 'error',
      duration: 1000,
      showClose: true,
    });
  }
};

const setCookie = () => {
  if (ruleForm.remember) {
    Cookies.set('nickname', encrypt(ruleForm.nickname), { expires: 30 });
    Cookies.set('password', encrypt(ruleForm.password), { expires: 30 });
    Cookies.set('remember', ruleForm.remember.toString(), { expires: 30 });
  } else {
    Cookies.remove('nickname');
    Cookies.remove('password');
    Cookies.remove('remember');
  }
};
const getCookie = () => {
  const nickname = decrypt(Cookies.get('nickname'));
  const password = decrypt(Cookies.get('password'));
  const remember = Cookies.get('remember');
  ruleForm.nickname = nickname ?? '';
  ruleForm.password = password ?? '';
  ruleForm.remember = remember === 'true';
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
      display: flex;
      flex-direction: column;
      .title {
        width: 100%;
        font-size: 30px;
        font-weight: 600;
        color: #fff;
        text-align: center;
      }
      .login {
        margin: 100px auto;
      }
      .register {
        margin: 56px auto 48px;
      }

      .content {
        // width: 100%;
        // height: 250px;
        display: flex;
        align-items: center;
        border-radius: 5px;
        padding: 0 15px;

        .el-form {
          width: 100%;
          margin: 0 10px;
          .login-tools {
            display: flex;
            align-items: center;
            margin-bottom: 32px;

            .el-checkbox {
              font-weight: 400;
              // color: #aeaeae;

              &.is-checked :deep(.el-checkbox__label) {
                color: var(--el-checkbox-checked-bg-color) !important;
              }
            }
          }
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
