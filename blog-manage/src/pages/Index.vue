<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:07
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-19 09:08:47
 * @FilePath: \blog-manage\src\pages\Index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div id="index-container">
    <my-header />
    <my-aside />
    <div class="content-box" :class="{ 'content-collapse': collapse }">
      <router-view />
    </div>
  </div>
</template>

<script setup lang="ts">
import eventBus from '@/assets/js/eventBus.js';
import store from '@/store/store';

let collapse = ref(false);
const emitter = eventBus();

let timer: any = null;

onMounted(() => {
  emitter.on('collapse', (val: boolean) => {
    collapse.value = val;
  });
});
nextTick(() => {
  // const indexElement: HTMLElement | null = document.getElementById('index-container');
  // const headerElement: HTMLElement | null = document.getElementById('header');
  const contentElement: HTMLElement | null = document.querySelector('.content-box');
  store.commit('table/setContentHeight', contentElement?.clientHeight);
  const tableHeaderElement: HTMLElement | null = document.querySelector('.el-table__header');
  console.log(tableHeaderElement?.clientHeight, 'tableHeaderElement?.clientHeight');
  store.commit('table/setTableHeaderHeight', '120');

  window.addEventListener('resize', () => {
    store.commit('table/setContentHeight', contentElement?.clientHeight);
    store.commit('table/setTableHeaderHeight', '120');
    timer = setTimeout(() => {
      store.commit('table/setTableHeight');
    }, 50);
  });
});
onUnmounted(() => {
  emitter.off('collapse');
  window.removeEventListener('resize', () => {});
  clearTimeout(timer);
});
</script>

<style lang="scss" scoped>
.content-box {
  position: absolute;
  left: 150px;
  right: 0;
  top: 70px;
  bottom: 0;
  overflow-x: hidden;
  -webkit-transition: left 0.3s ease-in-out;
  transition: left 0.3s ease-in-out;
  color: rgba($color: #000000, $alpha: 0.9);
  background: rgba(255, 255, 255, 0.8);
}

.content-collapse {
  left: 65px;
}
</style>
