<template>
  <div class="aside-container">
    <h2 class="animate__animated animate__backInRight animate__delay-1s">侧边栏</h2>
    <!-- 分类侧边栏 -->
    <el-card shadow="hover">
      <template #header>
        <div class="card-header">
          <div>
            <el-icon> <Suitcase /> </el-icon>分类
          </div>

          <div>
            更多
            <el-icon>
              <DArrowRight />
            </el-icon>
          </div>
        </div>
      </template>
      <div class="card-content-type">
        <el-row justify="space-between">
          <el-col :span="20">分类1</el-col>
          <el-col :span="4">22</el-col>
        </el-row>
        <el-row>
          <el-col :span="20">分类2</el-col>
          <el-col :span="4">10</el-col>
        </el-row>
        <el-row>
          <el-col :span="20">分类3</el-col>
          <el-col :span="4">12</el-col>
        </el-row>
      </div>
    </el-card>

    <!-- 标签侧边栏 -->
    <el-card shadow="hover">
      <template #header>
        <div class="card-header">
          <div>
            <el-icon> <Suitcase /> </el-icon>标签
          </div>

          <div>
            更多
            <el-icon>
              <DArrowRight />
            </el-icon>
          </div>
        </div>
      </template>
      <div class="card-content-tag">
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签1
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签2
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签3
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签类型4
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签类型5
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签6
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签7
        </div>
        <div
          :style="{
            borderRadius: `var(--el-border-radius-round)`,
          }"
        >
          标签类型8
        </div>
      </div>
    </el-card>

    <el-card shadow="hover">
      <template #header>
        <h3><img src="@/assets/img/svg/achieve.svg" />个人成就</h3>
      </template>
      <div class="card-content-achievement">
        <ul>
          <li>
            <i></i>
            <div>
              获得
              <span>81</span> 次点赞
            </div>
          </li>
          <li>
            <i></i>
            <div>
              内容获得
              <span>50</span> 次评论
            </div>
          </li>
          <li>
            <i></i>
            <div>
              获得
              <span>445</span> 次收藏
            </div>
          </li>
        </ul>
      </div>
    </el-card>

    <el-card shadow="hover">
      <template #header>
        <h3>
          <el-icon color="orange"><Notebook /></el-icon>
          最新博客
        </h3>
      </template>
      <div class="card-content-newblog">
        <el-row @click="gotoDetail(1)">博客标题1</el-row>
        <el-row>博客标题2</el-row>
        <el-row>博客标题3</el-row>
        <el-row>博客标题4</el-row>
        <el-row>博客标题5</el-row>
      </div>
    </el-card>

    <el-card shadow="hover">
      <template #header>
        <h3 class="collection-btn"><svg-icon icon-class="hand" size="25px"></svg-icon>扫码关注我</h3>
      </template>
      <div class="card-content-code">
        <img src="@/assets/img/CSDN.jpg" alt="二维码" />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const gotoDetail = (id: number) => {
  router.push(`/article/${id}`);
};
</script>

<style lang="scss" scoped>
@import url('@/assets/scss/animate.scss');
.aside-container {
  width: 100%;
  margin: 0 auto 1rem;
  display: flex;
  flex-direction: column;

  h2 {
    font-size: 2.3rem;
    font-weight: 560;
    text-align: center;
    font-family: 'STXingkai', serif;
    color: orange;
    margin-bottom: 1rem;
  }

  .el-card {
    margin-bottom: 1rem;
    margin-top: 0.5rem;
    .card-header {
      padding: 1rem;
      border-radius: 1rem;
      border: 1px solid #ccc;
      box-shadow: 0 6px 10px #ccc;
      display: flex;
      justify-content: space-between;
      align-items: center;
      > div:first-child {
        display: flex;
        align-items: center;
        color: #b18b17;
        > .el-icon {
          margin-right: 0.5rem;
        }
      }
      > div:last-child {
        color: #4183c4;
        display: flex;
        align-items: center;
        cursor: pointer;
        /* 取消div标签在移动端点击时的蓝色 */
        -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
        user-select: none;
        -webkit-user-select: none;
        -moz-user-focus: none;
        -moz-user-select: none;
        > .el-icon {
          margin-left: 0.5rem;
        }
      }
    }

    h3 {
      padding: 0 1rem;
      margin: 0 auto;
      font-weight: 560;
      display: flex;
      align-items: center;
      justify-content: center;
      > :first-child {
        margin-right: 0.3rem;
        width: 1.2rem;
      }
    }
    .card-content-type {
      padding: 1rem;
      border-radius: 1rem;
      border: 1px solid #ccc;
      box-shadow: 0 6px 10px #ccc;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      > .el-row {
        padding: 0.5rem;
        font-weight: 560;
        font-family: serif;
        color: #575757;
        border-bottom: 1px solid #ccc;
        cursor: pointer;
        &:hover {
          color: #4183c4;
          background-color: #f4f4f4;
          border-radius: 0.5rem;
          .el-col:last-child::before {
            background-color: #f4f4f4;
          }
        }
        > .el-col:last-child {
          text-align: center;
          border: 1px #00b5ad solid;
          border-radius: 0.2rem;
          position: relative;
          &::before {
            content: '';
            position: absolute;
            display: block;
            background-color: #ffffff;
            width: 0.5rem;
            height: 0.5rem;
            border-width: 1px;
            border-style: solid;
            border-color: transparent transparent #00b5ad #00b5ad;
            top: 50%;
            left: 0;
            transform: translateX(-50%) translateY(-50%) rotate(45deg);
          }
        }
      }
    }
    .card-content-tag {
      border-radius: 1rem;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;
      > div {
        padding: 0.6rem;
        margin: 0.5rem;
        font-weight: 560;
        font-family: serif;
        color: #00b5ad;
        border: #00b5ad solid 1px;
        box-shadow: 0 6px 10px #ccc;
        cursor: pointer;
        &:hover {
          color: #4183c4;
          background-color: #f4f4f4;
          border-radius: 0.5rem;
        }
      }
    }
    .card-content-achievement {
      > ul {
        padding: 0 1rem;
        > li:nth-child(1) {
          i {
            background: url(@/assets/img/icon/like.png) no-repeat center;
            background-size: cover;
          }
        }
        > li:nth-child(2) {
          i {
            background: url(@/assets/img/icon/comment.png) no-repeat center;
            background-size: cover;
          }
        }
        > li:nth-child(3) {
          i {
            background: url(@/assets/img/icon/collect.png) no-repeat center;
            background-size: cover;
          }
        }
        > li {
          display: flex;
          align-items: center;
          margin-bottom: 1rem;
          > i {
            display: inline-block;
            width: 2rem;
            height: 2rem;
            margin-right: 1rem;
          }
          > div {
            font-weight: 560;
            font-family: serif;
            color: #575757;

            > span {
              color: #00b5ad;
            }
          }
        }
      }
    }

    .card-content-newblog {
      .el-row {
        border-bottom: 1px solid #ccc;
        padding: 1rem;
        cursor: pointer;
        &:hover {
          color: #4183c4;
          background-color: #f4f4f4;
          border-radius: 0.5rem;
        }
      }
      .el-row:last-child {
        margin-bottom: 0;
      }
    }

    .card-content-code {
      display: flex;
      justify-content: center;
      align-items: center;
      > img {
        width: 8rem;
      }
    }
  }
}
</style>
