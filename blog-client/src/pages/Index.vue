<template>
  <div class="index-container">
    <recommend />
    <h1>Index</h1>
  </div>
</template>

<script>
import Recommend from '../components/recommend/Recommend.vue'
export default {
  components: {
    Recommend
  }
}
</script>

<style lang="scss" scoped>
.index-container {
  width: 100%;
  height: 100vh;
}
</style>