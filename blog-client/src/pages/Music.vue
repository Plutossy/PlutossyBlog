<template>
  <div class="music-container">
    <!--顶部背景信息-->
    <div class="top-img animate__animated animate__fadeInDown">
      <div>音乐盒</div>
      <div>昆山玉碎凤凰叫，芙蓉泣露香兰笑。</div>
    </div>

    <!-- 音乐内容区 -->
    <div class="song-audio">
      <audio
        ref="player"
        :src="url"
        controls="controls"
        preload="true"
        @canplay="startPlay"
        @ended="ended"
        @timeupdate="timeupdate"
        muted
      ></audio>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: 'http://music.163.com/song/media/outer/url?id=1406025645.mp3'
    }
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
      z-index: -1; /* 设置伪元素的层级为负数，保证它在元素后面 */
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
  }
}
</style>