<template>
  <el-row type="flex" class="login-body" justify="center">
    <el-col>
      <el-form ref="loginForm" :model="loginForm" :rules="rules" class="form-signin">
        <h2 class="form-signin-heading">用户登录</h2>
        <div class="login-wrap">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="用户名" ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"
                      @keyup.native.enter="login('loginForm')"></el-input>
          </el-form-item>
          <el-button type="danger" class="btn-login" @click="login('loginForm')" :loading="loginForm.logining">登录</el-button>
          <p class="text-align-center">欢迎您使用后台管理平台</p>
        </div>
      </el-form>
    </el-col>
  </el-row>
</template>
<script>

  import LoginJS from './login'

  export default LoginJS

</script>
<style>
  .form-signin {
    max-width: 330px;
    margin: 100px auto 0;
    background: #fff;
    border-radius: 5px;
    -webkit-border-radius: 5px;
  }
  body{
    background: none #f1f2f7;
  }
  .form-signin h2.form-signin-heading {
    margin: 0;
    padding: 20px 15px;
    text-align: center;
    background: #41cac0;
    border-radius: 5px 5px 0 0;
    -webkit-border-radius: 5px 5px 0 0;
    color: #fff;
    font-size: 18px;
    text-transform: uppercase;
    font-weight: 300;
    font-family: 'Open Sans', sans-serif;
  }
  .login-wrap {
    padding: 20px;
  }
  .login-wrap p {
    margin-bottom: 10px;
    color: #bbb;
  }
  .text-align-center {
    text-align: center;
  }

  .btn-login {
    width: 100%;
  }


</style>
