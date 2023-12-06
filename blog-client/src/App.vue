<script setup>
import MyHeader from '@/components/MyHeader.vue'
import ScrollTop from '@/components/ScrollTop.vue'
import MyFooter from '@/components/MyFooter.vue'
import PlayBar from '@/components/PlayBar.vue'

import { ref, onMounted, onUnmounted, watch } from 'vue'

let navShow = ref(true)
let scroll = ref(0) //上下滚动
let up = ref(300) //开始隐藏位置
let screenWidth = ref(document.body.clientWidth) //屏幕宽度
let timer1 = ref(true)
let timer2 = ref(true)

onMounted(() => {
  // 监听（绑定）滚轮 滚动事件
  window.addEventListener('scroll', handleScroll, true)

  window.addEventListener('resize', handleSize, true)
})

onUnmounted(() => {
  window.removeEventListener('scroll')
  window.removeEventListener('resize')
})

watch(
  scroll,
  newValue => {
    if (newValue >= up.value) {
      navShow.value = false
    } else {
      navShow.value = true
    }
  },
  { immediate: true }
)

watch(
  screenWidth,
  newValue => {
    if (newValue <= 768) {
      up.value = 500
    } else {
      up.value = 300
    }
  },
  { immediate: true }
)

const handleScroll = () => {
  let timer = null
  if (timer1.value) {
    timer1.value = false
    timer = setTimeout(() => {
      // 页面滚动距顶部距离
      const scrollTop = window.scrollY || document.documentElement.scrollTop || document.body.scrollTop
      scroll.value = scrollTop
    }, 200)
  } else {
    clearTimeout(timer)
    timer1.value = true
  }
}
const handleSize = () => {
  let timer = null
  if (timer2.value) {
    timer2.value = false
    timer = setTimeout(() => {
      // 页面滚动距顶部距离
      screenWidth.value = document.body.clientWidth
    }, 200)
  } else {
    clearTimeout(timer)
    timer2.value = true
  }
}
</script>

<template>
  <div>
    <el-collapse-transition>
      <my-header v-show="navShow" />
    </el-collapse-transition>
    <router-view></router-view>
    <play-bar />
    <scroll-top />
    <my-footer />
  </div>
</template>

<style lang="scss">
// * {
//   margin: 0;
//   padding: 0;
// }
</style>
