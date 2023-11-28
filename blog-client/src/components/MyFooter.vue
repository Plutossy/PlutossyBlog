<template>
  <!-- 底部部分 -->
  <div class="footer-container">
    <el-row>
      <el-col :span="6" class="el-col-code">
        <div class="grid-content ep-bg-purple-light grid-content-code">
          <img src="@/assets/img/CSDN.jpg" alt="二维码" />
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content ep-bg-purple-light">
          <h3>最新博客</h3>
          <ul>
            <li>
              <a href>博客标题1</a>
            </li>
            <li>
              <a href>博客标题2</a>
            </li>
            <li>
              <a href>博客标题3</a>
            </li>
            <li>
              <a href>博客标题4</a>
            </li>
            <li>
              <a href>博客标题5</a>
            </li>
          </ul>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content ep-bg-purple">
          <h3>联系我</h3>
          <ul>
            <li>
              <img src="@/assets/img/svg/gmail.svg" />&nbsp;:&nbsp;&nbsp;
              <p>pluto_ssy@outlook.com</p>
            </li>
            <li>
              <img src="@/assets/img/svg/wechat.svg" />&nbsp;:&nbsp;&nbsp;
              <p>zhlysun</p>
            </li>
            <li>
              <img src="@/assets/img/svg/tencentqq.svg" />&nbsp;:&nbsp;&nbsp;
              <p>2728154199</p>
            </li>
            <li>
              <img src="@/assets/img/svg/github.svg" />&nbsp;:&nbsp;&nbsp;
              <a
                href="https://github.com/Plutossy"
                target="_blank"
              >https://github.com/Plutossy</a>
            </li>
            <li>
              <img src="@/assets/img/svg/celery.svg" />&nbsp;:&nbsp;&nbsp;
              <a
                href="https://blog.csdn.net/Pluto_ssy"
                target="_blank"
              >https://blog.csdn.net/Pluto_ssy</a>
            </li>
          </ul>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content ep-bg-purple-light">
          <h3>SSY_Blog</h3>
          <p>这是我的个人博客、会分享关于编程、写作、思考相关的任何内容，希望可以给来到这儿的人有所帮助...</p>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <p>Copyright &copy; 2023-11 SSY_Blog Designed by Pluto_ssy</p>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <a rel="nofollow" href="javascript:;" target="_blank">
          <span>赣ICP备20004408号-1</span>
        </a>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="grid-content-time">
          <span v-if="time !== ''">我的星球已飞行 {{time}}</span>
          <span v-else>载入天数中...载入时分秒中...</span>
          <span>&nbsp;(*๓´╰╯`๓)</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'MyFooter',
  data() {
    return {
      time: ''
    }
  },
  mounted() {
    this.getTime()
  },
  updated() {
    this.getTime()
  },
  methods: {
    // 计算运行时间
    countTime(time) {
      let createTime = new Date(time) //在此处修改你的建站时间，格式：年-月-日 时:分:秒
      let nowTime = new Date() //获取当前时间
      let times = (createTime - nowTime) / 1000 //剩余时间总秒数
      let d = parseInt(times / 60 / 60 / 24) //计算天数
      // d = d < 10 ? '0' + d : d
      let h = parseInt((times / 60 / 60) % 24) //计算小时
      // h = h < 10 ? '0' + h : h
      let m = parseInt((times / 60) % 60) //计算分数
      // m = m < 10 ? '0' + m : m
      let s = parseInt(times % 60) //计算当前秒数
      // s = s < 10 ? '0' + s : s
      let countTime = d + ' 天 ' + h + ' 个小时 ' + m + ' 分 ' + s + ' 秒啦! '
      // 去除'-'号
      while (countTime.indexOf('-') !== -1) {
        countTime = countTime.replace('-', '')
      }
      return countTime
    },
    // 开启定时器获取时间
    getTime() {
      setInterval(() => {
        this.time = this.countTime('2023-11-27 12:00:00')
      }, 1000)
    }
  }
}
</script>

<style lang="scss" scoped>
.footer-container {
  color: rgba(255, 255, 255, 0.9);
  // display: flex;
  // flex-direction: column;
  // justify-content: center;
  .el-row {
    background-color: rgba($color: #000000, $alpha: 0.6);
    .el-col {
      border-radius: 4px;
      display: flex;
      justify-content: center;
      .grid-content {
        border-radius: 4px;
        min-height: 12rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        &.grid-content-code {
          justify-content: center;
        }
        > img {
          width: 10rem;
          height: 10rem;
        }
        h3 {
          font-size: 1.3rem;
          margin-bottom: 1rem;
        }
        ul > li {
          margin-bottom: 0.4rem;
          display: flex;
          align-items: center;
          align-content: space-around;
          > img {
            display: block;
            background-color: currentColor;
            border-radius: 0.4rem;
            width: 1.5rem;
            height: 1.5rem;
          }
          p {
            color: #aaa;
          }
        }
        > p {
          text-indent: 2em;
          line-height: 1.5;
        }
      }
      > p {
        color: #aaa;
      }
      > a {
        margin-top: 0.2rem;
      }
      .grid-content-time {
        color: #f08047;
      }
    }
    .el-col-code {
      align-items: center;
    }
    .el-col:last-child {
      padding: 0 1rem;
    }
  }
  .el-row:first-child {
    padding-top: 2rem;
    padding-bottom: 2rem;
  }
  .el-row:last-child {
    padding-top: 1rem;
    padding-bottom: 1rem;
  }
  a {
    color: #1e70bf;
    &:hover {
      color: rgba($color: #1e70bf, $alpha: 0.8);
    }
  }
}
</style>