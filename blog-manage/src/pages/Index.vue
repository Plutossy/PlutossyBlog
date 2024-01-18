<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:07
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-17 16:39:29
 * @FilePath: \blog-manage\src\pages\Index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-16 18:24:09
 * @FilePath: \PlutossyBlog\blog-manage\src\pages\Blog.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <my-header />
    <my-aside />
    <div class="content-box" :class="{'content-collapse': collapse}">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import MyHeader from '../components/MyHeader.vue'
import MyAside from '../components/MyAside.vue'
import { ref , onMounted, onUnmounted } from 'vue'
import eventBus from '../assets/js/eventBus.js'

let collapse = ref(false)
const emitter = eventBus()

onMounted(() => {
  emitter.on('collapse', (val: boolean) => {
    collapse.value = val
  })
})
onUnmounted(() => {
  emitter.off('collapse')
})
</script>

<style lang="scss" scoped>
.content-box {
  position: absolute;
  left: 150px;
  right: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
  -webkit-transition: left .3s ease-in-out;
  transition: left .3s ease-in-out;
  background: #f0f0f0;
}
.content-collapse {
  left: 65px;
}
</style>