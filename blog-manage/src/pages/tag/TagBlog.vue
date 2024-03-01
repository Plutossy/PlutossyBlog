<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-02-29 18:40:34
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-03-01 09:41:19
 * @FilePath: \blog-manage\src\pages\tag\BlogList.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <my-blog-list type="tag-blog" back-history="/tag" :id="id.toString()" />
</template>

<script setup lang="ts">
import MyBlogList from '@/pages/layout/MyBlogList.vue';

const route = useRoute();

console.log('route:', toRaw(route).query.id);
console.log(route.query.id);
const id = ref(toRaw(route).query.id);
</script>

<style scoped lang="scss"></style>
