<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-09 09:42:08
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-31 17:57:17
 * @FilePath: \blog-manage\src\pages\Weclome.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="container">
    <el-row :gutter="20" class="mb-20">
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-cont-center">
              <div class="grid-num">1212</div>
              <div>用户总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-cont-center">
              <div class="grid-num">12</div>
              <div>分类总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-cont-center">
              <div class="grid-num">62</div>
              <div>标签总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-cont-center">
              <div class="grid-num">1231</div>
              <div>博客总篇数</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <my-chart1 />
    <div class="master"></div>
    <my-chart2 />
    <el-row :gutter="20" class="music-picture">
      <el-col :span="12"><my-chart3 :title="'照片数量'" :data="PictureData" :color="'rgb(84, 112, 198)'" /></el-col>
      <el-col :span="12"><my-chart3 :title="'歌曲数量'" :data="SongData" :color="'#45f3ff'" /></el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import MyChart1 from '../components/MyECharts/chart1.vue';
import MyChart2 from '../components/MyECharts/chart2.vue';
import MyChart3 from '../components/MyECharts/chart3.vue';

const PictureData = reactive({ value: 15, name: '照片' });
const SongData = reactive({ value: 23, name: '歌曲' });
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 0 10px;

  .grid-content {
    height: 50px;
    display: flex;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.8);

    .grid-cont-center {
      flex: 1;
      text-align: center;
      font-size: 14px;
      color: darkgray;

      .grid-num {
        font-size: 30px;
        font-weight: bold;
      }
    }
  }

  // 定义动画
  @keyframes changeLine {
    0% {
      height: 100%;
    }

    100% {
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      height: 0;
    }
  }

  .master {
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    z-index: 999;
    animation: changeLine 0.5s ease-in-out forwards;
    // animation-fill-mode: forwards; // 停在最后一帧
  }
  .music-picture {
    height: 450px;
  }
}
</style>
