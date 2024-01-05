<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-05 15:59:51
 * @FilePath: \blog-client\src\App.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<script setup>
import MyHeader from '@/components/MyHeader.vue'
import ScrollTop from '@/components/ScrollTop.vue'
import MyFooter from '@/components/MyFooter.vue'
import PlayBar from '@/components/PlayBar.vue'
</script>

<template>
  <div>
    <my-header />
    <router-view></router-view>
    <play-bar />
    <scroll-top />
    <my-footer />
  </div>
</template>

<style lang="scss">
// * {
//   margin: 0;
//   padding: 0;
// }
</style>
