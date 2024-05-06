<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-06 17:27:12
 * @FilePath: \blog-client\src\components\ScrollTop.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-backtop v-if="showBackTop" :right="80" :bottom="80" :visibility-height="500">
    <i aria-hidden="true" @click="changeMouseAnimation()">12</i>
  </el-backtop>
  <!-- 点击动画 -->
  <canvas v-if="mouseAnimation" id="mousedown" style="position: fixed; left: 0; top: 0; pointer-events: none; z-index: 1000"> </canvas>
</template>

<script>
import { mapGetters } from 'vuex';
import mousedown from '@/utils/mousedown';

export default {
  name: 'scroll-top',
  data() {
    return {
      mouseAnimation: false,
    };
  },
  computed: {
    ...mapGetters('m_back', ['showBackTop']),
  },
  created() {
    if (this.mouseAnimation) {
      this.$nextTick(() => {
        mousedown();
      });
    }
  },
  methods: {
    changeMouseAnimation() {
      this.mouseAnimation = !this.mouseAnimation;
      if (this.mouseAnimation) {
        this.$nextTick(() => {
          mousedown();
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.el-backtop {
  width: 3.5rem;
  height: 3.5rem;
  transition: all 0.5s;
  // color: #fff;
  // background-color: rgb(83, 170, 231);
  // &:hover {
  //   background-color: rgb(83, 170, 231);
  //   border-radius: 2rem;
  //   height: 8rem;
  //   display: flex;
  //   flex-direction: column;
  //   align-items: center;
  //   &::after {
  //     content: '回到顶部';
  //     width: 100%;
  //     height: 100%;
  //     color: #ebebeb;
  //     margin-top: -1rem;
  //     display: flex;
  //     flex-direction: column;
  //     flex-wrap: nowrap;
  //     justify-content: center;
  //     align-items: center;
  //     overflow: hidden;
  //     white-space: nowrap;
  //     writing-mode: vertical-rl;
  //   }
  // }
}
</style>
