<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-03-04 14:51:32
 * @FilePath: \PlutossyBlog\blog-manage\src\pages\Blog.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <component :is="tabs[curComponent]" :forward="forward" :blog-detail="blogDetailData"></component>
</template>

<script setup lang="ts">
import BlogList from './blog/BlogList.vue';
import BlogDetail from './blog/BlogDetail.vue';

const tabs: any = {
  BlogList,
  BlogDetail,
};
let curComponent = ref('BlogList');
let blogDetailData: any = reactive({});

const forward = (com: string, data?: any) => {
  curComponent.value = com;
  blogDetailData = data;
};
</script>

<style lang="scss" scoped></style>
