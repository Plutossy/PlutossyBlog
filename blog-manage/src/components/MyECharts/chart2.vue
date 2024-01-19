<template>
  <div ref="chartDom2" class="chart2-container"></div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts/core';
import {
  GridComponent,
  GridComponentOption,
  GraphicComponent,
  GraphicComponentOption
} from 'echarts/components';
import { BarChart, BarSeriesOption } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { SVGRenderer } from 'echarts/renderers';

echarts.use([
  GridComponent,
  GraphicComponent,
  BarChart,
  SVGRenderer,
  UniversalTransition
]);

type EChartsOption = echarts.ComposeOption<
  GridComponentOption | GraphicComponentOption | BarSeriesOption
>;

interface DataItem {
  value: number;
  groupId: string;
}

const chartDom2 = ref<HTMLElement | null>(null);

let option: EChartsOption = {
  title: {
    text: '博客分类',
  },
  tooltip: { // 提示框组件
    trigger: 'axis', // 触发类型。可选：'item' | 'axis' | 'none'
    axisPointer: { // 坐标轴指示器配置项
      type: 'line' // 指示器类型。可选：'line' | 'shadow' | 'none' | 'cross'
    },
    formatter: (params) => {
      let str = '<center>' + params[0].axisValue + '</center>'
      params.forEach(item => {
        if (item.seriesName === 'type') {
          str +=
            '<span style="display:inline-block;margin-right:5px;border-radius:50%;width:10px;height:10px;left:5px;background-color:' +
            item.color + '"></span>' + '数量（篇）：' +
            item.value + '<br />'
        } else {
          str +=
            '<span style="display:inline-block;margin-right:5px;width:10px;height:10px;left:5px;background-color:' +
            item.color + '"></span>' + '数量（篇）：' +
            item.value + '<br />'
        }
      })
      return str
    },
  },
  xAxis: {
    type: 'category',
    data: ['分类1', '分类2', '分类3', '分类4', '分类5']
  },
  yAxis: {
    name: '数量（篇）', // y轴名称
    splitLine: { // y轴分割线设置
      show: true,
      lineStyle: {
        type: 'dashed', // y轴分割线虚线类型
        color: '#999'
      }
    },
  },
  dataGroupId: '', // 数据分组的id
  animationDurationUpdate: 500, // 数据更新动画的时长
  series: {
    name: 'type', // 系列名称
    type: 'bar',
    id: 'sales',
    color: '#00b5ad', // 柱状图颜色
    data: [
      {
        value: 5,
        groupId: 'type1'
      },
      {
        value: 2,
        groupId: 'type2'
      },
      {
        value: 4,
        groupId: 'type3'
      },
      {
        value: 5,
        groupId: 'type4'
      },
      {
        value: 8,
        groupId: 'type5'
      }
    ] as DataItem[],
    universalTransition: {
      enabled: true,
      divideShape: 'clone'
    }
  },
  graphic: [
    { // 图形元素组件，提供了在任意位置绘制图形的能力，例如图片、文字等。
      type: 'text',
      style: {
        text: '',
      },
    },
    { // 图形元素组件，提供了在任意位置绘制图形的能力，例如图片、文字等。
      type: 'text',
      style: {
        text: '',
      },
    }
  ]

};

const drilldownData = [
  {
    dataGroupId: 'type1',
    data: [
      ['标签1', 4],
      ['标签2', 2],
      ['标签3', 1],
      ['标签4', 2],
      ['标签5', 1]
    ]
  },
  {
    dataGroupId: 'type2',
    data: [
      ['标签2', 4],
      ['标签5', 2]
    ]
  },
  {
    dataGroupId: 'type3',
    data: [
      ['标签6', 4],
      ['标签1', 2],
      ['标签4', 2]
    ]
  },
  {
    dataGroupId: 'type4',
    data: [
      ['标签2', 4],
      ['标签1', 8],
      ['标签8', 2]
    ]
  },
  {
    dataGroupId: 'type5',
    data: [
      ['标签2', 9],
      ['标签10', 12],
      ['标签1', 1]
    ]
  }
];

onMounted(() => {
  let myChart = echarts.init(chartDom2.value, null, {
    renderer: 'svg'
  });

  myChart.on('click', function (event) {
    if (event.data) {
      var subData = drilldownData.find(function (data) {
        return data.dataGroupId === (event.data as DataItem).groupId;
      });
      if (!subData) {
        return;
      }
      myChart.setOption<EChartsOption>({
        title: {
          text: '博客标签',
        },
        xAxis: {
          data: subData.data.map(function (item) {
            return item[0];
          })
        },
        series: {
          name: 'tag',
          type: 'bar',
          id: 'sales',
          color: '#2185d0', // 柱状图颜色
          dataGroupId: subData.dataGroupId,
          data: subData.data.map(function (item) {
            return item[1];
          }),
          universalTransition: {
            enabled: true,
            divideShape: 'clone'
          }
        },
        graphic: [
          {
            type: 'text', // 图形元素类型, 可选值: 'image' | 'text' | 'circle' | 'sector' | 'ring' | 'polygon' | 'polyline' | 'rect' | 'line' | 'bezierCurve' | 'arc' | 'group'
            left: '90',
            top: '1%',
            style: {
              text: '|',
              fontSize: 20,
              fill: '#bbbbbb',
            },
          },
          {
            type: 'text',
            left: '108',
            top: '1.5%',
            style: {
              text: '返回',
              fontSize: 18,
              fill: '#4183c4', // 浅蓝色
            },
            onclick: function () {
              myChart.setOption<EChartsOption>(option);
            }
          }
        ]
      });
    }
  });

  // 监听窗口大小变化
  window.addEventListener('resize', () => {
    myChart.resize();
  });

  option && myChart.setOption(option);
});

onUnmounted(() => {
  window.removeEventListener('resize', () => { });
});
</script>

<style lang="scss" scoped>
.chart2-container {
  width: 100%;
  height: 50%;
  margin-top: 10px;
}
</style>