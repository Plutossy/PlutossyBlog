<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-12 08:55:00
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-28 11:54:25
 * @FilePath: \blog-manage\src\pages\user\UserList.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch type="user" :multipleSelection="multipleSelection" @delAllSuccess="delAllSuccess" @searchResult="searchResult" @reset="reset" :showAdd="queryParam.type" />
  </header>
  <main>
    <el-table :data="userData" :max-height="tableHeight" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="40" align="center" :selectable="selectable" />
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
          <div v-if="!scope.row.type" class="el-divider el-divider--vertical" direction="vertical" />
          <el-button v-if="!scope.row.type" type="primary" link @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
  <footer>
    <my-page v-model:queryParam="queryParam" :disabled="!queryParam.type" :total="newTotal" @tempParams="getTempParams" />
  </footer>
  <!-- 在vue3中，.sync 双向绑定不生效 -->
  <!-- 解决用 v-model -->
  <user-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import { getUserList, deleteUser, selectUserByName } from '@/api/modules/user';
import { dayjs } from 'element-plus';
import { beforeImgUpload } from '@/mixins';
import config from '@/config';
import store from '@/store/store';
import eventBus from '@/assets/js/eventBus';
const emitter = eventBus();

let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
  type: false,
  id: '',
});

let newTotal = ref(0); // 总数

let userData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据
let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据
const router = useRouter();

let tableHeight = ref(568); // 表格高度

onMounted(() => {
  nextTick(() => {
    getData();
  });
  emitter.on('addSuccess', (val: boolean) => {
    val && getData();
  });
});

let timer: any = null;
nextTick(() => {
  store.commit('table/setTableHeight');
  tableHeight.value = store.getters['table/tableHeight'];

  window.addEventListener('resize', () => {
    timer = setTimeout(() => {
      store.commit('table/setTableHeight');
      tableHeight.value = store.getters['table/tableHeight'];
    }, 80);
  });
});

onUnmounted(() => {
  emitter.off('addSuccess');
  window.removeEventListener('resize', () => {});
  clearTimeout(timer);
});

const selectable = (row: any) => {
  return !row.type;
};

const getData = async () => {
  queryParam.type = store.getters['user/userInfo'].type;
  queryParam.id = store.getters['user/userInfo'].id + ''; // 转化为字符串
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
const handleSelectionChange = (val: any) => {
  multipleSelection.value = val;
};

const delAllSuccess = (val: boolean) => {
  val && getData();
};

// 更新图片
const uploadUrl = (id: any) => {
  return `${config.HOST}/user/updateUserPic?id=${id}`;
};
// 上传图片之后要做的事情
const handleImgSuccess = (res: { code: number }, _file: any) => {
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
const formatterDate = (_row: any, _column: any, cellValue: string | number | Date | dayjs.Dayjs | null | undefined) => {
  return dayjs(cellValue).format('YYYY-MM-DD');
};

// 收藏
const getCollect = (id: any) => {
  console.log('getCollect--', id);
  router.push({
    path: '/user/collect',
    query: {
      id,
    },
  });
};

// 编辑
const handleEdit = (row: any) => {
  dialogVisible.value = true;
  dialogTitle.value = '编辑';
  dialogData.value = row;
};
const handleDelete = (id: any) => {
  ElMessageBox.confirm('此操作将永久删除该用户, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      const { code } = await deleteUser(id);
      if (code === 200) {
        ElNotification({
          type: 'success',
          message: '删除成功!',
          showClose: true,
          duration: 1000,
        });
        getData();
      } else {
        ElNotification({
          type: 'error',
          message: '删除失败!',
          duration: 1000,
          showClose: true,
        });
      }
    })
    .catch(() => {
      ElNotification({
        type: 'info',
        message: '已取消删除',
        showClose: true,
        duration: 1000,
      });
    });
};

const searchResult = async (param: string) => {
  try {
    const query = {
      pageNum: queryParam.pageNum,
      pageSize: queryParam.pageSize,
      type: queryParam.type,
      queryParam: param,
    };
    const { data, code } = await selectUserByName(query);
    if (code === 200) {
      // 因为 reactive 不能直接赋值，所以用 splice
      userData.splice(0, userData.length, ...data);
    } else {
      ElNotification({
        type: 'error',
        message: '搜索失败!',
        duration: 1000,
        showClose: true,
      });
    }
  } catch (error) {
    console.log(error);
  }
};

const reset = () => {
  queryParam.pageNum = 1;
  queryParam.pageSize = 10;
  getData();
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
