<template>
  <div class="clone-container">
    <!--顶部背景信息-->
    <div class="top-img animate__animated animate__fadeInDown">
      <div>归档</div>
      <div>山不辞土，故能成其高。</div>
    </div>
    <!-- 主体部分 -->
    <div class="content">
      <el-row justify="space-between" align="middle">
        <el-col :span="3">
          <h2>创作历程</h2>
        </el-col>
        <el-col :span="2">
          <p>
            共
            <span>7</span> 篇
          </p>
        </el-col>
      </el-row>

      <div>
        <el-row justify="center" align="middle">
          <h2>2023</h2>
        </el-row>
        <div class="content-title">
          <el-row align="middle">
            <!-- 点点 -->
            <el-col :span="1">
              <div></div>
            </el-col>
            <!-- 标题 -->
            <el-col :span="6">昆山玉碎凤凰叫,芙蓉泣露香兰笑。</el-col>
            <!-- 创作日期 -->
            <el-col :span="2">
              <div>3月24日</div>
            </el-col>
            <!-- 原创 / 转载 -->
            <el-col :span="1" :offset="13">
              <div>原创</div>
            </el-col>
          </el-row>

          <el-row align="middle">
            <el-col :span="1">
              <div></div>
            </el-col>
            <el-col :span="6">昆山玉。</el-col>
            <el-col :span="2">
              <div>3月24日</div>
            </el-col>
            <el-col :span="1" :offset="13">
              <div>原创</div>
            </el-col>
          </el-row>

          <el-row align="middle">
            <el-col :span="1">
              <div></div>
            </el-col>
            <el-col :span="6">昆山玉碎凤凰叫,芙蓉泣露香兰笑。</el-col>
            <el-col :span="2">
              <div>3月24日</div>
            </el-col>
            <el-col :span="1" :offset="13">
              <div>原创</div>
            </el-col>
          </el-row>
        </div>
      </div>

      <div>
        <el-row justify="center" align="middle">
          <h2>2022</h2>
        </el-row>
        <div class="content-title">
          <el-row align="middle">
            <!-- 点点 -->
            <el-col :span="1">
              <div></div>
            </el-col>
            <!-- 标题 -->
            <el-col :span="6">昆山玉碎凤凰露香兰笑。</el-col>
            <!-- 创作日期 -->
            <el-col :span="2">
              <div>3月24日</div>
            </el-col>
            <!-- 原创 / 转载 -->
            <el-col :span="1" :offset="13">
              <div>原创</div>
            </el-col>
          </el-row>

          <el-row align="middle">
            <el-col :span="1">
              <div></div>
            </el-col>
            <el-col :span="6">昆山玉叫,芙蓉泣露香兰笑。</el-col>
            <el-col :span="2">
              <div>3月24日</div>
            </el-col>
            <el-col :span="1" :offset="13">
              <div>原创</div>
            </el-col>
          </el-row>

          <el-row align="middle">
            <el-col :span="1">
              <div></div>
            </el-col>
            <el-col :span="6">昆山香兰笑。</el-col>
            <el-col :span="2">
              <div>3月24日</div>
            </el-col>
            <el-col :span="1" :offset="13">
              <div>原创</div>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style lang="scss" scoped>
.clone-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  .top-img {
    width: 100%;
    background: url(@/assets/img/bg4.jpeg) no-repeat center center;
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

  .content {
    position: relative;
    z-index: 1;
    // font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
    background-color: rgba(255, 255, 255, 0.7);
    width: 90%;
    margin-top: -2rem;
    margin-bottom: 2rem;
    padding: 3rem 0;
    border-radius: 1rem;
    box-shadow: 0 1px 2px 0 rgba(34, 36, 38, 0.6);
    display: flex;
    flex-direction: column;
    align-items: center;

    > .el-row {
      width: 95%;
      margin-bottom: 1rem;
      padding: 1rem 0;
      font-size: 1.2rem;
      font-weight: 560;
      color: #000000;
      border-bottom: 1px solid #000000;
      border-radius: 0.5rem;
      box-shadow: 0 1px 2px 0 rgba(34, 36, 38, 0.6);
      h2 {
        color: #f2711c;
      }
      p {
        font-family: 'STXingkai', serif;
        span {
          color: #f2711c;
          font-size: xx-large;
        }
      }
      .el-col {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
    > div {
      width: 95%;
      margin-bottom: 1rem;
      padding: 1rem 0;
      font-size: 1.2rem;
      font-weight: 560;
      color: #000000;
      border-bottom: 1px solid #000000;
      border-radius: 0.5rem;
      box-shadow: 0 1px 2px 0 rgba(34, 36, 38, 0.6);
      > .el-row {
        border-bottom: 1px solid #878787;
        h2 {
          font-family: 'STXingkai', serif;
          font-size: 2rem;
        }
      }

      .content-title {
        width: 100%;
        padding: 0 1rem;
        > .el-row {
          font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
          font-size: 1.2rem;
          font-weight: 500;
          margin-top: 1rem;
          cursor: pointer;
          > .el-col:nth-child(1) {
            div {
              width: 0.5rem;
              height: 0.5rem;
              border-radius: 50%;
              background-color: #00b5ad;
              margin: 0 auto;
            }
          }
          > .el-col:nth-child(3) {
            div {
              color: #00b5ad;
              border: 2px solid #00b5ad;
              border-radius: 5px;
              display: flex;
              justify-content: center;
              align-items: center;
              position: relative;
              &::before {
                content: '';
                display: block;
                width: 0.7rem;
                height: 0.7rem;
                background: none rgb(188, 188, 188);
                position: absolute;
                top: 50%;
                left: 0;
                transform: translate(-50%, -50%) rotate(45deg);
                border-style: solid;
                border-width: 2px;
                border-color: transparent transparent #00b5ad #00b5ad;
              }
            }
          }
          > .el-col:nth-child(4) {
            div {
              color: #f2711c;
              border: 2px solid #f2711c;
              border-radius: 5px;
              display: flex;
              justify-content: center;
              align-items: center;
            }
          }
        }
      }
    }
  }
}
</style>