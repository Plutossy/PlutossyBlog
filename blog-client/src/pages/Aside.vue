<template>
  <div class="aside-container">
    <h2 class="animate__animated animate__backInRight animate__delay-2s">侧边栏</h2>
    <!-- 分类侧边栏 -->
    <el-card shadow="hover">
      <template #header>
        <div class="card-header">
          <div>
            <el-icon>
              <Suitcase />
            </el-icon>分类
          </div>

          <div>
            更多
            <el-icon>
              <DArrowRight />
            </el-icon>
          </div>
        </div>
      </template>
      <div class="card-content">
        <el-row>
          <el-col :span="12">分类1</el-col>
          <el-col :span="12">22</el-col>
        </el-row>
        <el-row>
          <el-col :span="12">分类2</el-col>
          <el-col :span="12">10</el-col>
        </el-row>
        <el-row>
          <el-col :span="12">分类3</el-col>
          <el-col :span="12">12</el-col>
        </el-row>
      </div>
    </el-card>

    <!-- 标签侧边栏 -->
    <el-card shadow="hover">
      <template #header>
        <div class="card-header">
          <div>
            <el-icon>
              <Suitcase />
            </el-icon>标签
          </div>

          <div>
            更多
            <el-icon>
              <DArrowRight />
            </el-icon>
          </div>
        </div>
      </template>
      <div class="card-content">
        <el-row>分类2</el-row>
        <el-row>分类2</el-row>
        <el-row>分类2</el-row>
      </div>
    </el-card>

    <el-card shadow="hover">
      <template #header>
        <h3>个人成就</h3>
      </template>
      <div>
        <el-row>分类2</el-row>
        <el-row>分类2</el-row>
        <el-row>分类2</el-row>
      </div>
    </el-card>

    <el-card shadow="hover">
      <template #header>
        <h3>扫码关注我</h3>
      </template>
      <div>
        <img src="@/assets/img/CSDN.jpg" alt="二维码加载失败" />
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Aside'
}
</script>

<style lang="scss" scoped>
.aside-container {
  width: 100%;
  margin: 1rem auto;
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
        > .el-icon {
          margin-right: 0.5rem;
        }
      }
      > div:last-child {
        color: #4183c4;
        display: flex;
        align-items: center;
        cursor: pointer;
        > .el-icon {
          margin-left: 0.5rem;
        }
      }
    }
    .card-content {
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
        text-align: center;
        font-family: serif;
        color: #575757;
        border-bottom: 1px solid #ccc;
        cursor: pointer;
        &:hover {
          color: #4183c4;
          background-color: #f4f4f4;
          border-radius: 0.5rem;
        }
      }
      // > .el-row:last-child {
      //   border-bottom: none;
      // }
    }
  }
}
</style>