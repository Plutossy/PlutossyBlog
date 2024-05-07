<script lang="ts" setup>
import MySearch from './MySearch.vue';
import 'element-plus/theme-chalk/display.css';

import { ref, onMounted, onUnmounted, watch } from 'vue';

let btnHide = ref(true); //按钮显示隐藏

let navShow = ref(false);
let scroll = ref(0); //上下滚动
let up = ref(300); //开始隐藏位置
let screenWidth = ref(document.body.clientWidth); //屏幕宽度

let timer1: any = null; // 滚动防抖
let timer2: any = null; // 屏幕宽度防抖

onMounted(() => {
  // 监听（绑定）滚轮 滚动事件
  window.addEventListener('scroll', handleScroll, true);

  window.addEventListener('resize', handleSize, true);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
  window.removeEventListener('resize', handleSize);
});

watch(
  scroll,
  newValue => {
    if (newValue >= up.value) {
      navShow.value = true;
    } else {
      navShow.value = false;
    }
  },
  { immediate: true }
);

watch(
  screenWidth,
  newValue => {
    if (newValue <= 768) {
      up.value = 500;
    } else {
      up.value = 300;
    }
  },
  { immediate: true }
);

// 点击按钮，显示/隐藏导航栏
const toggleEvent = () => {
  btnHide.value = !btnHide.value;
};

const handleScroll = () => {
  // 防抖
  clearTimeout(timer1);
  timer1 = setTimeout(() => {
    // 页面滚动距顶部距离
    const scrollTop = window.scrollY || document.documentElement.scrollTop || document.body.scrollTop;
    scroll.value = scrollTop;
  }, 200);
};
const handleSize = () => {
  clearTimeout(timer2);
  timer2 = setTimeout(() => {
    // 页面滚动距顶部距离
    screenWidth.value = document.body.clientWidth;
  }, 200);
};
</script>

<template>
  <div class="header-nav animate__animated animate__fadeInDown" :class="{ animate__fadeOutUp: navShow }">
    <el-row class="header-container" :gutter="20" justify="space-around">
      <el-col :span="3" :xs="4" class="logo-box">
        <h2 class="logo">SSY_Blog</h2>
      </el-col>

      <!-- 导航栏 -->
      <el-col :span="16" class="hidden-sm-and-down">
        <el-row justify="space-around">
          <el-col :span="3" :xs="24">
            <router-link to="/home">
              <el-icon>
                <HomeFilled /> </el-icon
              >&nbsp;首页
            </router-link>
          </el-col>
          <el-col :span="3" :xs="24">
            <router-link to="/type">
              <el-icon>
                <Crop /> </el-icon
              >&nbsp;分类
            </router-link>
          </el-col>
          <el-col :span="3" :xs="24">
            <router-link to="/tag">
              <el-icon>
                <PriceTag /> </el-icon
              >&nbsp;标签
            </router-link>
          </el-col>
          <el-col :span="3" :xs="24">
            <router-link to="/clone">
              <el-icon>
                <CopyDocument /> </el-icon
              >&nbsp;归档
            </router-link>
          </el-col>
          <el-col :span="3" :xs="24">
            <router-link to="/music">
              <el-icon>
                <Headset /> </el-icon
              >&nbsp;音乐盒
            </router-link>
          </el-col>
          <el-col :span="3" :xs="24">
            <router-link to="/picture">
              <el-icon>
                <PictureFilled /> </el-icon
              >&nbsp;照片墙
            </router-link>
          </el-col>
          <el-col :span="3" :xs="24">
            <router-link to="/about">
              <el-icon>
                <UserFilled /> </el-icon
              >&nbsp;关于我
            </router-link>
          </el-col>
        </el-row>
      </el-col>

      <!-- 搜索框组件 -->
      <el-col :span="5" class="input hidden-sm-and-down">
        <my-search />
      </el-col>
      <!-- 按钮 -->
      <el-col :xs="4" :offset="16" class="mobile-hide-icon hidden-sm-and-up">
        <!-- 下拉菜单 -->
        <el-dropdown trigger="click" @visible-change="toggleEvent">
          <el-icon>
            <Expand v-show="btnHide" />
            <Fold v-show="!btnHide" />
          </el-icon>
          <template #dropdown>
            <el-dropdown-menu class="mobile-hide mobile-show">
              <el-dropdown-item>
                <router-link to="/home">
                  <el-icon>
                    <HomeFilled /> </el-icon
                  >&nbsp;首页
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/type">
                  <el-icon>
                    <Crop /> </el-icon
                  >&nbsp;分类
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/tag">
                  <el-icon>
                    <PriceTag /> </el-icon
                  >&nbsp;标签
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/clone">
                  <el-icon>
                    <CopyDocument /> </el-icon
                  >&nbsp;归档
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/music">
                  <el-icon>
                    <Headset /> </el-icon
                  >&nbsp;音乐盒
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/picture">
                  <el-icon>
                    <PictureFilled /> </el-icon
                  >&nbsp;照片墙
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/about">
                  <el-icon>
                    <UserFilled /> </el-icon
                  >&nbsp;关于我
                </router-link>
              </el-dropdown-item>
              <div class="input">
                <my-search />
              </div>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.header-nav {
  // position: fixed;
  position: sticky; // 粘性定位
  top: 0;
  z-index: 100;
  width: 100%;

  .header-container {
    // position: fixed;
    // position: sticky; // 粘性定位
    // top: 0;
    // z-index: 999;
    width: 100%;
    background-color: rgba($color: #000000, $alpha: 0.5);
    margin: 0 !important;
    padding: 0.8rem 5rem;
    border-bottom: 1px solid #b2b1b1;
    box-shadow: 0 1px #ccc;

    .logo-box {
      display: flex;
      justify-content: center;
      align-items: center;

      .logo {
        font-size: 28px;
        font-weight: 700;
        color: #f2711c;
        text-shadow: 1px 1px 1px #ccc;
      }
    }

    > .el-col:nth-child(2) {
      a {
        font-size: large;
        padding: 0.7rem 0;
        color: rgb(225, 224, 224);
        display: flex;
        justify-content: center;
        align-items: center;

        &:hover {
          color: #ffffff;
          background-color: #f2711c;
          // background-color: rgba($color: #000000, $alpha: 0.5);
          border-radius: 5px;
          cursor: pointer;
        }

        i {
          vertical-align: middle;
        }
      }

      .router-link-active {
        color: #ffffff;
        background-color: #f2711c;
        // background-color: rgba($color: #000000, $alpha: 0.4);
        border-radius: 5px;
        cursor: pointer;
      }
    }

    .input {
      display: flex;
      align-items: center;
    }

    .mobile-hide-icon {
      text-align: center;
      line-height: 100%;
      /* 取消i标签在移动端点击时的蓝色 */
      user-select: none;
      -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
      -webkit-user-select: none;
      -moz-user-focus: none;
      -moz-user-select: none;

      .el-dropdown {
        i {
          font-size: 30px;
          color: rgb(225, 224, 224);
          cursor: pointer;
        }
      }
    }
  }
}

.el-dropdown-menu {
  background-color: rgba($color: rgba(0, 0, 0), $alpha: 0.6);

  .el-dropdown-menu__item {
    a {
      padding: 0.5rem 0;
      font-size: 18px;
      font-weight: 540;
      color: rgb(225, 224, 224);

      &:hover {
        color: #f2711c;
      }
    }

    .router-link-active {
      font-size: 20px;
      color: #f2711c;
      width: 100%;
      background-color: rgba($color: #ffffff, $alpha: 0.3);
      border-radius: 5px;
      padding: 0.8rem 0.5rem;
      cursor: pointer;
    }
  }

  .input {
    padding: 1rem;
  }
}
</style>
