<template>
  <header>
    <MySearch type="blog" :multipleSelection="multipleSelection" @searchResult="searchResult" :goCom="goComAdd" @reset="reset" />
  </header>
  <main>
    <el-table :data="blogData" :max-height="tableHeight" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center" />
      <el-table-column prop="firstImg" label="博客首图" width="120" align="center">
        <template #default="scope">
          <div class="first-img">
            <!-- <img :src="scope.row.picture" alt="博客首图" /> -->
            <img src="@/assets/img/spaceAvatar.jpg" alt="博客首图" />
          </div>
          <el-upload :action="uploadUrl(scope.row.picId)" :before-upload="beforeImgUpload" :on-success="handleImgSuccess">
            <el-button size="small">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题" min-width="200">
        <template #default="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top-start">
            <span class="blog-title">{{ scope.row.title }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" width="150" align="center" />
      <el-table-column prop="flag" label="博客类型" width="100" align="center">
        <template #default="{ row }">
          <div v-if="row.flag === 0" class="publish-item no-status">原创</div>
          <div v-if="row.flag === 1" class="publish-item is-status">转载</div>
          <div v-if="row.flag === 2" class="publish-item set-status">翻译</div>
        </template>
      </el-table-column>
      <el-table-column prop="recommend" label="是否推荐" width="100" align="center">
        <template #default="{ row }">
          <el-tooltip :content="row.recommend ? '是' : '否'" placement="top">
            <el-switch v-model="row.recommend" @click="updateRecommend(row.id, row.recommend)" inline-prompt :active-icon="Check" :inactive-icon="Close" />
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="published" label="博客状态" width="100" align="center">
        <template #default="{ row }">
          <div v-if="row.published === 1" class="publish-item is-status">发布</div>
          <div v-if="row.published === 2" class="publish-item set-status">草稿</div>
        </template>
      </el-table-column>
      <el-table-column prop="commentabled" label="是否可评论" width="100" align="center">
        <template #default="{ row }">
          <el-tooltip :content="row.commentabled ? '是' : '否'" placement="top">
            <el-switch v-model="row.commentabled" @click="updateCommentabled(row.id, row.commentabled)" inline-prompt :active-icon="Check" :inactive-icon="Close" />
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="shared" label="是否可分享" width="100" align="center">
        <template #default="{ row }">
          <el-tooltip :content="row.shared ? '是' : '否'" placement="top">
            <el-switch v-model="row.shared" @click="updateShared(row.id, row.shared)" inline-prompt :active-icon="Check" :inactive-icon="Close" />
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="views" label="查看数量" width="100" align="center" />
      <el-table-column prop="description" label="简介" min-width="400" align="center">
        <template #default="scope">
          <!--  :teleported="false" -->
          <el-tooltip popper-class="blog-int-tool" effect="dark" :content="scope.row.description" placement="top-start">
            <div class="blog-intro">{{ scope.row.description }}</div>
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
</template>

<script setup lang="ts">
// 引入VueOfficePdf组件
// import VueOfficePdf from '@vue-office/pdf';
//引入相关样式
// import '@vue-office/pdf/lib/index.css';
import { Check, Close } from '@element-plus/icons-vue';
import { beforeImgUpload } from '@/mixins';
import config from '@/config';
import store from '@/store/store';
import eventBus from '@/assets/js/eventBus';
import type { ComponentInternalInstance } from 'vue';

const emitter = eventBus();
const { proxy } = getCurrentInstance() as ComponentInternalInstance | any;

const props = defineProps({
  forward: {
    type: Function,
    default: () => {},
  },
  blogDetail: {
    // 没必要写，这里写是为了去除警告
    type: Object,
    default: {},
  },
});

let blogData: any = reactive([]); // 数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据
let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
  queryParam: '',
});
let tableHeight = ref(568); // 表格高度
let newTotal = ref(0); // 总数

onMounted(() => {
  nextTick(() => {
    getData();
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
    const { data, code, total } = await proxy.$apis.blog.selectBlogByQuery(queryParam);
    if (code === 200) {
      blogData.splice(0, blogData.length, ...data);
      newTotal.value = total;
    }
  } catch (error) {
    console.error('getData error--', error);
  }
};

// 把已经选择的项赋值给multipleSelection
const handleSelectionChange = (val: any) => {
  multipleSelection.value = val;
};

// 跳转到博客详情页--新增
const goComAdd = () => {
  props.forward('BlogDetail');
};

// 更新图片
const uploadUrl = (id: string | number) => {
  return `${config.HOST}/blog/updateUserPic?id=${id}`;
};
// 上传图片之后要做的事情
const handleImgSuccess = (res: { code: number }, _file: any) => {
  if (res.code === 1) {
    ElNotification({
      message: '头像上传成功',
      type: 'success',
      duration: 1000,
      showClose: true,
    });
    getData(); // 重新获取数据
  } else {
    ElNotification({
      message: '头像上传失败',
      type: 'error',
      duration: 1000,
      showClose: true,
    });
  }
};

// 更新推荐状态
const updateRecommend = async (id: string | number, recommend: boolean) => {
  const query = {
    id,
    recommend,
  };
  try {
    const { code } = await proxy.$apis.blog.updateRecommend(query);
    if (code === 200) {
      ElNotification({
        type: 'success',
        message: '更新成功!',
        duration: 1000,
        showClose: true,
      });
    } else {
      ElNotification({
        type: 'error',
        message: '更新失败!',
        duration: 1000,
        showClose: true,
      });
    }
  } catch (error) {
    console.log(error);
  }
};

// 更新评论状态
const updateCommentabled = async (id: string | number, commentabled: boolean) => {
  const query = {
    id,
    commentabled,
  };
  try {
    const { code } = await proxy.$apis.blog.updateCommentabled(query);
    if (code === 200) {
      ElNotification({
        type: 'success',
        message: '更新成功!',
        duration: 1000,
        showClose: true,
      });
    } else {
      ElNotification({
        type: 'error',
        message: '更新失败!',
        duration: 1000,
        showClose: true,
      });
    }
  } catch (error) {
    console.log(error);
  }
};

// 更新分享状态
const updateShared = async (id: string | number, shared: boolean) => {
  const query = {
    id,
    shared,
  };
  try {
    const { code } = await proxy.$apis.blog.updateShared(query);
    if (code === 200) {
      ElNotification({
        type: 'success',
        message: '更新成功!',
        duration: 1000,
        showClose: true,
      });
    } else {
      ElNotification({
        type: 'error',
        message: '更新失败!',
        duration: 1000,
        showClose: true,
      });
    }
  } catch (error) {
    console.log(error);
  }
};

// 编辑
const handleEdit = (row: any) => {
  console.log('handleEdit--', row);
  props.forward('BlogDetail', row);
};

const handleDelete = (id: string | number) => {
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      try {
        const { code } = await proxy.$apis.blog.deleteBlog(id);
        if (code === 200) {
          ElNotification({
            type: 'success',
            message: '删除成功!',
            duration: 1000,
            showClose: true,
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
        duration: 1000,
        showClose: true,
      });
    });
};

const searchResult = (param: string) => {
  queryParam.queryParam = param;
  getData();
};

const reset = () => {
  queryParam.pageNum = 1;
  queryParam.pageSize = 10;
  queryParam.queryParam = '';
  getData();
};

// 页面变换页发起请求
const getTempParams = () => {
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

    .first-img {
      width: 100%;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .publish-item {
      width: 100%;
      height: 100%;
      display: inline-block;
      padding: 5px 10px;
      border-radius: 5px;
      color: #fff;
      // font-weight: bold;
      &.no-status {
        background-color: #f59090;
      }
      &.is-status {
        background-color: #9fdb80;
      }
      &.set-status {
        background-color: #83baf2;
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
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    .operate {
      display: flex;
      align-items: center;
      justify-content: center;
      > :first-child {
        display: flex;
        align-items: center;
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
