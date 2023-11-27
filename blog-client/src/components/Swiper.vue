<template>
  <div class="swiper-container">
    <!-- <swiper
      :slides-per-view="3"
      :space-between="50"
      @swiper="onSwiper"
      @slideChange="onSlideChange"
    >
      <swiper-slide>Slide 1</swiper-slide>
      <swiper-slide>Slide 2</swiper-slide>
      <swiper-slide>Slide 3</swiper-slide>
    </swiper>-->
  </div>
</template>

<script>
// // Import Swiper Vue.js components
// import { Swiper, SwiperSlide } from 'swiper/vue'
// // Import Swiper styles
// import 'swiper/scss'

export default {
  name: 'Swiper',
  components: {
    // Swiper,
    // SwiperSlide
  },
  setup() {
    // const onSwiper = swiper => {
    //   console.log(swiper)
    // }
    // const onSlideChange = () => {
    //   console.log('slide change')
    // }
    // return {
    //   onSwiper,
    //   onSlideChange
    // }
  }
}
</script>

<style lang="scss" scoped>
.swiper-container {
  width: 100%;
}
</style>