<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-16 17:28:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-16 17:54:11
 * @FilePath: \blog-manage\src\components\SvgIcon\SvgIcon.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <svg aria-hidden="true" class="svg-icon" :style="'width:' + size + '; height:' + size + '; color:' + color">
    <use :xlink:href="symbolId" :fill="color" class="svg-icon-use" />
  </svg>
</template>

<script setup>
const props = defineProps({
  prefix: {
    type: String,
    default: 'icon',
  },
  iconClass: {
    type: String,
    required: false,
    default: '',
  },
  color: {
    type: String,
    default: '',
  },
  size: {
    type: String,
    default: '1em',
  },
});

const symbolId = computed(() => `#${props.prefix}-${props.iconClass}`);
</script>

<style lang="scss" scoped>
.svg-icon {
  display: inline-block;
  width: 1em;
  height: 1em;
  overflow: hidden;
  vertical-align: -0.15em; /* 因icon大小被设置为和字体大小一致，而span等标签的下边缘会和字体的基线对齐，故需设置一个往下的偏移比例，来纠正视觉上的未对齐效果 */
  outline: none;
  fill: currentcolor; /* 定义元素的颜色，currentColor是一个变量，这个变量的值就表示当前元素的color值，如果当前元素未设置color值，则从父元素继承 */
  .svg-icon-use {
    fill: currentColor;
  }
}
</style>
