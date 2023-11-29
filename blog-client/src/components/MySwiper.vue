<template>
  <!-- <el-carousel :interval="4000" type="card" height="300px" trigger="click">
    <el-carousel-item v-for="(item, index) in swiperList" :key="index">
      <img :src="item.picImg" />
    </el-carousel-item>
  </el-carousel>-->

  <swiper
    :effect="'coverflow'"
    :grabCursor="true"
    :centeredSlides="true"
    :slidesPerView="'auto'"
    :coverflowEffect="{
      rotate: 50,
      stretch: 0,
      depth: 100,
      modifier: 1,
      slideShadows: true,
    }"
    :pagination="{
      dynamicBullets: true,
      clickable: true,
    }"
    :spaceBetween="30"
    :autoplay="{
      delay: 3000,
      disableOnInteraction: false,
    }"
    :loop="true"
    :modules="modules"
    class="mySwiper"
  >
    <swiper-slide v-for="(item, index) in swiperList" :key="index">
      <img :src="item.picImg" />
    </swiper-slide>
  </swiper>
</template>

<script>
import { swiperList } from '@/assets/js/swiper.js'

// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue'
// Import Swiper styles
import 'swiper/scss'
import 'swiper/scss/navigation'
import 'swiper/scss/pagination'

// import required modules
import { Autoplay, EffectCoverflow, Pagination, Navigation } from 'swiper/modules'

export default {
  name: 'MySwiper',
  data() {
    return {
      swiperList: []
    }
  },
  components: {
    Swiper,
    SwiperSlide
  },
  setup() {
    return {
      modules: [EffectCoverflow, Autoplay, Pagination, Navigation]
    }
  },
  created() {
    this.swiperList = swiperList
  }
}
</script>

<style lang="scss" scoped>
/* .el-carousel {
  margin-bottom: 1rem;
  padding: 1rem 1rem 0 1rem;
  .el-carousel__item {
    img {
      display: block;
      width: 100%;
    }
  }
} */

.swiper {
  width: 100%;
  padding-top: 100px;
  padding-bottom: 50px;
  .swiper-slide {
    background-position: center;
    background-size: cover;
    width: 380px;
    height: 240px;
    img {
      display: block;
      width: 100%;
      height: 100%;
    }
  }
}
</style>