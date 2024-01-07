<template>
  <div class="parallax-container" ref="parallax">
    <div class="layer" ref="layer" v-for="(item, index) in barnerImagesData" :key="index"
      :style="{ transform: `matrix(${item.transform})`, opacity: item.opacity && item.opacity[0] }">
      <img :src="item.url" :style="`width: ${item.width * compensate}px; filter: blur(${item.blur}px)`">
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, reactive } from 'vue'

const barnersData = [
  {
    "width": 1916,
    "height": 179,
    url: 'https://pic.imgdb.cn/item/64e08056661c6c8e540748c5.webp',
    "transform": [1, 0, 0, 1, 0, 0],
    a: 0.01
  },
  {
    "width": 690,
    "height": 56,
    url: 'https://pic.imgdb.cn/item/64e0808e661c6c8e5407fb3d.webp',
    "transform": [1, 0, 0, 1, 95.8065, -19.1613],
    a: 0.02
  },
  {
    "width": 1360,
    "height": 179,
    url: 'https://pic.imgdb.cn/item/64e083b8661c6c8e5411820c.webp',
    "transform": [1, 0, 0, 1, 223.548, 9.58065],
    a: 0.015
  },
  {
    "width": 1781,
    "height": 179,
    url: 'https://pic.imgdb.cn/item/64e083cf661c6c8e5411c5d2.webp',
    "transform": [1, 0, 0, 1, -102.194, 6.3871],
    a: 0.02
  },
  {
    "width": 911,
    "height": 141,
    "url": "https://pic.imgdb.cn/item/64e083e5661c6c8e541200a9.webp",
    "transform": [1, 0, 0, 1, -127.742, 25.5484],
    a: 0.04,
    opacity: [1, 0]
  },
  {
    "width": 911,
    "height": 141,
    "url": "https://pic.imgdb.cn/item/64e08404661c6c8e541256f4.webp",
    "transform": [1, 0, 0, 1, -127.742, 25.5484],
    a: 0.04,
    opacity: [0, 1],
  },
  {
    "width": 84,
    "height": 45,
    "url": "https://pic.imgdb.cn/item/64e0840d661c6c8e541271aa.webp",
    "transform": [1, 0, 0, 1, -558.871, 37.2581],
    a: 0.02
  },
  {
    "width": 201,
    "height": 103,
    "url": "https://pic.imgdb.cn/item/64e08416661c6c8e541289a1.webp",
    "transform": [1, 0, 0, 1, -606.774, 44.7097],
    a: 0.1
  },
  {
    "width": 95,
    "height": 34,
    "url": "https://pic.imgdb.cn/item/64e08421661c6c8e5412a883.webp",
    "transform": [1, 0, 0, 1, 380.565, 76.1129],
    a: 0.07
  },
  {
    "width": 68,
    "height": 40,
    "url": "https://pic.imgdb.cn/item/64e0842c661c6c8e5412c88f.webp",
    "transform": [1, 0, 0, 1, 63.871, 0],
    a: 0.075,
    deg: -Math.PI / 40000,
    g: -0.0075
  },
  {
    "width": 304,
    "height": 116,
    "url": "https://pic.imgdb.cn/item/64e08435661c6c8e5412e1ee.webp",
    "transform": [1, 0, 0, 1, -127.742, 12.7742],
    a: 0.04
  },
  {
    "width": 259,
    "height": 64,
    "url": "https://pic.imgdb.cn/item/64e0843c661c6c8e5412f5b4.webp",
    "transform": [1, 0, 0, 1, -193.742, 41.5161],
    a: 0.04,
    deg: Math.PI / 40000,
  },
  {
    "width": 1980,
    "height": 221,
    "url": "https://pic.imgdb.cn/item/64e08445661c6c8e54130dee.webp",
    "transform": [1, 0, 0, 1, -23.9516, -3.99194],
    a: 0.07
  },
  {
    "width": 196,
    "height": 88,
    "url": "https://pic.imgdb.cn/item/64e0844c661c6c8e54132471.webp",
    "transform": [1, 0, 0, 1, -268.258, -51.0968],
    a: 0.16
  },
  {
    "width": 2235,
    "height": 209,
    "url": "https://pic.imgdb.cn/item/64e08455661c6c8e54133dda.webp",
    "transform": [1, 0, 0, 1, 0, -14.9032],
    a: 0.16,
    blur: 3
  }
] // 图片数据

const parallax: any = ref(null) // 容器DOM
let layer: any = ref(null) // 图片DOM

let barnerImagesData: any = reactive(barnersData)

let compensate = ref(1) // 视窗补偿值
let initX = ref(0)
let moveX = ref(0)
let startTime = 0;
const duration = 300; // 动画持续时间（毫秒）
const lerp = (start, end, amt) => (1 - amt) * start + amt * end; // 计算线性插值

onMounted(() => {
  // 鼠标滑入与滑动
  parallax.value.addEventListener('mouseover', (e) => initX.value = e.pageX)
  parallax.value.addEventListener('mousemove', (e) => {
    moveX.value = e.pageX - initX.value
    requestAnimationFrame(mouseMove)
  })
  // 鼠标已经离开了视窗或者切出浏览器，执行回正动画
  parallax.value.addEventListener("mouseleave", leave)
  // document.addEventListener("mouseleave", leave)
  window.onblur = leave
  // 添加窗口大小监听
  window.addEventListener('resize', initItems)
})

onUnmounted(() => {
  // 移除窗口大小监听
  window.removeEventListener('resize', initItems)
})

// 添加图片元素
function initItems() {
  compensate.value = window.innerWidth > 1650 ? window.innerWidth / 1650 : 1
  barnerImagesData = reactive(barnersData)
}

const mouseMove = () => { // 滑动操作
  animate(null)
}

const leave = () => {
  startTime = 0;
  requestAnimationFrame(homing); // 开始动画
}

const homing = (timestamp) => {
  !startTime && (startTime = timestamp)
  const elapsed = timestamp - startTime
  const progress = Math.min(elapsed / duration, 1)
  animate(progress) // 传递动画进度
  progress < 1 && requestAnimationFrame(homing) // 继续下一帧
}

// 动画执行
const animate = (progress) => {
  if (barnersData.length <= 0) return
  const isHoming = typeof progress === 'number'
  for (let i = 0; i < barnersData.length; i++) {
    const item: any = barnersData[i]
    let m = new DOMMatrix(item.transform)
    let move = moveX.value * item.a // 移动X translateX
    let s = item.f ? item.f * moveX.value + 1 : 1 // 放大比例 Scale
    let g = moveX.value * (item.g || 0) // 移动Y translateY
    if (isHoming) { // 回正时处理
      m.e = lerp(moveX.value * item.a + item.transform[4], item.transform[4], progress)
      move = 0
      s = lerp(item.f ? item.f * moveX.value + 1 : 1, 1, progress)
      g = lerp(item.g ? item.g * moveX.value : 0, 0, progress)
    }
    m = m.multiply(new DOMMatrix([m.a * s, m.b, m.c, m.d * s, move, g]))
    if (item.deg) { // 有旋转角度
      const deg = isHoming ? lerp(item.deg * moveX.value, 0, progress) : item.deg * moveX.value
      m = m.multiply(new DOMMatrix([Math.cos(deg), Math.sin(deg), -Math.sin(deg), Math.cos(deg), 0, 0]))
    }
    if (item.opacity) { // 有透明度变化
      layer.value[i].style.opacity = isHoming && moveX.value > 0 ? lerp(item.opacity[1], item.opacity[0], progress) : lerp(item.opacity[0], item.opacity[1], moveX.value / window.innerWidth * 2)
    }
    layer.value[i].style.transform = m // 应用所有变换效果
  }
}

</script>

<style lang="scss" scoped>
.parallax-container {
  position: relative;
  overflow: hidden;
  margin: 0 auto;
  width: 100%;
  min-width: 1000px;
  min-height: 155px;
  height: 10vw;
  max-height: 240px;

  .layer {
    position: absolute;
    left: 0;
    top: 0;
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;

    img {
      user-select: none;
      pointer-events: none;
      border-style: none;
      overflow-clip-margin: content-box;
      overflow: clip;
    }
  }
}
</style>