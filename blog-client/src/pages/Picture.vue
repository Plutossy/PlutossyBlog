<template>
  <div class="picture-container">
    <!--顶部背景信息-->
    <div class="top-img animate__animated animate__fadeInDown">
      <div>照片墙</div>
      <div>往事情怀酿作酒，还我余生长醉不复忧</div>
    </div>

    <!--照片墙-->
    <div class="content">
      <Lightgallery
        :settings="{ speed: 500, plugins: plugins }"
        :onInit="onInit"
        :onBeforeSlide="onBeforeSlide"
      >
        <a
          v-for="item in pictures"
          :key="item.id"
          class="gallery-item"
          :data-src="item.src"
          data-lg-size="1400-933"
          :data-sub-html="item.subHtml"
        >
          <img class="img-responsive" :src="item.thumb" />
        </a>
      </Lightgallery>
    </div>
  </div>
</template>

<script>
import Lightgallery from 'lightgallery/vue'
import lgThumbnail from 'lightgallery/plugins/thumbnail'
import lgZoom from 'lightgallery/plugins/zoom'

import 'lightgallery/scss/lightgallery.scss'
import 'lightgallery/scss/lg-thumbnail.scss'
import 'lightgallery/scss/lg-zoom.scss'

export default {
  components: {
    Lightgallery
  },
  data: () => ({
    plugins: [lgThumbnail, lgZoom],
    pictures: [
      {
        id: '1',
        src: 'https://images.unsplash.com/photo-1542103749-8ef59b94f47e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1542103749-8ef59b94f47e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Name by <a href="https://unsplash.com/@dann">Pluto_ssy</a></h4>
                <p>Published on November 13, 2018</p>
                <p>Description</p>
            </div>`
      },
      {
        id: '2',
        src: 'https://images.unsplash.com/photo-1473876988266-ca0860a443b8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1473876988266-ca0860a443b8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@kylepyt">Kyle Peyton</a></h4>
                <p>Published on September 14, 2016</p>
            </div>`
      },
      {
        id: '3',
        src: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@jxnsartstudio">Garrett Jackson</a></h4>
                <p>Published on May 8, 2020</p>
            </div>`
      },
      {
        id: '4',
        src: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@jxnsartstudio">Garrett Jackson</a></h4>
                <p>Published on May 8, 2020</p>
            </div>`
      },
      {
        id: '5',
        src: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@jxnsartstudio">Garrett Jackson</a></h4>
                <p>Published on May 8, 2020</p>
            </div>`
      },
      {
        id: '6',
        src: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@jxnsartstudio">Garrett Jackson</a></h4>
                <p>Published on May 8, 2020</p>
            </div>`
      },
      {
        id: '7',
        src: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@jxnsartstudio">Garrett Jackson</a></h4>
                <p>Published on May 8, 2020</p>
            </div>`
      },
      {
        id: '8',
        src: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1400&q=80',
        thumb: 'https://images.unsplash.com/photo-1588953936179-d2a4734c5490?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=240&q=80',
        subHtml: `<div class="lightGallery-captions">
                <h4>Photo by <a href="https://unsplash.com/@jxnsartstudio">Garrett Jackson</a></h4>
                <p>Published on May 8, 2020</p>
            </div>`
      }
    ]
  }),
  methods: {
    onInit: () => {
      console.log('lightGallery has been initialized')
    },
    onBeforeSlide: () => {
      console.log('calling before slide')
    }
  }
}
</script>

<style lang="scss" scoped>
.picture-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  .top-img {
    width: 100%;
    background: url(@/assets/img/bg2.png) no-repeat center center;
    background-size: cover;

    font-family: 'STXingkai', serif;
    color: #ffffff;
    // background-color: rgba(0, 0, 0, 0.6);
    margin-top: -5rem;
    padding: 7rem 0 2rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;
    &::before {
      // 设置遮罩层
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(0, 0, 0, 0.5);
      z-index: -1; /* 设置伪元素的层级为负数，保证它在元素后面 */
    }
    div:first-child {
      font-size: 4rem;
      font-weight: 560;
      margin-bottom: 2rem;
    }
    div:last-child {
      font-size: 1.8rem;
      font-weight: 560;
      margin-bottom: 2rem;
    }
  }

  .content {
    position: relative;
    z-index: 1;
    // font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
    background-color: rgba(255, 255, 255, 0.7);
    width: 90%;
    margin-top: -2rem;
    margin-bottom: 2rem;
    padding-top: 3rem;
    border-radius: 1rem;
    box-shadow: 0 1px 2px 0 rgba(34, 36, 38, 0.6);
    .lightgallery-vue {
      display: flex;
      display: grid;
      flex-wrap: wrap;
      justify-content: space-around;
      grid-template-columns: repeat(auto-fill, 22rem);
      grid-gap: 1rem;
      .gallery-item {
        width: 22rem;
        margin-bottom: 2rem;
        .img-responsive {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }
    }
  }
}
</style>