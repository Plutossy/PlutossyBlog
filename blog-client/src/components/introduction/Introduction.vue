<template>
  <div class="introduction-container">
    <el-card shadow="hover" class="box-card">
      <template v-if="type === 'default'" #header>
        <div class="card-header">
          <h2>标题</h2>
        </div>
      </template>

      <div class="card-content" @click="gotoDetail(1)">
        <el-row :gutter="16" justify="space-around">
          <el-col :span="type === 'default' ? 6 : 24">
            <img src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" :class="type === 'recommend' ? 'content-img' : ''" />
          </el-col>
          <el-col v-if="type === 'default'" :span="18" class="content-text">
            <p>
              这是一个基于Vue.js的前后端分离的博客系统，前端使用Vue.js，后端使用Spring Boot，数据库使用MySQL，使用Nginx作为反向代理服务器。这是一个基于Vue.js的前后端分离的博客系统，前端使用Vue.js，后端使用Spring
              Boot，数据库使用MySQL，使用Nginx作为反向代理服务器。这是一个基于Vue.js的前后端分离的博客系统，前端使用Vue.js，后端使用Spring Boot，数据库使用MySQL，使用Nginx作为反向代理服务器。
            </p>
          </el-col>
          <el-col v-if="type === 'recommend'" class="content-time" :span="24">
            <el-icon>
              <i-ep-Calendar />
            </el-icon>
            <p>2023-11-29</p>
          </el-col>
          <el-col v-if="type === 'recommend'" class="content-title" :span="24">
            <h2>标题</h2>
          </el-col>
          <el-col v-if="type === 'recommend'" class="content-tips" :span="24">
            <div>
              <el-icon>
                <i-ep-View />
              </el-icon>
              <p>233</p>
            </div>
            <div>
              <svg-icon icon-class="heart" color="#E5404F" size="1.2rem"></svg-icon>
              <p>233</p>
            </div>
            <div>
              <svg-icon icon-class="comment" color="#272636" size="1.2rem"></svg-icon>
              <p>233</p>
            </div>
            <div>
              <svg-icon icon-class="collect" color="#1296db" size="1.2rem"></svg-icon>
              <p>233</p>
            </div>
          </el-col>
          <el-col v-if="type === 'recommend'" class="content-type" :span="24">
            <div class="type">
              <svg-icon icon-class="type" color="#FF9000" size="1.2rem"></svg-icon>
              <p>类型Type</p>
            </div>
          </el-col>
        </el-row>
      </div>

      <template v-if="type === 'default'" #footer>
        <el-row class="card-footer" justify="space-between" align="middle">
          <el-col :span="16">
            <el-row justify="space-between">
              <el-col :span="8">
                <img src="@/assets/img/avatar1.png" alt="博客主" />
                <a href="javascript:;">Pluto_ssy</a>
              </el-col>
              <el-col :span="8">
                <el-icon>
                  <i-ep-Calendar />
                </el-icon>
                <p>2023-11-29</p>
              </el-col>
              <el-col :span="8">
                <el-icon>
                  <i-ep-View />
                </el-icon>
                <p>233</p>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="8">
            <div class="type">类型Type</div>
          </el-col>
        </el-row>
      </template>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Introduction',
  props: {
    type: {
      type: String,
      default: 'default', // default, recommend
    },
  },
  methods: {
    gotoDetail(id) {
      this.$router.push(`/article/${id}`);
    },
  },
};
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
      display: flex;
      justify-content: center;
      > .el-row {
        width: 100%;
        > .el-col:first-child {
          margin: auto; // 居中一下
          margin-bottom: 16px;
          > img {
            width: 100%;
            transition: transform 0.8s;
            &:hover {
              transform: scale(1.1);
            }
          }
          > .content-img {
            height: 70%;
          }
        }
        .content-text {
          // z-index: 1;
          > p {
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5;
            text-indent: 2em;
            text-align: justify;
            /* 超出部分隐藏并显示... */
            overflow: hidden;
            text-overflow: ellipsis;
            display: box;
            line-clamp: 4;
            box-orient: vertical;
            display: -webkit-box;
            -webkit-line-clamp: 5;
            -webkit-box-orient: vertical;
          }
        }
        .content-time {
          display: flex;
          justify-content: flex-start;
          align-items: center;
          > :first-child {
            margin-right: 10px;
          }
        }
        .content-title {
          > h2 {
            font-size: 1.5rem;
            font-weight: 600;
            text-align: center;
            margin: 1rem 0;
          }
        }
        .content-tips {
          display: flex;
          justify-content: space-around;
          align-items: center;
          > div {
            display: flex;
            align-items: center;
            > p {
              margin-left: 0.5rem;
            }
          }
        }
        .content-type {
          display: flex;
          justify-content: flex-start;
          align-items: center;
          margin-top: 1rem;
          .type {
            position: relative;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            color: #585858;
            background-color: rgb(180, 180, 180);
            border-radius: 10px;
            text-align: center;
            line-height: 2;
            padding-left: 32px;
            padding-right: 64px;
            cursor: pointer;
            &::before {
              content: '';
              position: absolute;
              left: 0;
              top: 0;
              width: 0;
              height: 0;
              border-top: 16px solid #f5f7fa;
              border-right: 16px solid transparent;
              border-bottom: 16px solid transparent;
              border-left: 18px solid #f5f7fa;
            }
            &::after {
              content: '';
              position: absolute;
              right: 0;
              bottom: 0;
              width: 0;
              height: 0;
              border-top: 16px solid transparent;
              border-right: 18px solid #f5f7fa;
              border-bottom: 16px solid #f5f7fa;
              border-left: 18px solid transparent;
            }

            &:hover {
              background-color: rgb(217, 217, 22);
              color: #ffffff;
            }

            > p {
              margin-left: 5px;
            }
          }
        }
      }
    }

    .card-footer {
      color: #888686;
      font-size: 0.6rem;
      font-weight: 400;
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
</style>
