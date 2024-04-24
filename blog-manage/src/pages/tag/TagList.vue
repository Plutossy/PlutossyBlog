<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-24 13:56:50
 * @FilePath: \PlutossyBlog\blog-manage\src\pages\Blog.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch type="tag" :multipleSelection="multipleSelection" @searchResult="searchResult" @reset="reset" @delAllSuccess="delAllSuccess" />
  </header>
  <main>
    <el-table :data="tagData" :max-height="tableHeight" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="name" label="标签名称" min-width="500" show-overflow-tooltip />
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
  <footer>
    <my-page v-model:queryParam="queryParam" :total="newTotal" @tempParams="getTempParams" />
  </footer>
  <tag-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import { selectTagList, selectTagByName, deleteTag } from '@/api/modules/tag';
import store from '@/store/store';
import eventBus from '@/assets/js/eventBus';
const emitter = eventBus();

let tagData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据

let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
});
let newTotal = ref(0); // 总数

let tableHeight = ref(568); // 表格高度

const router = useRouter();

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

const getData = async () => {
  try {
    const { data, code, total } = await selectTagList(queryParam);
    if (code === 200) {
      tagData.splice(0, tagData.length, ...data);
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
const handleSelectionChange = (val: never[]) => {
  multipleSelection.value = val;
};

const goDetail = (id: string | number) => {
  router.push({
    path: '/tag/tagBlog',
    query: {
      id,
    },
  });
};

const handleEdit = (row: {}) => {
  dialogVisible.value = true;
  dialogTitle.value = '编辑';
  dialogData.value = row;
};

const handleDelete = (id: string | number) => {
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      const { code } = await deleteTag(id);
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
      });
    });
};

const searchResult = async (param: string) => {
  try {
    const query = {
      pageNum: queryParam.pageNum,
      pageSize: queryParam.pageSize,
      name: param,
    };
    const { data, code } = await selectTagByName(query);
    if (code === 200) {
      // 因为 reactive 不能直接赋值，所以用 splice
      tagData.splice(0, tagData.length, ...data);
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

const delAllSuccess = (val: boolean) => {
  val && getData();
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
