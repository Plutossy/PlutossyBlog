<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-24 18:58:30
 * @FilePath: \PlutossyBlog\blog-manage\src\pages\Blog.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch :type="'music'" :multipleSelection="multipleSelection" @searchResult="searchResult" />
  </header>
  <main>
    <div class="my-freemp3-show">
      <span @click="drawer = true">点击我搜索音乐吧！</span>
    </div>
    <el-table :data="musicData" :max-height="tableHeight" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="img" label="歌曲图片" width="100" align="center">
        <template #default="scope">
          <div class="song-img">
            <!-- <img :src="getUrl(scope.row.avatar)" alt="用户图片" width="100%" /> -->
            <img src="@/assets/img/spaceAvatar.jpg" alt="歌曲图片" />
          </div>
          <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeImgUpload" :on-success="handleImgSuccess">
            <el-button size="small">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="歌曲名" min-width="160" show-overflow-tooltip />
      <el-table-column prop="singer" label="歌手 / 组合" width="160" show-overflow-tooltip />
      <el-table-column prop="url" label="歌曲链接" min-width="240" show-overflow-tooltip>
        <template #default="scope">
          <el-link type="primary" :underline="false" :href="scope.row.url" target="_blank">{{ scope.row.url }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="lyric" label="歌词" min-width="360">
        <template #default="scope">
          <div class="song-lyric">
            <div v-if="scope.row.lyric" v-for="(item, index) in formatLyric(scope.row.lyric)" :key="index">{{ item }}</div>
            <div v-else>暂无歌词！</div>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="160" align="center">
        <template #default="scope">
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

  <music-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />

  <el-drawer v-model="drawer" :show-close="false" direction="btt" size="90%">
    <iframe src="http://myfreemp3.sharerj.com/" frameborder="0"></iframe>
    <template #header="{ close }">
      <div class="close-drawer">
        <el-button type="danger" plain @click="close">
          <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
          关闭
        </el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script setup lang="ts">
import { formatLyric, beforeImgUpload } from '@/mixins';
import { CircleCloseFilled } from '@element-plus/icons-vue';
import store from '@/store/store';
import eventBus from '@/assets/js/eventBus';
import config from '@/config';
import { ComponentInternalInstance } from 'vue';

const emitter = eventBus();
const { proxy } = getCurrentInstance() as ComponentInternalInstance | any;

let musicData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据
let drawer = ref(false); // 搜索抽屉是否显示
let queryParam = reactive({
  pageNum: 1,
  pageSize: 10,
});
let newTotal = ref(0); // 总数
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

const getData = async () => {
  try {
    const { data, code, total } = await proxy.$apis.music.selectMusicList(queryParam);
    if (code === 200) {
      musicData.splice(0, musicData.length, ...data);
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

// 更新图片
const uploadUrl = (id: string | number) => {
  return `${config.HOST}/user/updateUserPic?id=${id}`;
};
// 上传图片之后要做的事情
const handleImgSuccess = (res: { code: number }, _file: any) => {
  if (res.code === 1) {
    ElNotification({
      message: '头像上传成功',
      type: 'success',
    });
    // getData(userId); // 重新获取数据
    getData(); // 重新获取数据
  } else {
    ElNotification({
      message: '头像上传失败',
      type: 'error',
    });
  }
};

const handleEdit = (row: {}) => {
  dialogVisible.value = true;
  dialogTitle.value = '编辑';
  dialogData.value = row;
};

const handleDelete = (id: string | number) => {
  console.log(id);
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      try {
        const { code } = await proxy.$apis.music.deleteMusic(id);
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
      queryParam: param,
    };
    const { data, code } = await proxy.$apis.music.selectMusicByQuery(query);
    if (code === 200) {
      // 因为 reactive 不能直接赋值，所以用 splice
      musicData.splice(0, musicData.length, ...data);
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
  .my-freemp3-show {
    width: 100%;
    font-size: 32px;
    color: #409eff;
    display: flex;
    justify-content: center;
    cursor: pointer;
    span:hover {
      color: orange;
    }
    > span {
      position: relative;
      font-size: 2.3rem;
      font-weight: 560;
      text-align: center;
      font-family: 'STXingkai', serif;
      color: #67c23a;

      // 打字机效果
      overflow: hidden;
      width: 0;
      white-space: nowrap;
      animation: width 2s steps(9) forwards;
      // animation-delay: 2s;
      &::after {
        content: '\266C';
        position: absolute;
        right: 0;
        height: 80%;
        border-right: 2px solid white;
        animation: showInfinite 1s infinite both;
      }
    }
    @keyframes width {
      0% {
        width: 0;
      }
      100% {
        width: 11em; //  单位em,表示一个字体的宽度
      }
    }
    @keyframes showInfinite {
      0% {
        opacity: 1;
      }
      100% {
        opacity: 0;
      }
    }
  }
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
.el-drawer {
  overflow: hidden;
  iframe {
    width: 100%;
    height: 95%;
  }
  .close-drawer {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
