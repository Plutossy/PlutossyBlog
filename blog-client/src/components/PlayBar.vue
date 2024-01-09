<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-05 14:58:01
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-09 16:48:22
 * @FilePath: \blog-client\src\components\PlayBar.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="play-bar" :class="{ show: !toggle }">
    <!-- 显示按钮 -->
    <el-tooltip effect="dark" content="音乐条" placement="top">
      <div @click="toggle = !toggle" class="item-up" :class="{ turn: toggle }">
        <!-- <svg class="icon">
        <use xlink:href="#icon-jiantou-xia-cuxiantiao" />
        </svg>-->
        <el-icon>
          <ArrowUpBold />
        </el-icon>
      </div>
    </el-tooltip>

    <!-- 隐藏的音乐插件 -->
    <audio ref="player" :src="songUrl" controls="controls" preload="auto" @canplay="startPlay" @ended="ended"
      @timeupdate="timeupdate" @error="errorHandler" style="display: none;"></audio>

    <!-- 音乐控件 -->
    <el-row class="song-control">
      <!-- 上一首 -->
      <el-col :span="1" class="item" @click="prevSong">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi" />
        </svg>
      </el-col>
      <!-- 播放 -->
      <el-col :span="1" class="item" @click="togglePlay">
        <svg class="icon">
          <use :xlink:href="playButtonUrl" />
        </svg>
      </el-col>
      <!-- 下一首 -->
      <el-col :span="1" class="item" @click="nextSong">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi1" />
        </svg>
      </el-col>
      <!-- 歌曲图片 -->
      <el-col :span="2" class="item-img" @click="toMusic">
        <img :src="songContent.img" />
      </el-col>
      <!-- 播放进度 -->
      <el-col :span="12" class="playing-speed">
        <el-row align="middle" justify="space-between">
          <!-- 播放开始时间 -->
          <el-col :span="3" class="current-time">{{ currentTime }}</el-col>
          <!-- 进度条和名字 -->
          <el-col :span="18" class="progress-box">
            <el-row justify="space-between" align="middle" class="item-song-title">
              <el-col :span="14">{{ songContent.name }}</el-col>
              <el-col :span="3">&nbsp;--&nbsp;</el-col>
              <el-col :span="7">
                <el-tooltip effect="dark" :content="songContent.singer" placement="top">
                  {{ songContent.singer }}
                </el-tooltip>
              </el-col>
            </el-row>
            <div class="progress">
              <!-- 进度条 -->
              <div ref="bg" class="bg" @click.stop="updatemove">
                <div ref="curProgress" class="cur-progress" :style="{ width: curLength + '%' }"></div>
              </div>
              <!-- 拖动的点 -->
              <!-- onselectstart禁止复制，优化体验 -->
              <div ref="idot" class="idot" @mousedown.stop="mousedown" onselectstart="return false"></div>
            </div>
          </el-col>

          <!-- 播放结束时间 -->
          <el-col :span="3" class="last-time">{{ durationTime }}</el-col>
        </el-row>
      </el-col>
      <!-- 音量 -->
      <el-col :span="1" class="item item-volume">
        <svg v-if="volume == 0" class="icon">
          <use xlink:href="#icon-yinliangjingyinheix" />
        </svg>
        <svg v-else class="icon">
          <use xlink:href="#icon-yinliang1" />
        </svg>
        <el-slider class="volume" v-model="volume" vertical></el-slider>
      </el-col>
      <!-- 下载 -->
      <el-col :span="1" class="item" @click="download">
        <el-tooltip class="item" effect="dark" content="下载" placement="top">
          <svg class="icon">
            <use xlink:href="#icon-xiazai" />
          </svg>
        </el-tooltip>
      </el-col>
      <!-- 播放歌曲列表 -->
      <el-col :span="1" class="item">
        <el-tooltip class="item" effect="dark" content="播放列表" placement="top">
          <svg class="icon hideAside-w" @click="listShow = !listShow">
            <use xlink:href="#icon-liebiao" class="hideAside-w" />
          </svg>
        </el-tooltip>
      </el-col>
    </el-row>

    <!-- 弹出歌曲列表 -->
    <transition name="el-zoom-in-bottom">
      <el-row class="play-list-container" type="flex" justify="center" v-show="listShow">
        <el-col>
          <el-row class="title" type="flex" justify="center">
            <el-col :span="21">播放列表</el-col>
            <el-col :span="1">
              <el-icon @click="listShow = false">
                <CloseBold />
              </el-icon>
            </el-col>
          </el-row>
          <el-row class="list-title">
            <el-col :span="2">&nbsp;</el-col>
            <el-col :span="6">歌曲</el-col>
            <el-col :span="6">时长</el-col>
            <el-col :span="6">歌手</el-col>
            <el-col :span="4"></el-col>
          </el-row>

          <el-row v-if="playSongList.length" class="play-list" align="middle" v-for="site in playSongList" :key="site.id">
            <el-col :span="2" class="song-img">
              &nbsp;
              <img v-show="(site.id === songContent.id && playStatus === true) ? true : false"
                src="@/assets/img/song/wave.gif" />
            </el-col>
            <el-col :span="6" class="song-name">
              <el-tooltip effect="dark" :content="site.name" placement="top">
                {{ site.name }}
              </el-tooltip>
            </el-col>
            <el-col :span="6">歌曲时长</el-col>
            <el-col :span="6">{{ site.singer }}</el-col>
            <el-col :span="1">
              <el-tooltip class="item" effect="dark" content="播放/暂停" placement="top">
                <el-icon @click="cutSong(site)">
                  <VideoPlay v-if="(site.id !== songContent.id || playStatus === false) ? true : false" />
                  <VideoPause v-else />
                </el-icon>
              </el-tooltip>
            </el-col>
            <el-col :span="3" @click="removeSong(site)">
              <el-tooltip class="item" effect="dark" content="移除" placement="top">
                <el-icon>
                  <Delete />
                </el-icon>
              </el-tooltip>
            </el-col>
          </el-row>

          <el-row v-else class="play-list-tips" justify="center">
            <el-col :span="24">空空如也~</el-col>
            <el-col :span="24">请先添加一些歌曲吧！</el-col>
          </el-row>
        </el-col>
      </el-row>
    </transition>
  </div>
</template>

<script>
// svg图标资源
import '@/assets/js/iconfont.js'
import '@/assets/js/iconfont1.js'
import '@/assets/js/iconfont2.js'
import '@/assets/js/iconfont3.js'
import { mapGetters } from 'vuex'
import { downloadSong } from '@/api'
import { mixin } from '@/mixins'

export default {
  mixins: [mixin],
  name: 'play-bar',
  data() {
    return {
      songUrl: '', // 歌曲链接
      playButtonUrl: '#icon-bofang', // 播放按钮图标
      Timer: null, //定时器
      currentTime: '00:00', // 当前播放时间
      durationTime: '00:00', // 歌曲总时间
      curLength: 0, // 当前播放长度
      tag: false, // 是否拖拽
      mouseStartX: 0, // 拖拽开始位置
      volume: 50, // 音量,默认一半
      toggle: false, // 显示隐藏播放器页面
      listShow: false, // 显示播放菜单
      playSongList: [], // 播放列表
    }
  },
  computed: {
    ...mapGetters('m_song', ['playStatus', 'songContent', 'playList'])
  },
  watch: {
    playStatus: {
      handler() {
        this.playButtonUrl = this.playStatus ? '#icon-zanting' : '#icon-bofang'
      },
      immediate: true
    },
    // 监听歌曲变化
    songContent: {
      handler() {
        this.songUrl = this.songContent.url
      },
      immediate: true
    },
    playSongList: {
      handler() {
        if (this.playList.length > 7) {
          this.playSongList = this.playList.slice(this.playList.length - 7, this.playList.length)
          console.log(this.playSongList);
        } else {
          this.playSongList = this.playList
        }
      },
      immediate: true
    },
    // 监听音量变化
    volume(val) {
      this.$refs.player.volume = val / 100
    },
    // // 监听当前播放时间变化
    // currentTime: {
    //   handler() {
    //     // this.currentTime = this.formatSeconds(newVal)
    //     // this.curLength = this.currentTime / this.durationTime * 100
    //   },
    //   immediate: true
    // },
    // durationTime: {
    //   bind(newVal) {
    //     this.durationTime = this.formatSeconds(newVal)
    //   },
    //   immediate: true
    // },
  },
  mounted() {
    document.querySelector('.item-volume').addEventListener('click', (e) => {
      document.querySelector('.volume').classList.toggle('show-volume')
      e.stopPropagation()
    }, false)
    // document.addEventListener('click', () => {
    //   document.querySelector('.volume').classList.remove('show-volume')
    // }, false)
  },
  methods: {
    // 控制音乐播放、暂停
    togglePlay() {
      if (!this.songContent.url) return this.$message.error('还没有选择歌曲哦~')
      // 通过$nextTick()方法，确保拿到最新的DOM节点
      this.$nextTick(() => {
        if (this.$refs.player.paused) {
          this.$store.commit('m_song/setPlayStatus', true)
          this.$refs.player.play()
          this.playButtonUrl = '#icon-zanting'
        } else {
          this.$store.commit('m_song/setPlayStatus', false)
          this.$refs.player.pause()
          this.playButtonUrl = '#icon-bofang'
        }
      })
    },
    //列表点击播放歌曲
    cutSong(obj) {
      obj.id === this.songContent.id ? this.togglePlay() : this.$store.commit('m_song/setSongContent', obj)
    },
    // 初始化，获取链接后准备播放
    startPlay() {
      // this.currentTime = '00:00'
      this.currentTime = this.formatSeconds(this.$refs.player.currentTime)
      // 设置播放时长
      this.durationTime = this.formatSeconds(this.$refs.player.duration)
      // 设置音量
      this.$refs.player.volume = this.volume / 100
    },
    //上一首
    prevSong() {
      let index = this.playList.indexOf(this.songContent)
      const length = this.playList.length
      index == 0 ? (index = length - 1) : index--
      this.$store.commit('m_song/setSongContent', this.playList[index])
    },
    //下一首
    nextSong() {
      // 列表循环
      let index = this.playList.indexOf(this.songContent)
      const length = this.playList.length
      index == length - 1 ? (index = 0) : index++
      this.$store.commit('m_song/setSongContent', this.playList[index])
    },
    // 自动播放下一首
    ended() {
      this.nextSong()
    },
    // 播放进度
    timeupdate() {
      this.currentTime = this.formatSeconds(this.$refs.player.currentTime)
      this.$store.commit('m_song/setCurrentTime', this.currentTime)
      this.$store.commit('m_song/setCurrentSeconds', this.$refs.player.currentTime)
      // 进度条长度
      this.curLength = this.$refs.player.currentTime / this.$refs.player.duration * 100
    },
    download() {
      // this.songContent.url
      downloadSong(this.songContent.url).then(res => {
        const content = res.data
        let eleLink = document.createElement('a')
        eleLink.download = `${this.artist}-${this.title}.mp3`
        eleLink.style.display = 'none'
        // 把字符内容转换为blob地址
        const blob = new Blob([content])
        eleLink.href = URL.createObjectURL(blob)
        // 把链接地址加到document中
        document.body.appendChild(eleLink)
        // 触发点击
        eleLink.click()
        // 然后移除
        document.body.removeChild(eleLink)
        /* handelExport({ portalIds: ids }).then((res) => {
          const blob = new Blob([res], { type: 'application/zip' })
          const url = window.URL.createObjectURL(blob)
          const link = document.createElement('a') // 创建a标签
          link.href = url
          link.download = '装饰入户调查统计' // 重命名文件
          document.body.appendChild(link)
          link.click()
          URL.revokeObjectURL(url) // 释放内存
          document.body.removeChild(link)
        }) */
      }).catch(err => {
        console.log(err)
        this.$message({
          message: '下载失败',
          type: 'error'
        })
      })
    },
    // 播放失败
    errorHandler() {
      this.$message.error('播放失败, 请检查歌曲链接是否有效~')
    },
    //从播放列表中移除
    removeSong(obj) {
      this.$store.commit('m_song/removeSong', obj)
    },
    // 鼠标按下
    mousedown(e) {
      this.tag = true
      this.mouseStartX = e.clientX
      // 解决鼠标移除当前窗口抬起后还能拖动的bug
      // window.addEventListener('mouseout', () => {
      //   this.tag = false
      // })
      // 鼠标抬起
      document.addEventListener('mouseup', this.mouseup)
      // 拖动进度条
      document.addEventListener('mousemove', this.mousemove)
    },
    // 鼠标抬起
    mouseup() {
      this.tag = false
    },
    // 拖动进度条
    mousemove(e) {
      if (!this.durationTime) return false
      if (this.tag) {
        // 防抖
        this.Timer = setTimeout(() => {
          // getBoundingClientRect 获取元素的大小及其相对于视口的位置
          const curLength = this.$refs.curProgress.getBoundingClientRect().width // 当前进度条长度
          const progressLength = this.$refs.bg.getBoundingClientRect().width // 进度条总长度
          let moveX = e.clientX - this.mouseStartX // 点移动的距离
          let newPercent = ((curLength + moveX) / progressLength) * 100 // 新的进度条百分比乘以100
          if (newPercent < 0) newPercent = 0 // 判断是否超出左边界
          if (newPercent > 100) newPercent = 100 // 判断是否超出右边界
          this.curLength = newPercent
          this.mouseStartX += moveX
          this.changeProgress(newPercent)
          this.Timer && clearTimeout(this.Timer)
        }, 200)
      }
    },
    // 点击播放条切换播放进度
    updatemove(e) {
      if (!this.durationTime) return false
      if (!this.tag) {
        // 相对于有定位的父元素的坐标 也就是 progress 的左侧坐标
        const curLength = this.$refs.bg.offsetLeft // 当前进度条左侧坐标  0
        const progressLength = this.$refs.bg.getBoundingClientRect().width // 进度条总长度
        let newPercent = ((e.offsetX - curLength) / progressLength) * 100 // 新的进度条百分比乘以100
        if (newPercent < 0) newPercent = 0
        if (newPercent > 100) newPercent = 100
        this.curLength = newPercent
        this.mouseStartX = e.clientX
        this.changeProgress(newPercent)
      }
    },

    // 更改歌曲进度
    changeProgress(percent) {
      this.$refs.player.currentTime = percent * this.$refs.player.duration / 100
    },

    toMusic() {
      this.$router.push({ path: '/music' })
    },
  },
  unmounted() {
    // 解除绑定
    document.removeEventListener('mousemove')
    document.removeEventListener('mouseup')
  },
}
</script>

<style scoped>
/* 单文件组件的 <style> 标签支持使用 v-bind CSS 函数将 CSS 的值链接到动态的组件状态： */
/* 注意: 没有scss */
.idot {
  left: calc(v-bind(curLength + '%') - 0.4rem);
}
</style>

<style lang="scss" scoped>
@import '../assets/css/play-bar.scss';

.play-list-container {
  position: absolute;
  bottom: 3.85rem;
  left: 0;
  right: 0;
  margin: auto;
  width: 30rem;
  height: 20rem;
  background: rgba(255, 255, 255);
  border-radius: 0.5rem 0.5rem 0 0;
  color: #f2f6fc;
  transition: 0.3s;
  border-bottom: 1px solid;

  .title {
    padding: 0.5rem 0 0.5rem 0;
    background: rgb(100, 100, 100);
    border-radius: 0.5rem 0.5rem 0 0;
    color: #f2f6fc;
    text-align: left;

    .el-col:last-child {
      display: flex;
      justify-content: center;
      align-items: center;

      &:hover {
        background-color: red;
        border-radius: 50%;
      }
    }
  }

  .list-title {
    margin-top: 0.5rem;
    padding-bottom: 0.5rem;
    border-bottom: 2px solid slategray;
  }

  .list-title .el-col {
    font-size: 0.8rem;
    color: slategray;
  }

  .play-list {
    font-size: 0.8rem;
    padding: 0.5rem 0 0.5rem 0;
    border-bottom: 1px solid slategray;

    .song-img {
      img {
        filter: invert(100%); // 图片反色
        -webkit-filter: invert(100%);
      }
    }

    .el-col {
      font-size: 0.8rem;
      color: #000000;
    }

    .el-col:nth-child(5):hover {
      color: #30a4fc;
    }

    .el-col:last-child:hover {
      color: red;
    }

    &:hover {
      background: rgb(180, 180, 180);
      cursor: pointer;
    }
  }

  .play-list-tips {
    font-size: 1rem;
    color: #f08047;
    margin-top: 4rem;

    & .el-col:nth-child(n) {
      text-align: center;
    }
  }
}
</style>
