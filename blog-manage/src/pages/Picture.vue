<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-19 23:25:24
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-31 19:07:58
 * @FilePath: \blog-manage\src\pages\User.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch :type="'picture'" :multipleSelection="multipleSelection" @searchResult="searchResult" />
  </header>
  <main>
    <el-table :data="pictureData" height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="60" align="center"></el-table-column>
      <el-table-column prop="picUrl" label="图片预览" width="150" align="center">
        <template #default="scope">
          <el-tooltip class="item" effect="dark" content="点击预览" placement="top">
            <div class="preview-pic" @click="previewPic(scope.row.picUrl)">
              <img alt="照片" :src="scope.row.picUrl" />
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="picTitle" label="照片标题" min-width="200">
        <template #default="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.picTitle" placement="top-start">
            <div class="pic-title">{{ scope.row.picTitle }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" width="150" />
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
            <el-upload :action="uploadUrl(scope.row.picId)" :before-upload="beforeImgUpload" :on-success="handleImgSuccess">
              <el-button type="primary" link>更新图片</el-button>
            </el-upload>
            <div class="el-divider el-divider--vertical" direction="vertical" />
            <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
            <div class="el-divider el-divider--vertical" direction="vertical" />
            <el-button type="primary" link @click="handleDelete(scope.row.blogId)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </main>
  <preview-img v-model:visible="previewShow">
    <img :src="previewUrl" alt="预览图片加载失败" />
  </preview-img>
  <picture-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import { ElNotification, ElMessageBox } from 'element-plus';
import { beforeImgUpload } from '@/mixins';
import config from '@/config';
// 导入透明图片
import touming from '@/assets/img/touming.png';

let pictureData: any = reactive([]); // 用户数据
let multipleSelection = ref([]); // 用于存放多选框选中的数据
let previewShow = ref(false); // 预览图片的弹窗是否显示
let previewUrl = ref(touming); // 预览图片的url, 默认透明图片

let dialogVisible = ref(false); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let dialogData = ref({}); // 详情弹窗数据

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
      userId: 1,
      picId: 1,
      picUrl: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      picTitle: '图片标题',
      author: '张三',
      description: '这是一张图片',
    },
    {
      userId: 2,
      picId: 2,
      picUrl: 'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
      picTitle: '图片标题',
      author: '李四',
      description: '这也是一张图片',
    },
    {
      userId: 3,
      picId: 3,
      picUrl: 'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg',
      picTitle: '图片标题',
      author: '王麻子',
      description: '这还是一张图片这还是一张图片这还是一张图片这还是一张图片这还是一张图片',
    },
  ];
  pictureData.splice(0, pictureData.length, ...data1);
};

// 把已经选择的项赋值给multipleSelection
const handleSelectionChange = val => {
  multipleSelection.value = val;
};

const previewPic = (url: string) => {
  console.log('previewPic--', url);
  previewUrl.value = url;
  previewShow.value = true;
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
  pictureData.splice(0, pictureData.length, data);
};
</script>

<style lang="scss" scoped>
main {
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
</style>
<style lang="scss">
.pic-des-tool {
  max-width: 50vw !important;
}
</style>
