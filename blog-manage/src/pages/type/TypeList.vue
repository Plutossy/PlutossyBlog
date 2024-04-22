<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-22 18:23:15
 * @FilePath: \PlutossyBlog\blog-manage\src\pages\Blog.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch type="type" :multipleSelection="multipleSelection" @searchResult="searchResult" @reset="reset" />
  </header>
  <main>
    <el-table :data="typeData" max-height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="name" label="分类名称" min-width="500" show-overflow-tooltip />
      <el-table-column fixed="right" label="操作" min-width="500" align="center">
        <template #default="scope">
          <el-button type="primary" link @click="goDetail(scope.row.id)">详情</el-button>
          <div class="el-divider el-divider--vertical" direction="vertical" />
          <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
          <div class="el-divider el-divider--vertical" direction="vertical" />
          <el-button type="primary" link @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
  <type-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import { selectTypeList, selectTypeByName } from '@/api/modules/type';

let typeData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据

let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
});

const router = useRouter();

onMounted(() => {
  nextTick(() => {
    // getData(userId);
    getData();
  });
});

const getData = async () => {
  try {
    const { data, code } = await selectTypeList(queryParam);
    if (code === 200) {
      typeData.splice(0, typeData.length, ...data);
    }
  } catch (error) {
    console.log(error);
  }
};

// 把已经选择的项赋值给multipleSelection
const handleSelectionChange = (val: never[]) => {
  multipleSelection.value = val;
};

const goDetail = (id: number | string) => {
  console.log('goDetail--', id);
  router.push({
    path: '/type/typeBlog',
    query: {
      id,
    },
  });
};

const handleEdit = (row: {}) => {
  console.log('handleEdit--', row);
  dialogVisible.value = true;
  dialogTitle.value = '编辑';
  dialogData.value = row;
};

const handleDelete = (id: any) => {
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
  // getData(userId);
  getData();
};

const searchResult = async (param: string) => {
  try {
    const query = {
      pageNum: queryParam.pageNum,
      pageSize: queryParam.pageSize,
      name: param,
    };
    const { data, code } = await selectTypeByName(query);
    if (code === 200) {
      // 因为 reactive 不能直接赋值，所以用 splice
      typeData.splice(0, typeData.length, data);
    } else {
      ElNotification({
        type: 'error',
        message: '查询失败!',
        showClose: true,
        duration: 1000,
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

    .song-img {
      width: 100%;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .song-lyric {
      line-height: 2;
      /* 超出部分隐藏并显示... */
      overflow: auto;
      display: box;
      line-clamp: 5;
      box-orient: vertical;
      display: -webkit-box;
      -webkit-line-clamp: 5;
      -webkit-box-orient: vertical;
    }
  }
}
</style>
