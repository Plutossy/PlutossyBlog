<template>
  <div class="home-container">
    <my-header />
    <router-view></router-view>
    <scroll-top />
    <my-footer />
  </div>
</template>

<script>
import MyHeader from '../components/MyHeader.vue'
import MySwiper from '../components/MySwiper.vue'
import ScrollTop from '../components/ScrollTop.vue'
import MyFooter from '../components/MyFooter.vue'

export default {
  components: {
    MyHeader,
    MySwiper,
    ScrollTop,
    MyFooter
  }
}
</script>

<style lang="scss" scoped>
.home-container {
  width: 100%;
}
</style>