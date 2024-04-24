<template>
  <el-dialog v-model="detailVisible" :title="props.dialogTitle">
    <el-form ref="ruleFormRef" :rules="rules" :model="detailData" label-width="120px">
      <el-form-item label="歌曲名：" prop="name">
        <el-input v-model="detailData.name" />
      </el-form-item>
      <el-form-item label="歌手 / 组合：" prop="singer">
        <el-input v-model="detailData.singer" />
      </el-form-item>
      <el-form-item label="歌曲链接：" prop="url">
        <el-input v-model="detailData.url" />
      </el-form-item>
      <el-form-item label="歌词：" prop="lyric">
        <el-input type="textarea" :rows="3" v-model="detailData.lyric" autocomplete="off" />
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
import type { FormInstance, FormRules } from 'element-plus';
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
  name: string;
  singer: string;
  url: string;
  lyric: string;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例
let detailData = reactive<RuleForm>({
  // 表单数据
  name: '',
  singer: '',
  url: '',
  lyric: '',
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  name: [{ required: true, message: '请输入歌曲名', trigger: 'blur' }],
  singer: [{ required: true, message: '请输入歌手或组合', trigger: 'blur' }],
  url: [{ required: true, message: '请输入歌曲链接', trigger: 'blur' }],
  lyric: [{ message: '请输入歌词', trigger: 'blur' }],
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
              const { code } = await proxy.$apis.music.updateMusic(detailData);
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
              const { code } = await proxy.$apis.music.addMusic(detailData);
              if (code === 200) {
                ElNotification({
                  type: 'success',
                  message: '新增成功!',
                  showClose: true,
                  duration: 1000,
                });
                detailVisible.value = false;
                emitter.emit('addSuccess', true);
              } else if (code === 500) {
                ElNotification({
                  type: 'warning',
                  message: '分类名称不能重复!',
                  showClose: true,
                  duration: 1000,
                });
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

<style lang="scss" scoped></style>
