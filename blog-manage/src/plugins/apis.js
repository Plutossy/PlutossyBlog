import apis from '@/api';
const apiPlugin = {
  install: function (app) {
    app.config.globalProperties.$apis = apis;
  }
}
export default apiPlugin;
