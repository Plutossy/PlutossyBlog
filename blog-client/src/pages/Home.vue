<template>
  <div class="home-container">
    <swiper />
    <my-header />
    <router-view></router-view>
    <scroll-top />
  </div>
</template>

<script>
import MyHeader from '../components/MyHeader.vue'
import Swiper from '../components/Swiper.vue'
import ScrollTop from '../components/ScrollTop.vue'
export default {
  components: {
    MyHeader,
    Swiper,
    ScrollTop
  }
}
</script>

<style lang="scss">
.home-container {
  width: 100%;
  height: 100rem;
}
</style>