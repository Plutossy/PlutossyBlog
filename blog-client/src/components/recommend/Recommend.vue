<template>
  <div class="recommend-container animate__animated animate__bounceInUp">
    <el-row align="middle" justify="center">
      <h1>最新推荐</h1>
    </el-row>
    <el-row align="middle" :gutter="20" justify="space-around">
      <el-col :span="7" :xs="20">
        <introduction />
      </el-col>
      <el-col :span="9" :xs="20">
        <introduction />
      </el-col>
      <el-col :span="7" :xs="20">
        <introduction />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Introduction from '../introduction/Introduction.vue'
export default {
  name: 'Recommend',
  components: {
    Introduction
  }
}
</script>

<style lang="scss" scoped>
.recommend-container {
  width: 100%;
  border-bottom: 1px solid #ccc;
  > .el-row:first-child {
    color: whitesmoke;
    text-align: center;
  }
}
</style>