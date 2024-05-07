<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-29 14:57:14
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-07 17:33:55
 * @FilePath: \blog-client\src\components\recommend\Recommend.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="recommend-container animate__animated animate__bounceInUp">
    <el-row align="middle" justify="center">
      <h1>最新推荐</h1>
    </el-row>
    <el-row align="middle" :gutter="12" justify="space-around">
      <el-col :span="7" :xs="20">
        <introduction type="recommend" />
      </el-col>
      <el-col :span="7" :xs="20">
        <introduction type="recommend" />
      </el-col>
      <el-col :span="7" :xs="20">
        <introduction type="recommend" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Introduction from '../introduction/Introduction.vue';
export default {
  name: 'Recommend',
  components: {
    Introduction,
  },
};
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
