<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-06 16:29:18
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-28 18:02:12
 * @FilePath: \blog-client\src\components\songlist\LyricInfo.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="lyric-info">
    <el-row justify="center">
      <el-col class="content-name">歌曲名字</el-col>
    </el-row>
    <el-row justify="center" class="songer">歌手</el-row>
    <el-row justify="center" class="lyric-contain">
      <!-- 有歌词 -->
      <el-col v-if="lyricList" class="song-lyric">
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
import { mapState } from 'vuex'
import { mixin } from '@/mixins'

export default {
  mixins: [mixin],
  name: 'LyricInfo',
  data() {
    return {
      lyric: '[00:00.00] 作词 : Riesling[00:00.99] 作曲 : 陈鹏杰[00:01.98]词：Riesling[00:03.22]曲：陈鹏杰[00:05.23]原唱：流浪的蛙蛙[00:07.03]翻唱：悟我[00:11.90]后期：添添[00:13.74]《斗破苍穹.特别篇》片尾曲[00:19.98]一片云落入掌心[00:24.12]渺渺万里何处去[00:28.44]只记取离别时的你[00:32.24]趁月朗风清[00:36.94]转身过不知归期[00:41.09]愿我如那时的风[00:45.85]拂过你的发鬓 轻拥着你[00:53.04]枉将箫声吹尽[00:57.11]沧海一遇却难找寻[01:01.60]前路崇山峻岭不再有你同行[01:10.17]纵使微茫如烟 纵有万般思念[01:18.69]流光总将故人搁浅在断简残篇[01:23.55]不成眠[01:27.05]不等谁来证明 不必狂歌痛饮[01:35.44]唯盼重相见[01:39.53]归来时一醉明月[02:02.00]那片云落入我心[02:06.16]渺渺万里何处寻[02:10.19]只记取离别时的你[02:14.07]趁月朗风清[02:18.95]经年过不知归期[02:23.01]愿我如那时的风[02:27.93]拂过你的发鬓 轻拥着你[02:34.91]苍穹箫声吹尽[02:39.20]此生魂梦已逐云去[02:43.71]前路崇山峻岭不再有你同行[02:52.04]纵使微茫如烟 纵有万般思念[03:00.52]流光总将故人搁浅在断简残篇[03:05.32]不成眠[03:09.09]不等谁来证明 不必狂歌痛饮[03:17.63]唯盼重相见[03:21.25]我如倦鸟归林[03:26.36]纵使微茫如烟 纵有万般思念[03:34.42]流光总将故人搁浅在断简残篇[03:39.50]不成眠[03:43.12]不理白驹过隙 不信生死有命[03:51.49]唯盼重相见[03:55.70]归来时一醉明月',
      lyricList: [], // 歌词列表
      preRow: [], // 已经播放的歌词
      currentRow: [], // 当前歌词
      nextRow: [], // 未播放的歌词
    }
  },
  computed: {
    ...mapState('m_song', ['songContent', 'currentSeconds'])
  },
  watch: {
    currentSeconds() {
      // 确保歌词已经加载完毕
      if (this.lyricList.length > 0) {
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
    }
  },
  mounted() {
    this.parseLyricList()
  },
  methods: {
    parseLyricList() {
      this.lyricList = this.parseLyric(this.lyric)
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