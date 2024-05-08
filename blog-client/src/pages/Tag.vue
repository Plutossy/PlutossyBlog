<template>
  <div class="type-container">
    <el-row>
      <el-card shadow="hover" class="box-card animate__animated animate__fadeInRight">
        <template #header>
          <div class="card-header">
            <h2>标签</h2>
            <div class="header-right">
              共
              <span>888</span> 个
            </div>
          </div>
        </template>
        <div class="card-content">
          <div
            class="active"
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签1</span>
            <span>8</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签2</span>
            <span>10</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签3</span>
            <span>5</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>

          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>

          <div
            :style="{
              borderRadius: `var(--el-border-radius-base)`,
            }"
          >
            <span>标签4</span>
            <span>23</span>
          </div>
        </div>
      </el-card>
    </el-row>

    <el-row class="animate__animated animate__fadeInUpBig">
      <introduction />
    </el-row>
    <el-row class="animate__animated animate__fadeInUpBig">
      <introduction />
    </el-row>
    <el-row class="animate__animated animate__fadeInUpBig">
      <introduction />
    </el-row>
    <el-row class="animate__animated animate__fadeInUpBig">
      <introduction />
    </el-row>
  </div>
</template>

<script>
import Introduction from '../components/introduction/Introduction.vue';
export default {
  components: {
    Introduction,
  },
};
</script>

<style lang="scss" scoped>
.type-container {
  width: 100%;
  .box-card {
    width: 100%;
    .card-header {
      color: #f2711c;
      display: flex;
      justify-content: space-between;
      align-items: center;
      .header-right {
        font-family: 'STXingkai', serif;
        color: #0f0f0f;
        span {
          color: #f2711c;
          font-size: 2rem;
          font-weight: 560;
        }
      }
    }
    .card-content {
      display: flex;
      justify-content: space-around;
      align-items: center;
      flex-wrap: wrap;
      > div {
        /* 取消div标签在移动端点击时的蓝色 */
        -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
        user-select: none;
        -webkit-user-select: none;
        -moz-user-focus: none;
        -moz-user-select: none;

        width: 8rem;
        height: 2rem;
        margin: 1rem;
        border: 2px solid #ccc;
        display: flex;
        justify-content: space-between;
        align-items: center;
        cursor: pointer;
        span:first-child {
          width: 70%;
          color: #737373;
          text-align: center;
        }
        span:last-child {
          height: 100%;
          display: flex;
          align-items: center;
          justify-content: center;
          color: #737373;
          width: 30%;
          position: relative;
          border-left: #ccc 2px solid;

          &::before {
            content: '';
            position: absolute;
            display: block;
            background-color: #ffffff;
            width: 0.6rem;
            height: 0.6rem;
            border-width: 2px;
            border-style: solid;
            border-color: transparent transparent #ccc #ccc;
            top: 50%;
            left: 0;
            transform: translateX(-50%) translateY(-50%) rotate(45deg);
          }
        }
        &:hover {
          border-color: #2185d0;
          span:first-child {
            color: #2185d0;
          }
          span:last-child {
            color: #2185d0;
            border-color: #2185d0;
            &::before {
              border-color: transparent transparent #2185d0 #2185d0;
            }
          }
        }
        &.active {
          border-color: #2185d0;
          span:first-child {
            color: #2185d0;
          }
          span:last-child {
            color: #2185d0;
            border-color: #2185d0;
            &::before {
              border-color: transparent transparent #2185d0 #2185d0;
            }
          }
        }
      }
    }
  }
  > .el-row:nth-child(n) {
    margin: 0 5rem;
  }
  > .el-row:first-child {
    margin-top: 1rem;
  }
  > .el-row:last-child {
    margin-bottom: 2rem;
  }
}
</style>
