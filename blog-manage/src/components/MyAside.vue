<template>
  <div class="sidebar switch-item">
    <el-menu class="sider-menu" :collapse="collapse" background-color="rgba(0, 0, 0, 0)" text-color="rgba(255, 255, 255, 0.9)" active-text-color="#ffffff" router>
      <el-menu-item v-for="item in routeLinks" :key="item.index" :index="item.path" @click="routeHandler" :style="{ 'background-color': routeActive.includes(item.path) ? '#f2711c' : '' }" @mouseenter="setTipBgc">
        <el-icon size="larger" class="switch-item-color-borderColor">
          <component :is="item.icon"></component>
        </el-icon>
        <template #title>
          <span class="switch-item-color-borderColor">{{ item.title }}</span>
        </template>
      </el-menu-item>
    </el-menu>
    <el-switch v-model="switchLight" size="large" class="switch-light cancel-bgc" style="--el-switch-on-color: #000000; --el-switch-off-color: #ffffff" :active-action-icon="View" :inactive-action-icon="View" @click="switchHandler" />
  </div>
</template>

<script lang="ts" setup>
import eventBus from '@/assets/js/eventBus';
import { View } from '@element-plus/icons-vue';

const emitter = eventBus();
// let onRoutes = ref('/index') // 默认路由
let routeActive = ref('/index'); // 点击后的路由
let collapse = ref(false);
let switchLight = ref(false);
const routeLinks = reactive([
  {
    index: '1',
    title: '首页',
    path: '/info',
    icon: 'home-filled',
  },
  {
    index: '2',
    title: '博客管理',
    path: '/blog',
    icon: 'document',
  },
  {
    index: '3',
    title: '分类管理',
    path: '/type',
    icon: 'crop',
  },
  {
    index: '4',
    title: '标签管理',
    path: '/tag',
    icon: 'price-tag',
  },
  {
    index: '5',
    title: '音乐管理',
    path: '/music',
    icon: 'headset',
  },
  {
    index: '6',
    title: '照片管理',
    path: '/picture',
    icon: 'picture',
  },
  {
    index: '7',
    title: '用户管理',
    path: '/user',
    icon: 'user',
  },
]);

onMounted(() => {
  // 通过eventbus监听折叠事件
  emitter.on('collapse', (val: boolean) => {
    collapse.value = val;
  });
  // 处理刷新页面后的路由栏背景目不对应的问题
  const path = window.location.pathname;
  console.log(`path--${path}`);
  if (path !== '/index') {
    // onRoutes.value = path
    routeActive.value = path;
  }

  // 处理replace返回后路由栏背景目不对应的问题
  if (window.history) {
    window.addEventListener('popstate', () => {
      const path = window.location.pathname;
      // console.log(`path--${path}`);
      if (path !== '/index') {
        // onRoutes.value = path
        routeActive.value = path;
      }
    });
  }
});

// 点击添加背景色
const routeHandler = (e: { index: string }) => {
  routeActive.value = e.index;
};

// 点击切换主题
const switchHandler = () => {
  const switchIcon: any = document.querySelector('.el-switch__action');
  const html: any = document.documentElement;
  html.dataset.theme = switchLight.value ? 'light' : 'dark';
  if (switchLight.value) {
    switchIcon.classList.add('el-switch__action__light');
  } else {
    switchIcon.classList.remove('el-switch__action__light');
  }
};

const setTipBgc = () => {
  if (collapse.value && switchLight.value) {
    const popperLightAll: any = document.querySelectorAll('.el-popper.is-dark');
    popperLightAll.forEach((item: any) => {
      item.style.backgroundColor = '#ffffff';
    });
  }
  if (collapse.value && !switchLight.value) {
    const popperDarkAll: any = document.querySelectorAll('.el-popper.is-dark');
    popperDarkAll.forEach((item: any) => {
      item.style.backgroundColor = '#464646';
    });
  }
};

onUnmounted(() => {
  emitter.off('collapse');
  if (window.history) {
    window.removeEventListener('popstate', () => {});
  }
});
</script>

<style lang="scss" scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  background-color: #464646;
  color: #fff;
  overflow: auto !important;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}

.sider-menu:not(.el-menu--collapse) {
  width: 150px;
}

.sidebar > ul {
  height: 85%;
  margin: 10px 0;
  display: flex;
  flex-direction: column;

  > li {
    font-size: medium;
    border-radius: 8px;
    margin-bottom: 10px;

    &:hover {
      background-color: #f7873c;
    }
  }
}

.switch-light {
  position: absolute;
  bottom: 16px;
  left: 50%;
  transform: translateX(-50%);
}

:deep(.el-switch--large) .el-switch__core .el-switch__action {
  background: #464646;
}

:deep(.el-switch--large) .el-switch__core .el-switch__action__light {
  background: #ffffff;
}
</style>
