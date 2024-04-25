<template>
  <el-dialog v-model="detailVisible" :title="props.dialogTitle">
    <el-form ref="ruleFormRef" :rules="rules" :model="detailData" label-width="80px">
      <el-form-item label="照片地址" prop="url">
        <div class="item-picUrl">
          <el-image :src="detailData.url" fit="contain" />
          <el-upload :action="uploadUrl(detailData.url)" :before-upload="beforeImgUpload" :on-success="handleImgSuccess">
            <el-button type="primary" link>更新图片</el-button>
          </el-upload>
        </div>
      </el-form-item>
      <el-form-item label="标题" prop="name">
        <el-input v-model="detailData.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input type="textarea" :rows="3" v-model="detailData.description" autocomplete="off" />
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
import { beforeImgUpload } from '@/mixins';
import type { FormInstance, FormRules } from 'element-plus';
import config from '@/config';
import { ComponentInternalInstance } from 'vue';
import eventBus from '@/assets/js/eventBus';

const emitter = eventBus();
const { proxy } = getCurrentInstance() as ComponentInternalInstance | any;

const props = defineProps({
  dialogVisible: {
    type: Boolean,
    default: false,
  },
  dialogTitle: {
    type: String,
    default: '详情',
  },
  dialogData: {
    type: Object,
    default: () => ({}),
  },
});
let emit = defineEmits(); // 如果用的setup函数则是用 cotext.emit 去使用
let detailVisible = ref(false);

interface RuleForm {
  // 表单数据类型
  url: string;
  name: string;
  description: string;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例
let detailData = reactive<RuleForm>({
  // 表单数据
  url: '',
  name: '',
  description: '',
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  url: [{ required: true, message: '请输入照片链接', trigger: 'blur' }],
  name: [{ required: true, message: '请输入照片标题', trigger: 'blur' }],
  description: [{ message: '请输入照片描述', trigger: 'blur' }],
});

watch(
  () => props.dialogVisible,
  val => {
    detailVisible.value = val;
  }
);
watch(
  () => props.dialogData,
  val => {
    // detailData.value = val
    detailData = val as RuleForm;
  }
);
watch(detailVisible, val => {
  emit('update:dialogVisible', val);
});

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
      showClose: true,
      duration: 1000,
    });
    // getData(id); // 重新获取数据
  } else {
    ElNotification({
      message: '头像上传失败',
      type: 'error',
      showClose: true,
      duration: 1000,
    });
  }
};

const cancel = () => {
  detailVisible.value = false;
};

const confirm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('验证成功!');
      ElMessageBox.confirm(props.dialogTitle === '编辑' ? '此操作将修改此信息, 是否确定修改?' : '是否确定新增?', '提示', {
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
        .then(async () => {
          detailVisible.value = false;
          if (props.dialogTitle === '编辑') {
            try {
              const { code } = await proxy.$apis.picture.updatePicture(detailData);
              if (code === 200) {
                ElNotification({
                  type: 'success',
                  message: '修改成功!',
                  showClose: true,
                  duration: 1000,
                });
                detailVisible.value = false;
              } else {
                ElNotification({
                  type: 'error',
                  message: '修改失败!',
                  showClose: true,
                  duration: 1000,
                });
              }
            } catch (error) {
              console.log(error);
            }
          } else if (props.dialogTitle === '新增') {
            try {
              const { code } = await proxy.$apis.picture.addPicture(detailData);
              if (code === 200) {
                ElNotification({
                  type: 'success',
                  message: '新增成功!',
                  showClose: true,
                  duration: 1000,
                });
                detailVisible.value = false;
                emitter.emit('addSuccess', true);
              } else {
                ElNotification({
                  type: 'error',
                  message: '新增失败!',
                  showClose: true,
                  duration: 1000,
                });
              }
            } catch (error) {
              console.log(error);
            }
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
.item-picUrl {
  display: flex;
  align-items: center;
  .el-image {
    width: 100px;
    height: 100px;
    margin-right: 20px;
  }
  .el-upload {
    .el-button {
      margin-top: 20px;
    }
  }
}
</style>
