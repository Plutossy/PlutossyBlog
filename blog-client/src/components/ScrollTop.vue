<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-11-27 16:59:47
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-07 13:55:38
 * @FilePath: \blog-client\src\components\ScrollTop.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-backtop v-if="showBackTop" :right="60" :bottom="100" :visibility-height="500">
    <div class="content">
      <svg-icon class="backTop-icon" icon-class="back-top" size="50px" color="#474747" />
      <el-popover placement="left" trigger="hover" transition="custom">
        <template #reference>
          <svg-icon class="iconRotate" icon-class="setting" size="40px" color="#474747" @click.prevent="disClick" />
        </template>
        <template #default>
          <div class="setting-fire" @click="changeMouseAnimation">
            <svg-icon v-if="mouseAnimation" icon-class="fire-work-active" size="30px" />
            <svg-icon v-else icon-class="fire-work" size="30px" color="#474747" />
          </div>
        </template>
      </el-popover>
    </div>
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
      mousedown();
    }
  },
  methods: {
    // 阻止点击事件
    disClick(e) {
      e.stopPropagation();
      return false;
    },
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
/* 图标旋转 */
.iconRotate {
  animation: rotate 4s linear infinite;
}
.el-backtop {
  width: 0;
  height: 0;
  transition: all 0.5s;
  background-color: rgba($color: #000000, $alpha: 0);
  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    .backTop-icon {
      margin-bottom: 10px;
    }
  }
}
.setting-fire {
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-left: 5px;
  transition: all 0.5s;
  &:hover {
    transform: scale(1.2);
  }
}
</style>
<style>
/* 自定义弹出层动画 */
.custom-enter-active,
.custom-leave-active {
  transition: opacity 0.5s, transform 0.5s; /* 过渡效果 */
}
.custom-enter-from,
.custom-leave-to {
  opacity: 0;
  transform: translateX(20px);
}
</style>
