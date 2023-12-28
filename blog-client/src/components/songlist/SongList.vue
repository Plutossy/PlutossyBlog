<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-06 16:12:41
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-28 20:29:24
 * @FilePath: \blog-client\src\components\songlist\SongList.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="song-list">
    <el-row class="list-title">
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="6">歌曲</el-col>
      <el-col :span="6">时长</el-col>
      <el-col :span="6">歌手</el-col>
      <el-col :span="4"></el-col>
    </el-row>
    <el-row class="song-list" @click="cutSong(site)">
      <el-col :span="2" class="song-img">
        &nbsp;
        <!-- <img src v-show="(site.id==songContent.id&&playStatus===true)?true:false" /> -->
        <img src alt />
      </el-col>
      <el-col :span="6" class="song-name">歌曲名称</el-col>
      <el-col :span="6">歌曲时长</el-col>
      <el-col :span="6">歌手</el-col>

      <el-col :span="1">
        <el-tooltip class="item" effect="dark" content="播放/暂停" placement="top">
          <el-icon>
            <VideoPlay v-if="!this.playStatus" />
            <VideoPause v-else />
          </el-icon>
        </el-tooltip>
      </el-col>
    </el-row>
    <el-row class="song-list" @click="cutSong(site)">
      <el-col :span="2" class="song-img">
        &nbsp;
        <!-- <img src v-show="(site.id==songContent.id&&playStatus===true)?true:false" /> -->
        <img src alt />
      </el-col>
      <el-col :span="6" class="song-name">歌曲名称</el-col>
      <el-col :span="6">歌曲时长</el-col>
      <el-col :span="6">歌手</el-col>

      <el-col :span="1">
        <el-tooltip class="item" effect="dark" content="播放/暂停" placement="top">
          <el-icon>
            <VideoPlay v-if="!this.playStatus" />
            <VideoPause v-else />
          </el-icon>
        </el-tooltip>
      </el-col>
    </el-row>
    <el-row class="song-list" @click="cutSong(site)">
      <el-col :span="2" class="song-img">
        &nbsp;
        <!-- <img src v-show="(site.id==songContent.id&&playStatus===true)?true:false" /> -->
        <img src alt />
      </el-col>
      <el-col :span="6" class="song-name">歌曲名称</el-col>
      <el-col :span="6">歌曲时长</el-col>
      <el-col :span="6">歌手</el-col>

      <el-col :span="1">
        <el-tooltip class="item" effect="dark" content="播放/暂停" placement="top">
          <el-icon>
            <VideoPlay v-if="!this.playStatus" />
            <VideoPause v-else />
          </el-icon>
        </el-tooltip>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { getSongList } from '@/api'

export default {
  name: 'SongList',
  data() {
    return {
      songList: [], // 歌曲列表
    }
  },
  computed: {
    ...mapState('m_song', ['playStatus', 'songContent'])
  },
  created() {
    this.getSongList()
  },
  methods: {
    //列表点击播放歌曲
    cutSong(obj) {
      if (obj == this.songContent) {
        this.$store.commit('m_song/setPlayStatus', !this.playStatus)
      } else {
        this.$store.commit('m_song/setSongContent', obj)
        this.$store.commit('m_song/setPlayStatus', true)
      }
    },
    // 获取所有歌曲
    async getSongList() {
      const { data: res } = await getSongList
      if (res.code === 200) {
        this.songList = res.data
      } else {
        this.$message.error(res.msg)
      }
    },
  }
}
</script>

<style lang="scss" scoped>
div {
  /* 取消div标签在移动端点击时的蓝色 */
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
  -webkit-user-select: none;
  -moz-user-focus: none;
  -moz-user-select: none;
}

.song-list {
  width: 100%;
  padding: 2rem 0 3rem;

  .list-title {
    margin-top: 0.5rem;
    padding-bottom: 0.5rem;
    border-bottom: 2px solid slategray;

    .el-col {
      font-size: 1.2rem;
      color: #f2711c;
    }
  }

  .song-list {
    font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
    font-size: 0.8rem;
    padding: 0.5rem 0 0.5rem 0;
    border-bottom: 1px solid slategray;

    .el-col {
      color: #000000;
    }

    .el-col:last-child {
      color: #30a4fc;
    }
  }

  .song-list:hover {
    background: rgb(180, 180, 180);
    cursor: pointer;
  }
}
</style>