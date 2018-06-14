export default {
  name: 'user',
  mounted: function(){
    this.queryPage();
    //保存下初始值
    this.defaultData = JSON.parse(JSON.stringify(this.addForm));
  },
  methods:{
    //查询按钮执行方法
    queryPage:function () {
      this.query(1);
      this.queryGroup();
    },
    //查询
    query: function (pageNum) {
      this.loading = true;
      let params ={
        // pageIndex: pageNum,
        // pageSize: this.pageInfo.pageSize
      };
      let url = '/api/users?pageIndex=' + pageNum + '&pageSize=' + this.pageInfo.pageSize;
      this.$ajax.post(url, params, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.itemList = response.data.list;
        this.pageInfo.totalNum = response.data.totalCount;
        this.loading = false;
      });
    },
    //分页size变化
    handleSizeChange(val) {
      this.pageInfo.pageSize = val;
      this.query(1);
    },
    //翻页
    handleCurrentChange(val) {
      this.query(parseInt(val));
    },
    //加载分组
    queryGroup(){
      this.$ajax.post("/api/group/querys", {}, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.groupList = response.data;
      });
    },
    //显示增加窗口()
    showAddDialog(){
      this.formTitle = '新增用户';
      this.saving = false;
      if(this.$refs['addForm']){
        this.$refs['addForm'].resetFields();
        this.addForm = Object.assign(this.addForm, this.defaultData);
      }
      this.addFormVisible = true;
    },
    showModifyDialog(row){
      this.formTitle = '修改用户';
      this.saving = false;
      this.addFormVisible = true;
      if(this.$refs['addForm']){
        this.$refs['addForm'].resetFields();
      }
      this.addForm.id = row.id;
      this.addForm.user_name = row.user_name;
      this.addForm.real_name = row.real_name;
      this.addForm.group_id = parseInt(row.group_id);
      this.addForm.expire_time = row.expire_time;
      this.addForm.password = '123';//随便设置的值 防止验证
    },
    //保存或修改用户
    saveOrUpdateUser(){
      this.saving = true;
      let url = '/api/users/insert';
      if(!!this.addForm.id){
        url = '/api/users/update';
      }
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          let params = {
            id : this.addForm.id,
            user_name : this.addForm.user_name,
            real_name : this.addForm.real_name,
            password : this.$getMD5(this.addForm.password),
            group_id : this.addForm.group_id,
            expire_time : this.addForm.expire_time
          };
          this.$ajax.post(url, params, (response) => {
            if(response.success){
              this.$message.success(response.msg);
              this.addFormVisible = false;
              this.query(1);
            }else{
              this.$message.error(response.msg);
            }
            this.saving = false;
          });
        } else {
          this.saving = false;
          return false;
        }
      });
    },
    //删除用户
    deleteUser(id){
      this.$confirm('确定删除该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.post('/api/users/delete', {id: id}, (response) => {
          if(response.success){
            this.$message.success(response.msg);
            this.addFormVisible = false;
            this.query(1);
          }else{
            this.$message.error(response.msg);
          }
        });
      });
    },
    //显示配置权限窗口
    showConfigAuth(row){
      this.currentId = row.id;
      this.$ajax.post('/api/menu/queryMenusInTree', {group_id: row.group_id}, (response) => {
        if(response.success){
          this.menuList = response.data;
          this.configVisible = true;
          this.userMenus = [];
          for(let a of row.auths){
            this.userMenus.push(a.id);
          }
          this.configLoading = false;
          this.configVisible = true;
          setTimeout(()=>{
            this.$refs.tree.setCheckedKeys(this.userMenus);
          }, 200);
        }else{
          this.$message.error(response.msg);
        }
      });
    },
    configAuth(){
      //被选中的菜单ID 只包含二级
      let selectMenuId = this.$refs.tree.getCheckedKeys(true);
      let menuIds = '';
      for(let mid of selectMenuId){
        menuIds += mid + ',';
      }
      this.$confirm('确定为该用户分配菜单吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.post('/api/auth/saveAuths', {menuIds: menuIds, userId: this.currentId}, (response) => {
          if(response.success){
            this.$message.success(response.msg);
            this.configVisible = false;
            this.query();
          }else{
            this.$message.error(response.msg);
          }
        });
      });
    },
    //树形控件重置
    cleanSelected(){
      this.$refs.tree.setCheckedKeys([]);
    }
  },
  data (){
    return {
      //存放搜索框数据
      searchForm:{
        name: '',
        type: '-1',
        source: '-1'
      },
      //存放列表数据
      itemList:[

      ],
      //存放分页数据
      pageInfo:{
        currentPage: 1,
        pageSize: 10,
        totalNum: 0
      },
      //分组数据
      groupList:[],
      //是否在加载标记
      loading: true,
      saving: false,
      addFormVisible: false,
      importVisible: false,
      addForm: {
        id : null,
        user_name: '',
        real_name: '',
        password: '123456',
        group_id: '',
        expire_time: '2099-12-31',
      },
      formTitle:'新增用户',
      addRules:{
        user_name: [
          { required: true, message: '本字段必填', trigger: 'blur' },
          { max: 50, message: '最长限制50个字符', trigger: 'blur' },
          { pattern: /^[\u4E00-\u9FA5A-Za-z0-9]+$/, message: '不能包含特殊字符!', trigger: 'blur'}
        ],
        real_name:[
          { required: true, message: '本字段必填', trigger: 'blur' },
          { max: 50, message: '最长限制50个字符', trigger: 'blur' },
          { pattern: /^[\u4E00-\u9FA5A-Za-z0-9]+$/, message: '不能包含特殊字符!', trigger: 'blur'}
        ],
        password:[
          { required: true, message: '本字段必填', trigger: 'blur' },
          { max: 50, message: '最长限制50个字符', trigger: 'blur' },
          { pattern: /^[\u4E00-\u9FA5A-Za-z0-9]+$/, message: '不能包含特殊字符!', trigger: 'blur'}
        ],
        group_id:[
          { required: true, message: '本字段必填', trigger: 'blur' },
        ],
        expire_time:[
          { required: true, message: '本字段必填', trigger: 'blur' },
        ]
      },

      configVisible: false,
      configLoading: false,
      menuList:[],
      defaultProps: {
        children: 'chlid_list',
        label: 'menu_name'
      },
      currentId: null,
      userMenus:[],
    }
  }
}
