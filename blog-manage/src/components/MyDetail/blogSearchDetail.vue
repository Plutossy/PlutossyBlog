<template>
  <el-dialog v-model="detailVisible" :title="props.dialogTitle">
    <el-form ref="ruleFormRef" :rules="rules" :model="detailData" label-width="80px">
      <el-form-item label="标题" prop="title">
        <el-autocomplete v-model.trim="detailData.title" :fetch-suggestions="querySearch" @select="handleSelect" @input="handleSelect" placeholder="请输入博客标题" :suffix-icon="Search" clearable>
          <template #default="{ item }">
            <div class="title">{{ item }}</div>
          </template>
        </el-autocomplete>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="confirm(ruleFormRef)">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { Search } from '@element-plus/icons-vue';
import type { FormInstance, FormRules } from 'element-plus';

const props = defineProps({
  type: {
    // collect, type-blog, tag-blog
    type: String,
  },
  dialogVisible: {
    type: Boolean,
    default: false,
  },
  dialogTitle: {
    type: String,
    default: '详情',
  },
});
let emit = defineEmits(['update:dialogVisible']); // 如果用的setup函数则是用 cotext.emit 去使用
let detailVisible = ref(false);

interface RuleForm {
  // 表单数据类型
  title: string;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例
let detailData = reactive<RuleForm>({
  // 表单数据
  title: '',
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  title: [{ required: true, message: '请输入博客标题', trigger: 'change' }],
});

watch(
  () => props.dialogVisible,
  val => {
    detailVisible.value = val;
  }
);
watch(detailVisible, val => {
  emit('update:dialogVisible', val);
});

const createFilter = (queryString: string) => {
  return (item: string) => {
    return item.toLowerCase().indexOf(queryString.toLowerCase()) === 0;
  };
};

const querySearch = (queryString: string, cb: (arg0: any[]) => void) => {
  const queryTitles = [
    'vue',
    'react',
    'angular',
    'js',
    'ts',
    'css',
    'html',
    'node',
    'webpack',
    'vite',
    'rollup',
    'gulp',
    'grunt',
    'parcel',
    'deno',
    'koa',
    'egg',
    'express',
    'mongodb',
    'mysql',
    'redis',
    'nginx',
    'docker',
    'k8s',
    'linux',
    'mac',
    'windows',
    'git',
    'svn',
    'github',
    'gitlab',
    'gitee',
    'coding',
    'vscode',
    'webstorm',
    'sublime',
    'vim',
    'emacs',
    'chrome',
    'firefox',
    'safari',
    'ie',
    'edge',
    'postman',
    'charles',
    'fiddler',
    'wireshark',
    'xshell',
    'securecrt',
    'xftp',
    'navicat',
    'typora',
    'markdown',
    'office',
    'excel',
    'word',
    'ppt',
    'outlook',
    'onenote',
    'visio',
    'project',
    'photoshop',
    'sketch',
    'axure',
    'pr',
    'ae',
    'c4d',
    '3dmax',
    'maya',
    'blender',
    'premiere',
    'finalcut',
    'cad',
    'proe',
    'ug',
    'solidworks',
    'catia',
    'rhino',
    'autocad',
    'office',
    'excel',
    'word',
    'ppt',
    'outlook',
    'onenote',
    'visio',
    'project',
    'photoshop',
    'sketch',
    'axure',
    'pr',
    'ae',
    'c4d',
    '3dmax',
    'maya',
    'blender',
    'premiere',
    'finalcut',
    'cad',
    'proe',
    'ug',
    'solidworks',
    'catia',
    'rhino',
    'autocad',
  ];
  const results = queryString ? queryTitles.filter(createFilter(queryString)) : queryTitles;
  // 调用 callback 返回建议列表的数据
  cb(results);
};

const handleSelect = (val: string) => {
  detailData.title = val;
};

const cancel = () => {
  detailVisible.value = false;
};

const confirm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('验证成功!');
      ElMessageBox.confirm('是否确定新增?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = 'Loading...';
            setTimeout(() => {
              done();
              instance.confirmButtonLoading = false;
            }, 300);
          } else {
            done();
          }
        },
      })
        .then(() => {
          detailVisible.value = false;
          if (props.dialogTitle === '编辑') {
            // updateUser(detailData)
            ElNotification({
              type: 'success',
              message: '修改成功!',
            });
          } else if (props.dialogTitle === '新增') {
            // collect, type-blog, tag-blog
            // addUser(detailData)
            ElNotification({
              type: 'success',
              message: '新增成功!',
            });
          }
        })
        .catch(() => {
          props.dialogTitle === '编辑'
            ? ElNotification({
                type: 'info',
                message: '已取消修改',
              })
            : ElNotification({
                type: 'info',
                message: '已取消新增',
              });
        });
    } else {
      console.log('验证失败!', fields);
    }
  });
};
</script>

<style lang="scss" scoped>
:deep(.el-form-item) {
  .el-autocomplete {
    width: 85%;
  }
}
</style>
