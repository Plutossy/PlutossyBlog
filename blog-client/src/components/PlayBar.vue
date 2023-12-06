<template>
  <div class="play-bar" :class="{show: !toggle}">
    <!-- 显示按钮 -->
    <div @click="toggle=!toggle" class="item-up" :class="{turn: toggle}">
      <!-- <svg class="icon">
        <use xlink:href="#icon-jiantou-xia-cuxiantiao" />
      </svg>-->
      <el-icon>
        <ArrowUpBold />
      </el-icon>
    </div>

    <!-- 隐藏的音乐插件 -->
    <audio
      ref="player"
      :src="url"
      controls="controls"
      preload="true"
      @canplay="startPlay"
      @ended="ended"
      @timeupdate="timeupdate"
      muted
      autoplay
      style="display: none;"
    ></audio>

    <!-- 音乐控件 -->
    <el-row class="song-control">
      <!-- 上一首 -->
      <el-col :span="1" class="item" @click="prev">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi" />
        </svg>
      </el-col>
      <!-- 播放 -->
      <el-col :span="1" class="item" @click="togglePlay">
        <svg class="icon">
          <!-- <use :xlink:href="playButtonUrl" /> -->
          <use xlink:href="#icon-bofang" />
        </svg>
      </el-col>
      <!-- 下一首 -->
      <el-col :span="1" class="item" @click="next">
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
          <el-col :span="3" class="current-time">{{ nowTime }}</el-col>
          <!-- 进度条和名字 -->
          <el-col :span="18" class="progress-box">
            <el-row justify="center" align="middle" class="item-song-title">
              <el-col :span="4">11111</el-col>
              <el-col :span="2">&nbsp;--&nbsp;</el-col>
              <el-col :span="4">2222</el-col>
            </el-row>
            <el-row ref="progress" class="progress" @mousemove="mousemove">
              <!-- 进度条 -->
              <div ref="bg" class="bg" @click="updatemove">
                <div ref="curProgress" class="cur-progress" :style="{width: curLength + '%'}"></div>
              </div>
              <!-- 拖动的点 -->
              <!-- onselectstart禁止复制，优化体验 -->
              <div
                ref="idot"
                class="idot"
                :style="{left: curLength + '%'}"
                @mousedown="mousedown"
                @mouseup="mouseup"
                onselectstart="return false"
              ></div>
            </el-row>
          </el-col>

          <!-- 播放结束时间 -->
          <el-col :span="3" class="last-time">{{ songTime }}</el-col>
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
        <el-slider class="volume" v-model="volume" :vertical="true"></el-slider>
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
          <svg class="icon hideAside-w" @click="listShow=!listShow">
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
              <el-icon @click="listShow=false">
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
            <el-col :span="6" class="song-name">歌曲名称</el-col>
            <el-col :span="6">歌曲时长</el-col>
            <el-col :span="6">歌手</el-col>

            <!-- <el-col :span="1" v-show="(site.id!=songContent.id||playStatus==false)?true:false"> -->
            <el-col :span="1">
              <el-tooltip class="item" effect="dark" content="播放" placement="top">
                <el-icon @click="cutSong(site)">
                  <VideoPlay />
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
              <el-tooltip
                class="item"
                effect="dark"
                content="移除"
                placement="top"
                @click.native="removeSong(site)"
              >
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
// import { mapGetters } from 'vuex'
// import { mixin } from '@/mixins'
// import { download, setCollect, getCollectOfUserId } from '@/api/index.js'

export default {
  // mixins: [mixin],
  name: 'play-bar',
  data() {
    return {
      nowTime: '00:00', // 当前播放时间
      songTime: '00:00', // 歌曲总时间
      curLength: 0, // 当前播放长度
      progressLength: 0, // 歌曲总长度
      mouseStartX: 0, // 拖拽开始位置
      tag: false, // 是否拖拽
      volume: 50, // 音量,默认一半
      toggle: true, // 显示隐藏播放器页面
      listShow: false // 显示播放菜单
    }
  },
  methods: {
    //点击播放
    play() {
      this.$refs.player.ended ? this.playSong(this.songContent) : this.$refs.player.play(), this.setTimer()
      this.playStatus = true
    },
    //点击暂停
    pause() {
      this.playStatus = false
      this.$refs.audio.pause(), this.clearTimer()
    },
    //列表点击播放歌曲
    cutSong(obj) {
      obj == this.songContent ? this.play() : this.$store.commit('setContent', obj)
    },
    //初始化播放信息
    playSong(obj) {
      this.durationText = obj.dt
      this.playStatus = true
      this.currentText = '00:00'
      this.currentBar = 0
      let song_url = 'https://api.imjad.cn/cloudmusic/?type=song&id=' + obj.id
      this.getSong(song_url)
    }
  }
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
  }
}
</style>
