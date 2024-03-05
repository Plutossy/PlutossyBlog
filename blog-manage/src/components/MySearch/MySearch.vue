<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-22 11:33:53
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-03-01 11:42:43
 * @FilePath: \blog-manage\src\components\MySearch\MySearch.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="header-search">
    <el-button type="danger" @click="delAll">批量删除</el-button>
    <el-input v-model.trim="keyWord" placeholder="请输入搜索内容..." class="search-input" clearable @keyup.enter.native="goSearch" @input.lazy="goSearch" />
    <el-button type="primary" @click="goSearch">搜索</el-button>
    <el-button type="info" plain @click="reset">重置</el-button>
    <div class="header-right">
      <el-button type="primary" @click="add">新增</el-button>
      <div v-if="backHistory || props.goComBack" class="el-divider el-divider--vertical" direction="vertical" />
      <el-button v-if="backHistory || props.goComBack" @click="goBack">返回</el-button>
    </div>
  </div>
  <!-- 在vue3中，.sync 双向绑定不生效 -->
  <!-- 解决用 v-model -->
  <user-detail v-model:dialogVisible="dialogVisible['user']" :dialogTitle="dialogTitle" />
  <picture-detail v-model:dialogVisible="dialogVisible['picture']" :dialogTitle="dialogTitle" />
  <type-detail v-model:dialogVisible="dialogVisible['type']" :dialogTitle="dialogTitle" />
  <tag-detail v-model:dialogVisible="dialogVisible['tag']" :dialogTitle="dialogTitle" />
  <blog-search-detail v-model:dialogVisible="dialogVisible['blog-search']" :dialogTitle="dialogTitle" :type="props.type" />
  <music-detail v-model:dialogVisible="dialogVisible['music']" :dialogTitle="dialogTitle" />
</template>

<script setup lang="ts">
import { ElMessage, ElMessageBox, ElNotification } from 'element-plus';

const props = defineProps({
  type: {
    type: String,
    default: '',
    required: true,
  },
  multipleSelection: {
    type: Array,
    default: () => [],
  },
  back: {
    type: String,
  },
  goCom: {
    type: Function,
    default: () => {},
  },
  goComBack: {
    type: Function,
  },
});

let emit = defineEmits(['searchResult']); // 如果用的setup函数则是用 cotext.emit 去使用
const router = useRouter();

type VisibleType = {
  [K: string]: boolean;
};
const keyWord = ref('');
let dialogVisible: VisibleType = reactive({}); // 详情弹窗是否显示
let dialogTitle = ref('详情'); // 详情弹窗标题
let backHistory = ref(props.back); // 返回路由

const delAll = () => {
  console.log('delAll--', props.multipleSelection);
  if (props.multipleSelection.length === 0)
    return ElMessage({
      showClose: true,
      message: '请至少选择一项！',
      type: 'warning',
    });
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(() => {
      props.multipleSelection.forEach((item: any) => {
        console.log('item--', item);
        // delUser(item.id)
      });
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
};

const goSearch = () => {
  console.log('goSearch--', keyWord.value);
  if (!keyWord.value)
    ElMessage({
      showClose: true,
      message: '搜索内容不能为空！',
      type: 'warning',
    });
  // searchUser(keyWord.value)
  emit('searchResult', {
    id: 1,
    username: '张三',
    sex: '男',
    phoneNum: '12345678901',
    email: '12345678901@qq.com',
    birth: '1999-01-01',
    introduction: '我是张三',
    location: '北京',
    userId: 1,
    picId: 1,
    picUrl: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
    picTitle: '图片标题',
    author: '张三',
    description: '这是一张图片',
  });
};

const reset = () => {
  keyWord.value = '';
  // getData()
};

const add = () => {
  switch (props.type) {
    case 'user':
      dialogVisible['user'] = true;
      break;
    case 'blog':
      if (props.goCom) {
        props.goCom();
      }
      break;
    case 'picture':
      dialogVisible['picture'] = true;
      break;
    case 'type':
      dialogVisible['type'] = true;
      break;
    case 'tag':
      dialogVisible['tag'] = true;
      break;
    case 'collect':
      dialogVisible['blog-search'] = true;
      break;
    case 'type-blog':
      dialogVisible['blog-search'] = true;
      break;
    case 'tag-blog':
      dialogVisible['blog-search'] = true;
      break;
    case 'music':
      dialogVisible['music'] = true;
      break;
    default:
      break;
  }
  dialogTitle.value = '新增';
};

const goBack = () => {
  console.log('goBack--');
  if (backHistory.value) return router.replace(backHistory.value);
  if (props.goComBack) return props.goComBack;
};
</script>

<style scoped>
.header-search {
  background-color: #fff;
  display: flex;
  align-items: center;
  padding: 20px;

  .search-input {
    width: 320px;
    margin: 0 20px;
  }

  .header-right:last-child {
    position: absolute;
    right: 20px;
  }
}
</style>
