<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-19 23:25:24
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-01-25 20:11:05
 * @FilePath: \blog-manage\src\pages\User.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <header>
    <MySearch :type="'user'" :multipleSelection="multipleSelection" @searchResult="searchResult" />
  </header>
  <main>
    <el-table :data="userData" height="568" @selection-change="handleSelectionChange">
      <el-table-column fixed type="selection" width="40" align="center"></el-table-column>
      <el-table-column prop="avatar" label="用户头像" width="100" align="center">
        <template #default="scope">
          <div class="consumer-img">
            <!-- <img :src="getUrl(scope.row.avatar)" alt="用户图片" width="100%" /> -->
            <img src="@/assets/img/spaceAvatar.jpg" alt="用户头像" />
          </div>
          <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccess">
            <el-button size="small">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="80" />
      <el-table-column prop="sex" label="性别" width="60" align="center">
        <template #default="scope">
          {{ handleSex(scope.row.sex) }}
        </template>
      </el-table-column>
      <el-table-column prop="phoneNum" label="手机号" width="120" />
      <el-table-column prop="email" label="Email" width="140" />
      <el-table-column prop="github" label="GitHub" width="140" />
      <el-table-column prop="csdn" label="CSDN" width="140" />
      <el-table-column prop="qq" label="QQ" width="120" />
      <el-table-column prop="wechat" label="WeChat" width="120" />
      <el-table-column prop="birth" label="生日" width="120" />
      <el-table-column prop="introduction" label="个人简介" width="200">
        <template #default="scope">
          <el-tooltip effect="dark" :content="scope.row.introduction" placement="top-start">
            <div class="user-intro">{{ scope.row.introduction }}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="收藏" width="80" align="center">
        <template #default="scope">
          <el-button type="primary" link size="small" @click="getCollect(scope.row.id)">收藏</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template #default="scope">
          <el-button size="small" type="warning" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
  <!-- 在vue3中，.sync 双向绑定不生效 -->
  <!-- 解决用 v-model -->
  <user-detail v-model:dialogVisible="dialogVisible" :dialogTitle="dialogTitle" :dialogData="dialogData" />
</template>

<script setup lang="ts">
import MySearch from '@/components/MySearch/MySearch.vue';
import UserDetail from '@/components/MyDetail/userDetail.vue';
import { ref, reactive, nextTick, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { dayjs, ElNotification, ElMessageBox } from 'element-plus';
import { handleSex, beforeAvatarUpload } from '@/mixins';
import config from '@/config';

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

const getData = () => {
  console.log('getData');
  // getUserList()
  let data1 = [
    {
      id: 1,
      username: '张三',
      sex: 1,
      phoneNum: '13845678901',
      email: '12345678901@qq.com',
      github: 'https://github.com/Plutossy',
      csdn: 'https://blog.csdn.net/qq_41893274',
      qq: '12345678901',
      wechat: 'PlutoSsy',
      birth: dayjs('1999-01-01').format('YYYY-MM-DD'),
      introduction: '我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三我是张三',
    },
    {
      id: 2,
      username: '张三',
      sex: 0,
      phoneNum: '13845678901',
      email: '12345678901@qq.com',
      github: 'https://github.com/Plutossy',
      csdn: 'https://blog.csdn.net/qq_41893274',
      qq: '12345678901',
      wechat: 'PlutoSsy',
      birth: dayjs('1999-01-01').format('YYYY-MM-DD'),
      introduction: '我是张三',
    },
    {
      id: 3,
      username: '张三',
      sex: -1,
      phoneNum: '18245678901',
      email: '12345678901@qq.com',
      github: 'https://github.com/Plutossy',
      csdn: 'https://blog.csdn.net/qq_41893274',
      qq: '12345678901',
      wechat: 'PlutoSsy',
      birth: dayjs('1999-01-01').format('YYYY-MM-DD'),
      introduction: '我是张三',
    },
    {
      id: 4,
      username: '张三',
      sex: -1,
      phoneNum: '18345678901',
      email: '12345678901@qq.com',
      github: 'https://github.com/Plutossy',
      csdn: 'https://blog.csdn.net/qq_41893274',
      qq: '12345678901',
      wechat: 'PlutoSsy',
      birth: dayjs('1999-01-01').format('YYYY-MM-DD'),
      introduction: '我是张三',
    },
    {
      id: 5,
      username: '张三',
      sex: -1,
      phoneNum: '18345678901',
      email: '12345678901@qq.com',
      github: 'https://github.com/Plutossy',
      csdn: 'https://blog.csdn.net/qq_41893274',
      qq: '12345678901',
      wechat: 'PlutoSsy',
      birth: dayjs('1999-01-01').format('YYYY-MM-DD'),
      introduction: '我是张三',
    },
  ];
  userData.splice(0, userData.length, ...data1);
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
const handleAvatarSuccess = (res, file) => {
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

// 收藏
const getCollect = userId => {
  console.log('getCollect--', userId);
  router.push({
    path: '/user/collect',
    query: {
      userId,
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

    ::v-deep .el-table__header tr th {
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
    .user-intro {
      width: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
}
</style>
