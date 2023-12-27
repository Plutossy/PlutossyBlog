export const mixin = {
  methods: {
    // 提示信息
    notify (message, type) {
      this.$notify({
        title: message,
        type: type
      })
    },
  }
}
