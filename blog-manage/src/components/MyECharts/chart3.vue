<template>
  <div ref="chartDom3" class="chart3-container"></div>
</template>

<script setup lang="ts">
import * as echarts from 'echarts/core';
import { TooltipComponent, TooltipComponentOption } from 'echarts/components';
import { GaugeChart, GaugeSeriesOption } from 'echarts/charts';
import { SVGRenderer } from 'echarts/renderers';

echarts.use([TooltipComponent, GaugeChart, SVGRenderer]);

type EChartsOption = echarts.ComposeOption<TooltipComponentOption | GaugeSeriesOption>;

const props = defineProps({
  title: {
    type: String,
    default: '照片数量',
    required: true,
  },
  data: {
    type: Object,
    default: { value: 50, name: '照片' },
    required: true,
  },
  color: {
    type: String,
    default: 'rgb(84, 112, 198)',
  },
});

const title = ref(props.title);
const value = ref(props.data.value);
const name = ref(props.data.name);
const color = ref(props.color);

let chartDom3 = ref<HTMLElement | null>(null);

let option: EChartsOption = {
  title: {
    text: title.value,
    textStyle: {
      fontFamily: 'STXingkai',
      fontSize: 24,
    },
  },
  tooltip: {
    formatter: '<center>{a}</center>' + '<span style="display:inline-block;margin-right:5px;border-radius:50%;width:10px;height:10px;left:5px;background-color:' + color.value + '"></span>' + '数量（个）：{c}',
  },
  series: [
    {
      name: name.value,
      type: 'gauge',
      color: color.value, // 图形的颜色
      progress: {
        show: true,
      },
      detail: {
        valueAnimation: true,
        formatter: '{value}',
      },
      data: [
        {
          value: value.value,
          name: name.value,
        },
      ],
    },
  ],
};

onMounted(() => {
  let myChart = echarts.init(chartDom3.value, null, {
    renderer: 'svg',
  });

  // 监听窗口大小变化
  window.addEventListener('resize', () => {
    myChart.resize();
  });

  option && myChart.setOption(option);
});

onUnmounted(() => {
  window.removeEventListener('resize', () => {});
  chartDom3.value = null;
});
</script>

<style lang="scss" scoped>
.chart3-container {
  width: 100%;
  height: 100%;
  margin-top: 10px;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 5px;
  box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.2);
}
</style>
