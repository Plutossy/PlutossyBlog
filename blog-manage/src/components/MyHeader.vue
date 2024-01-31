<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:48:58
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-19 22:40:26
 * @FilePath: \blog-manage\src\components\MyHeader.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<script setup lang="ts">
import eventBus from '../assets/js/eventBus';

let collapse = ref(false); //不折叠
const emitter = eventBus();
let fullscreen = ref(false); //不全屏
const router = useRouter();

// 全屏后点击esc退出全屏，也能监听到
onMounted(() => {
  window.addEventListener('resize', () => {
    let isFull =
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
});

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

// 退出登录
const hadleCommand = (command: string) => {
  if (command === 'logout') {
    console.log('退出登录成功！');
    router.push('/login');
  }
};
</script>

<template>
  <div class="header-container">
    <!-- 折叠图片 -->
    <div class="collapse-btn" @click="collapseChange">
      <el-icon v-if="!collapse">
        <Fold />
      </el-icon>
      <el-icon v-else>
        <Expand />
      </el-icon>
    </div>
    <div class="logo">SSY_Blog 后台管理</div>
    <div class="header-right">
      <div class="btn-fullscreen" @click="handleFullScreen">
        <el-tooltip :content="fullscreen ? '取消全屏' : '全屏'" placement="bottom">
          <img v-if="!fullscreen" src="@/assets/img/svg/screen.svg" />
          <img v-else src="@/assets/img/svg/exitscreen.svg" />
        </el-tooltip>
      </div>
      <div class="user-avator">
        <img src="@/assets/img/head.jpg" alt="管理员头像" />
      </div>
      <el-dropdown class="user-name" trigger="click" @command="hadleCommand">
        <span class="el-dropdown-link">
          Plutossy<el-icon class="el-icon--right">
            <CaretBottom />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
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

      img {
        width: 30px;
        height: 30px;
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
</style>
