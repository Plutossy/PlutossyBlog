<template>
  <div class="type-container">
    <el-row>
      <el-card shadow="hover" class="box-card">
        <template #header>
          <div class="card-header">
            <h2>分类</h2>
            <div class="header-right">
              共
              <span>6</span> 个
            </div>
          </div>
        </template>
        <div class="card-content">
          <div
            class="active"
            :style="{
            borderRadius: `var(--el-border-radius-base)`
          }"
          >
            <span>分类1</span>
            <span>8</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类2</span>
            <span>10</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类3</span>
            <span>5</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
          <div :style="{
            borderRadius: `var(--el-border-radius-base)`
          }">
            <span>分类4</span>
            <span>23</span>
          </div>
        </div>
      </el-card>
    </el-row>
    <el-row>
      <introduction />
    </el-row>
    <el-row>
      <introduction />
    </el-row>
    <el-row>
      <introduction />
    </el-row>
    <el-row>
      <introduction />
    </el-row>
    <el-row justify="center">
      <el-pagination
        :page-size="5"
        :pager-count="4"
        layout="prev, pager, next"
        :total="28"
        prev-text="上一页"
        next-text="下一页"
        :hide-on-single-page="true"
        class="mt-4"
        background
      />
    </el-row>
  </div>
</template>

<script>
import Introduction from '../components/introduction/Introduction.vue'
export default {
  components: {
    Introduction
  }
}
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
          border-color: #00b5ad;
          span:first-child {
            color: #00b5ad;
          }
          span:last-child {
            color: #00b5ad;
            border-color: #00b5ad;
            &::before {
              border-color: transparent transparent #00b5ad #00b5ad;
            }
          }
        }
        &.active {
          border-color: #00b5ad;
          span:first-child {
            color: #00b5ad;
          }
          span:last-child {
            color: #00b5ad;
            border-color: #00b5ad;
            &::before {
              border-color: transparent transparent #00b5ad #00b5ad;
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