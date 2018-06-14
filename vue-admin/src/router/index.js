import Vue from 'vue'
import Router from 'vue-router'
//引入404页面
import NotFound from '@/components/NotFound'
//引入登录页
import Login from '@/components/login'
//如果未来需要独立的欢迎页  可以在这里引入
// import Welcome from '@/components/welcome/welcome.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from '../store/user.js';

Vue.use(Router);

//目前欢迎页是个简单的div
const Welcome = {
  template: '<div>欢迎使用</div>'
};

// const NotFound = {
//   template: '<div>404 NotFound</div>'
// };

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '',
    name: '',
    component: resolve => require(['../Content.vue'], resolve),
    hidden: true,
    children:[
      //需要在内窗口显示的页面 路由就写在这里面
      {
        path: '/',
        name: 'welcome',
        component: Welcome
      },
      {
        path: '/404',
        component: NotFound,
        name: '404',
        hidden: true
      }
    ]
  }
];

const router = new Router({routes});

/**
 * 路由异常捕获
 */
router.onError(err => {
  console.error('调试打印异常...', err);
  //ElementUI.Message.error("路由切换错误 : " + err.message);
  //跳转的页面不存在时会抛出异常   这里先把404页面设置为当前活动path，用于页面显示，参考 Content.vue 第37行
  store.commit('setActivePath', "/404");
  //跳转到404页面
  router.push("/404");
});

export default router;
