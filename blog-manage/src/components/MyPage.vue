<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-02-05 09:43:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-18 18:52:09
 * @FilePath: \blog-manage\src\components\MyPage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-config-provider :locale="zhCn">
    <el-pagination
      v-model:current-page="queryParam.pageNum"
      v-model:page-size="queryParam.pageSize"
      :page-sizes="[10, 20, 50, 100]"
      :small="small"
      :disabled="props.disabled"
      :background="background"
      :layout="layout"
      :total="props.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </el-config-provider>
</template>

<script setup lang="ts">
import store from '@/store/store';
import { ElConfigProvider } from 'element-plus';

import zhCn from 'element-plus/dist/locale/zh-cn.mjs';

// 修改分页器默认文字
zhCn.el.pagination.total = '共 ' + `{total}` + ' 条';
zhCn.el.pagination.goto = '跳转至';

const props = defineProps({
  queryParam: {
    type: Object,
    default: () => ({
      pageNum: 1,
      pageSize: 10,
    }),
  },
  small: {
    type: Boolean,
    default: false,
  },
  background: {
    type: Boolean,
    default: false,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  layout: {
    type: String,
    default: 'total, sizes, prev, pager, next, jumper',
  },
  total: {
    type: Number,
    default: 0,
  },
});
let queryParam = reactive(props.queryParam);
let small = ref(props.small);
let background = ref(props.background);
let layout = ref(props.layout);
let total = ref(props.total);

let emit = defineEmits(['update:queryParam', 'tempParams']);
const instance: any = getCurrentInstance();

nextTick(() => {
  const footerElement: HTMLElement | null = document.querySelector('.el-pagination');
  store.commit('table/setFooterHeight', footerElement?.clientHeight);

  window.addEventListener('resize', () => {
    store.commit('table/setFooterHeight', footerElement?.clientHeight);
  });
});

onUnmounted(() => {
  window.removeEventListener('resize', () => {});
});

// 发送参数
const sendParams = () => {
  const tempParams = instance.appContext.config.globalProperties.$deepClone(queryParam);
  emit('update:queryParam', tempParams);
  emit('tempParams', queryParam);
};

const handleSizeChange = () => {
  console.log(total.value);
  sendParams();
};

const handleCurrentChange = () => {
  sendParams();
};
</script>

<style lang="scss" scoped>
.el-pagination {
  margin-top: 16px;
  margin-right: 20px;
  float: right;
}
</style>
