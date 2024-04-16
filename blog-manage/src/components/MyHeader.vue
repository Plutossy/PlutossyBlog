<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:48:58
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-16 17:58:29
 * @FilePath: \blog-manage\src\components\MyHeader.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<script setup lang="ts">
import { logout, getUserInfo, sendEmail, verifyPwd, updatePwd } from '@/api/modules/user';
import eventBus from '@/assets/js/eventBus';
import store from '@/store/store';
import { FormInstance, FormRules } from 'element-plus';
import { User, Message, Cellphone, Unlock, Lock } from '@element-plus/icons-vue';
import { clearForm } from '@/mixins';
import { keysOf } from 'element-plus/es/utils/objects.mjs';

let collapse = ref(false); //不折叠
const emitter = eventBus();
let fullscreen = ref(false); //不全屏
const router = useRouter();

interface userType {
  [key: string]: any;
}
let userInfo = reactive<userType>({});
let drawer = ref(false);

watch(drawer, val => {
  if (!val) {
    loading.value = false;
    drawer.value = false;
    clearForm(setPwdform);
  }
});

interface RuleForm {
  // nickname: string;
  email: string;
  captcha: string;
  password: string;
  newPassword1: string;
  newPassword2: string;
}
const setPwdRef = ref<FormInstance>();
let setPwdform = reactive<RuleForm>({
  // nickname: '',
  email: '',
  captcha: '',
  password: '',
  newPassword1: '',
  newPassword2: '',
});
let showCaptcha = ref(false); // 是否显示验证码
let loadingCaptcha = ref(false); // 获取验证码按钮loading
let loading = ref(false);

const validateEmail = (_rule: any, value: string, callback: any) => {
  if (value === userInfo.email) {
    callback();
  } else {
    callback(new Error('邮箱错误'));
  }
};

// 验证旧密码是否正确
let pwdIsTrue = ref(false);
const verifyPassword = async () => {
  try {
    const { code } = await verifyPwd({ nickname: userInfo.nickname, password: setPwdform.password });
    if (code === 200) {
      pwdIsTrue.value = true;
    } else {
      pwdIsTrue.value = false;
    }
  } catch (error) {
    pwdIsTrue.value = false;
    console.log(error);
  }
};
let timer: any; // 定时器
const validatePassword = (_rule: any, _value: string, callback: any) => {
  verifyPassword();
  // 解决verifyPassword后执行的bug
  timer = setTimeout(() => {
    pwdIsTrue.value ? callback() : callback(new Error('密码错误'));
  }, 100);
};
const validatePwdAgain = (_rule: any, value: string, callback: any) => {
  if (value === setPwdform.newPassword1) {
    callback();
  } else {
    callback(new Error('两次输入密码不一致'));
  }
};
const rules = reactive<FormRules<RuleForm>>({
  // nickname: [
  //   { required: true, message: '请输入用户昵称', trigger: 'blur' },
  //   { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  // ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '邮箱格式不正确', trigger: 'blur' },
    { validator: validateEmail, trigger: 'blur' },
  ],
  captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
  password: [
    { required: true, message: '请输入旧密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在6-20个字符之间', trigger: 'blur' },
    { validator: validatePassword, trigger: 'blur' },
  ],
  newPassword1: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在6-20个字符之间', trigger: 'blur' },
  ],
  newPassword2: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在6-20个字符之间', trigger: 'blur' },
    { validator: validatePwdAgain, trigger: 'blur' },
  ],
});

// 全屏后点击esc退出全屏，也能监听到
onMounted(() => {
  // 获取用户信息
  getCurUserInfo();
  window.addEventListener('resize', () => {
    // fullscreenElement属性是当前全屏的元素，如果当前没有元素全屏，返回null
    let isFull =
      document.fullscreenElement ||
      document.fullScreen ||
      document.mozFullScreen ||
      //谷歌浏览器及Webkit内核浏览器
      document.webkitIsFullScreen ||
      document.webkitRequestFullScreen ||
      document.mozRequestFullScreen ||
      document.msFullscreenEnabled;
    if (isFull !== undefined) {
      fullscreen.value = true;
    } else {
      fullscreen.value = false;
    }
  });
});

onUnmounted(() => {
  window.removeEventListener('resize', () => {});
  clearTimeout(timer);
});

const getCurUserInfo = async () => {
  try {
    const userId = store.getters['user/userInfo'].id;
    const { code, data } = await getUserInfo({ id: userId });
    if (code === 200) {
      store.commit('user/setUserInfo', data);
      // 处理用户信息 响应式不生效问题
      // userInfo = reactive(data);
      keysOf(data).forEach(key => {
        userInfo[key.toString()] = data[key];
      });
    }
  } catch (error) {
    console.log(error);
  }
};

// 折叠事件
const collapseChange = () => {
  collapse.value = !collapse.value;
  emitter.emit('collapse', collapse.value);
};

// 全屏事件
const handleFullScreen = () => {
  if (fullscreen.value) {
    // 取消全屏
    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document.mozCancelFullScreen) {
      // Firefox (works in nightly)
      document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
      // Webkit (works in Safari5.1 and Chrome 15)
      document.webkitCancelFullScreen();
    } else if (document.msExitFullscreen) {
      // IE11
      document.msExitFullscreen();
    }
    fullscreen.value = false;
  } else {
    // 全屏
    let element = document.documentElement;
    if (element.requestFullscreen) {
      element.requestFullscreen();
    } else if (element.mozRequestFullScreen) {
      // Firefox (works in nightly)
      element.mozRequestFullScreen();
    } else if (element.webkitRequestFullScreen) {
      // Webkit (works in Safari5.1 and Chrome 15)
      element.webkitRequestFullScreen();
    } else if (element.msRequestFullscreen) {
      // IE11
      element.msRequestFullscreen();
    }
    fullscreen.value = true;
  }
};

// 退出登录--设置密码
const hadleCommand = async (command: string) => {
  if (command === 'logout') {
    try {
      const { code } = await logout();
      if (code === 200) {
        console.log('退出登录成功！');
        store.dispatch('user/removeToken');
        store.dispatch('user/removeUserInfo');
        router.push('/login');
      }
    } catch (error) {
      console.log(error);
      console.log('退出登录失败！');
    }
  } else if (command === 'setPassword') {
    // router.push('/setPassword');
    drawer.value = true;
  }
};

// 取消修改密码
const cancelForm = (formEl: FormInstance | undefined) => {
  // 清除校验结果
  if (formEl) {
    formEl.clearValidate();
    formEl.resetFields();
    clearForm(setPwdform);
  }
  drawer.value = false;
};

// 发起修改密码请求
const updatePassword = async () => {
  try {
    loading.value = true;
    const { code } = await updatePwd({
      id: userInfo.id,
      password: setPwdform.password,
      newPassword: setPwdform.newPassword2,
      email: setPwdform.email,
      captcha: setPwdform.captcha,
    });
    if (code === 200) {
      // 清空表单
      clearForm(setPwdform);
      loading.value = false;
      drawer.value = false;
      ElNotification({
        message: '修改密码成功！',
        type: 'success',
        duration: 1000,
        showClose: true,
      });
    }
  } catch (error) {
    console.log(error);
    console.log('修改密码失败！');
  } finally {
    loading.value = false;
  }
};

// 提交修改密码
const setPassword = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!!');
      updatePassword();
    } else {
      console.log('error submit!!', fields);
      return false;
    }
  });
};

// 发送验证码
const sendCaptcha = async () => {
  loadingCaptcha.value = true;
  try {
    const { code } = await sendEmail({ id: userInfo.id, email: setPwdform.email });
    if (code === 200) {
      showCaptcha.value = true;
      loadingCaptcha.value = false;
    }
  } catch (error) {
    loadingCaptcha.value = false;
    console.log(error);
    console.log('获取验证码失败！');
  } finally {
    loadingCaptcha.value = false;
  }
};
</script>

<template>
  <div class="header-container switch-item">
    <!-- 折叠图片 -->
    <div class="collapse-btn" @click="collapseChange">
      <el-icon v-if="!collapse" class="switch-item">
        <Fold />
      </el-icon>
      <el-icon v-else class="switch-item">
        <Expand />
      </el-icon>
    </div>
    <div class="logo">SSY_Blog 后台管理</div>
    <div class="header-right">
      <div class="btn-fullscreen" @click="handleFullScreen">
        <el-tooltip :content="fullscreen ? '取消全屏' : '全屏'" placement="bottom">
          <svg-icon v-if="!fullscreen" icon-class="screen" size="30px" class="switch-item-color" />
          <svg-icon v-else icon-class="exitscreen" size="30px" class="switch-item-color" />
        </el-tooltip>
      </div>
      <div class="user-avator">
        <img src="@/assets/img/head.jpg" alt="管理员头像" />
      </div>
      <el-dropdown class="user-name" trigger="click" @command="hadleCommand">
        <span class="el-dropdown-link switch-item">
          {{ userInfo.nickname }}
          <el-icon class="el-icon--right">
            <CaretBottom />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="setPassword">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
  <el-drawer v-model="drawer" direction="rtl" :show-close="false" style="height: 75vh">
    <template #header="{ titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">修改密码</h4>
    </template>
    <div class="demo-drawer__content">
      <el-form :model="setPwdform" ref="setPwdRef" :rules="rules">
        <el-form-item>
          <el-input v-model="userInfo.nickname" disabled placeholder="用户昵称" :prefix-icon="User" />
        </el-form-item>
        <el-form-item prop="email" class="email-item">
          <el-input v-model="setPwdform.email" class="item-input" clearable placeholder="用户邮箱" :prefix-icon="Message" />
          <el-button :loading="loadingCaptcha" type="primary" @click="sendCaptcha" class="item-btn">获取验证码</el-button>
        </el-form-item>
        <el-form-item v-if="setPwdform.email === userInfo.email && showCaptcha" prop="captcha" class="animate__animated animate__bounceIn">
          <el-input v-model="setPwdform.captcha" clearable placeholder="验证码" :prefix-icon="Cellphone" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="setPwdform.password" type="password" placeholder="旧密码" :prefix-icon="Lock" clearable />
        </el-form-item>
        <el-form-item prop="newPassword1">
          <el-input v-model="setPwdform.newPassword1" type="password" placeholder="新密码" :prefix-icon="Unlock" clearable />
        </el-form-item>
        <el-form-item prop="newPassword2" v-if="setPwdform.newPassword1" class="animate__animated animate__bounceIn">
          <el-input v-model="setPwdform.newPassword2" type="password" placeholder="请再次输入新密码" :prefix-icon="Lock" clearable />
        </el-form-item>
      </el-form>
      <div class="demo-drawer__footer">
        <el-button @click="cancelForm(setPwdRef)">取消</el-button>
        <el-button type="primary" :loading="loading" @click="setPassword(setPwdRef)">{{ loading ? '提交中 ...' : '提交' }}</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<style lang="scss" scoped>
* {
  /* 取消标签点击时的蓝色 */
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
  -webkit-user-select: none;
  -moz-user-focus: none;
  -moz-user-select: none;
}

.header-container {
  position: relative;
  background-color: rgba($color: #000000, $alpha: 0.5);
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: rgba($color: #ffffff, $alpha: 0.9);
  border-bottom: 1px solid #b2b1b1;
  box-shadow: 0 1px #ccc;
  display: flex;
  align-items: center;

  .collapse-btn {
    margin-left: 48px;
    margin-right: 24px;
    cursor: pointer;
    display: flex;
    align-items: center;

    i {
      color: #ffffff;
    }
  }

  .logo {
    text-align: center;
  }

  .header-right {
    flex: 1;
    padding-right: 48px;
    display: flex;
    align-items: center;
    justify-content: flex-end;

    .btn-fullscreen {
      margin-right: 5px;
      display: flex;
      align-items: center;

      :deep(.el-only-child__content) {
        display: flex;
        align-items: center;
      }
      .svg-icon {
        cursor: pointer;
      }
    }

    .user-avator {
      margin-left: 20px;
      display: flex;
      align-items: center;

      img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
      }
    }

    .user-name {
      margin-left: 10px;
    }

    .el-dropdown-link {
      color: rgba($color: #ffffff, $alpha: 0.9);
      display: flex;
      align-items: center;
      cursor: pointer;
    }
  }
}

:v-deep(.el-drawer) {
  position: relative;
}
.demo-drawer__content {
  .email-item {
    display: flex;
    .item-input {
      flex: 3;
    }
    .item-btn {
      flex: 1;
      height: 32px;
      margin-left: 8px;
    }
  }
  .demo-drawer__footer {
    position: absolute;
    right: 0;
    bottom: 0;
    margin: 32px;
  }
}
</style>
