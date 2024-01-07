<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-07 17:12:57
 * @FilePath: \blog-client\src\components\MySearch.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';

let key = ref('')
const searchInput: any = ref(null)
const router = useRouter();

onMounted(() => {
  // ctrl + f 光标跳转到搜索框
  document.addEventListener('keydown', (e) => {
    if (e.ctrlKey && e.key === 'f') {
      searchInput.value?.focus()
    }
  })
})

const handleSearch = () => {
  if (key.value === '') {
    key.value = ''
    ElMessage({
      showClose: true,
      message: '搜索内容不能为空！',
      type: 'warning',
    })
  } else {
    router.push({
      path: '/search',
      query: {
        key: key.value
      }
    })
  }
}
const keyBoard = (e: any) => {
  // 回车键
  if (e.keyCode === 13) {
    handleSearch()
  }
}
</script>

<template>
  <div class="search-container">
    <input ref="searchInput" type="text" placeholder="Search...." v-model.trim="key" @keyup="keyBoard" />
    <el-icon size="18" @click="handleSearch">
      <Search />
    </el-icon>
  </div>
</template>

<style lang="scss" scoped>
.search-container {
  display: flex;
  align-items: center;

  input {
    width: 200px;
    height: 32px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 0 24px 0 10px;
    outline: none;
    overflow: hidden;
  }

  i {
    color: #757373;
    margin-left: -24px;
    vertical-align: middle;
    cursor: pointer;
    &:hover {
      color: #f2711c !important;
    }
  }
}
</style>