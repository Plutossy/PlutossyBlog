<template>
  <el-backtop :right="80" :bottom="80" :visibility-height="400" />
</template>


<script>
export default {
  name: 'scroll-top'
}
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
      margin-top: -0.5rem;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      overflow: hidden;
      writing-mode: vertical-rl;
    }
  }
}
</style>
