<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-26 14:39:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-31 11:37:12
 * @FilePath: \blog-manage\src\components\MyPreview\previewImg.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="preview-container animate__animated" :class="props.visible ? 'animate__fadeInLeft' : 'animate__fadeOutRight'" @click="closePreview">
    <slot />
    <el-button v-if="props.visible" type="info" link icon="CloseBold" size="large"></el-button>
  </div>
</template>

<script setup lang="ts">
const props = defineProps({
  visible: {
    type: Boolean,
    default: false,
  },
});
const emit = defineEmits(['update:visible']);

const closePreview = () => {
  emit('update:visible', false);
};
</script>

<style lang="scss" scoped>
.preview-container {
  background-color: rgba(0, 0, 0, 0.8);
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 999;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;

  > :first-child {
    height: 85%;
  }

  > .el-button {
    position: absolute;
    top: 20px;
    right: 20px;

    &:hover {
      color: #fff;
      background-color: rgb(212, 8, 8);
    }
  }
}
</style>
