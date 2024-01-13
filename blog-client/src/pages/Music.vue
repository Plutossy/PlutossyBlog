<template>
  <div class="music-container">
    <!--顶部背景信息-->
    <div class="top-img animate__animated animate__fadeInDown">
      <div>音乐盒</div>
      <div>昆山玉碎凤凰叫，芙蓉泣露香兰笑。</div>
    </div>

    <!-- 音乐内容区 -->
    <div class="song-audio animate__animated animate__zoomIn">
      <el-row justify="space-between">
        <el-col :span="12">
          <song-list />
        </el-col>
        <el-col :span="10">
          <lyric-info />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import LyricInfo from '../components/songlist/LyricInfo.vue'
import SongList from '../components/songlist/SongList.vue'
export default {
  data() {
    return {
      playStatus: false, //播放状态，用来控制播放、暂停按钮的显示
      Timer: '', //定时器，我们需要实时监听到歌曲的播放进度
      currentBar: 0, //进度条长度，默认为0，根据歌曲进度同步更新
      currentText: '00:00', //进度条旁边的播放时间，同样要实时更新
      durationText: '00:00', //当前歌曲的播放时长
      listShow: false, //控制播放列表的显示
      loopStyle: 'list' //not:单曲播放 list:列表循环 single:单曲循环 random:随机循环
    }
  },
  components: {
    SongList,
    LyricInfo
  }
}
</script>

<style lang="scss" scoped>
.music-container {
  display: flex;
  flex-direction: column;
  align-items: center;

  .top-img {
    width: 100%;
    background: url(@/assets/img/bg5.jpeg) no-repeat center center;
    background-size: cover;

    font-family: 'STXingkai', serif;
    color: #ffffff;
    // background-color: rgba(0, 0, 0, 0.6);
    margin-top: -5rem;
    padding: 7rem 0 2rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;

    &::before {
      // 设置遮罩层
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(0, 0, 0, 0.5);
      z-index: -1;
      /* 设置伪元素的层级为负数，保证它在元素后面 */
    }

    div:first-child {
      font-size: 4rem;
      font-weight: 560;
      margin-bottom: 2rem;
    }

    div:last-child {
      font-size: 1.8rem;
      font-weight: 560;
      margin-bottom: 2rem;
    }
  }

  .song-audio {
    position: relative;
    z-index: 1;
    font-family: 'STXingkai', serif;
    // font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
    background-color: rgba(255, 255, 255, 0.7);
    width: 90%;
    margin-top: -2rem;
    margin-bottom: 2rem;
    border-radius: 1rem;
    box-shadow: 0 1px 2px 0 rgba(34, 36, 38, 0.6);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .el-row {
      width: 100%;

      .el-col {
        height: 40rem;
      }

      .el-col:last-child {
        margin-right: 1rem;
      }
    }
  }
}
</style>