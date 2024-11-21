<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-01-10 14:16:56
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-11-21 10:29:34
 * @FilePath: \blog-client\src\components\blogDetail\blogDetail.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="article-container">
    <el-row align="middle" class="animate__animated animate__pulse">
      <div class="flag">
        <div class="flag-ss" style="border-color: transparent transparent #f2711c #f2711c"></div>
        <div class="flag-tt" style="background-color: #f2711c">原创</div>
        <div class="flag-xs" style="border-color: #f2711c transparent transparent #f2711c"></div>
      </div>
      <div>
        <a href="/about">Pluto_ssy</a>
      </div>
      <div>
        <el-icon>
          <i-ep-Calendar />
        </el-icon>
        <p>2023-11-29</p>
      </div>
      <div>
        <el-icon>
          <i-ep-View />
        </el-icon>
        <p>233</p>
      </div>
      <div class="type">
        <p>分类专栏：</p>
        <div>分类1</div>
      </div>
      <div class="tag">
        <p>文章标签：</p>
        <div>标签1</div>
        <div>标签2</div>
        <div>标签3</div>
      </div>
    </el-row>
    <el-row align="middle" class="animate__animated animate__zoomIn">
      <img src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" />
    </el-row>
    <el-row align="middle">
      <h1>标题</h1>
    </el-row>
    <el-row>
      <div ref="articleRef" class="article-content">
        <!--/*-->
        <h2 id="section1">一、关于 <i class="serif">Typo.css</i></h2>

        <p><i class="serif">Typo.css</i> 的目的是，在一致化浏览器排版效果的同时，构建最适合中文阅读的网页排版。</p>
        <h4>现状和如何去做：</h4>

        <p class="typo-first">
          排版是一个麻烦的问题 <sup><a href="#appendix1"># 附录一</a></sup
          >，需要精心设计，而这个设计却是常被视觉设计师所忽略的。前端工程师更常看到这样的问题，但不便变更。因为在多个 OS 中的不同浏览器渲染不同，改动需要多的时间做回归测试，所以改变变得更困难。而像我们一般使用的 Yahoo、Eric Meyer 和 Alice base.css 中采用的 Reset 都没有很好地考虑中文排版。<i
            class="serif"
            >Typo.css</i
          >
          要做的就是解决中文排版的问题。
        </p>

        <p>
          <strong><i class="serif">Typo.css</i> 测试于如下平台：</strong>
        </p>
        <table class="ui table" summary="Typo.css 的测试平台列表">
          <thead>
            <tr>
              <th>OS/浏览器</th>
              <th>Firefox</th>
              <th>Chrome</th>
              <th>Safari</th>
              <th>Opera</th>
              <th>IE9</th>
              <th>IE8</th>
              <th>IE7</th>
              <th>IE6</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>OS X</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>-</td>
              <td>-</td>
              <td>-</td>
              <td>-</td>
            </tr>
            <tr>
              <td>Win 7</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>-</td>
            </tr>
            <tr>
              <td>Win XP</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
              <td>-</td>
              <td>✔</td>
              <td>✔</td>
              <td>✔</td>
            </tr>
            <tr>
              <td>Ubuntu</td>
              <td>✔</td>
              <td>✔</td>
              <td>-</td>
              <td>✔</td>
              <td>-</td>
              <td>-</td>
              <td>-</td>
              <td>-</td>
            </tr>
          </tbody>
        </table>

        <h4>中文排版的重点和难点</h4>

        <p>
          在中文排版中，HTML4 的很多标准在语义在都有照顾到。但从视觉效果上，却很难利用单独的 CSS 来实现，像<abbr title="在文字下多加一个点">着重号</abbr>（例：这里<em class="typo-em">强调一下</em>）。在 HTML4 中，专名号标签（<code>&lt;u&gt;</code>）已经被放弃，而 HTML5 被<a
            href="//html5doctor.com/u-element/"
            >重新提起</a
          >。<i class="serif">Typo.css</i> 也根据实际情况提供相应的方案。我们重要要注意的两点是：
        </p>
        <ol>
          <li>语义：语义对应的用法和样式是否与中文排版一致</li>
          <li>表现：在各浏览器中的字体、大小和缩放是否如排版预期</li>
        </ol>
        <p>对于这些，<i class="serif">Typo.css</i> 排版项目的中文偏重注意点，都添加在附录中，详见：</p>
        <blockquote>
          <b>附录一</b>：<a href="#appendix1"><i class="serif">Typo.css</i> 排版偏重点</a>
        </blockquote>

        <p>
          目前已有像百姓网等全面使用 <i class="serif">Typo.css</i> 的项目，测试平台的覆盖，特别是在<abbr title="手机、平板电脑等移动平台">移动端</abbr>上还没有覆盖完主流平台，希望有能力的同学能加入测试行列，或者加入到 <i class="serif">Typo.css</i> 的开发。加入方法：<a
            href="https://github.com/sofish/Typo.css"
            >参与 <i class="serif">Typo.css</i> 开发</a
          >。如有批评、建议和意见，也随时欢迎给在 Github 直接提 <a href="https://github.com/sofish/Typo.css/issues">issues</a>，或给<abbr title="Sofish Lin, author of Typo.css" role="author">我</abbr>发<a href="mailto:sofish@icloud.com">邮件</a>。
        </p>

        <h2 id="section2">二、排版实例：</h2>

        <p>提供2个排版实例，第一个中文实例来自于来自于<cite class="typo-unique">张燕婴</cite>的《论语》，由于古文排版涉及到的元素比较多，所以采用《论语》中《学而》的第一篇作为排版实例介绍；第2个来自到经典的 Lorem Ipsum，并加入了一些代码和列表等比较具有代表性的排版元素。</p>

        <h3 id="section2-1">例1：论语学而篇第一</h3>

        <p>
          <small
            ><b>作者：</b><abbr title="名丘，字仲尼">孔子</abbr>（
            <time>前551年9月28日－前479年4月11日</time>
            ）
          </small>
        </p>

        <h4>本篇引语</h4>

        <p>《学而》是《论语》第一篇的篇名。《论语》中各篇一般都是以第一章的前二三个字作为该篇的篇名。《学而》一篇包括16章，内容涉及诸多方面。其中重点是「吾日三省吾身」；「节用而爱人，使民以时」；「礼之用，和为贵」以及仁、孝、信等道德范畴。</p>

        <h4>原文</h4>

        <p>子曰：「学而时习之，不亦说乎？有朋自远方来，不亦乐乎？人不知，而不愠，不亦君子乎？」</p>

        <h4>译文</h4>

        <p>孔子说：「学了又时常温习和练习，不是很愉快吗？有志同道合的人从远方来，不是很令人高兴的吗？人家不了解我，我也不怨恨、恼怒，不也是一个有德的君子吗？」</p>

        <h4>评析</h4>

        <p>
          宋代著名学者<u class="typo-u">朱熹</u
          >对此章评价极高，说它是「入道之门，积德之基」。本章这三句话是人们非常熟悉的。历来的解释都是：学了以后，又时常温习和练习，不也高兴吗等等。三句话，一句一个意思，前后句子也没有什么连贯性。但也有人认为这样解释不符合原义，指出这里的「学」不是指学习，而是指学说或主张；「时」不能解为时常，而是时代或社会的意思，「习」不是温习，而是使用，引申为采用。而且，这三句话不是孤立的，而是前后相互连贯的。这三句的意思是：自己的学说，要是被社会采用了，那就太高兴了；退一步说，要是没有被社会所采用，可是很多朋友赞同<abbr
            title="张燕婴"
            >我</abbr
          >的学说，纷纷到我这里来讨论问题，我也感到快乐；再退一步说，即使社会不采用，人们也不理解我，我也不怨恨，这样做，不也就是君子吗？（见《齐鲁学刊》1986年第6期文）这种解释可以自圆其说，而且也有一定的道理，供读者在理解本章内容时参考。
        </p>

        <p>
          此外，在对「人不知，而不愠」一句的解释中，也有人认为，「人不知」的后面没有宾语，人家不知道什么呢？当时因为孔子有说话的特定环境，他不需要说出知道什么，别人就可以理解了，却给后人留下一个谜。有人说，这一句是接上一句说的，从远方来的朋友向我求教，我告诉他，他还不懂，我却不怨恨。这样，「人不知」就是「人家不知道我所讲述的」了。这样的解释似乎有些牵强。
        </p>

        <p>总之，本章提出以学习为乐事，做到人不知而不愠，反映出孔子学而不厌、诲人不倦、注重修养、严格要求自己的主张。这些思想主张在《论语》书中多处可见，有助于对第一章内容的深入了解。</p>

        <h3 id="section2-2">例2：英文排版</h3>

        <p>
          Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but
          also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
        </p>
        <blockquote>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</blockquote>

        <h4>The standard Lorem Ipsum passage, used since the 1500s</h4>

        <p>
          "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
          velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        </p>

        <h4>Section 1.10.32 of "de Finibus Bonorum et Malorum", written by Cicero in 45 BC</h4>

        <p>
          "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut
          fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim
          ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"
        </p>

        <h4>List style in action</h4>
        <ul>
          <li>
            If you wish to succeed, you should use persistence as your good friend, experience as your reference, prudence as your brother and hope as your sentry.
            <p>如果你希望成功，当以恒心为良友，以经验为参谋，以谨慎为兄弟，以希望为哨兵。</p>
          </li>
          <li>
            Sometimes one pays most for the things one gets for nothing.
            <p>有时候一个人为不花钱得到的东西付出的代价最高。</p>
          </li>
          <li>
            Only those who have the patience to do simple things perfectly ever acquire the skill to do difficult things easily.
            <p>只有有耐心圆满完成简单工作的人，才能够轻而易举的完成困难的事。</p>
          </li>
        </ul>

        <h4>You may want to create a perfect <code>&lt;hr /&gt;</code> line, despite the fact that there will never have one</h4>
        <hr />
        <p>
          <abbr title="法国作家罗切福考尔德">La Racheforcauld</abbr> said:
          <mark>"Few things are impossible in themselves; and it is often for want of will, rather than of means, that man fails to succeed". </mark>
          You just need to follow the browser's behavior, and set a right <code>margin</code> to it。it will works nice as the demo you're watching now. The following code is the best way to render typo in Chinese:
        </p>
        <pre><code class="language-css">/* 标题应该更贴紧内容，并与其他块区分，margin 值要相应做优化 */
            h1,h2,h3,h4,h5,h6 {
                line-height:1;font-family:Arial,sans-serif;margin:1.4em 0 0.8em;
            }
            h1{font-size:1.8em;}
            h2{font-size:1.6em;}
            h3{font-size:1.4em;}
            h4{font-size:1.2em;}
            h5,h6{font-size:1em;}

            /* 现代排版：保证块/段落之间的空白隔行 */
            .typo p, .typo pre, .typo ul, .typo ol, .typo dl, .typo form, .typo hr {
                margin:1em 0 0.6em;
            }
          </code></pre>

        <h2 id="section3">三、附录</h2>

        <h3 id="appendix1">1、<i class="serif">Typo.css</i> 排版偏重点</h3>
        <table class="ui table" summary="Typo.css 排版偏重点">
          <thead>
            <tr>
              <th>类型</th>
              <th>语义</th>
              <th>标签</th>
              <th>注意点</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th rowspan="15">基础标签</th>
              <td>标题</td>
              <td><code>h1</code> ～ <code>h6</code></td>
              <td>全局不强制大小，<code>.typo</code> 中标题与其对应的内容应紧贴，并且有相应的大小设置</td>
            </tr>
            <tr>
              <td>上、下标</td>
              <td><code>sup</code>/<code>sub</code></td>
              <td>保持与 MicroSoft Office Word 等程序的日常排版一致</td>
            </tr>
            <tr>
              <td>引用</td>
              <td><code>blockquote</code></td>
              <td>显示/嵌套样式</td>
            </tr>
            <tr>
              <td>缩写</td>
              <td><code>abbr</code></td>
              <td>是否都有下划线，鼠标 <code>hover</code> 是否为帮助手势</td>
            </tr>
            <tr>
              <td>分割线</td>
              <td><code>hr</code></td>
              <td>显示的 <code>padding</code> 和 <code>margin</code>正确</td>
            </tr>
            <tr>
              <td>列表</td>
              <td><code>ul</code>/<code>ol</code>/<code>dl</code></td>
              <td>在全局没有 <code>list-style</code>，在 .<code>typo</code> 中对齐正确</td>
            </tr>
            <tr>
              <td>定义列表</td>
              <td><code>dl</code></td>
              <td>全局 <code>padding</code> 和 <code>margin</code>为0， .<code>typo</code> 中对齐正确</td>
            </tr>
            <tr>
              <td>选项</td>
              <td><code>input[type=radio[, checkbox]]</code></td>
              <td>与其他 <code>form</code> 元素排版时是否居中</td>
            </tr>
            <tr>
              <td>斜体</td>
              <td><code>i</code></td>
              <td>只设置一种斜体，让 <code>em</code> 和 <code>cite</code> 显示为正体</td>
            </tr>
            <tr>
              <td>强调</td>
              <td><code>em</code></td>
              <td>在全局显示正体，在 <code>.typo</code> 中显示与 <code>b</code> 和 <code>strong</code> 的样式一致，为粗体</td>
            </tr>
            <tr>
              <td>加强</td>
              <td><code>strong/b</code></td>
              <td>显示为粗体</td>
            </tr>
            <tr>
              <td>标记</td>
              <td><code>mark</code></td>
              <td>类似荧光笔</td>
            </tr>
            <tr>
              <td>印刷</td>
              <td><code>small</code></td>
              <td>保持为正确字体的 80% 大小，颜色设置为浅灰色</td>
            </tr>
            <tr>
              <td>表格</td>
              <td><code>table</code></td>
              <td>全局不显示线条，在 <code>table</code> 中显示表格外框，并且表头有浅灰背景</td>
            </tr>
            <tr>
              <td>代码</td>
              <td><code>pre</code>/<code>code</code></td>
              <td>字体使用 <code>courier</code> 系字体，保持与 <code>serif</code> 有比较一致的显示效果</td>
            </tr>
            <tr>
              <th rowspan="5">特殊符号</th>
              <td>着重号</td>
              <td><em class="typo-em">在文字下加点</em></td>
              <td>在支持 <code>:after</code> 和 <code>:before</code> 的浏览器可以做渐进增强实现</td>
            </tr>
            <tr>
              <td>专名号</td>
              <td><u>林建锋</u></td>
              <td>专名号，有下划线，使用 <code>u</code> 或都 <code>.typo-u</code> 类</td>
            </tr>
            <tr>
              <td>破折号</td>
              <td>——</td>
              <td>保持一划，而非两划</td>
            </tr>
            <tr>
              <td>人民币</td>
              <td>&yen;</td>
              <td>使用两平等线的符号，或者 HTML 实体符号 <code>&amp;yen;</code></td>
            </tr>
            <tr>
              <td>删除符</td>
              <td>
                <del>已删除（deleted）</del>
              </td>
              <td>一致化各浏览器显示，中英混排正确</td>
            </tr>
            <tr>
              <th rowspan="3">加强类</th>
              <td>专名号</td>
              <td><code>.typo-u</code></td>
              <td>由于 <code>u</code> 被 HTML4 放弃，在向后兼容上推荐使用 <code>.typo-u</code></td>
            </tr>
            <tr>
              <td>着重符</td>
              <td><code>.typo-em</code></td>
              <td>利用 <code>:after</code> 和 <code>:before</code> 实现着重符</td>
            </tr>
            <tr>
              <td>清除浮动</td>
              <td><code>.clearfix</code></td>
              <td>与一般 CSS Reset 保持一对致 API</td>
            </tr>
            <tr>
              <th rowspan="5">注意点</th>
              <td colspan="3">（1）中英文混排行高/行距</td>
            </tr>
            <tr>
              <td colspan="3">（2）上下标在 IE 中显示效果</td>
            </tr>
            <tr>
              <td colspan="3">（3）块/段落分割空白是否符合设计原则</td>
            </tr>
            <tr>
              <td colspan="3">（4）input 多余空间问题</td>
            </tr>
            <tr>
              <td colspan="3">（5）默认字体色彩，目前采用 <code>#333</code> 在各种浏览显示比较好</td>
            </tr>
          </tbody>
        </table>

        <h3 id="appendix2">2、开源许可</h3>
        <!--*/-->
      </div>
      <!-- 导航 -->
      <div :style="{ right: navShow ? '1.5rem' : '-3rem', bottom: navShow ? '4rem' : '-4rem' }" class="toolbar">
        <el-popover placement="left-end" trigger="click" :width="240">
          <template #reference>
            <el-button type="warning" class="toc-button">目录</el-button>
          </template>
          <template #default>
            <div class="article-tocbot"></div>
          </template>
        </el-popover>
        <el-popover placement="left-start" trigger="hover" :width="100">
          <template #reference>
            <el-button class="qrcode">
              <img src="@/assets/img/svg/qrcode.svg" alt="二维码" />
            </el-button>
          </template>
          <template #default>
            <QRCodeVue3
              :width="130"
              :height="130"
              :dotsOptions="{
                type: 'dots',
                color: '#000000',
                gradient: {
                  type: 'linear',
                  rotation: 0,
                  colorStops: [
                    { offset: 0, color: '#000000' },
                    { offset: 1, color: '#000000' },
                  ],
                },
              }"
              value="http://localhost:3000/article/1"
            />
          </template>
        </el-popover>
        <el-tooltip effect="light" content="返回顶部" placement="left-end">
          <el-button plain class="toTop-button" @click="toTop">
            <el-icon>
              <i-ep-ArrowUpBold />
            </el-icon>
          </el-button>
        </el-tooltip>
      </div>
    </el-row>
    <el-row align="middle">
      <el-col :span="11"></el-col>
      <el-col :span="2">END</el-col>
      <el-col :span="11"></el-col>
    </el-row>
    <div class="affix-footer">
      <!-- <el-affix target=".article-content" position="bottom"> -->
      <div class="article-footer">
        <div class="avatar-name">
          <img src="@/assets/img/avatar2.png" alt="博客主" />
          <a href="javascript:;">Pluto_ssy</a>
        </div>
        <el-row align="middle">
          <el-col :span="5">
            <el-tooltip effect="dark" content="点赞" placement="top">
              <div @click="heartActive = !heartActive">
                <img src="@/assets/img/icon/heart.png" v-show="heartActive" />
                <img src="@/assets/img/icon/heartActive.png" v-show="!heartActive" />
              </div>
            </el-tooltip>
            <p>211</p>
          </el-col>
          <el-col :span="5">
            <el-tooltip effect="dark" content="收藏" placement="top">
              <div @click="collectActive = !collectActive">
                <img src="@/assets/img/icon/newCollectBlack.png" v-show="collectActive" />
                <img src="@/assets/img/icon/newCollectActive.png" v-show="!collectActive" />
              </div>
            </el-tooltip>
            <p>985</p>
          </el-col>
          <el-col :span="5">
            <el-tooltip effect="dark" content="评论" placement="top">
              <div @click="commentDrawer = true">
                <img src="@/assets/img/icon/showComment.png" />
              </div>
            </el-tooltip>
            <el-drawer v-model="commentDrawer" title="评论" :z-index="999" size="40%" class="commentDrawer">
              <span>评论内容</span>
            </el-drawer>
            <p>20</p>
          </el-col>
          <el-col :span="3">
            <el-popover :width="400">
              <template #reference>
                <img src="@/assets/img/icon/shareBlack.png" />
              </template>
              <template #default>
                <!-- 博客信息 -->
                <div class="share-content">
                  <div class="share-content-list">
                    <ul>
                      <li>作者：Pluto_ssy（<a href="../about" target="_self">联系作者</a>）</li>
                      <li>发表时间：15:08</li>
                      <li>版权声明：自由转载-非商用-非衍生-保持署名（创意共享3.0许可证）</li>
                      <li>公众号转载：请在文末添加作者博客二维码</li>
                    </ul>
                  </div>
                  <div class="share-img">
                    <img src="@/assets/img/CSDN.jpg" alt="code" />
                  </div>
                </div>
              </template>
            </el-popover>
          </el-col>
        </el-row>
      </div>
      <!-- </el-affix> -->
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import { useStore } from 'vuex';
import * as tocbot from 'tocbot';
import QRCodeVue3 from 'qrcode-vue3';
import { mixin } from '../mixins';

let heartActive = ref(true);
let collectActive = ref(true);
let commentDrawer = ref(false);
const articleRef: any = ref(null); // 文章容器
let navShow = ref(false); // 右导航条显示
// let showToc = ref(false)
const store = useStore();
let timer: any = null;

// const showTocList = () => {
//   showToc.value = !showToc.value
//   if (showToc.value) {
//     tocbot.refresh()
//   }
// }

onMounted(() => {
  // 刚一进入页面就返回顶部
  mixin.methods.animate(document.documentElement, 0);
  // 取消全局返回顶部按钮返回顶部按钮
  store.commit('m_back/setShowBackTop', false);
  // 右导航条显示
  document.addEventListener('scroll', handleScroll);
  tocbot.init({
    // 在何处呈现目录
    tocSelector: '.article-tocbot',
    // 在哪里获取标题以构建目录
    contentSelector: '.article-content',
    // 要在contentSelector元素内部获取的标题
    headingSelector: 'h1, h2, h3',
    // 用于内容中相对或绝对定位容器内的标题.
    hasInnerContainers: true,
  });
  tocbot.refresh();

  // 处理el-affix组件的bug
  document.addEventListener('scroll', affixScroll);
});
// 处理el-affix组件的bug处理函数
const affixScroll = () => {
  const scrollTop = document.documentElement.scrollTop || window.scrollY || document.body.scrollTop;
  if (scrollTop >= 180 && scrollTop <= articleRef.value.clientHeight + 400) {
    document.querySelector('.affix-footer')?.classList.add('affix-fixed');
  } else {
    document.querySelector('.affix-footer')?.classList.remove('affix-fixed');
  }
};

// 导航条处理函数
const handleScroll = () => {
  // 防抖
  clearTimeout(timer);
  timer = setTimeout(() => {
    // 页面滚动距顶部距离
    const scrollTop = document.documentElement.scrollTop || window.scrollY || document.body.scrollTop;
    // 文章可视高度
    // const clientHeight = articleRef.value.clientHeight || articleRef.value.scrollHeight || articleRef.value.offsetHeight
    const clientHeight = articleRef.value.clientHeight + 250 || document.documentElement.scrollHeight - 1100;
    if (scrollTop >= 550 && scrollTop <= clientHeight) {
      navShow.value = true;
    } else {
      navShow.value = false;
    }
  }, 100);
};

const toTop = () => {
  mixin.methods.animate(document.documentElement, 500);
};

onUnmounted(() => {
  // 恢复全局返回顶部按钮返回顶部按钮
  store.commit('m_back/setShowBackTop', true);
  tocbot.destroy();
  // 右导航条隐藏
  document.removeEventListener('scroll', handleScroll);
  document.removeEventListener('scroll', affixScroll);
});
</script>

<style lang="scss" scoped>
@import url('../assets/scss/tocbot.scss');

.article-container {
  background-color: rgba(255, 255, 255, 0.9);
  width: 75%;
  margin: 1rem auto;
  margin-bottom: 2rem;
  padding-top: 1rem;
  border-radius: 5px;
  box-shadow: 0 1px 2px 0 rgba(34, 36, 38, 0.6);

  > .el-row {
    margin: auto 1rem;
  }

  > .el-row:first-child {
    color: #888686;
    font-size: 1rem;
    font-weight: 400;
    padding: 1rem 0.5rem;
    border-radius: 6px;
    border-bottom: 1px solid #888686;
    box-shadow: 0 1px 1px 0 rgba(110, 111, 113, 0.6);

    > div {
      display: flex;
      align-items: center;
      margin-right: 1rem;

      > a {
        color: #888686;
        margin-left: 0.5rem;

        &:hover {
          color: #f2711c;
        }
      }

      > p {
        text-align: center;
        margin-left: 0.3rem;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      i {
        font-size: 1rem;
        margin-right: 0.2rem;
      }
    }

    .flag {
      width: 2.4rem;
      color: rgba(255, 255, 255, 0.9);
      border-radius: 4px;
      margin-right: 0.2rem;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: flex-start;

      .flag-ss {
        width: 0;
        height: 0;
        border-style: solid;
        border-width: 0.2rem 1.2rem;
        border-radius: 4px 0 0 0;
      }

      .flag-tt {
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 0 2px 2px 0;
      }

      .flag-xs {
        width: 0;
        height: 0;
        border-style: solid;
        border-width: 0.2rem 1.2rem;
        border-radius: 0 0 0 4px;
      }

      // &::before {
      //   content: '';
      //   position: absolute;
      //   top: 0;
      //   right: 0;
      //   display: block;
      //   width: 0;
      //   height: 0;
      //   border-color: transparent transparent #f2711c #f2711c;
      //   border-style: solid;
      //   border-width: 0.2rem 1rem;
      //   border-radius: 4px 0 0 0;
      // }
      // &::after {
      //   position: absolute;
      //   bottom: 0;
      //   right: 0;
      //   content: '';
      //   display: block;
      //   width: 0;
      //   height: 0;
      //   border-color: transparent rgba(255, 255, 255, 0.9) rgba(255, 255, 255, 0.9) transparent;
      //   border-style: solid;
      //   border-width: 0.2rem 1rem;
      //   border-radius: 0 0 0 4px;
      // }
    }

    .type {
      > div {
        background-color: #fff;
        border: 1px solid #00b5ad;
        border-radius: 5px;
        padding: 0.2rem 0.5rem;
        cursor: pointer;

        &:hover {
          background-color: #00b5ad;
          color: #fff;
        }
      }
    }

    .tag {
      > div {
        background-color: #fff;
        border: 1px solid #2185d0;
        border-radius: 5px;
        padding: 0.2rem 0.5rem;
        margin-right: 0.5rem;
        cursor: pointer;

        &:hover {
          background-color: #2185d0;
          color: #fff;
        }
      }
    }
  }

  > .el-row:nth-child(2) {
    margin: 1rem;
    margin-bottom: 2rem;
    padding: 1rem 0;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #969595;

    img {
      width: 100%;
    }
  }

  > .el-row:nth-child(3) {
    margin: 1rem;
    display: flex;
    justify-content: center;

    h1 {
      font-size: 2rem;
      font-weight: 700;
    }
  }

  > .el-row:nth-child(5) {
    margin: 1rem;

    > .el-col:first-child,
    > .el-col:last-child {
      width: 100%;
      border-bottom: #888686 solid 1px;
      box-shadow: 0 1px 1px 0 rgba(110, 111, 113, 0.6);
    }

    > .el-col:nth-child(2) {
      text-align: center;
    }
  }

  .affix-footer {
    position: sticky;
    bottom: 0;
    .article-footer {
      padding: 1rem;
      color: #888686;
      background-color: #fff;
      border-radius: 5px;
      display: flex;
      justify-content: space-between;
      cursor: pointer;

      .avatar-name {
        display: flex;
        align-items: center;

        > img {
          width: 1.5rem;
          height: 1.5rem;
          border-radius: 50%;
        }

        > a {
          color: #1e70bf;
          margin-left: 0.5rem;
        }
      }

      > .el-row {
        width: 16rem;

        > .el-col {
          display: flex;
          align-items: center;
          justify-content: center;
          margin-left: 1rem;

          img {
            width: 1.5rem;
            height: 1.5rem;
          }

          p {
            height: 1.5rem;
            line-height: 1.3rem;
            margin-left: 0.3rem;
          }
        }

        > .el-col:last-child {
          margin-bottom: 0.4rem;
        }
      }
    }
  }
  .affix-fixed {
    position: fixed;
    bottom: 0;
    width: 75%;
  }
}

.share-content {
  display: flex;
  align-items: center;
  justify-content: space-between;

  .share-content-list {
    margin: 1rem;
    padding-right: 1rem;
    border-right: #888686 solid 1px;

    ul > li {
      list-style: disc;
    }
  }

  .share-img {
    img {
      width: 6rem;
      height: 6rem;
    }
  }
}

/* 评论标题 */
:deep(.commentDrawer) .el-drawer__header {
  margin-bottom: 0 !important;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid #e8e8ed;
  color: #222226;
}

// 导航
.toolbar {
  width: 3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: fixed;
  right: 1.5rem;
  bottom: 4rem;
  transition: all 0.3s ease-in-out;

  .el-button {
    margin: 0;
    padding: 1.2rem 0;
    width: 3rem;
    border-radius: 0;
  }

  .toc-button {
    border-radius: 4px 4px 0 0;
  }

  .qrcode {
    img {
      width: 2rem;
      height: 2rem;
    }

    &:active {
      background-color: #e8e8ed;
    }
  }

  .toTop-button {
    border-radius: 0 0 4px 4px;

    &:active {
      background-color: #e8e8ed;
    }
  }
}
</style>
