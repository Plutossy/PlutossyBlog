<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-03-01 09:47:23
 * @FilePath: \PlutossyBlog\blog-manage\src\pages\Blog.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch type="type" :multipleSelection="multipleSelection" @searchResult="searchResult" />
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
import { ElNotification, ElMessageBox } from 'element-plus';

let typeData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据

const router = useRouter();

onMounted(() => {
  nextTick(() => {
    // getData(userId);
    getData();
  });
});

const getData = () => {
  console.log('getData');
  // getUserCollect(id)
  let data1 = [
    {
      id: 1,
      name: '分类1',
    },
    {
      id: 2,
      name: '分类2',
    },
    {
      id: 3,
      name: '分类3',
    },
    {
      id: 4,
      name: '分类4',
    },
    {
      id: 5,
      name: '分类5',
    },
    {
      id: 6,
      name: '分类6',
    },
    {
      id: 7,
      name: '分类7',
    },
    {
      id: 8,
      name: '分类8',
    },
    {
      id: 9,
      name: '分类9',
    },
    {
      id: 10,
      name: '分类10',
    },
    {
      id: 11,
      name: '分类11',
    },
    {
      id: 12,
      name: '分类12',
    },
    {
      id: 13,
      name: '分类13',
    },
    {
      id: 14,
      name: '分类14',
    },
    {
      id: 15,
      name: '分类15',
    },
    {
      id: 16,
      name: '分类16',
    },
    {
      id: 17,
      name: '分类17',
    },
    {
      id: 18,
      name: '分类18',
    },
    {
      id: 19,
      name: '分类19',
    },
    {
      id: 20,
      name: '分类20',
    },
  ];
  typeData.splice(0, typeData.length, ...data1);
};

// 把已经选择的项赋值给multipleSelection
const handleSelectionChange = (val: any[]) => {
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

const searchResult = (data: any) => {
  console.log('searchResult--', data);
  // 因为 reactive 不能直接赋值，所以用 splice
  typeData.splice(0, typeData.length, data);
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
