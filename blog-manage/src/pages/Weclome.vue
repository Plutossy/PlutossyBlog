<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-09 09:42:08
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-19 18:31:50
 * @FilePath: \blog-manage\src\pages\Weclome.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="container">
    <my-chart1 />
    <div class="master"></div>
    <my-chart2 />
  </div>
</template>

<script setup lang="ts">
import MyChart1 from '../components/MyECharts/chart1.vue';
import MyChart2 from '../components/MyECharts/chart2.vue';

</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 10px;

  // 定义动画
  @keyframes changeLine {
    0% {
      height: 100%;
    }

    100% {
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      height: 0.5px;
      box-shadow: 0 0.5px 0.5px #a8a8a8;
    }
  }

  .master {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, .5);
    z-index: 999;
    animation: changeLine .3s ease-in-out forwards;
    // animation-fill-mode: forwards; // 停在最后一帧
  }
}
</style>