<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-03-01 10:19:31
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-30 14:30:09
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
          <el-button v-if="!props.blogDetail.id" type="primary" @click="saveForm">暂存</el-button>
          <el-button type="warning" @click="publishForm1(ruleFormRef)">发布文章</el-button>
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
      <el-form-item label="文章分类：" prop="typeId">
        <el-select v-model="blogForm.typeId" filterable clearable allow-create :reserve-keyword="false" placeholder="请选择文章分类">
          <el-option v-for="item in typeOptions" :key="item.id" :label="item.name" :value="item.id" />
          <template #footer>
            <el-button v-if="!isTypeAdding" text bg size="small" @click="addOption('type')"> 新增分类 </el-button>
            <div v-else class="add-btn">
              <el-input v-model="optionName" class="option-input" placeholder="请输入分类名称" size="small" />
              <div class="btn-footer">
                <el-button type="primary" size="small" @click="onConfirm('type')"> 确认 </el-button>
                <el-button size="small" @click="clearAddOption('type')">取消</el-button>
              </div>
            </div>
          </template>
        </el-select>
      </el-form-item>
      <el-form-item label="文章标签：" prop="tagId">
        <el-select v-model="blogForm.tagId" filterable clearable multiple collapse-tags collapse-tags-tooltip :max-collapse-tags="3" allow-create :reserve-keyword="false" placeholder="请选择文章标签">
          <el-option v-for="item in tagOptions" :key="item.id" :label="item.name" :value="item.id" />
          <template #footer>
            <el-button v-if="!isTagAdding" text bg size="small" @click="addOption('tag')"> 新增分类 </el-button>
            <div v-else class="add-btn">
              <el-input v-model="optionName" class="option-input" placeholder="请输入分类名称" size="small" />
              <div class="btn-footer">
                <el-button type="primary" size="small" @click="onConfirm('tag')"> 确认 </el-button>
                <el-button size="small" @click="clearAddOption('tag')">取消</el-button>
              </div>
            </div>
          </template>
        </el-select>
      </el-form-item>
      <el-form-item label="文章简介：" prop="description">
        <el-input v-model="blogForm.description" maxlength="256" placeholder="请输入文章简介......" show-word-limit type="textarea" :rows="4" />
      </el-form-item>
      <el-form-item label="文章类型：" prop="flag">
        <el-radio-group v-model="blogForm.flag">
          <el-radio :value="0" class="original">
            原创
            <el-icon v-if="blogForm.flag === 0" color="green"><SuccessFilled /></el-icon>
          </el-radio>
          <el-radio :value="1">转载</el-radio>
          <el-radio :value="2">翻译</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="publishVisible = false">取消</el-button>
        <el-button @click="publishForm2(rulePublishRef, 2)">保存为草稿</el-button>
        <el-button type="primary" @click="publishForm2(rulePublishRef, 1)">发布文章</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ArrowLeft } from '@element-plus/icons-vue';
import type { FormInstance, FormRules } from 'element-plus';
import type { ComponentInternalInstance } from 'vue';
import { clearForm, clearEmptyProperty } from '@/mixins';
import store from '@/store/store';

const { proxy } = getCurrentInstance() as ComponentInternalInstance | any;

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
  typeId: number | string;
  tagId: number | string;
  description: string;
  flag: number | string;
  published: number;
  userId: number | string;
}

type TypeTagItem = {
  id: string | number;
  name: string;
};
type TypeTagList = TypeTagItem[];

const ruleFormRef = ref<FormInstance>();
const rulePublishRef = ref<FormInstance>();
let blogForm = reactive<RuleForm>({
  title: '【暂无标题】',
  content: '',
  typeId: '',
  tagId: '',
  description: '',
  flag: 0, // 0 原创 1 转载 2 翻译
  published: 1, // 0 暂存 1 发布 2 草稿
  userId: store.getters['user/userInfo'].id,
});

let typeOptions: TypeTagList = reactive([]); // 文章分类
let tagOptions: TypeTagList = reactive([]); // 文章标签

let publishVisible = ref(false);

let isTypeAdding = ref(false);
let isTagAdding = ref(false);
let optionName = ref('');

watch(publishVisible, val => {
  if (!val) {
    rulePublishRef.value?.clearValidate();
    blogForm.typeId = '';
    blogForm.tagId = '';
    blogForm.description = '';
    blogForm.flag = 0;
  }
});

onMounted(() => {
  getTypeList();
  getTagList();
  getBlogDetail(props.blogDetail.id);
});

const rules = reactive<FormRules<RuleForm>>({
  title: [
    { required: true, message: '文章标题不能为空', trigger: 'blur' },
    { max: 100, message: '长度在 100 字以内', trigger: 'change' },
  ],
  typeId: [{ required: true, message: '请选择文章分类', trigger: 'blur' }],
  tagId: [{ required: true, message: '请选择文章标签', trigger: 'blur' }],
  description: [{ required: true, message: '文章简介不能为空', trigger: 'blur' }],
  flag: [{ required: true, message: '请选择文章类型', trigger: 'blur' }],
});

const getBlogDetail = async (id?: string | number) => {
  try {
    // 博客 - 编辑 // 博客 - 暂存
    const { code, data } = id ? await proxy.$apis.blog.selectBlogById(id) : await proxy.$apis.blog.selectBlogByPublished({ published: 0 });
    if (id && code === 200 && data) {
      // initData(blogForm, data);
      for (const key in data) {
        if (Object.hasOwnProperty.call(blogForm, key)) {
          (blogForm as any)[key] = data[key];
        }
      }
    }
    if (!id && code === 200 && data[0]) {
      // initData(blogForm, data);
      for (const key in data[0]) {
        if (Object.hasOwnProperty.call(blogForm, key)) {
          (blogForm as any)[key] = data[0][key];
        }
      }
    }
  } catch (error) {
    console.log(error);
  }
};

// 返回
const goBack = () => {
  props.forward('BlogList');
  // 清空表单
  clearForm(blogForm);
};

const addOption = async (val: string) => {
  if (val === 'type') {
    isTypeAdding.value = true;
  }
  if (val === 'tag') {
    isTagAdding.value = true;
  }
};

const onConfirm = async (val: string) => {
  if (!optionName.value) return ElNotification.warning('名称不能为空');
  if (val === 'type') {
    const { code } = await proxy.$apis.type.addType({ name: optionName.value });
    if (code === 200) {
      getTypeList();
    }
    clearAddOption('type');
  }
  if (val === 'tag') {
    const { code } = await proxy.$apis.type.addTag({ name: optionName.value });
    if (code === 200) {
      getTagList();
    }
    clearAddOption('tag');
  }
};

const clearAddOption = (val: string) => {
  optionName.value = '';
  if (val === 'type') {
    isTypeAdding.value = false;
  }
  if (val === 'tag') {
    isTagAdding.value = false;
  }
};

// 暂存
const saveForm = async () => {
  blogForm.published = 0;
  blogForm.userId = store.getters['user/userInfo'].id;
  console.log(clearEmptyProperty(blogForm));
  if (!blogForm.content) return ElNotification.warning('博客内容不能为空');
  try {
    const { code } = await proxy.$apis.blog.addBlog(clearEmptyProperty(blogForm));
    if (code === 200) {
      ElNotification({
        message: '博客已暂存成功！',
        type: 'success',
        showClose: true,
        duration: 1000,
      });
      goBack();
    }
  } catch (error) {
    console.log(error);
  }
};

// 打开发布弹窗
const publishForm1 = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      publishVisible.value = false;
    } else {
      console.log('error!', fields);
    }
  });
};

// 发布
const publishForm2 = async (formEl: FormInstance | undefined, published: number) => {
  if (!formEl) return;
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      blogForm.published = published;
      blogForm.userId = blogForm.userId || store.getters['user/userInfo'].id;
      if (!blogForm.content) return ElNotification.warning('博客内容不能为空！');
      try {
        const { code } = await proxy.$apis.blog.addBlog(blogForm);
        if (code === 200) {
          ElNotification({
            message: '博客已发布成功！',
            type: 'success',
            showClose: true,
            duration: 1000,
          });
          publishVisible.value = false;
          goBack();
        } else {
          ElNotification.error('发布失败！');
        }
      } catch (error) {
        console.log(error);
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

const getTypeList = async () => {
  const param = {
    type: 'all',
  };
  const { code, data } = await proxy.$apis.type.selectTypeByQuery(param);
  code === 200 && typeOptions.splice(0, typeOptions.length, ...data);
};
const getTagList = async () => {
  const param = {
    type: 'all',
  };
  const { code, data } = await proxy.$apis.tag.selectTagByQuery(param);
  code === 200 && tagOptions.splice(0, tagOptions.length, ...data);
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
.add-btn {
  .btn-footer {
    display: flex;
    justify-content: flex-end;
    margin-top: 10px;
  }
}
</style>
