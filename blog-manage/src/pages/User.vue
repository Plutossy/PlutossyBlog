<template>
  <el-container>
    <el-header>
      <MySearch />
    </el-header>
    <el-main>user</el-main>
  </el-container>
</template>

<script setup lang="ts">
import MySearch from '../components/MySearch/MySearch.vue';
</script>

<style lang="scss" scoped>

:deep(.el-header) {
  width: 100%;
}
</style>