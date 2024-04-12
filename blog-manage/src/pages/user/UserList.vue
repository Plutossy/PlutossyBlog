<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-19 23:25:24
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-12 12:48:48
 * @FilePath: \blog-manage\src\pages\User.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch type="user" :multipleSelection="multipleSelection" @searchResult="searchResult" />
  </header>
  <main>
    <el-table :data="userData" max-height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="40" align="center"></el-table-column>
      <el-table-column prop="avatar" label="用户头像" width="100" align="center">
        <template #default="scope">
          <div class="consumer-img">
            <!-- <img :src="getUrl(scope.row.avatar)" alt="用户图片" width="100%" /> -->
            <img src="@/assets/img/spaceAvatar.jpg" alt="用户头像" />
          </div>
          <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeImgUpload" :on-success="handleImgSuccess">
            <el-button size="small">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="80" />
      <el-table-column prop="username" label="用户名" width="80" />
      <el-table-column prop="sex" label="性别" width="60" align="center" />
      <el-table-column prop="phone" label="手机号" width="120" />
      <el-table-column prop="email" label="Email" min-width="140">
        <template #default="scope">
          <el-tooltip effect="dark" :content="scope.row.email" placement="top">
            <el-link type="primary" :underline="false" :href="'mailto:' + scope.row.email">
              <div class="over-email">{{ scope.row.email }}</div>
            </el-link>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="github" label="GitHub" min-width="140">
        <template #default="scope">
          <el-tooltip effect="dark" :content="scope.row.github" placement="top">
            <el-link type="primary" :underline="false" :href="scope.row.github" target="_blank">
              <div class="over-github">{{ scope.row.github }}</div>
            </el-link>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="csdn" label="CSDN" min-width="140">
        <template #default="scope">
          <el-tooltip effect="dark" :content="scope.row.csdn" placement="top">
            <el-link type="primary" :underline="false" :href="scope.row.csdn" target="_blank">
              <div class="over-csdn">{{ scope.row.csdn }}</div>
            </el-link>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="qq" label="QQ" width="120" />
      <el-table-column prop="wechat" label="WeChat" width="120" />
      <el-table-column prop="birth" label="生日" width="120" :formatter="formatterDate" />
      <el-table-column prop="address" label="所在地" width="140" />
      <el-table-column prop="introduction" label="个人简介" min-width="200">
        <template #default="scope">
          <el-tooltip popper-class="user-int-tool" effect="dark" :content="scope.row.introduction" placement="top-start">
            <div class="user-intro">{{ scope.row.introduction }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="180" align="center">
        <template #default="scope">
          <el-button type="primary" link @click="getCollect(scope.row.id)">收藏</el-button>
          <div class="el-divider el-divider--vertical" direction="vertical" />
          <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
          <div class="el-divider el-divider--vertical" direction="vertical" />
          <el-button type="primary" link @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
  <footer>
    <my-page v-model:queryParam="queryParam" :total="newTotal" @tempParams="getTempParams" />
  </footer>
  <!-- 在vue3中，.sync 双向绑定不生效 -->
  <!-- 解决用 v-model -->
  <user-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import { getUserList } from '@/api/modules/user';
import { dayjs, ElNotification, ElMessageBox } from 'element-plus';
import { beforeImgUpload } from '@/mixins';
import config from '@/config';

let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
  type: true,
  id: 1,
});

let newTotal = ref(0); // 总数

let userData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据
let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据
const router = useRouter();

onMounted(() => {
  nextTick(() => {
    getData();
  });
});

const getData = async () => {
  try {
    const { data, code, total } = await getUserList(queryParam);
    if (code === 200) {
      userData.splice(0, userData.length, ...data);
      newTotal.value = total;
    }
  } catch (error) {
    console.log(error);
  }
};

// 页面变换页发起请求
const getTempParams = () => {
  getData();
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
const handleImgSuccess = (res, file) => {
  if (res.code === 1) {
    ElNotification({
      message: '头像上传成功',
      type: 'success',
    });
    getData(); // 重新获取数据
  } else {
    ElNotification({
      message: '头像上传失败',
      type: 'error',
    });
  }
};

// 格式化时间
const formatterDate = (row, column, cellValue) => {
  return dayjs(cellValue).format('YYYY-MM-DD');
};

// 收藏
const getCollect = id => {
  console.log('getCollect--', id);
  router.push({
    path: '/user/collect',
    query: {
      id,
    },
  });
};

// 编辑
const handleEdit = row => {
  dialogVisible.value = true;
  dialogTitle.value = '编辑';
  dialogData.value = row;
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
  getData();
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

    :deep(.el-table__header tr th) {
      background-color: #f0f0f0;
    }

    .consumer-img {
      width: 100%;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
      }
    }
    .over-email,
    .over-github,
    .over-csdn,
    .user-intro {
      width: 100%;
      line-height: 2;
      overflow: hidden;
      text-overflow: ellipsis;
      display: box;
      line-clamp: 2;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
    }
    .user-intro {
      line-clamp: 3;
      -webkit-line-clamp: 3;
    }
  }
}
</style>
<style lang="scss">
.user-int-tool {
  max-width: 50vw;
}
</style>
