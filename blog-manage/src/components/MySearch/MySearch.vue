<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-22 11:33:53
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-22 12:36:39
 * @FilePath: \blog-manage\src\components\MySearch\MySearch.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="header-search">
    <el-button type="danger" @click="delAll" size="mini">批量删除</el-button>
    <el-input v-model="keyWord" size="mini" placeholder="请输入搜索内容..." class="search-input" clearable />
    <el-button type="primary">搜索</el-button>
    <!-- <el-button type="primary" @click="centerDialogVisible = true" size="mini">添加新用户</el-button> -->
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const keyWord = ref('')

const delAll = () => {
  console.log('delAll')
}
</script>

<style scoped>
.header-search {
  background-color: #fff;
  display: flex;
  align-items: center;
  padding: 20px;

  .search-input {
    width: 200px;
    margin: 0 20px;
  }
}
</style>