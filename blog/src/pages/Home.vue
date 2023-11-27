<template>
  <div class="home-container">
    <my-header />
    <router-view></router-view>
  </div>
</template>

<script>
import MyHeader from '../components/MyHeader.vue'
export default {
  components: {
    MyHeader
  }
}
</script>

<style lang="scss">
.home-container {
  width: 100%;
  height: 100rem;
}
</style>