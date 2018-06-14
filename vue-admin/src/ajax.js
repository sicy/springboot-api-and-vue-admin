import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import store from '@/store/user';


const isDevelopment = process.env.NODE_ENV === 'development'
const config = require('../config')

axios.defaults.withCredentials = true;

const path = config.dev.assetsPublicPath || '';

/**
 * 只有开发环境时 使用这个参数
 * @type {string}
 */
const pathForDownload = isDevelopment ? config.dev.assetsPublicPath : '';

/**
 * 封装了ajax工具
 * @type {{post: ajax.post}}
 */
const ajax = {
  post: function (url, params, callback, errorCallBack) {
    axios.post(path + url,
      params
    ).then((response)=>{
      if(response.data.status === -1){
        ElementUI.Message.error("未登录");
        setTimeout(function () {
          store.dispatch('cleanSession');
          router.ux_login = false;
          router.replace("/login");
        }, 500);
        return false;
      }
      if (typeof callback === "function"){
        callback(response.data);
      }
    }).catch((error)=>{
      if (error.response) {
        if(error.response.status === 500){
          //sever error
          ElementUI.Notification.error({
            title: '错误',
            duration: 0,
            message: '服务器异常,按F12查看详情!'
          });
          console.log(error.response.data.exception);
        }
      } else if (error.request) {
        console.log(error.request);
      } else {
        console.log('Error', error);
      }
      if (typeof errorCallBack === "function"){
        errorCallBack();
      }
    });
  },
  download: function (url, callback) {
    axios({
      method: 'post',
      url: path + url,
      responseType: 'blob',
    }).then(function (response) {
      if (typeof callback === "function"){
        callback(response);
      }
    }).catch(function (error) {
        console.log(error);
    });
  }
};

export default ajax;

