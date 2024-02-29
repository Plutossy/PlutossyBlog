<template>
  <my-blog-list type="type-blog" back-history="/type" :id="id" />
</template>

<script setup lang="ts">
import MyBlogList from '@/pages/layout/MyBlogList.vue';

const route = useRoute();

console.log('route:', toRaw(route).query.userId);
console.log(route.query.id);
const id = ref(toRaw(route.query.id));
</script>

<style scoped lang="scss"></style>
