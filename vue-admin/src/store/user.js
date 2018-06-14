import Vue from 'vue';
import Vuex from 'vuex';
import http from '../ajax'

Vue.use(Vuex);

const userStore=new Vuex.Store({
  state:{
    //存放用户名
    userInfo: window.localStorage.userInfo? JSON.parse(window.localStorage.userInfo) : {},
    //存放用户菜单
    userMenu: window.localStorage.userMenu? JSON.parse(window.localStorage.userMenu) : [],
    //存放路由导航列表 用于显示在面包蟹上
    routeList: [],
    //当前激活的菜单
    activePath: ''
  },
  getters:{
    getUserInfo(state){
      return state.userInfo;
    },
    getUserMenu(state){
      return state.userMenu;
    },
    getRouteList(state){
      return state.routeList;
    },
    getActivePath(state){
      return state.activePath;
    }
  },
  mutations:{
    /**
     * 缓存用户名
     * @param state
     * @param userInfo
     */
    setUserInfo(state, userInfo){
      window.localStorage.userInfo = JSON.stringify(userInfo);
      state.userInfo = userInfo;
    },
    /**
     * 缓存菜单
     * @param state
     * @param menus
     */
    setUserMenu(state, menus){
      window.localStorage.userMenu = JSON.stringify(menus);
      state.userMenu = menus;
    },

    /**
     * 设置 routeList
     * @param state
     * @param param
     */
    setRouteList(state, param){
      state.routeList = [];
      let routeList = [];
      routeList.push({
        path : '/',
        name: '首页',
        index: -99,
        order : 1
      });
      for(let p of param.router.options.routes){
        if(!p.children){
          return;
        }
        for(let c of p.children){
          if(param.matchedPath.indexOf(c.path + '/') > -1){
            //用来排序
            let r = {};
            r.path = c.path;
            r.order = c.path.length;
            r.name = c.name;
            r.index = c.index;
            routeList.push(r);
          }
        }
      }
      //排序
      routeList.sort((a, b)=>{
        return a.order - b.order;
      });
      routeList[routeList.length - 1].path = '';
      state.routeList = routeList;
    },
    setActivePath(state, path){
      let i = path.indexOf("/", 1);
      if(i === -1){
        state.activePath = path;
      }else{
        state.activePath = path.substr(0, i);
      }
    }
  },
  actions:{
    logout({commit}, callback){
      http.post('/api/auth/logout', {}, function (resp) {
        window.localStorage.removeItem('userInfo');
        window.localStorage.removeItem('userMenu');
        callback();
      });
    },
    cleanSession({commit}){
      window.localStorage.removeItem('userInfo');
      window.localStorage.removeItem('userMenu');
    }
  }

});

export default userStore;
