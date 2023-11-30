<template>
  <div class="index-container">
    <my-swiper />
    <recommend />
    <el-row :gutter="20" align="middle">
      <el-col :span="16" :xs="24">
        <h2>欢迎来到我的博客星球！</h2>
        <introduction />
        <introduction />
        <introduction />
        <introduction />
        <introduction />
      </el-col>
      <el-col :span="8" :xs="24">
        <aside-vue />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MySwiper from '../components/MySwiper.vue'
import Recommend from '../components/recommend/Recommend.vue'
import AsideVue from './Aside.vue'
import Introduction from '../components/introduction/Introduction.vue'
export default {
  components: {
    MySwiper,
    Recommend,
    AsideVue,
    Introduction
  }
}
</script>

<style lang="scss" scoped>
.index-container {
  width: 100%;
  > .el-row {
    margin: 1rem 0 !important;
    > .el-col:first-child {
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      > h2 {
        position: relative;
        font-size: 2.3rem;
        font-weight: 560;
        text-align: center;
        font-family: 'STXingkai', serif;
        color: orange;

        // 打字机效果
        overflow: hidden;
        width: 0;
        white-space: nowrap;
        animation: width 2s steps(11) forwards;
        animation-delay: 2s;
        &::after {
          content: '';
          position: absolute;
          right: 0;
          height: 80%;
          border-right: 2px solid white;
          animation: showInfinite 1s infinite both;
        }
      }
      @keyframes width {
        0% {
          width: 0;
        }
        100% {
          width: 11em; //  单位em,表示一个字体的宽度
        }
      }
      @keyframes showInfinite {
        0% {
          opacity: 1;
        }
        100% {
          opacity: 0;
        }
      }
    }
    > .el-col:last-child {
      height: 100%;
    }
  }
}
</style>