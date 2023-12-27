<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-05 14:58:01
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-27 20:31:19
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
      @timeupdate="timeupdate" style="display: none;"></audio>

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
          <!-- <use xlink:href="#icon-bofang" /> -->
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
        <!-- <img :src="picUrl" /> -->
        <img src="@/assets/img/CSDN.jpg" alt />
      </el-col>
      <!-- 播放进度 -->
      <el-col :span="12" class="playing-speed">
        <el-row align="middle" justify="space-between">
          <!-- 播放开始时间 -->
          <el-col :span="3" class="current-time">{{ currentTime }}</el-col>
          <!-- 进度条和名字 -->
          <el-col :span="18" class="progress-box">
            <el-row justify="space-between" align="middle" class="item-song-title">
              <el-col :span="14">歌曲名字歌曲名字</el-col>
              <el-col :span="3">&nbsp;--&nbsp;</el-col>
              <el-col :span="7">歌手名字歌手名字</el-col>
            </el-row>
            <div ref="progress" class="progress" @mousemove="mousemove">
              <!-- 进度条 -->
              <div ref="bg" class="bg" @click="updatemove">
                <div ref="curProgress" class="cur-progress" :style="{ width: curLength + '%' }"></div>
              </div>
              <!-- 拖动的点 -->
              <!-- onselectstart禁止复制，优化体验 -->
              <div ref="idot" class="idot" :style="{ left: curLength + '%' }" @mousedown="mousedown" @mouseup="mouseup" onselectstart="return false"></div>
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
          <el-row class="play-list">
            <el-col :span="2" class="song-img">
              &nbsp;
              <!-- <img src v-show="(site.id==songContent.id&&playStatus===true)?true:false" /> -->
              <img src alt />
            </el-col>
            <el-col :span="6" class="song-name">歌曲名字</el-col>
            <el-col :span="6">歌曲时长</el-col>
            <el-col :span="6">歌手名</el-col>

            <!-- <el-col :span="1" v-show="(site.id!=songContent.id||playStatus==false)?true:false"> -->
            <el-col :span="1">
              <el-tooltip class="item" effect="dark" content="播放/暂停" placement="top">
                <el-icon @click="cutSong(site)">
                  <VideoPlay v-if="!this.playStatus" />
                  <VideoPause v-else />
                </el-icon>
              </el-tooltip>
            </el-col>
            <!-- <el-col :span="1">
              <el-tooltip class="item" effect="dark" content="暂停" placement="top">
                <el-icon @click="pause">
                  <VideoPause />
                </el-icon>
              </el-tooltip>
            </el-col>-->
            <el-col :span="3">
              <el-tooltip class="item" effect="dark" content="移除" placement="top" @click.native="removeSong(site)">
                <el-icon>
                  <Delete />
                </el-icon>
              </el-tooltip>
            </el-col>
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
import { mapState } from 'vuex'
import { downloadSong } from '@/api'
// import { mixin } from '@/mixins'

export default {
  // mixins: [mixin],
  name: 'play-bar',
  data() {
    return {
      songUrl: 'http://music.163.com/song/media/outer/url?id=1985969510.mp3', // 歌曲url
      playStatus: false, // 播放状态
      playButtonUrl: '#icon-bofang', // 播放按钮图标
      Timer: '', //定时器
      currentTime: '00:00', // 当前播放时间
      durationTime: '00:00', // 歌曲总时间
      curLength: 0, // 当前播放长度
      progressLength: 0, // 歌曲总长度
      tag: false, // 是否拖拽
      mouseStartX: 0, // 拖拽开始位置
      volume: 50, // 音量,默认一半
      toggle: false, // 显示隐藏播放器页面
      listShow: false // 显示播放菜单
    }
  },
  computed: {
    ...mapState('m_song', ['songContent', 'playList'])
  },
  watch: {
    // 监听歌曲变化
    songContent() {
      this.startPlay(this.songContent)
    },
    // 监听音量变化
    volume(val) {
      this.$refs.player.volume = val / 100
    },
    // 格式化时间
    currentTime: {
      bind() {
        // this.currentTime = this.formatSeconds(newVal)
        this.curLength = this.currentTime / this.durationTime * 100
      },
      immediate: true
    },
    // durationTime: {
    //   bind(newVal) {
    //     this.durationTime = this.formatSeconds(newVal)
    //   },
    //   immediate: true
    // },
  },
  mounted() {
    // getBoundingClientRect 获取元素的大小及其相对于视口的位置
    this.progressLength = this.$refs.progress.getBoundingClientRect().width

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
      // 通过$nextTick()方法，确保拿到最新的DOM节点
      this.$nextTick(() => {
        if (this.$refs.player.paused) {
          this.playStatus = true
          this.$refs.player.play()
          this.playButtonUrl = '#icon-zanting'
        } else {
          this.playStatus = false
          this.$refs.player.pause()
          this.playButtonUrl = '#icon-bofang'
        }
      })
    },
    //列表点击播放歌曲
    cutSong(obj) {
      obj == this.songContent ? this.togglePlay() : this.$store.commit('m_song/setSongContent', obj)
    },
    // 初始化，获取链接后准备播放
    startPlay() {
      // 播放状态
      this.playStatus = true
      // 重置播放进度
      // this.currentTime = '00:00'
      this.currentTime = this.formatSeconds(this.$refs.player.currentTime)
      // 设置播放时长
      this.durationTime = this.formatSeconds(this.$refs.player.duration)
      // // 设置播放进度条长度
      // this.progressLength = this.$refs.progress.getBoundingClientRect().width
      // 设置音量
      this.$refs.player.volume = this.volume / 100
      // // 设置当前播放歌曲
      // this.$refs.player.src = this.songContent.url
      // 开始播放
      // this.$refs.player.play()
    },
    //上一首
    prevSong() {
      let index = this.playList.indexOf(this.songContent)
      let length = this.playList.length
      index == 0 ? (index = length - 1) : index--
      this.$store.commit('m_song/setSongContent', this.playList[index])
    },
    //下一首
    nextSong() {
      // 列表循环
      let index = this.playList.indexOf(this.songContent)
      let length = this.playList.length
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
      // 进度条长度
      this.curLength = this.$refs.player.currentTime / this.$refs.player.duration * 100
    },
    download() {
      // this.songContent.url
      downloadSong(this.songUrl).then(res => {
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
      }).catch(err => {
        console.log(err)
        this.$message({
          message: '下载失败',
          type: 'error'
        })
      })
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
      // 解决鼠标在当前窗口别的地方抬起后还能拖动的bug
      window.addEventListener('mouseup', () => {
        this.tag = false
      })
    },
    // 鼠标抬起
    mouseup() {
      this.tag = false
    },
    // 拖动进度条
    mousemove(e) {
      if (!this.durationTime) return false
      if (this.tag) {
        let moveX = e.clientX - this.mouseStartX // 点移动的距离
        if (moveX < 0) moveX = 0
        const curLength = this.$refs.curProgress.getBoundingClientRect().width // 当前进度条长度
        let newPercent = ((curLength + moveX) / this.progressLength) * 100 // 新的进度条百分比乘以100
        if (newPercent < 0) newPercent = 0
        if (newPercent > 100) newPercent = 100
        this.curLength = newPercent
        this.mouseStartX = e.clientX
        this.changeProgress(newPercent)
      }
    },
    // 点击播放条切换播放进度
    updatemove(e) {
      if (!this.durationTime) return false
      if (!this.tag) {
        // 相对于有定位的父元素的坐标 也就是 progress 的左侧坐标
        const curLength = this.$refs.bg.offsetLeft // 当前进度条左侧坐标  0
        let newPercent = ((e.offsetX - curLength) / this.progressLength) * 100 // 新的进度条百分比乘以100
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

    // 格式化时间
    formatSeconds(val) {
      let result = ''
      const theTime = parseFloat(val)
      const hour = parseInt(theTime / 3600) // 小时
      const minute = parseInt((theTime / 60) % 60) // 分钟
      const second = parseInt(theTime % 60) // 秒
      if (hour > 0) {
        if (hour < 10) {
          result = '0' + hour + ':'
        } else {
          result = hour + ':'
        }
      }
      if (minute > 0) {
        if (minute < 10) {
          result += '0' + minute + ':'
        } else {
          result += minute + ':'
        }
      } else {
        result += '00:'
      }
      if (second) {
        if (second < 10) {
          result += '0' + second
        } else {
          result += second
        }
      } else {
        result += '00'
      }
      return result
    },
  },
  unmounted() {
    // 解除绑定
    this.$refs.progress.removeEventListener('mousemove')
    window.removeEventListener('mouseup')
  },
}
</script>

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

  .play-list .el-col {
    font-size: 0.8rem;
    color: #000000;
  }

  .play-list {
    font-size: 0.8rem;
    padding: 0.5rem 0 0.5rem 0;
    border-bottom: 1px solid slategray;
  }

  .play-list:hover {
    background: rgb(180, 180, 180);
    cursor: pointer;
  }
}
</style>
