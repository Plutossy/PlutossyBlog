<template>
  <my-blog-list type="collect" back-history="/user" :id="id ? id.toString() : ''" />
</template>

<script setup lang="ts">
import MyBlogList from '@/pages/layout/MyBlogList.vue';

const route = useRoute();

console.log('route:', toRaw(route).query.id);
console.log(route.query.id);
const id = ref(toRaw(route.query.id));
</script>

<style scoped lang="scss"></style>
