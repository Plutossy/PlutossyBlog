<template>
  <el-dialog v-model="detailVisible" :title="props.dialogTitle">
    <el-form ref="ruleFormRef" :rules="rules" :model="detailData" label-width="80px">
      <el-form-item label="作者" prop="author">
        <el-input v-model="detailData.author" disabled />
      </el-form-item>
      <el-form-item label="标题" prop="picTitle">
        <el-input v-model="detailData.picTitle" autocomplete="off" />
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
import { ref, reactive, watch } from 'vue';
import { ElMessageBox, ElNotification } from 'element-plus';
import type { FormInstance, FormRules } from 'element-plus';

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
  author: string;
  picTitle: string;
  description: string;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例
let detailData = reactive<RuleForm>({
  // 表单数据
  author: '',
  picTitle: '',
  description: '',
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  picTitle: [{ required: true, message: '请输入照片标题', trigger: 'blur' }],
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
        .then(() => {
          detailVisible.value = false;
          if (props.dialogTitle === '编辑') {
            // updateUser(detailData)
            ElNotification({
              type: 'success',
              message: '修改成功!',
            });
          } else if (props.dialogTitle === '新增') {
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

<style lang="scss" scoped></style>
