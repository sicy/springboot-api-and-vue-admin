import Content from '../../Content.vue'
import store from '@/store/user';

const Login = {
  name : 'Login',
  methods:{
    login:function (formName) {
      this.loginForm.logining = true;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$ajax.post('/api/auth/login', {
            user_name: this.loginForm.username,
            password: this.$getMD5(this.loginForm.password)
          }, (response) => {
            if(!response.success){
              this.$message.error(response.msg);
              this.loginForm.logining = false;
            }else{
              //保存用户信息
              store.commit('setUserInfo', {userName: response.data.user_name});

              //登录成功后 获取菜单
              this.$ajax.post('/api/menu/queryForLogin', {group_id: null, user_id: response.data.id}, (resp) => {
                const authData = resp.data;
                let userRouters = [];
                for(let n of authData){
                  let f_node = {};
                  f_node.name = n.menu_name;
                  f_node.index = n.id;
                  f_node.class = n.icon_class;
                  f_node.path = '';
                  f_node.children = [];
                  f_node.component = Content;
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
                this.$router.options.routes = userRouters;
                this.$router.ux_login = true;
                this.$router.addRoutes(userRouters);

                this.$message.success({
                  message: response.msg,
                  duration: 1000
                });
                setTimeout(() => {
                  this.$router.replace("/");
                }, 200);
                //将菜单信息缓存到sessionStorage里
                store.commit('setUserMenu', resp.data);

              });
            }
            return false;
          });
        } else {
          this.loginForm.logining = false;
          return false;
        }
      });
    }
  },
  data () {
    return {
      loginForm:{
        username:'',
        password:'',
        logining: false
      },
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { max: 50, message: '最长限制50个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  }
};

export default Login;
