<!--
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2023-12-06 16:12:41
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2023-12-29 18:04:24
 * @FilePath: \blog-client\src\components\songlist\SongList.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="song-list">
    <el-row class="list-title">
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="6">歌曲</el-col>
      <el-col :span="6">时长</el-col>
      <el-col :span="6">歌手</el-col>
      <el-col :span="4"></el-col>
    </el-row>
    <el-row v-for="site in songList" :key="site.id" class="song-list-content"
      :style="{ color: (site.id == songContent.id && playStatus === true) ? 'blue' : '#000000' }" @click="cutSong(site)">
      <el-col :span="2" class="song-img">
        &nbsp;
        <img v-show="(site.id === songContent.id && playStatus === true) ? true : false"
          src="@/assets/img/song/wave.gif" />
      </el-col>
      <el-col :span="6" class="song-name">
        <el-tooltip effect="dark" :content="site.name" placement="top">
          {{ site.name }}
        </el-tooltip>
      </el-col>
      <el-col :span="6">歌曲时长</el-col>
      <el-col :span="6">
        <el-tooltip effect="dark" :content="site.singer" placement="top">
          {{ site.singer }}
        </el-tooltip>
      </el-col>

      <el-col :span="1">
        <el-tooltip effect="dark" content="播放/暂停" placement="top">
          <el-icon>
            <VideoPlay v-if="(site.id !== songContent.id || playStatus === false) ? true : false" />
            <VideoPause v-else />
          </el-icon>
        </el-tooltip>
      </el-col>
    </el-row>
    <!-- 分页 -->
    <el-row justify="center">
      <el-pagination :page-size="5" :pager-count="5" layout="prev, pager, next" :total="28" :hide-on-single-page="true"
        background class="animate__animated animate__fadeInUpBig" />
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getSongList } from '@/api'

export default {
  name: 'SongList',
  data() {
    return {
      songList: [
        {
          id: 1,
          name: '从别后',
          singer: '悟我',
          url: 'http://music.163.com/song/media/outer/url?id=1406025645.mp3',
          img: 'http://p1.music.126.net/sb5vlbS_Vt5lg9tKtbMxTA==/109951164511663354.jpg?param=300x300',
          lyric: '[00:00.00] 作词 : Riesling[00:00.99] 作曲 : 陈鹏杰[00:01.98]词：Riesling[00:03.22]曲：陈鹏杰[00:05.23]原唱：流浪的蛙蛙[00:07.03]翻唱：悟我[00:11.90]后期：添添[00:13.74]《斗破苍穹.特别篇》片尾曲[00:19.98]一片云落入掌心[00:24.12]渺渺万里何处去[00:28.44]只记取离别时的你[00:32.24]趁月朗风清[00:36.94]转身过不知归期[00:41.09]愿我如那时的风[00:45.85]拂过你的发鬓 轻拥着你[00:53.04]枉将箫声吹尽[00:57.11]沧海一遇却难找寻[01:01.60]前路崇山峻岭不再有你同行[01:10.17]纵使微茫如烟 纵有万般思念[01:18.69]流光总将故人搁浅在断简残篇[01:23.55]不成眠[01:27.05]不等谁来证明 不必狂歌痛饮[01:35.44]唯盼重相见[01:39.53]归来时一醉明月[02:02.00]那片云落入我心[02:06.16]渺渺万里何处寻[02:10.19]只记取离别时的你[02:14.07]趁月朗风清[02:18.95]经年过不知归期[02:23.01]愿我如那时的风[02:27.93]拂过你的发鬓 轻拥着你[02:34.91]苍穹箫声吹尽[02:39.20]此生魂梦已逐云去[02:43.71]前路崇山峻岭不再有你同行[02:52.04]纵使微茫如烟 纵有万般思念[03:00.52]流光总将故人搁浅在断简残篇[03:05.32]不成眠[03:09.09]不等谁来证明 不必狂歌痛饮[03:17.63]唯盼重相见[03:21.25]我如倦鸟归林[03:26.36]纵使微茫如烟 纵有万般思念[03:34.42]流光总将故人搁浅在断简残篇[03:39.50]不成眠[03:43.12]不理白驹过隙 不信生死有命[03:51.49]唯盼重相见[03:55.70]归来时一醉明月'
        },
        {
          id: 2,
          name: '璀璨冒险人',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: '[00:00.00] 暂无歌词'
        },
        {
          id: 3,
          name: '奔跑',
          singer: '黄征,羽·泉',
          url: 'http://music.163.com/song/media/outer/url?id=96100.mp3',
          img: 'http://p1.music.126.net/yf3OEUZxCkqbQ5IwyMfcMw==/109951167132527652.jpg?param=300x300',
          lyric: '[00:00.000] 作词 : 黄征/羽泉[00:01.000] 作曲 : 黄征/羽泉[00:04.560]速度七十迈[00:07.110]心情是自由自在[00:10.820]希望终点是爱琴海[00:14.580]全力奔跑 梦在彼岸[00:18.630]我们想漫游世界[00:22.290]看奇迹就在眼前[00:26.090]等待夕阳染红了天[00:29.750]肩并着肩 许下心愿[00:35.960]随风奔跑自由是方向[00:39.670]追逐雷和闪电的力量[00:43.230]把浩瀚的海洋装进我胸膛[00:47.280]即使再小的帆也能远航[00:50.990]随风飞翔有梦作翅膀[00:54.840]敢爱敢做勇敢闯一闯[00:59.890]哪怕遇见再大的风险再大的浪[01:04.500]也会有默契的目光[01:08.360]速度七十迈[01:11.360]心情是自由自在[01:15.070]希望终点是爱琴海[01:18.880]全力奔跑 梦在彼岸[01:22.730]我们想漫游世界[01:26.610]看奇迹就在眼前[01:30.260]等待夕阳染红了天[01:33.910]肩并着肩 许下心愿[01:37.530]随风奔跑自由是方向[01:41.740]追逐雷和闪电的力量[01:45.230]把浩瀚的海洋装进我胸膛[01:49.400]即使再小的帆也能远航[01:53.320]随风飞翔有梦作翅膀[01:59.130]敢爱敢做勇敢闯一闯[02:02.680]哪怕遇见再大的风险再大的浪[02:07.540]也会有默契的目光[02:24.800]随风奔跑自由是方向[02:28.800]追逐雷和闪电的力量[02:33.180]把浩瀚的海洋装进我胸膛[02:37.280]即使再小的帆也能远航[02:40.970]随风飞翔有梦作翅膀[02:44.730]敢爱敢做勇敢闯一闯[02:48.640]哪怕遇见再大的风险再大的浪[02:53.190]也会有默契的目光[02:58.020]我们想漫游世界[03:02.130]看奇迹就在眼前[03:05.720]等待夕阳染红了天[03:09.380]肩并着肩 许下心愿'
        },
        {
          id: 4,
          name: '篝火',
          singer: '吕大叶,马子林,陈觅Lynne',
          url: 'http://music.163.com/song/media/outer/url?id=518725853.mp3',
          img: 'http://p2.music.126.net/sN5dTpmeJO1DhxIj1ogMLg==/109951163416453597.jpg?param=300x300',
          lyric: "[00:00.000] 作词 : 吕大叶/鳄鱼/马子林[00:01.000] 作曲 : Kaleo/吕大叶/马子林[00:19.434][00:20.936]你还坐在小时候的篝火旁[00:23.433]看天上的月亮[00:24.683]还是那个模样[00:25.941]北斗星在指着你的前方[00:28.433]在夜里你也不会失去你的方向[00:31.197]不会再度感到迷茫[00:33.193]湖面倒映着天空是你心中的梦想[00:36.187]丢弃内心不安的彷徨[00:38.947]不会在任何寂静夜里孤单幻想流浪[00:41.444]你说你 想不起曾经 梦境里面想要的对话[00:43.932]你说你 看不见星星 在天空点亮你的童话[00:46.440]一转眼斗转星移 在漆黑的夜里 砍断了荆棘[00:48.938]你听见声音 在讨论你的曾经 想要走过去澄清[00:51.683]冲破水泥钢筋 不让谁用嘲笑压迫神经[00:54.192]哪怕他在咆哮 故事再搞笑 不要无理取闹[00:56.683]给失败画上个 句号 再向敌人 提出个 警告[00:58.941]在天黑之前证明 你行 松开紧绷的神经 去聆听[01:01.683]当镜子里的眼神 早已不再天真 学会了深沉[01:04.441]很多回忆 内心深处 最不为人知的秘密[01:06.933]曾经自己 无知地以为 欺骗了自己就能 欺骗了 全世界[01:09.688]到现在才发现 其实那只是 无知地 在发泄[01:11.935]忘记了天空的模样 骗自己不会觉得彷徨 开始无理的张狂[01:15.442]丢弃了形象 丢弃了理想 不再背着皮包 去流浪[01:17.933]去追逐梦想 奋力的奔跑 仿佛回到[01:19.944]童年操场上 挥洒汗水的跑道 滋味你知道[01:22.438]看见了 时间在自己身边 一点点的流逝[01:24.939]开始慌了 发现自己的好运气都用光了[01:27.441]开始低下了头 不再张扬 尝试着探索真理的流向[01:29.934]相信最终会抵达成功的 太平洋 不再四处流浪[01:32.698]内心深处的旗帜会在 太空飘扬 驾一艘船去 扬帆起航[01:35.936]太阳的指向 那是你前进的方向[01:37.685]它让你不再彷徨 给你希望的光亮 做自己榜样[01:40.194]抓住时机 下一班飞机 跟自己说句 后会有期[01:43.439]你还坐在小时候的篝火旁[01:45.943]看天上的月亮[01:47.193]还是那个模样[01:48.682]北斗星在指着你的前方[01:51.192]在夜里你也不会失去你的方向[01:53.933]不会再度感到迷茫[01:55.685]湖面倒映着天空是你心中的梦想[01:58.943]丢弃内心不安的彷徨[02:01.443]不会在任何寂静夜里孤单幻想流浪[02:03.933]try many times 4 the fight of life[02:05.194]this rap **** is just like the dice[02:06.443]Open your eyes in the last light[02:07.689]prepare your rhyme for the last fight[02:08.946]Gamble it all with your last dime[02:10.432]raise your head homie never cry[02:11.687]so many people gonna mention my name[02:12.937]I'm heading toward to my dream we homies like a team[02:16.949]I spit the rap **** underground By grace I've risen to the top[02:19.437]I've bounced No body knows the untold[02:22.185]u try 2 break the chains step on the train[02:23.184]2 dream inside screaming u try 2 stop shaking[02:23.684]no more faking, it's dream ，you'll make it[02:24.692]在夜里 讲着真心话 从不怕 别人说你眼神的变化[02:27.683]带着家人温馨的牵挂 让冰雪融化[02:29.934]去见证童话 不会再说一次空话[02:31.947]睁开黑色的双眼 忘掉了所有的嘲笑 点起了一根香烟[02:34.933]把不堪回忆的往事 全部都烧掉[02:36.933]松开紧绷的神经 抛下狼狈的神情 想你的曾经 陪你的身影[02:40.185]记住火热的体温 赌上你最张扬的青春[02:42.433]你的固执让你的目标精准 付出了英勇的精神[02:45.441]打开了陌生的大门 别怕 就算黑暗吞噬了最后的一丝的光线[02:47.945]我依旧还在捍卫我们的明天[02:50.937]用歌声记录 这场梦想的战役 多么的深远[02:52.933]被第一缕的曙光 拉出了无底的深渊 你不再困倦[02:55.690]未来的道路荒凉 也要抓着故乡的泥土[02:58.182]不忘初心 想象着跟当初的自己见面[03:00.434]告诉自己 坚持信念不变 梦想终会实现[03:03.182]不会再度彷徨 你一直坐在那个曾经的篝火旁[03:06.874]你还坐在小时候的篝火旁[03:08.890]看天上的月亮[03:10.134]还是那个模样[03:11.375]北斗星在指着你的前方[03:13.628]在夜里你也不会失去你的方向[03:16.379]不会再度感到迷茫[03:18.383]湖面倒映着天空是你心中的梦想[03:21.639]丢弃内心不安的彷徨[03:24.127]不会在任何寂静夜里孤单幻想流浪[03:27.625]编曲：muxo/Kaleo[03:28.625]制作人Producer：Oboisky/muxo/承让"
        },
        {
          id: 5,
          name: '有些人',
          singer: '余佳运',
          url: 'http://music.163.com/song/media/outer/url?id=1387607263.mp3',
          img: 'http://p1.music.126.net/lQquBDnd1jVXU1W4lRVDQg==/109951164333148921.jpg?param=300x300',
          lyric: '[00:00.00] 作词 : 余佳运[00:01.00] 作曲 : 余佳运[00:18.24]编曲：余佳运[00:21.24]开放[00:23.24]敞开你胸膛[00:26.24]崭新的殿堂[00:29.24]太多的人[00:31.24]还没有准备[00:33.24]起航[00:35.24]张开了翅膀[00:37.24]找到方向[00:40.24]新世界的钟声敲响[00:43.24]Sounds like[00:44.24]oh…no…wu…no[00:47.24]都争先恐后[00:50.24]都力争上游[00:55.24]oh…no…wu…no[00:58.24]抉择的路口[01:01.24]都不分左右[01:05.24]有些人在回首[01:07.24]有人孤身地在往前走[01:10.24]有人在原地躺下等候[01:13.24]还有人宣称 命运不公[01:16.24]有些人看不透[01:18.24]有人沉浸在美梦之中[01:21.24]有人时常被旁人左右[01:24.24]道理都很懂 却都没用[01:28.24]没尝试过你就别说不行[01:30.24]失败了也别说这就是命[01:33.24]别留恋捉摸不透的运气[01:36.24]首先你得有自己信[01:39.24]这就是一场自选的旅行[01:42.24]每个人有自己的目的地[01:49.24]oh…no…wu…no[01:53.24]都争先恐后[01:55.24]都力争上游[02:00.24]oh…no…wu…no[02:04.24]抉择的路口[02:06.24]都不分左右[02:11.24]有些人在回首[02:13.24]有人孤身地在往前走[02:15.24]有人在原地躺下等候[02:18.24]还有人宣称 命运不公[02:21.24]有些人看不透[02:23.24]有人沉浸在美梦之中[02:26.24]有人时常被旁人左右[02:29.24]道理都很懂 却都没用[02:33.24]有些人在回首[02:35.24]有人孤身地在往前走[02:37.24]有人在原地躺下等候[02:40.24]还有人宣称 命运不公[02:43.24]有些人看不透[02:45.24]有人沉浸在美梦之中[02:48.24]有人时常被旁人左右[02:51.24]道理都很懂 却都没用[03:04.24]贝斯：彭宏立(Simon)[03:06.24]键盘：余佳运[03:08.24]和声编写：余佳运[03:10.24]混音：余佳运[03:12.24]母带：沈会斌@NEWBAND STUDIO[03:14.24]制作人：余佳运'
        },
        {
          id: 6,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
        {
          id: 7,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
        {
          id: 8,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
        {
          id: 9,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
        {
          id: 10,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
        {
          id: 11,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
        {
          id: 12,
          name: '璀璨冒险人 - 无歌词',
          singer: '周深',
          url: 'http://music.163.com/song/media/outer/url?id=2053832337.mp3',
          img: 'https://y.gtimg.cn/music/photo_new/T002R300x300M000000pHw2K355mzf.jpg',
          lyric: ''
        },
      ], // 歌曲列表
    }
  },
  computed: {
    ...mapGetters('m_song', ['playStatus', 'songContent'])
  },
  created() {
    // this.getSongList()
  },
  methods: {
    //列表点击播放歌曲
    cutSong(obj) {
      if (obj.id == this.songContent.id) {
        this.$store.commit('m_song/setPlayStatus', !this.playStatus)
      } else {
        this.$store.commit('m_song/setSongContent', obj)
        this.$store.commit('m_song/setPlayStatus', true)
      }
    },
    // 获取所有歌曲
    async getSongList() {
      const { data: res } = await getSongList
      if (res.code === 200) {
        this.songList = res.data
      } else {
        this.$message.error(res.msg)
      }
    },
  }
}
</script>

<style lang="scss" scoped>
div {
  /* 取消div标签在移动端点击时的蓝色 */
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
  -webkit-user-select: none;
  -moz-user-focus: none;
  -moz-user-select: none;
}

.song-list {
  width: 100%;
  padding: 2rem 0 3rem;
  margin-left: 2rem;

  .list-title {
    margin-top: 0.5rem;
    padding-bottom: 0.5rem;
    border-bottom: 2px solid slategray;

    .el-col {
      font-size: 1.2rem;
      color: #f2711c;
    }
  }

  .song-list-content {
    font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
    font-size: 0.8rem;
    padding: 0.5rem 0;
    border-bottom: 1px solid slategray;

    .el-col {
      color: #000000;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }

    .el-col:last-child {
      color: #30a4fc;
    }
  }

  .song-list-content:hover {
    background: rgb(180, 180, 180);
    cursor: pointer;
  }

  .el-row:last-child {
    margin-top: 1rem;
  }
}
</style>