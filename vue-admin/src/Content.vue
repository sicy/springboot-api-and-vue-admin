<template>
  <el-container class="home_container">
    <el-header class="navbar">
      <div class="home_title">后台管理平台</div>
      <div class="home_userinfoContainer">
        <el-dropdown @command="handleCommand" trigger="click" size="medium">
          <span class="el-dropdown-link home_userinfo">
            {{userInfo.userName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="modifyPWD">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout">注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <div class="xs-aside">
        <el-menu :default-active="activePath" class="el-menu-vertical-demo" style="background-color: #ECECEC;" :collapse="isCollapse" router>
          <el-submenu v-for="(item , index) in $router.options.routes" :key="index+''" :index="index+''" v-if="!item.hidden">
            <template slot="title">
              <i :class="item.class"></i>
              <span>{{item.name}}</span>
            </template>
            <el-menu-item v-for="(child , index) in item.children" :index="child.path"  :key="child.path" v-if="!child.hidden">
              <i :class="child.class"></i>{{child.name}}
            </el-menu-item>
          </el-submenu>
        </el-menu>
        <el-row class="collapse-buttons">
          <el-button type="primary" class="xs-shadow" icon="el-icon-d-arrow-right" @click="isCollapse = false" v-show="isCollapse" circle></el-button>
          <el-button type="primary" class="xs-shadow" style="margin-left:0" icon="el-icon-d-arrow-left" @click="isCollapse = true" v-show="!isCollapse" circle></el-button>
        </el-row>
      </div>

      <el-main>
        <el-row class="main-bread" v-show="activePath != '/404'">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item v-for="(item, index) in routeList" :key="index" :to="{ path: item.path}">
              {{ item.name }}
            </el-breadcrumb-item>
          </el-breadcrumb>
        </el-row>
        <el-row>
          <router-view />
        </el-row>
      </el-main>

    </el-container>

    <el-dialog
      title="提示"
      :visible.sync="changePWDdialogVisible"
      width="30%">
      <span>修改密码 开发中...</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="changePWDdialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="changePWDdialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </el-container>
</template>

<script>
  import store from '@/store/user';
  import { mapGetters } from 'vuex'
  import router from './router'
  import ElAside from "../node_modules/element-ui/packages/aside/src/main";
  import ElRow from "element-ui/packages/row/src/row";

  export default {
    components: {
      ElRow,
      ElAside},
    name: 'contentBody',
    methods: {
      handleCommand(command) {
        if(command === 'logout'){
          //退出登录
          store.dispatch('logout', () => {
            this.$message.info('退出登录!');
            router.ux_login = false;
            router.replace("/login");
          });
        }
        if(command === 'modifyPWD'){
          this.changePWDdialogVisible = true;
        }
      }
    },
    computed:{
      ...mapGetters({userInfo:'getUserInfo'}),
      ...mapGetters({routeList:'getRouteList'}),
      ...mapGetters({activePath:'getActivePath'}),
    },
    data(){
      return {
        changePWDdialogVisible: false,
        isCollapse:false
      }
    }
  }
</script>

<style>
  body{
    background: none #fff;
  }

  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    -moz-box-shadow: 0 2px 4px #888888; /* 老的 Firefox */
    box-shadow: 0 2px 4px #888888;
  }

  .xs-aside {
    background-color: #ECECEC;
    -webkit-box-shadow: 2px 0 4px rgba(0, 21, 41, 0.35);
    box-shadow: 2px 0 4px rgba(0, 21, 41, 0.35);
  }

  .el-main {
    background-color: #fff;
    color: #000;
    margin-top: 5px;
    margin-left: 5px;
    /*text-align: center;*/
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }


  .main-bread{
    background-color: #FFF;
    margin-bottom: 15px;
  }
  .main-content{
    background-color: #FFF;
    min-height: 800px;
  }

  .el-notification{
    z-index: 10001 !important;
  }

  .word-break{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 250px;
    min-height: 400px;
  }

  .xs-shadow{
    -moz-box-shadow: 2px 2px 5px #888888; /* 老的 Firefox */
    box-shadow: 2px 2px 5px #888888;
  }

  .collapse-buttons{
    left: 0;
    position: fixed;
    bottom: 0;
    z-index: 100;
    margin-left: 20px;
    margin-bottom: 20px;
  }

</style>
