<template>
  <div class="lyric-info">
    <el-row justify="center">
      <el-col class="content-name">歌曲名字</el-col>
    </el-row>
    <el-row justify="center" class="songer">歌手</el-row>
    <el-row justify="center" class="lyric-contain">
      <el-col class="song-lyric">
        <el-row class="lyric-row" justify="center">歌词</el-row>
        <el-row
          class="lyric-row"
          justify="center"
          :style="{'font-size': (index==currentRow ? '1.3rem':'.9rem')}"
        >歌词</el-row>
        <el-row class="lyric-row" justify="center">歌词</el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'LyricInfo'
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
    margin: 1rem 0 3rem;
  }
  .lyric-contain {
    .song-lyric {
    }
  }
}
</style>