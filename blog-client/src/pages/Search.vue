<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-05 17:15:02
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-07 13:56:25
 * @FilePath: \blog-client\src\pages\Search.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<script setup lang="ts">
import Introduction from '../components/introduction/Introduction.vue';
import Parallax from '../components/parallax/parallax.vue';
import { useRoute } from 'vue-router';
import { onMounted, toRaw } from 'vue';

const route = useRoute();

onMounted(() => {
  // 打印
  console.log('route:', toRaw(route).query)
})

</script>

<template>
  <div class="search-container">
    <Parallax />
    <Introduction />
    <Introduction />
    <Introduction />
    <el-pagination :page-size="5" :pager-count="5" layout="prev, pager, next" :total="28" prev-text="上一页" next-text="下一页"
      :hide-on-single-page="true" background />
  </div>
</template>

<style lang="scss" scoped>
.search-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>