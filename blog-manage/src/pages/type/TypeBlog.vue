<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-02-29 16:01:59
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-30 09:27:28
 * @FilePath: \blog-manage\src\pages\type\BlogList.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <my-blog-list type="type-blog" back-history="/type" :id="id ? id.toString() : ''" />
</template>

<script setup lang="ts">
import MyBlogList from '@/pages/layout/MyBlogList.vue';

const route = useRoute();

console.log('route:', toRaw(route).query.id);
console.log(route.query.id);
const id = ref(toRaw(route).query.id);
</script>

<style scoped lang="scss"></style>
