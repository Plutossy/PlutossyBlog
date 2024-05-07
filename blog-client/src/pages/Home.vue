<template>
  <div class="home-container">
    <my-swiper />
    <recommend />
    <el-row :gutter="20">
      <el-col :span="16" :xs="24" class="animate__animated animate__fadeInLeftBig">
        <h2 v-show="recommend">欢迎来到我的博客星球！</h2>
        <h2 v-show="!recommend">这有我的最新博客发布！</h2>
        <introduction />
        <introduction />
        <introduction />
        <introduction />
        <introduction />
        <el-pagination :page-size="5" :pager-count="5" layout="prev, pager, next" :total="28" prev-text="上一页" next-text="下一页" :hide-on-single-page="true" background />
      </el-col>
      <el-col :span="8" :xs="24" class="animate__animated animate__fadeInRightBig animate__delay-1s">
        <aside />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import MySwiper from '../components/MySwiper.vue';
import Recommend from '../components/recommend/Recommend.vue';
import Aside from './Aside.vue';
import Introduction from '../components/introduction/Introduction.vue';

const recommend = ref(true);
let timer: any = null;

timer = setInterval(() => {
  recommend.value = !recommend.value;
}, 3000);

onUnmounted(() => {
  clearInterval(timer);
});
</script>

<style lang="scss" scoped>
.home-container {
  width: 100%;
  > .el-row {
    margin: 1rem !important;
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
        // animation-delay: 2s;
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

      // 分页
      > .el-pagination {
        margin-bottom: 2rem;
      }
    }
    > .el-col:last-child {
      height: 100%;
    }
  }
}
</style>
