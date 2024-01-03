<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-06 16:29:18
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-29 18:19:17
 * @FilePath: \blog-client\src\components\songlist\LyricInfo.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="lyric-info">
    <el-row justify="center">
      <el-col class="content-name">{{ songContent.name }}</el-col>
    </el-row>
    <el-row justify="center" class="songer">{{ songContent.singer }}</el-row>
    <el-row justify="center" class="lyric-contain">
      <!-- 有歌词 -->
      <el-col v-if="lyricList.length > 1" class="song-lyric">
        <div v-for="(item, index) in preRow" :key="index" class="lyric-row lyric-row-over">{{ item[1] }}</div>
        <div class="lyric-row lyric-row-active">{{ currentRow[1] }}</div>
        <div v-for="(item, index) in nextRow" :key="index" class="lyric-row">{{ item[1] }}</div>
      </el-col>
      <!-- 没有歌词 -->
      <el-col v-else class="no-lyric">
        <el-row justify="center">SSY_Blog 正在努力加载歌词中...</el-row>
        <br />
        <el-row justify="center">暂时先听听音乐吧！</el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mixin } from '@/mixins'

export default {
  mixins: [mixin],
  name: 'LyricInfo',
  data() {
    return {
      lyricList: [], // 歌词列表
      preRow: [], // 已经播放的歌词
      currentRow: [], // 当前歌词
      nextRow: [], // 未播放的歌词
    }
  },
  computed: {
    ...mapGetters('m_song', ['songContent', 'currentSeconds'])
  },
  watch: {
    currentSeconds() {
      // 确保歌词已经加载完毕
      if (this.lyricList.length > 1) {
        for (let i = 0; i < this.lyricList.length; i++) {
          if (this.currentSeconds > this.lyricList[i][0] && this.currentSeconds < this.lyricList[i + 1][0]) {
            // 已经播放的歌词
            this.preRow = this.lyricList.slice(0, i)
            if (this.preRow.length > 3) {
              this.preRow = this.preRow.slice(this.preRow.length - 3, this.preRow.length)
            }
            // 当前播放的歌词
            this.currentRow = this.lyricList[i]
            // 未播放的歌词
            this.nextRow = this.lyricList.slice(i + 1, i + 6)
          }
        }
      }
    },
    songContent: {
      handler() {
        this.parseLyricList()
      },
      immediate: true
    }
  },
  methods: {
    parseLyricList() {
      this.lyricList = this.parseLyric(this.songContent.lyric)
    }
  },
}
</script>

<style lang="scss" scoped>
.lyric-info {
  width: 100%;
  padding: 2rem 0 3rem;

  .content-name {
    font-size: 1.5rem;
    font-weight: bold;
    text-align: center;
    margin-top: 0.5rem;
  }

  .songer {
    font-size: 1rem;
    margin: 1rem 0 2rem;
  }

  .lyric-contain {
    .song-lyric {
      font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;

      .lyric-row {
        font-size: 1rem;
        color: #2c3e50;
        text-align: center;
        margin: 1rem auto;
      }

      .lyric-row-over {
        color: rgb(111, 111, 111);
        font-size: 0.9rem;
      }

      .lyric-row-active {
        color: #30a4fc;
        font-size: 1.3rem;
        text-shadow: #9dccf0 2px 2px 10px;
      }
    }

    .no-lyric {
      font-size: 1.2rem;
      color: #f2711c;
    }
  }
}
</style>