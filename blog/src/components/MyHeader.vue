<template>
  <div class="header-container">
    <h2 class="logo">SSY_Blog</h2>
    <!-- 导航栏 -->
    <nav>
      <router-link to="/index">
        <el-icon>
          <HomeFilled />
        </el-icon>&nbsp;首页
      </router-link>
      <router-link to="/type">
        <el-icon>
          <Crop />
        </el-icon>&nbsp;分类
      </router-link>
      <router-link to="/tag">
        <el-icon>
          <PriceTag />
        </el-icon>&nbsp;标签
      </router-link>
      <router-link to="/clone">
        <el-icon>
          <CopyDocument />
        </el-icon>&nbsp;归档
      </router-link>
      <router-link to="/music">
        <el-icon>
          <Headset />
        </el-icon>&nbsp;音乐盒
      </router-link>
      <router-link to="/picture">
        <el-icon>
          <PictureFilled />
        </el-icon>&nbsp;照片墙
      </router-link>
      <router-link to="/about">
        <el-icon>
          <UserFilled />
        </el-icon>&nbsp;关于我
      </router-link>
    </nav>
    <!-- 搜索框组件 -->
    <my-search />
  </div>
</template>

<script>
import MySearch from './MySearch.vue'
export default {
  name: 'MyHeader',
  components: {
    MySearch
  }
}
</script>

<style lang="scss" scoped>
.header-container {
  padding: 0.8rem 5rem;
  border-bottom: 1px solid #b2b1b1;
  box-shadow: 0 1px #ccc;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .logo {
    font-size: 28px;
    font-weight: 700;
    color: #f2711c;
    text-shadow: 1px 1px 1px #ccc;
  }

  nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    a {
      padding: 0.7rem 2rem;
      color: rgb(225, 224, 224);
      display: flex;
      justify-content: center;
      align-items: center;
      &:hover {
        color: #f2711c;
        background-color: rgba($color: #000000, $alpha: 0.3);
        border-radius: 5px;
        cursor: pointer;
      }
      i {
        vertical-align: middle;
      }
    }
    .router-link-active {
      color: #f2711c;
      background-color: rgba($color: #000000, $alpha: 0.4);
      border-radius: 5px;
      cursor: pointer;
    }
  }
}
</style>