<template>
  <div class="song-list">
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
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'SongList'
}
</script>

<style lang="scss" scoped>
.song-list {
  width: 100%;
  padding: 2rem 0 3rem;

  .list-title {
    margin-top: 0.5rem;
    padding-bottom: 0.5rem;
    border-bottom: 2px solid slategray;
  }
  .list-title .el-col {
    font-size: 1.2rem;
    color: rgb(13, 124, 164);
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