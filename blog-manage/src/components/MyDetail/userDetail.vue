<template>
  <el-dialog v-model="detailVisible" :title="props.dialogTitle">
    <el-form ref="ruleFormRef" :rules="rules" :model="detailData" label-width="80px">
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="detailData.nickname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="detailData.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password" v-if="props.dialogTitle === '新增'" class="animate__animated animate__bounceIn">
        <el-input v-model="detailData.password" autocomplete="off" type="password" />
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPwd" v-if="props.dialogTitle === '新增' && detailData.password" class="animate__animated animate__bounceIn">
        <el-input v-model="detailData.confirmPwd" autocomplete="off" type="password" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="detailData.sex">
          <el-radio value="男">男</el-radio>
          <el-radio value="女">女</el-radio>
          <el-radio value="" disabled>未知</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="detailData.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="detailData.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="github" prop="github">
        <el-input v-model="detailData.github" autocomplete="off" />
      </el-form-item>
      <el-form-item label="csdn" prop="csdn">
        <el-input v-model="detailData.csdn" autocomplete="off" />
      </el-form-item>
      <el-form-item label="qq" prop="qq">
        <el-input v-model="detailData.qq" autocomplete="off" />
      </el-form-item>
      <el-form-item label="微信" prop="wechat">
        <el-input v-model="detailData.wechat" autocomplete="off" />
      </el-form-item>
      <el-form-item label="生日" prop="birth">
        <el-date-picker v-model="detailData.birth" type="date" placeholder="请选择日期" format="YYYY-MM-DD" style="width: 100%" :disabled-date="disabledDate" />
      </el-form-item>
      <el-form-item label="所在地" prop="address" class="address">
        <el-cascader v-model="detailData.address" :options="addressOptions" :props="addressProps" @change="addressChange" placeholder="请选择所在地" clearable show-all-levels />
      </el-form-item>
      <el-form-item label="个人简介" prop="introduction">
        <el-input type="textarea" :rows="3" v-model="detailData.introduction" autocomplete="off" />
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
import { editUser, addUser } from '@/api/modules/user';
// 导入地区数据
import { addressToTree } from '@/assets/ts/address';
import { clearForm, clearEmptyProperty } from '@/mixins';

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
let emit = defineEmits(['update:dialogVisible']); // 如果用的setup函数则是用 cotext.emit 去使用
let detailVisible = ref(false);

const addressProps = {
  value: 'label',
  label: 'label',
  children: 'children',
};
const addressOptions = addressToTree(); // 地区数据

// 表单数据类型
interface RuleForm {
  id: string | number;
  nickname: string;
  username: string;
  password: string;
  confirmPwd: string;
  sex: string;
  phone: string;
  email: string;
  github: string;
  csdn: string;
  qq: string;
  wechat: string;
  birth: string;
  address: string[] | string;
  introduction: string;
}
const ruleFormRef: any = ref<FormInstance>(); // 表单实例
// 表单数据
let detailData = reactive<RuleForm>({
  id: '',
  nickname: '',
  username: '',
  password: '',
  confirmPwd: '',
  sex: '',
  phone: '',
  email: '',
  github: '',
  csdn: '',
  qq: '',
  wechat: '',
  birth: '',
  address: [],
  introduction: '',
});
const rules = reactive<FormRules<RuleForm>>({
  // 表单验证规则
  nickname: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  ],
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },
    { pattern: /^\S*$/, message: '密码不能包含空格', trigger: 'blur' },
  ],
  confirmPwd: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },
    { pattern: /^\S*$/, message: '密码不能包含空格', trigger: 'blur' },
    {
      validator: (_rule, value, callback) => {
        if (value === detailData.password) {
          callback();
        } else {
          callback(new Error('两次输入密码不一致'));
        }
      },
      trigger: 'blur',
    },
  ],
  sex: [{ required: true, message: '请选择性别', trigger: 'blur' }],
  phone: [{ pattern: /^1[3456789]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { pattern: /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/, message: '邮箱格式不正确', trigger: 'blur' },
  ],
  birth: [{ required: true, message: '请选择生日', trigger: 'change' }],
  introduction: [
    { required: true, message: '请输入个人简介', trigger: 'blur' },
    { min: 2, max: 200, message: '长度在 2 到 200 个字符', trigger: 'blur' },
  ],
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
  console.log(val, 'val1111111111-----');
  emit('update:dialogVisible', val);
  console.log(props.dialogVisible, 'val2222222222222-----');
  if (val) {
    if (detailData.address) {
      if (typeof detailData.address === 'string') {
        detailData.address = detailData.address.split('-').map((item: string) => item.trim());
      }
    }
    if (!detailData.sex) {
      detailData.sex = '';
    }
  }
  if (props.dialogTitle === '新增' && !val) {
    // 清空表单数据以及验证
    ruleFormRef.value.clearValidate();
    clearForm(detailData);
  }
});

// 禁用未来的时间
const disabledDate = (time: Date) => {
  return time.getTime() > Date.now();
};

// 地区选择
let addressStr = ref('');
const addressChange = (val: any) => {
  addressStr.value = val.join('-');
};

const cancel = () => {
  detailVisible.value = false;
  // 清空表单验证
  ruleFormRef.value.clearValidate();
};

const confirm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      ElMessageBox.confirm(props.dialogTitle === '编辑' ? '此操作将修改此信息, 是否确定修改?' : '是否确定新增?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '加载中...';
            setTimeout(async () => {
              done();
              instance.confirmButtonLoading = false;
            }, 300);
          } else {
            done();
          }
        },
      })
        .then(async () => {
          if (props.dialogTitle === '编辑') {
            interface paramsType {
              [key: string]: string | number | string[] | undefined;
            }
            let params: paramsType = detailData;
            params.address = addressStr.value;
            params = clearEmptyProperty(params);
            const { code } = await editUser(params);
            if (code === 200) {
              ElNotification({
                type: 'success',
                message: '修改成功!',
                duration: 1000,
                showClose: true,
              });
              detailVisible.value = false;
            } else {
              ElNotification({
                type: 'error',
                message: '修改失败!',
                duration: 1000,
                showClose: true,
              });
            }
          } else if (props.dialogTitle === '新增') {
            interface paramsType {
              [key: string]: string | number | string[] | undefined;
            }
            let params: paramsType = detailData;
            params.address = addressStr.value;
            params.password = detailData.confirmPwd;
            Object.keys(params).forEach(key => {
              if (key === 'confirmPwd') {
                delete params[key];
              }
            });
            params = clearEmptyProperty(params);
            const { code, msg } = await addUser(params);
            if (code === 200) {
              ElNotification({
                type: 'success',
                message: '新增成功!',
                duration: 1000,
                showClose: true,
              });
              detailVisible.value = false;
            } else if (code === 400 && msg === '用户昵称已存在！') {
              ElNotification({
                message: msg,
                type: 'warning',
                duration: 1000,
                showClose: true,
              });
              // 聚焦到昵称输入框
              ruleFormRef.value.validateField('nickname');
            } else {
              ElNotification({
                type: 'error',
                message: '新增失败!',
                duration: 1000,
                showClose: true,
              });
            }
          }
        })
        .catch(() => {
          props.dialogTitle === '编辑'
            ? ElNotification({
                type: 'info',
                message: '已取消修改',
                duration: 1000,
                showClose: true,
              })
            : ElNotification({
                type: 'info',
                message: '已取消新增',
                duration: 1000,
                showClose: true,
              });
        });
    } else {
      console.log('验证失败!', fields);
    }
  });
};
</script>

<style lang="scss" scoped>
.address {
  :deep(.el-cascader) {
    width: 100%;
  }
}
</style>
