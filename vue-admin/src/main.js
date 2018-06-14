// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import crypto from 'crypto'
import moment from 'moment'
import store from './store/user.js';
//引入font-awesome字体
import 'font-awesome/css/font-awesome.min.css'

const config = require('../config')

//下面两行代码是模拟数据用的  正式环境请删除
// import mock from './mock'
// mock.bootstrap();

Vue.config.productionTip = false;
Vue.use(ElementUI, { size: 'mini' });

import VueCookies from 'vue-cookies';
Vue.use(VueCookies);

import http from './ajax'
Vue.prototype.$ajax = http;

/**
 * 全局date格式化过滤器  不要修改
 * 用法 {{ scope.row.createTime | date('YYYY-MM-DD hh:mm:ss') }}
 */
Vue.filter('date', function (value, format) {
  if (!value) return '';
  if (!format) return value;

  value = parseInt(value);
  return moment(value).format(format);
});

/**
 * 格式化工具
 * 用法：  this.$dateFormat(date, format);
 * @param date
 * @param format
 * @returns {*}
 */
Vue.prototype.$dateFormat = function (date, format) {
  if (!date) return '';
  if (!format) return date;
  return moment(date).format(format);
};

/**
 * md5加密方法  不要修改
 * 用法：  this.$getMD5(str)
 * @param str
 * @returns {*}
 */
Vue.prototype.$getMD5 = function(str){
  const md5 = crypto.createHash("md5");
  md5.update(str);
  return md5.digest('hex');
};

//下面两个增加到JavaScript的自定义方法  使用方式： var str = 'aavv'; var flag = str.endsWith('vv');
//判断当前字符串是否以str开始 先判断是否存在function是避免和js原生方法冲突，自定义方法的效率不如原生的高
if (typeof String.prototype.startsWith !== 'function'){
  String.prototype.startsWith = function (str){
    return this.slice(0, str.length) === str;
  };
}

//判断当前字符串是否以str结束
if (typeof String.prototype.endsWith !== 'function') {
  String.prototype.endsWith = function (str){
    return this.slice(-str.length) === str;
  };
}

//刷新页面时  vue框架会重新加载  这时候需要去localStorage中获取菜单缓存重建菜单
let menuData = window.localStorage.userMenu;
if(menuData){
  let authData = JSON.parse(menuData);
  let userRouters = [];
  for(let n of authData){
    let f_node = {};
    f_node.name = n.menu_name;
    f_node.index = n.id;
    f_node.class = n.icon_class;
    f_node.path = '';
    f_node.children = [];
    f_node.component = resolve => require(['./Content.vue'], resolve);
    for(let c of n.chlid_list){
      let c_node = {};
      c_node.name = c.menu_name;
      c_node.path = c.url;
      c_node.index = c.id;
      c_node.component = resolve => require(['@/pages'+c.url+''], resolve);
      c_node.hidden = c.hidden || false;
      c_node.class = c.icon_class;
      f_node.children.push(c_node);
    }
    userRouters.push(f_node);
  }
  // userRouters.push({path:'*', redirect:'/404', hidden: true});
  router.options.routes = userRouters;
  router.addRoutes(userRouters);
}

//刷新页面时产生一个ajax请求到后台 更新菜单信息
http.post('/api/menu/queryForLogin', {}, (resp) => {
  //将菜单信息缓存到sessionStorage里
  store.commit('setUserMenu', resp.data);
});

router.beforeEach((route, redirect, next) => {
  let menuData = window.localStorage.userMenu;
  if(router.ux_login === false && route.path === '/login'){
    //这里不使用router进行跳转，是因为，跳转到登录页面的时候，是需要重新登录，获取数据的，这个时候，会再次向router实例里面add路由规则，
    //而next()跳转过去之后，没有刷新页面，之前的规则还是存在的，但是使用location的话，可以刷新页面，当刷新页面的时候，整个app会重新加载
    //而我们在刷新之前已经把sessionStorage里的user移除了，所以上面的添加路由也不行执行
    //跳转到项目名称位置
    window.location.href = config.apiPath;
    return false
  }
  if (!menuData && route.path !== '/login') {
    next({ path: '/login' })
  } else {
    //跳转的URL匹配到了路由，那么就从这里走
    if (route.matched.length > 0) {
      //跳转前  做点操作
      if(router.options.routes.length > 0){
        let matchedPath = route.matched[route.matched.length-1].path + '/';
        //将跳转的路由加到一个list中，用来显示面包蟹，就是导航条
        store.commit('setRouteList', {router: router, matchedPath: matchedPath});
        //设置当前活动path，用于菜单高亮显示
        store.commit('setActivePath', route.path);
      }
      next();
    } else {
      //否则 跳到404
      next({ path: '/404' })
    }
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
});
