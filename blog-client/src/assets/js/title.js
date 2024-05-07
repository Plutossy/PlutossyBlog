/*
 * @Author: Plutossy pluto_ssy@outlook.com
 * @Date: 2024-05-07 15:52:24
 * @LastEditors: Plutossy pluto_ssy@outlook.com
 * @LastEditTime: 2024-05-07 15:59:15
 * @FilePath: \blog-client\src\assets\js\title.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
let titleTime,
  OriginTitile = document.title;
document.addEventListener('visibilitychange', () => {
  document.hidden
    ? ((document.title = 'w(ﾟДﾟ)w 不要走！再看看嘛！'), clearTimeout(titleTime))
    : ((document.title = '♪(^∇^*)欢迎肥来！'),
      (titleTime = setTimeout(() => {
        document.title = OriginTitile;
      }, 2e3)));
});
window.addEventListener('visibilitychange', () => {
  if (!document.hidden) showMessage('哇，你终于回来了～', 6000, 9);
});

// 显示消息
const showMessage = (text, timeout, priority) => {
  if (!text || (localStorage.getItem('waifu-text') && localStorage.getItem('waifu-text') > priority)) return;
  if (messageTimer) {
    clearTimeout(messageTimer);
    messageTimer = null;
  }
  text = randomSelection(text);
  localStorage.setItem('waifu-text', priority);
  const tips = document.getElementById('waifu-tips');
  tips.innerHTML = text;
  tips.classList.add('waifu-tips-active');
  messageTimer = setTimeout(() => {
    localStorage.removeItem('waifu-text');
    tips.classList.remove('waifu-tips-active');
  }, timeout);
};
