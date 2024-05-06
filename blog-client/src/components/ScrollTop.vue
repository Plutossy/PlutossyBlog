<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-06 16:34:08
 * @FilePath: \blog-client\src\components\ScrollTop.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-backtop v-if="showBackTop" :right="80" :bottom="80" :visibility-height="500" />
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'scroll-top',
  computed: {
    ...mapGetters('m_back', ['showBackTop']),
  },
};
</script>

<style lang="scss" scoped>
.el-backtop {
  width: 3.5rem;
  height: 3.5rem;
  color: #fff;
  background-color: rgb(83, 170, 231);
  transition: height 0.3s;
  &:hover {
    background-color: rgb(83, 170, 231);
    border-radius: 2rem;
    height: 8rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    &::after {
      content: '回到顶部';
      width: 100%;
      height: 100%;
      color: #ebebeb;
      margin-top: -1rem;
      display: flex;
      flex-direction: column;
      flex-wrap: nowrap;
      justify-content: center;
      align-items: center;
      overflow: hidden;
      white-space: nowrap;
      writing-mode: vertical-rl;
    }
  }
}
</style>
