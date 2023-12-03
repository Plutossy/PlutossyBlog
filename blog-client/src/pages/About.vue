<template>
  <div class="about-container">
    <!--顶部背景信息-->
    <div class="top-img animate__animated animate__fadeInDown">
      <div>关于我</div>
      <div>头一次活这么久，记得善待自己</div>
    </div>

    <!--个人资料描述-->
    <div class="content animate__animated animate__rollIn">
      <!-- 头像 -->
      <div class="content-avatar">
        <img src="../assets/img/head.jpg" />
      </div>
      <!-- 昵称 -->
      <div class="content-name">Pluto_ssy</div>

      <!-- 链接 -->
      <div class="content-a">
        <ul>
          <li>
            <el-popover
              placement="bottom"
              trigger="hover"
              :width="150"
              popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
            >
              <template #reference>
                <span></span>
              </template>
              <template #default>
                <img src="@/assets/img/wechat.jpg" />
                <!-- <div class="hover-img"></div> -->
              </template>
            </el-popover>
            <!-- <span></span> -->
          </li>
          <li>
            <el-popover
              placement="bottom"
              trigger="hover"
              :width="150"
              popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
            >
              <template #reference>
                <span></span>
              </template>
              <template #default>
                <img src="@/assets/img/qq.jpg" />
                <!-- <div class="hover-img"></div> -->
              </template>
            </el-popover>
          </li>
          <li>
            <el-popover
              placement="bottom"
              trigger="hover"
              :width="160"
              popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
            >
              <template #reference>
                <a href="https://github.com/Plutossy" target="_blank"></a>
              </template>
              <template #default>
                <div class="hover-github">点击跳转到GitHub</div>
              </template>
            </el-popover>
          </li>
          <li>
            <el-popover
              placement="bottom"
              trigger="hover"
              :width="160"
              popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
            >
              <template #reference>
                <a href="https://blog.csdn.net/Pluto_ssy" target="_blank"></a>
              </template>
              <template #default>
                <div class="hover-csdn">点击跳转到CSDN</div>
              </template>
            </el-popover>
          </li>
          <li>
            <el-popover
              placement="bottom"
              trigger="hover"
              :width="210"
              popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
            >
              <template #reference>
                <span></span>
              </template>
              <template #default>
                <div class="hover-email">pluto_ssy@outlook.com</div>
              </template>
            </el-popover>
          </li>
        </ul>
      </div>

      <!-- 简介 -->
      <div class="content-detail">
        <p>你好！我是外星人</p>
        <p>多年来一直尝试与地球人联系</p>
        <p>这是第666次试验</p>
        <p>为了方便统计，如果你收到并看懂此消息</p>
        <p>请站在附近最高处仰天长啸！</p>
        <br />
        <br />
        <p>“各位网友，你们好！“</p>
        <p>”我是Pluto_ssy，此网站的管理员“</p>
        <p>“00后”</p>
        <p>“爱编程、爱折腾”</p>
        <br />
        <br />
        <p>九曲黄河天上流，迂回百代绕春秋，</p>
        <p>折腾本事家常饭，多少良心挂钓钩。</p>
        <br />
        <br />
        <p>生命在于折腾</p>
        <p>你错过的人和事，别人才有机会遇到，别人错过了，你才有机会拥有。</p>
        <p>人人都会错过，人人都曾经错过，真正属于你的，永远不会错过。</p>
      </div>

      <!-- 打赏 -->
      <div class="content-dashang">
        <h2>打赏本站</h2>
        <p>初到地球，生活窘迫，暂无粮饷。期待您与我联系，并请我吃一个香气喷喷的汉堡包！</p>
        <div class="pay">
          <div class="zhifubao">
            <span></span>
            <p>支付宝</p>
          </div>
          <div class="wechat">
            <span></span>
            <p>微信</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style lang="scss" scoped>
// 鼠标悬浮显示图片
.el-popover img {
  width: 100%;
  height: 100%;
}
.about-container {
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
    .content-avatar {
      margin-top: 2rem;
      width: 15%;
      img {
        width: 100%;
        border-radius: 50%;
      }
    }
    .content-name {
      font-family: STSong;
      margin-top: 0.6rem;
      font-size: x-large;
      text-align: center;
    }
    .content-a {
      width: 16rem;
      margin-top: 1.5rem;
      padding: 0 2rem 1rem;
      border-bottom: 2px solid #7a7a7a;
      ul {
        display: flex;
        justify-content: space-around;
        align-items: center;
        li {
          width: 2.5rem;
          height: 2.5rem;
          background: #e0e1e2 none;
          border-radius: 50%;
          display: flex;
          justify-content: center;
          align-items: center;
          cursor: pointer;

          span {
            display: inline-block;
            width: 1.5rem;
            height: 1.5rem;
          }
          a {
            display: inline-block;
            width: 1.5rem;
            height: 1.5rem;
          }

          &:hover {
            background: #b1b1b1 none;
          }
        }
        li:nth-child(1) {
          span {
            background: url(@/assets/img/svg/wechat.svg) no-repeat center center;
            background-size: cover;
          }
        }
        li:nth-child(2) {
          span {
            background: url(@/assets/img/svg/tencentqq.svg) no-repeat center center;
            background-size: cover;
          }
        }
        li:nth-child(3) {
          a {
            background: url(@/assets/img/svg/github.svg) no-repeat center center;
            background-size: cover;
          }
        }
        li:nth-child(4) {
          a {
            background: url(@/assets/img/svg/celery.svg) no-repeat center center;
            background-size: cover;
          }
        }
        li:nth-child(5) {
          span {
            background: url(@/assets/img/svg/gmail.svg) no-repeat center center;
            background-size: cover;
          }
        }
      }
    }
    .content-detail {
      margin-top: 4rem;
      width: 80%;
      color: #3c2d23;
      font-size: 2rem;
      line-height: 2rem;
      text-align: center;
      border-bottom: 1px solid #7a7a7a;
      box-shadow: 0 2px 0 #3c2d23;
      p {
        margin-bottom: 1rem;
      }
    }
    .content-dashang {
      font-family: STSong;
      margin: 2rem 0;
      width: 80%;
      text-align: center;
      h2 {
        margin-bottom: 1rem;
        font-size: xx-large;
        font-weight: bold;
        color: #f08047;
      }
      > p {
        margin-bottom: 1rem;
        font-size: large;
        color: #f08047;
      }
      .pay {
        font-size: large;
        display: flex;
        justify-content: center;
        align-items: center;
        border: 1px solid #f08047;
        border-radius: 5px;
        > div {
          margin: 1rem 0;
          span {
            width: 10rem;
            height: 10rem;
            display: inline-block;
          }
          p {
            margin-top: 0.5rem;
          }
        }
        .zhifubao {
          margin-right: 10rem;
          span {
            background: url(@/assets/img/zhifupay.jpg) no-repeat center center;
            background-size: cover;
          }
        }
        .wechat {
          span {
            background: url(@/assets/img/wechatpay.jpg) no-repeat center center;
            background-size: cover;
          }
        }
      }
    }
  }
}
</style>