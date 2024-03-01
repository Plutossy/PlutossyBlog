<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-08 19:17:21
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-03-01 10:06:56
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
    <el-table :data="musicData" max-height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="songImg" label="歌曲图片" width="100" align="center">
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
      <el-table-column prop="songName" label="歌曲名" min-width="160" show-overflow-tooltip />
      <el-table-column prop="singer" label="歌手 / 组合" width="160" show-overflow-tooltip />
      <el-table-column prop="songUrl" label="歌曲链接" min-width="240" show-overflow-tooltip>
        <template #default="scope">
          <el-link type="primary" :underline="false" :href="scope.row.songUrl" target="_blank">{{ scope.row.songUrl }}</el-link>
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
          <el-button type="primary" link @click="handleDelete(scope.row.blogId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
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
import { formatLyric } from '@/mixins';
import { ElNotification, ElMessageBox } from 'element-plus';
import { CircleCloseFilled } from '@element-plus/icons-vue';
import { beforeImgUpload } from '@/mixins';
import config from '@/config';

let musicData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据
let drawer = ref(false); // 搜索抽屉是否显示

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
      songId: 1,
      songImg: 'https://p1',
      songName: '那些年',
      singer: 'singer1',
      songUrl: 'http://music.163.com/song/media/outer/url?id=97357.mp3',
      lyric:
        '[00:00.00] 作词 : 九把刀[00:01.00] 作曲 : 木村充利[00:02.00] 编曲 : 侯志坚/林冠吟[00:03.00] 制作人 : 薛忠铭[00:18.10]又回到最初的起点[00:20.89]记忆中你青涩的脸[00:24.24]我们终于[00:25.95]来到了这一天[00:30.33]桌垫下的老照片[00:33.46]无数回忆连结[00:35.70]今天男孩要赴女孩最后的约[00:42.45]又回到最初的起点[00:45.37]呆呆地站在镜子前[00:48.58]笨拙系上红色领带的结[00:53.57]将头发梳成大人模样[00:57.41]穿上一身帅气西装[00:59.97]等会儿见你一定比想象美[01:07.19]好想再回到那些年的时光[01:11.53]回到教室座位前后[01:14.49]故意讨你温柔的骂[01:19.31]黑板上排列组合[01:22.25]你舍得解开吗[01:25.39]谁与谁坐他又爱着她[01:34.04]那些年错过的大雨[01:37.05]那些年错过的爱情[01:39.87]好想拥抱你[01:42.00]拥抱错过的勇气[01:46.16]曾经想征服全世界[01:49.14]到最后回首才发现[01:51.57]这世界滴滴点点全部都是你[01:58.44]那些年错过的大雨[02:01.32]那些年错过的爱情[02:04.17]好想告诉你[02:06.21]告诉你我没有忘记[02:10.48]那天晚上满天星星[02:13.45]平行时空下的约定[02:15.95]再一次相遇我会[02:18.50]紧紧抱着你[02:21.38]紧紧抱着你[02:37.89]又回到最初的起点[02:40.75]呆呆地站在镜子前[02:44.02]笨拙系上红色领带的结[02:48.88]将头发梳成大人模样[02:52.90]穿上一身帅气西装[02:55.43]等会儿见你一定比想象美[03:02.58]好想再回到那些年的时光[03:06.95]回到教室座位前后[03:09.70]故意讨你温柔的骂[03:14.86]黑板上排列组合[03:17.59]你舍得解开吗[03:20.86]谁与谁坐他又爱着她[03:29.53]那些年错过的大雨[03:32.52]那些年错过的爱情[03:35.25]好想拥抱你[03:37.37]拥抱错过的勇气[03:41.56]曾经想征服全世界[03:44.58]到最后回首才发现[03:47.04]这世界滴滴点点全部都是你[03:53.87]那些年错过的大雨[03:56.79]那些年错过的爱情[03:59.59]好想告诉你[04:01.62]告诉你我没有忘记[04:05.82]那天晚上满天星星[04:08.85]平行时空下的约定[04:11.35]再一次相遇我会[04:13.95]紧紧抱着你[04:16.87]紧紧抱着你[04:45.49]那些年错过的大雨[04:48.40]那些年错过的爱情[04:51.20]好想拥抱你[04:53.24]拥抱错过的勇气[04:57.48]曾经想征服全世界[05:00.59]到最后回首才发现[05:03.01]这世界滴滴点点全部都是你[05:09.71]那些年错过的大雨[05:12.79]那些年错过的爱情[05:15.52]好想告诉你[05:17.60]告诉你我没有忘记[05:21.81]那天晚上满天星星[05:24.83]平行时空下的约定[05:27.33]再一次相遇我会[05:29.98]紧紧抱着你[05:32.73]紧紧抱着你',
    },
    {
      songId: 2,
      songImg: 'https://p2',
      songName: 'songName2',
      singer: 'singer2',
      songUrl: 'https://s2',
      lyric: '',
    },
    {
      songId: 3,
      songImg: 'https://p3',
      songName: 'songName3',
      singer: 'singer-singer',
      songUrl: 'http://ws.stream.qqmusic.qq.com/C400001MV1IY4Yhfvc.m4a?guid=959212257&vkey=A8951D944FC43C2E3EC63E00A9A3DF031117527BE95F362AA2EEBF95FF0ADAF086F83608B8FCD48CC48A860BC4EC25C199DA2F7EC7EE3540&uin=&fromtag=120032',
      lyric: 'lyric',
    },
  ];
  musicData.splice(0, musicData.length, ...data1);
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
const handleImgSuccess = (res: { code: number }, file: any) => {
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

const handleEdit = row => {
  console.log('handleEdit--', row);
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
  // getData(userId);
  getData();
};

const searchResult = data => {
  console.log('searchResult--', data);
  // 因为 reactive 不能直接赋值，所以用 splice
  musicData.splice(0, musicData.length, data);
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
