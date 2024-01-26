<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-19 23:25:24
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-26 08:46:37
 * @FilePath: \blog-manage\src\pages\User.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch :type="'user'" :multipleSelection="multipleSelection" @searchResult="searchResult" :back="backHistory" />
  </header>
  <main>
    <el-table :data="userData" height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="firstImg" label="博客首图" width="120" align="center">
        <template #default="scope">
          <div class="first-img">
            <!-- <img :src="scope.row.firstImg" alt="博客首图" /> -->
            <img src="@/assets/img/spaceAvatar.jpg" alt="博客首图" />
          </div>
          <el-upload :action="uploadUrl(scope.row.blogId)" :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccess">
            <el-button size="small">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题" min-width="200">
        <template #default="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top-start">
            <a class="blog-title" target="_blank" :href="scope.row.blogUrl">{{ scope.row.title }}</a>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" width="150" />
      <el-table-column prop="introduction" label="介绍" min-width="400">
        <template #default="scope">
          <!--  :teleported="false" -->
          <el-tooltip popper-class="blog-int-tool" effect="dark" :content="scope.row.introduction" placement="top-start">
            <div class="blog-intro">{{ scope.row.introduction }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="180" align="center">
        <template #default="scope">
          <el-button size="small" type="danger" @click="handleDelete(scope.row.blogId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
</template>

<script setup lang="ts">
//引入VueOfficePdf组件
// import VueOfficePdf from '@vue-office/pdf';
//引入相关样式
// import '@vue-office/pdf/lib/index.css';

import MySearch from '@/components/MySearch/MySearch.vue';
import { ref, toRaw, reactive, nextTick, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { ElNotification, ElMessageBox } from 'element-plus';
import { beforeAvatarUpload } from '@/mixins';
import config from '@/config';

let userData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据
const route = useRoute();
let backHistory = ref('/user'); // 返回的路由

console.log('route:', toRaw(route).query.userId);
console.log(route.query.userId);
const userId = toRaw(route.query.userId);

onMounted(() => {
  nextTick(() => {
    getData(userId);
  });
});

const getData = id => {
  console.log('getData');
  // getUserCollect(id)
  let data1 = [
    {
      blogId: 1,
      firstImg: 'https://img-blog.csdnimg.cn/20210125173259900.png',
      title: '博客1博客1博客1博客1博客1博客1博客1',
      blogUrl: 'https://blog.csdn.net/qq_41893274/article/details/113000000',
      author: '作者1',
      introduction:
        '博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博介博客简介博客简介博客简介博介博客简介博客简介博客简介博客简博客简介博客简介博客简介博客简介博客简博客简介博客简介博客简介博客简介博客简博客简介博客简介博客简介博客简介博客简博客简介博客简介博客简介博客简介博客简博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介博客简介',
    },
    {
      blogId: 2,
      firstImg: 'https://img-blog.csdnimg.cn/20210125173259900.png',
      blogUrl: 'https://blog.csdn.net/qq_41893274/article/details/113000000',
      title: '博客2',
      author: '作者2',
      introduction: '博客简介',
    },
    {
      blogId: 3,
      firstImg: 'https://img-blog.csdnimg.cn/20210125173259900.png',
      blogUrl: 'https://blog.csdn.net/qq_41893274/article/details/113000000',
      title: '博客3',
      author: '作者3',
      introduction: '博客简介',
    },
    {
      id: 4,
      firstImg: 'https://img-blog.csdnimg.cn/20210125173259900.png',
      blogUrl: 'https://blog.csdn.net/qq_41893274/article/details/113000000',
      title: '博客4',
      author: '作者4',
      introduction: '博客简介',
    },
    {
      blogId: 5,
      firstImg: 'https://img-blog.csdnimg.cn/20210125173259900.png',
      blogUrl: 'https://blog.csdn.net/qq_41893274/article/details/113000000',
      title: '博客5',
      author: '作者5',
      introduction: '博客简介',
    },
  ];
  userData.splice(0, userData.length, ...data1);
};

// 把已经选择的项赋值给multipleSelection
const handleSelectionChange = val => {
  multipleSelection.value = val;
};

// 更新图片
const uploadUrl = id => {
  return `${config.HOST}/user/updateUserPic?id=${id}`;
};
// 上传图片之后要做的事情
const handleAvatarSuccess = (res, file) => {
  if (res.code === 1) {
    ElNotification({
      message: '头像上传成功',
      type: 'success',
    });
    getData(userId); // 重新获取数据
  } else {
    ElNotification({
      message: '头像上传失败',
      type: 'error',
    });
  }
};

const handleDelete = id => {
  console.log(id);
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      // delUser(id)
      ElNotification({
        type: 'success',
        message: '删除成功!',
      });
    })
    .catch(() => {
      ElNotification({
        type: 'info',
        message: '已取消删除',
      });
    });
  getData(userId);
};

const searchResult = data => {
  console.log('searchResult--', data);
  // 因为 reactive 不能直接赋值，所以用 splice
  userData.splice(0, userData.length, data);
};
</script>

<style lang="scss" scoped>
main {
  .el-table {
    width: 100%;

    ::v-deep .el-table__header tr th {
      background-color: #f0f0f0;
    }

    .first-img {
      width: 100%;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .blog-intro {
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
      -webkit-line-clamp: 4;
      -webkit-box-orient: vertical;
    }
    .blog-title {
      color: var(--el-color-primary);
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      &:hover {
        color: var(--el-color-primary-light-3);
      }
    }
  }
}
</style>
<style lang="scss">
.blog-int-tool {
  max-width: 50vw !important;
}
</style>
