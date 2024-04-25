<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-19 23:25:24
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-25 10:47:50
 * @FilePath: \blog-manage\src\pages\User.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch :type="'picture'" :multipleSelection="multipleSelection" @searchResult="searchResult" />
  </header>
  <main>
    <el-table :data="pictureData" max-height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="url" label="图片预览" width="150" align="center">
        <template #default="scope">
          <!-- <el-tooltip class="item" effect="dark" content="点击预览" placement="top"> -->
          <!-- <div class="preview-pic" @click="previewPic(scope.row.picUrl)">
              <img alt="照片" :src="scope.row.picUrl" />
            </div> -->
          <el-image :src="scope.row.url" :zoom-rate="1.2" :max-scale="7" :min-scale="0.2" :preview-src-list="picUrlList" :initial-index="scope.$index" fit="contain" />
          <!-- </el-tooltip> -->
        </template>
      </el-table-column>
      <el-table-column prop="name" label="照片标题" min-width="200">
        <template #default="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.name" placement="top-start">
            <div class="pic-title">{{ scope.row.name }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" min-width="400">
        <template #default="scope">
          <!--  :teleported="false" -->
          <el-tooltip popper-class="pic-des-tool" effect="dark" :content="scope.row.description" placement="top-start">
            <div class="pic-desc">{{ scope.row.description }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200" align="center">
        <template #default="scope">
          <div class="operate">
            <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
            <div class="el-divider el-divider--vertical" direction="vertical" />
            <el-button type="primary" link @click="handleDelete(scope.row.id)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </main>
  <footer>
    <my-page v-model:queryParam="queryParam" :total="newTotal" @tempParams="getTempParams" />
  </footer>
  <!-- <preview-img v-model:visible="previewShow">
    <img :src="previewUrl" alt="预览图片加载失败" />
  </preview-img> -->
  <picture-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import store from '@/store/store';
import eventBus from '@/assets/js/eventBus';
import { ComponentInternalInstance } from 'vue';

const emitter = eventBus();
const { proxy } = getCurrentInstance() as ComponentInternalInstance | any;

// 导入透明图片
// import touming from '@/assets/img/touming.png';

let pictureData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据
// let previewShow = ref(false); // 预览图片的弹窗是否显示
// let previewUrl = ref(touming); // 预览图片的url, 默认透明图片

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据
let picUrlList: string[] = reactive([]); // 图片预览列表

let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
});
let newTotal = ref(0); // 总数
let tableHeight = ref(568); // 表格高度

onMounted(() => {
  nextTick(() => {
    // getData(userId);
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
    const { data, code, total } = await proxy.$apis.picture.selectPictureList(queryParam);
    if (code === 200) {
      pictureData.splice(0, pictureData.length, ...data);
      picUrlList.splice(0, picUrlList.length, ...data.map((item: { url: string }) => item.url));
      newTotal.value = total;
    } else {
      ElNotification({
        message: '获取音乐数据失败',
        type: 'error',
        showClose: true,
        duration: 1000,
      });
    }
  } catch (error) {
    console.log(error);
  }
};

// 把已经选择的项赋值给multipleSelection
const handleSelectionChange = (val: any) => {
  multipleSelection.value = val;
};

// const previewPic = (url: string) => {
//   console.log('previewPic--', url);
//   previewUrl.value = url;
//   previewShow.value = true;
// };

const handleEdit = (row: any) => {
  console.log('handleEdit--', row);
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
      try {
        const { code } = await proxy.$apis.picture.deletePicture(id);
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
      } catch (error) {
        console.log(error);
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
      queryParam: param,
    };
    const { data, code } = await proxy.$apis.picture.selectPictureByQuery(query);
    if (code === 200) {
      // 因为 reactive 不能直接赋值，所以用 splice
      pictureData.splice(0, pictureData.length, ...data);
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

// 页面变换页发起请求
const getTempParams = () => {
  getData();
};
</script>

<style lang="scss" scoped>
main {
  :deep(.el-table .el-table__cell) {
    z-index: auto;
  }
  .el-table {
    width: 100%;

    :deep(.el-table__header tr th) {
      background-color: #f0f0f0;
    }

    .preview-pic {
      width: 100%;
      overflow: hidden;
      cursor: pointer;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .pic-desc {
      line-height: 1.5;
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

    .pic-title {
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }

    .operate {
      display: flex;
      align-items: center;
      > :first-child {
        display: flex;
        align-items: center;
      }
    }
  }
}
:deep(.el-image-viewer__close:hover) {
  background-color: rgb(212, 8, 8);
}
</style>
<style lang="scss">
.pic-des-tool {
  max-width: 50vw !important;
}
</style>
