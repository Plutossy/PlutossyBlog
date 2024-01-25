<template>
  <el-dialog v-model="detailVisible" :title="props.dialogTitle">
    <el-form ref="ruleFormRef" :rules="rules" :model="detailData" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="detailData.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="detailData.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="0">女</el-radio>
          <el-radio :label="-1">未知</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNum">
        <el-input v-model="detailData.phoneNum" autocomplete="off" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="detailData.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="生日" prop="birth">
        <el-date-picker v-model="detailData.birth" type="date" placeholder="请选择日期" format="YYYY-MM-DD"
          style="width: 100%;" :disabled-date="disabledDate" />
      </el-form-item>
      <el-form-item label="个人简介" prop="introduction">
        <el-input type="textarea" v-model="detailData.introduction" autocomplete="off" />
      </el-form-item>
      <el-form-item label="所在地" prop="location">
        <el-input v-model="detailData.location" autocomplete="off" />
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
import { ref, reactive, watch } from 'vue'
import { ElMessageBox, ElNotification } from 'element-plus';
import type { FormInstance, FormRules } from 'element-plus'

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
})
let emit = defineEmits() // 如果用的setup函数则是用 cotext.emit 去使用
let detailVisible = ref(false)

interface RuleForm { // 表单数据类型
  username: string
  sex: number
  phoneNum: string
  email: string
  birth: string
  introduction: string
  location: string
}
const ruleFormRef: any = ref<FormInstance>() // 表单实例
let detailData = reactive<RuleForm>({ // 表单数据
  username: '',
  sex: -1,
  phoneNum: '',
  email: '',
  birth: '',
  introduction: '',
  location: '',
})
const rules = reactive<FormRules<RuleForm>>({ // 表单验证规则
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
  ],
  sex: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phoneNum: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3456789]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { pattern: /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/, message: '邮箱格式不正确', trigger: 'blur' }
  ],
  birth: [
    { required: true, message: '请选择生日', trigger: 'change' }
  ],
  introduction: [
    { required: true, message: '请输入个人简介', trigger: 'blur' },
    { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
  ],
  location: [
    { required: true, message: '请选择所在地', trigger: 'blur' },
    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
  ],
})

watch(() => props.dialogVisible, (val) => {
  detailVisible.value = val
})
watch(() => props.dialogData, (val) => {
  // detailData.value = val
  detailData = val as RuleForm;
})
watch(detailVisible, (val) => {
  emit('update:dialogVisible', val)
})

// 禁用未来的时间
const disabledDate = (time: Date) => {
  return time.getTime() > Date.now()
}

const cancel = () => {
  detailVisible.value = false
}

const confirm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('验证成功!')
      ElMessageBox.confirm(props.dialogTitle === '修改' ? '此操作将修改此信息, 是否确定修改?' : '是否确定新增?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true
            instance.confirmButtonText = 'Loading...'
            setTimeout(() => {
              done()
              instance.confirmButtonLoading = false
            }, 300)
          } else {
            done()
          }
        },
      }).then(() => {
        detailVisible.value = false
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

      }).catch(() => {
        props.dialogTitle === '编辑' ? ElNotification({
          type: 'info',
          message: '已取消修改',
        }) : ElNotification({
          type: 'info',
          message: '已取消新增',
        });
      });
    } else {
      console.log('验证失败!', fields)
    }
  })
}

</script>

<style lang="scss" scoped></style>
