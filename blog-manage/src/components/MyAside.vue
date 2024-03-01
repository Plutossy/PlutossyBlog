<template>
  <div class="sidebar">
    <el-menu class="sider-menu" :collapse="collapse" background-color="rgba(0, 0, 0, 0)" text-color="rgba(255, 255, 255, 0.9)" active-text-color="#ffffff" router>
      <el-menu-item v-for="item in routeLinks" :key="item.index" :index="item.path" @click="routeHandler" :style="{ 'background-color': routeActive.includes(item.path) ? '#f2711c' : '' }">
        <el-icon size="larger">
          <component :is="item.icon"></component>
        </el-icon>
        <template #title>{{ item.title }}</template>
      </el-menu-item>
    </el-menu>
    <el-switch v-model="switchLight" size="large" class="switch-light" style="--el-switch-on-color: #000000; --el-switch-off-color: #ffffff" :active-action-icon="View" :inactive-action-icon="View" @click="switchHandler" />
  </div>
</template>

<script lang="ts" setup>
import eventBus from '../assets/js/eventBus';
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
    title: '评论管理',
    path: '/comment',
    icon: 'comment',
  },
  {
    index: '4',
    title: '分类管理',
    path: '/type',
    icon: 'crop',
  },
  {
    index: '5',
    title: '标签管理',
    path: '/tag',
    icon: 'price-tag',
  },
  {
    index: '6',
    title: '音乐管理',
    path: '/music',
    icon: 'headset',
  },
  {
    index: '7',
    title: '照片管理',
    path: '/picture',
    icon: 'picture',
  },
  {
    index: '8',
    title: '用户管理',
    path: '/user',
    icon: 'user',
  },
]);

onMounted(() => {
  // 通过eventbus监听折叠事件
  emitter.on('collapse', val => {
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
  if (window.history && window.history.pushState) {
    window.addEventListener('popstate', () => {
      const path = window.location.pathname;
      console.log(`path--${path}`);
      if (path !== '/index') {
        // onRoutes.value = path
        routeActive.value = path;
      }
    });
  }
});

// 点击添加背景色
const routeHandler = e => {
  routeActive.value = e.index;
};

// 点击切换主题
const switchHandler = () => {
  const switchIcon: any = document.querySelector('.el-switch__action');

  // const headerElement: any = document.querySelector('.header-container')
  const sidebarElement: any = document.querySelector('.sidebar');
  const sidebarLis: any = document.querySelectorAll('.sidebar li');

  if (switchLight.value) {
    switchIcon.classList.add('el-switch__action__light');

    // headerElement.classList.add('light')
    sidebarElement.classList.add('light');
    sidebarLis.forEach((item: any) => {
      item.style.color = '#000000';
    });
  } else {
    switchIcon.classList.remove('el-switch__action__light');

    sidebarElement.classList.remove('light');
    sidebarLis.forEach((item: any) => {
      item.style.color = '#ffffff';
    });
  }
};

onUnmounted(() => {
  emitter.off('collapse');
  if (window.history && window.history.pushState) {
    window.removeEventListener('popstate', () => {});
  }
});
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  overflow: scroll;
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
  justify-content: space-between;

  > li {
    font-size: medium;
    border-radius: 8px;

    &:hover {
      background-color: #f2711c;
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
  background: #000000;
}

:deep(.el-switch--large) .el-switch__core .el-switch__action__light {
  background: #ffffff;
}
</style>
