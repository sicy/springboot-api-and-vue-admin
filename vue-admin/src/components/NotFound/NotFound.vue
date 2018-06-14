<template>
  <div style="margin-top: -20px;height:100%;">
    <div class="wscn-http404">
      <div class="pic-404">
        <img class="pic-404__parent" :src="img_404" alt="404">
        <img class="pic-404__child left" :src="img_404_cloud" alt="404">
        <img class="pic-404__child mid" :src="img_404_cloud" alt="404">
        <img class="pic-404__child right" :src="img_404_cloud" alt="404">
      </div>
      <div class="bullshit">
        <div class="bullshit__headline">抱歉，你访问的页面不存在或正在开发中...</div>
        <a :href="config.apiPath" class="bullshit__return-home">返回首页</a>
      </div>
    </div>
  </div>
</template>

<script>
import img_404 from '@/assets/404.png';
import img_404_cloud from '@/assets/404_cloud.png';
const config = require('../../../config')

export default {
  name: 'page404',
  data() {
    return {
      img_404,
      img_404_cloud,
      config
    };
  },
};
</script>

<style scoped>
  .wscn-http404 {
    display: -webkit-flex;
    display: -moz-box;
    display: flex;
    -webkit-align-items: center;
    -moz-box-align: center;
    align-items: center;
    position: relative;
    margin: 20px auto 60px;
    padding: 0 100px;
    overflow: hidden;
  }

  .wscn-http404 .pic-404 {
    position: relative;
    float: left;
    width: 600px;
    padding: 150px 0;
    overflow: hidden;
  }

  .wscn-http404 .pic-404__parent {
    width: 100%;
  }

  .wscn-http404 .pic-404__child {
    position: absolute;
  }

  .wscn-http404 .pic-404__child.left {
    width: 80px;
    top: 17px;
    left: 220px;
    opacity: 0;
    -webkit-animation-name: cloudLeft;
    -moz-animation-name: cloudLeft;
    -o-animation-name: cloudLeft;
    animation-name: cloudLeft;
    -webkit-animation-duration: 2s;
    -moz-animation-duration: 2s;
    -o-animation-duration: 2s;
    animation-duration: 2s;
    -webkit-animation-timing-function: linear;
    -moz-animation-timing-function: linear;
    -o-animation-timing-function: linear;
    animation-timing-function: linear;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
    -webkit-animation-delay: 1s;
    -moz-animation-delay: 1s;
    -o-animation-delay: 1s;
    animation-delay: 1s;
  }

  .wscn-http404 .pic-404__child.mid {
    width: 46px;
    top: 10px;
    left: 420px;
    opacity: 0;
    -webkit-animation-name: cloudMid;
    -moz-animation-name: cloudMid;
    -o-animation-name: cloudMid;
    animation-name: cloudMid;
    -webkit-animation-duration: 2s;
    -moz-animation-duration: 2s;
    -o-animation-duration: 2s;
    animation-duration: 2s;
    -webkit-animation-timing-function: linear;
    -moz-animation-timing-function: linear;
    -o-animation-timing-function: linear;
    animation-timing-function: linear;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
    -webkit-animation-delay: 1.2s;
    -moz-animation-delay: 1.2s;
    -o-animation-delay: 1.2s;
    animation-delay: 1.2s;
  }

  .wscn-http404 .pic-404__child.right {
    width: 62px;
    top: 100px;
    left: 500px;
    opacity: 0;
    -webkit-animation-name: cloudRight;
    -moz-animation-name: cloudRight;
    -o-animation-name: cloudRight;
    animation-name: cloudRight;
    -webkit-animation-duration: 2s;
    -moz-animation-duration: 2s;
    -o-animation-duration: 2s;
    animation-duration: 2s;
    -webkit-animation-timing-function: linear;
    -moz-animation-timing-function: linear;
    -o-animation-timing-function: linear;
    animation-timing-function: linear;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
    -webkit-animation-delay: 1s;
    -moz-animation-delay: 1s;
    -o-animation-delay: 1s;
    animation-delay: 1s;
  }

  @-webkit-keyframes cloudLeft {
    0% {
      top: 17px;
      left: 220px;
      opacity: 0;
    }
    20% {
      top: 33px;
      left: 188px;
      opacity: 1;
    }
    80% {
      top: 81px;
      left: 92px;
      opacity: 1;
    }
    100% {
      top: 97px;
      left: 60px;
      opacity: 0;
    }
  }

  @-moz-keyframes cloudLeft {
    0% {
      top: 17px;
      left: 220px;
      opacity: 0;
    }
    20% {
      top: 33px;
      left: 188px;
      opacity: 1;
    }
    80% {
      top: 81px;
      left: 92px;
      opacity: 1;
    }
    100% {
      top: 97px;
      left: 60px;
      opacity: 0;
    }
  }

  @-o-keyframes cloudLeft {
    0% {
      top: 17px;
      left: 220px;
      opacity: 0;
    }
    20% {
      top: 33px;
      left: 188px;
      opacity: 1;
    }
    80% {
      top: 81px;
      left: 92px;
      opacity: 1;
    }
    100% {
      top: 97px;
      left: 60px;
      opacity: 0;
    }
  }

  @keyframes cloudLeft {
    0% {
      top: 17px;
      left: 220px;
      opacity: 0;
    }
    20% {
      top: 33px;
      left: 188px;
      opacity: 1;
    }
    80% {
      top: 81px;
      left: 92px;
      opacity: 1;
    }
    100% {
      top: 97px;
      left: 60px;
      opacity: 0;
    }
  }

  @-webkit-keyframes cloudMid {
    0% {
      top: 10px;
      left: 420px;
      opacity: 0;
    }
    20% {
      top: 40px;
      left: 360px;
      opacity: 1;
    }
    70% {
      top: 130px;
      left: 180px;
      opacity: 1;
    }
    100% {
      top: 160px;
      left: 120px;
      opacity: 0;
    }
  }

  @-moz-keyframes cloudMid {
    0% {
      top: 10px;
      left: 420px;
      opacity: 0;
    }
    20% {
      top: 40px;
      left: 360px;
      opacity: 1;
    }
    70% {
      top: 130px;
      left: 180px;
      opacity: 1;
    }
    100% {
      top: 160px;
      left: 120px;
      opacity: 0;
    }
  }

  @-o-keyframes cloudMid {
    0% {
      top: 10px;
      left: 420px;
      opacity: 0;
    }
    20% {
      top: 40px;
      left: 360px;
      opacity: 1;
    }
    70% {
      top: 130px;
      left: 180px;
      opacity: 1;
    }
    100% {
      top: 160px;
      left: 120px;
      opacity: 0;
    }
  }

  @keyframes cloudMid {
    0% {
      top: 10px;
      left: 420px;
      opacity: 0;
    }
    20% {
      top: 40px;
      left: 360px;
      opacity: 1;
    }
    70% {
      top: 130px;
      left: 180px;
      opacity: 1;
    }
    100% {
      top: 160px;
      left: 120px;
      opacity: 0;
    }
  }

  @-webkit-keyframes cloudRight {
    0% {
      top: 100px;
      left: 500px;
      opacity: 0;
    }
    20% {
      top: 120px;
      left: 460px;
      opacity: 1;
    }
    80% {
      top: 180px;
      left: 340px;
      opacity: 1;
    }
    100% {
      top: 200px;
      left: 300px;
      opacity: 0;
    }
  }

  @-moz-keyframes cloudRight {
    0% {
      top: 100px;
      left: 500px;
      opacity: 0;
    }
    20% {
      top: 120px;
      left: 460px;
      opacity: 1;
    }
    80% {
      top: 180px;
      left: 340px;
      opacity: 1;
    }
    100% {
      top: 200px;
      left: 300px;
      opacity: 0;
    }
  }

  @-o-keyframes cloudRight {
    0% {
      top: 100px;
      left: 500px;
      opacity: 0;
    }
    20% {
      top: 120px;
      left: 460px;
      opacity: 1;
    }
    80% {
      top: 180px;
      left: 340px;
      opacity: 1;
    }
    100% {
      top: 200px;
      left: 300px;
      opacity: 0;
    }
  }

  @keyframes cloudRight {
    0% {
      top: 100px;
      left: 500px;
      opacity: 0;
    }
    20% {
      top: 120px;
      left: 460px;
      opacity: 1;
    }
    80% {
      top: 180px;
      left: 340px;
      opacity: 1;
    }
    100% {
      top: 200px;
      left: 300px;
      opacity: 0;
    }
  }

  .wscn-http404 .bullshit {
    position: relative;
    float: left;
    width: 380px;
    padding: 150px 0;
    overflow: hidden;
  }

  .wscn-http404 .bullshit__oops {
    font-size: 32px;
    font-weight: bold;
    line-height: 40px;
    color: #1482f0;
    opacity: 0;
    margin-bottom: 20px;
    -webkit-animation-name: slideUp;
    -moz-animation-name: slideUp;
    -o-animation-name: slideUp;
    animation-name: slideUp;
    -webkit-animation-duration: 0.5s;
    -moz-animation-duration: 0.5s;
    -o-animation-duration: 0.5s;
    animation-duration: 0.5s;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
  }

  .wscn-http404 .bullshit__headline {
    font-size: 20px;
    line-height: 24px;
    color: #434e59;
    opacity: 0;
    margin-bottom: 20px;
    -webkit-animation-name: slideUp;
    -moz-animation-name: slideUp;
    -o-animation-name: slideUp;
    animation-name: slideUp;
    -webkit-animation-duration: 0.5s;
    -moz-animation-duration: 0.5s;
    -o-animation-duration: 0.5s;
    animation-duration: 0.5s;
    -webkit-animation-delay: 0.1s;
    -moz-animation-delay: 0.1s;
    -o-animation-delay: 0.1s;
    animation-delay: 0.1s;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
  }

  .wscn-http404 .bullshit__info {
    font-size: 13px;
    line-height: 21px;
    color: grey;
    opacity: 0;
    margin-bottom: 30px;
    -webkit-animation-name: slideUp;
    -moz-animation-name: slideUp;
    -o-animation-name: slideUp;
    animation-name: slideUp;
    -webkit-animation-duration: 0.5s;
    -moz-animation-duration: 0.5s;
    -o-animation-duration: 0.5s;
    animation-duration: 0.5s;
    -webkit-animation-delay: 0.2s;
    -moz-animation-delay: 0.2s;
    -o-animation-delay: 0.2s;
    animation-delay: 0.2s;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
  }

  .wscn-http404 .bullshit__return-home {
    display: block;
    float: left;
    width: 110px;
    height: 36px;
    background: #1482f0;
    border-radius: 100px;
    text-align: center;
    color: #ffffff;
    opacity: 0;
    font-size: 14px;
    line-height: 36px;
    cursor: pointer;
    -webkit-animation-name: slideUp;
    -moz-animation-name: slideUp;
    -o-animation-name: slideUp;
    animation-name: slideUp;
    -webkit-animation-duration: 0.5s;
    -moz-animation-duration: 0.5s;
    -o-animation-duration: 0.5s;
    animation-duration: 0.5s;
    -webkit-animation-delay: 0.3s;
    -moz-animation-delay: 0.3s;
    -o-animation-delay: 0.3s;
    animation-delay: 0.3s;
    -webkit-animation-fill-mode: forwards;
    -moz-animation-fill-mode: forwards;
    -o-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
    text-decoration-line: none;
  }

  @-webkit-keyframes slideUp {
    0% {
      -webkit-transform: translateY(60px);
      transform: translateY(60px);
      opacity: 0;
    }
    100% {
      -webkit-transform: translateY(0);
      transform: translateY(0);
      opacity: 1;
    }
  }

  @-moz-keyframes slideUp {
    0% {
      -moz-transform: translateY(60px);
      transform: translateY(60px);
      opacity: 0;
    }
    100% {
      -moz-transform: translateY(0);
      transform: translateY(0);
      opacity: 1;
    }
  }

  @-o-keyframes slideUp {
    0% {
      -o-transform: translateY(60px);
      transform: translateY(60px);
      opacity: 0;
    }
    100% {
      -o-transform: translateY(0);
      transform: translateY(0);
      opacity: 1;
    }
  }

  @keyframes slideUp {
    0% {
      -webkit-transform: translateY(60px);
      -moz-transform: translateY(60px);
      -o-transform: translateY(60px);
      transform: translateY(60px);
      opacity: 0;
    }
    100% {
      -webkit-transform: translateY(0);
      -moz-transform: translateY(0);
      -o-transform: translateY(0);
      transform: translateY(0);
      opacity: 1;
    }
  }

</style>
