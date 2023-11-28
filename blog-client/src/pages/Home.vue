<template>
  <div class="home-container">
    <my-swiper />
    <my-header />
    <router-view></router-view>
    <scroll-top />
  </div>
</template>

<script>
import MySwiper from '../components/MySwiper.vue';
import MyHeader from '../components/MyHeader.vue';
import ScrollTop from '../components/ScrollTop.vue';
export default {
  components: {
    MyHeader,
    MySwiper,
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