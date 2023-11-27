<template>
  <div class="search-container">
    <input type="text" placeholder="Search...." v-model="key" />
    <el-icon size="18">
      <Search />
    </el-icon>
  </div>
</template>

<script>
export default {
  name: 'MySearch',
  data() {
    return {
      key: ''
    }
  }
}
</script>

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
  }
}
</style>