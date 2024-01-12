<template>
  <div class="introduction-container">
    <el-card shadow="hover" class="box-card">
      <template #header>
        <div class="card-header">
          <h2>标题</h2>
        </div>
      </template>

      <div class="card-content" @click="gotoDetail(1)">
        <el-row :gutter="16" justify="space-around">
          <el-col :span="6">
            <img src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" />
          </el-col>
          <el-col :span="18">
            <p>这是一个基于Vue.js的前后端分离的博客系统，前端使用Vue.js，后端使用Spring Boot，数据库使用MySQL，使用Nginx作为反向代理服务器。这是一个基于Vue.js的前后端分离的博客系统，前端使用Vue.js，后端使用Spring Boot，数据库使用MySQL，使用Nginx作为反向代理服务器。这是一个基于Vue.js的前后端分离的博客系统，前端使用Vue.js，后端使用Spring Boot，数据库使用MySQL，使用Nginx作为反向代理服务器。</p>
          </el-col>
        </el-row>
      </div>

      <div class="card-footer">
        <el-row justify="space-between" align="middle">
          <el-col :span="16">
            <el-row justify="space-between">
              <el-col :span="8">
                <img src="@/assets/img/avatar1.png" alt="博客主" />
                <a href="javascript:;">Pluto_ssy</a>
              </el-col>
              <el-col :span="8">
                <el-icon>
                  <Calendar />
                </el-icon>
                <p>2023-11-29</p>
              </el-col>
              <el-col :span="8">
                <el-icon>
                  <View />
                </el-icon>
                <p>233</p>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="8">
            <div class="type">类型Type</div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Introduction',
  methods: {
    gotoDetail(id) {
      this.$router.push(`/article/${id}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.introduction-container {
  width: 100%;
  margin: 1.5rem auto;
  .box-card {
    background-color: #f5f7fa;

    .card-header {
      text-align: center;
      > h2 {
        font-size: 20px;
        font-weight: 600;
      }
    }
    .card-content {
      cursor: pointer;
      > .el-row {
        width: 100%;
        > .el-col:first-child {
          margin: auto; // 居中一下
          > img {
            width: 100%;
          }
        }
        > .el-col:last-child {
          > p {
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5;
            text-indent: 2em;
            text-align: justify;
            /* 超出部分隐藏并显示... */
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 5;
            -webkit-box-orient: vertical;
          }
        }
      }
    }

    .card-footer {
      color: #888686;
      font-size: 0.6rem;
      font-weight: 400;
      margin-top: 20px;
      > .el-row {
        > .el-col:first-child {
          .el-row {
            > .el-col {
              display: flex;
              align-items: center;
              > img {
                width: 1.5rem;
                height: 1.5rem;
                border-radius: 50%;
                cursor: pointer;
              }
              > a {
                color: #1e70bf;
                margin-left: 0.5rem;
              }
              > p {
                text-align: center;
                margin-left: 0.5rem;
                display: flex;
                align-items: center;
                justify-content: center;
              }
              i {
                font-size: 1rem;
                margin-right: 0.2rem;
              }
            }
          }
        }
        > .el-col:last-child {
          .type {
            width: 4rem;
            height: 100%;
            color: #00b5ad;
            border: 1px solid #00b5ad;
            border-radius: 10px;
            text-align: center;
            line-height: 2;
            margin: auto;
            cursor: pointer;

            &:hover {
              background-color: #00b5ad;
              color: #fff;
            }
          }
        }
      }
    }
  }
}
</style>