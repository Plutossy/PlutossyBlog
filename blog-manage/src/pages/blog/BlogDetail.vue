<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-03-01 10:19:31
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-25 11:20:38
 * @FilePath: \blog-manage\src\pages\layout\MyBlogDetail.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="blog-detail">
    <el-page-header :icon="ArrowLeft" title="博客管理" @back="goBack">
      <template #content>
        <div class="detail-header">
          <el-avatar :size="32" src="@/assets/img/head.jpg" />
          <span class="header-user" style="color: var(--el-text-color-regular)"> 用户名 </span>
        </div>
      </template>
      <template #extra>
        <div class="flex items-center">
          <el-button type="primary" @click="saveForm">保存草稿</el-button>
          <el-button type="warning" @click="publishForm(ruleFormRef)">发布文章</el-button>
        </div>
      </template>
    </el-page-header>
    <el-form ref="ruleFormRef" :model="blogForm" :rules="rules" size="default" status-icon>
      <el-form-item label="文章标题：" prop="title">
        <el-input v-model="blogForm.title" maxlength="100" placeholder="请输入文章标题" show-word-limit type="text" clearable />
      </el-form-item>
    </el-form>
    <v-md-editor v-model="blogForm.content" height="600px" placeholder="请输入文章内容......" :disabled-menus="[]" @upload-image="handleUploadImage" @save="saveForm" />
  </div>

  <el-dialog v-model="publishVisible" title="发布文章" width="500">
    <el-form ref="rulePublishRef" :model="blogForm" :rules="rules" size="default">
      <el-form-item label="文章分类：" prop="type">
        <el-select v-model="blogForm.type" filterable clearable placeholder="请选择文章分类">
          <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="文章标签：" prop="tag">
        <el-select v-model="blogForm.tag" filterable clearable placeholder="请选择文章标签">
          <el-option v-for="item in tagOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="文章简介：" prop="introduction">
        <el-input v-model="blogForm.introduction" maxlength="256" placeholder="请输入文章简介......" show-word-limit type="textarea" :rows="4" />
      </el-form-item>
      <el-form-item label="文章类型：" prop="status">
        <el-radio-group v-model="blogForm.status">
          <el-radio :value="0" class="original">
            原创
            <el-icon v-if="blogForm.status === 0" color="green"><SuccessFilled /></el-icon>
          </el-radio>
          <el-radio :value="1">转载</el-radio>
          <el-radio :value="2">翻译</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="publishVisible = false">取消</el-button>
        <el-button @click="saveForm(true)">保存为草稿</el-button>
        <el-button type="primary" @click="publishForm(rulePublishRef, true)">发布文章</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';

import { ElNotification } from 'element-plus';
import { ArrowLeft } from '@element-plus/icons-vue';
import type { FormInstance, FormRules } from 'element-plus';

const props = defineProps({
  forward: {
    type: Function,
    default: () => {},
  },
  blogDetail: {
    type: Object,
    default: {},
  },
});

interface RuleForm {
  title: string;
  content: string;
  type: string;
  tag: string;
  introduction: string;
  status: number | string;
}

type TypeTagItem = {
  value: string;
  label: string;
};
type TypeTagList = TypeTagItem[];

const ruleFormRef = ref<FormInstance>();
const rulePublishRef = ref<FormInstance>();
let blogForm = reactive<RuleForm>({
  title: '【暂无标题】',
  content: '',
  type: '',
  tag: '',
  introduction: '',
  status: 0, // 0 原创 1 转载 2 翻译
});

let typeOptions: TypeTagList = reactive([]); // 文章分类
let tagOptions: TypeTagList = reactive([]); // 文章标签

let publishVisible = ref(false);

onMounted(() => {
  getTypeTagList();
  if (props.blogDetail.blogId) {
    getBlogDetail(props.blogDetail.blogId);
  }
});

const rules = reactive<FormRules<RuleForm>>({
  title: [
    { required: true, message: '文章标题不能为空', trigger: 'blur' },
    { max: 100, message: '长度在 100 字以内', trigger: 'change' },
  ],
  type: [{ required: true, message: '请选择文章分类', trigger: 'change' }],
  tag: [{ required: true, message: '请选择文章标签', trigger: 'change' }],
  introduction: [{ required: true, message: '文章简介不能为空', trigger: 'blur' }],
});

const getBlogDetail = (id: string | number) => {
  console.log('getBlogDetail', id);
  blogForm.content = '```vue\n' + 'console.log("hello world", ' + id + ')' + '\n```';
};

// 返回
const goBack = () => {
  props.forward('BlogList');
};

// 保存草稿
const saveForm = (val?: boolean) => {
  if (val) {
    publishVisible.value = false;
  }
  if (!blogForm.content) return ElNotification.warning('博客内容不能为空');
  console.log('save!');
  ElNotification.success('保存成功！');
};

// 发布
const publishForm = async (formEl: FormInstance | undefined, val?: boolean) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('publish!');
      if (val) {
        console.log(blogForm);
        publishVisible.value = false;
        ElNotification.success('发布成功！');
      } else {
        console.log(blogForm.title);
        publishVisible.value = true;
      }
    } else {
      console.log('error!', fields);
    }
  });
};

function handleUploadImage(_event: any, insertImage: any, files: any) {
  // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
  console.log(files);

  // 此处只做示例
  insertImage({
    url: 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1269952892,3525182336&fm=26&gp=0.jpg',
    desc: '七龙珠',
    // width: 'auto',
    // height: 'auto',
  });
}

const getTypeTagList = () => {
  typeOptions = [
    { value: '分类1', label: '前端' },
    { value: '分类2', label: '后端' },
    { value: '分类3', label: '数据库' },
    { value: '分类4', label: '运维' },
    { value: '分类5', label: '其他' },
  ];
  tagOptions = [
    { value: '标签1', label: 'Vue' },
    { value: '标签2', label: 'React' },
    { value: '标签3', label: 'Angular' },
    { value: '标签4', label: 'Node' },
    { value: '标签5', label: 'Webpack' },
  ];
};
</script>

<style scoped lang="scss">
.blog-detail {
  background-color: #fff;
  padding: 20px;
  :deep(.el-page-header__header) {
    margin-bottom: 20px;
    .el-page-header__content {
      display: flex;
      align-items: center;
      .detail-header {
        display: flex;
        align-items: center;
        .header-user {
          margin-left: 10px;
        }
      }
    }
  }
}
:deep(.el-radio__label) {
  display: flex;
  align-items: center;
  > :last-child {
    margin-left: 5px;
  }
}
</style>
