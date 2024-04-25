/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-04-18 17:50:12
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-04-25 18:32:46
 * @FilePath: \blog-manage\src\store\modules\table.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export default {
  namespaced: true,
  state: {
    contentHeight: 0,
    searchHeight: 0,
    tableHeight: 568,
    footerHeight: 0,
  },
  getters: {
    tableHeight: (state: any) => state.tableHeight,
  },
  mutations: {
    setTableHeight(state: any) {
      state.tableHeight = state.contentHeight - state.searchHeight - state.footerHeight - 20;
    },
    setContentHeight(state: any, height: number) {
      state.contentHeight = height;
    },
    setSearchHeight(state: any, height: number) {
      state.searchHeight = height;
    },
    setTableHeaderHeight(state: any, height: number) {
      state.tableHeaderHeight = height;
    },
    setFooterHeight(state: any, height: number) {
      state.footerHeight = height;
    },
  },
};
