<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-02-05 09:43:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-02-29 10:31:36
 * @FilePath: \blog-manage\src\components\MyPage.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-config-provider :locale="zhCn">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 50, 100]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </el-config-provider>
</template>

<script setup lang="ts">
import { ElConfigProvider } from 'element-plus';

import zhCn from 'element-plus/dist/locale/zh-cn.mjs';

// 修改分页器默认文字
zhCn.el.pagination.total = '共 ' + `{total}` + ' 条';
zhCn.el.pagination.goto = '跳转至';

const props = defineProps({
  currentPage: {
    type: Number,
    default: 1,
  },
  pageSize: {
    type: Number,
    default: 10,
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
  total: {
    type: Number,
    default: 0,
  },
});
let currentPage = ref(props.currentPage);
let pageSize = ref(props.pageSize);
let small = ref(props.small);
let background = ref(props.background);
let disabled = ref(props.disabled);
let total = ref(props.total);

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`);
};
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`);
};
</script>

<style lang="scss" scoped></style>
