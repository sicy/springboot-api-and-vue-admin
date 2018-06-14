export default {
  name: 'sysmenu',
  mounted: function(){
    this.queryFirstMenus();
    this.query();
  },
  created() {
    //保存下初始值
    this.defaultData = JSON.parse(JSON.stringify(this.addForm));
  },
  methods:{
    //查询
    query: function () {
      this.loading = true;
      let url = '/api/menu/query';
      this.$ajax.post(url, {}, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.itemList = response.data;
        this.loading = false;
      });
    },
    //查询父菜单
    queryFirstMenus(){
      this.$ajax.post('/api/menu/query', {level: 0}, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.firstMenuList = response.data;
      });
    },
    //删除菜单
    deleteMenu(id){
      this.$confirm('确定删除该菜单吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.post('/api/menu/delete', {id: id}, (response) => {
          if(response.success){
            this.$message.success(response.msg);
            this.addFormVisible = false;
            this.query();
          }else{
            this.$message.error(response.msg);
          }
        });
      });
    },
    //显示增加窗口()
    showAddDialog(){
      this.formTitle = '新增菜单';
      this.addForm.saving = false;
      if(this.$refs['addForm']){
        //reset之后将重置掉所有验证提示
        this.$refs['addForm'].resetFields();
        this.addForm = Object.assign(this.addForm, this.defaultData);
      }
      this.addFormVisible = true;
    },
    //保存或修改
    saveMenuOrModify(){
      this.saving = true;
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          let params = this.addForm;
          let url = '';
          if(!!this.addForm.id){
            //存在ID表示 修改状态
            url = "/api/menu/update";
          }else{
            url = "/api/menu/save";
          }
          this.$ajax.post(url, params, (response) => {
            if(response.success){
              this.$message.success(response.msg);
              this.addFormVisible = false;
              this.saving = false;
              this.query();
            }else{
              this.$message.error(response.msg);
            }
          });
        } else {
          this.saving = false;
          return false;
        }
      });
    },
    //显示修改窗口
    showModifyDialog(row){
      this.formTitle = '修改菜单';
      this.addForm.saving = false;
      if(this.$refs['addForm']){
        //reset之后将重置掉所有验证提示
        this.$refs['addForm'].resetFields();
        this.addForm = Object.assign(this.addForm, this.defaultData);
      }
      this.addForm = Object.assign(this.addForm, row);
      this.addForm.level = row.level+'';
      this.addFormVisible = true;
    },

    //级别单选框变化事件
    changeLevel(level){
      if(level === '0'){
        //每次选到1级就重置下面两个参数
        this.addForm.fid = '';
        this.addForm.url = '';
      }
    },
    //打开图标样式页面
    openFontWebpage(){
      window.open('http://fontawesome.dashgame.com/');
    }
  },
  data (){
    let validateByLevel = (rule, value, callback) => {
      if (this.addForm.level === '0') {
        callback();
      } else {
        //只有选择为二级菜单时 才需要验证是否为空
        if(!value){
          callback(new Error('本字段必填'));
        }
        callback();
      }
    };
    return {
      addFormVisible:false,
      //存放列表数据
      itemList:[

      ],
      //父菜单
      firstMenuList:[

      ],
      //是否在加载标记
      loading: true,
      saving: false,
      addForm: {
        id : null,
        menu_name: '',
        url: '',
        icon_class: '',
        level: '0',
        fid: '',
        order_code: '',
        state: 1
      },
      formTitle:'新增用户',
      addRules:{
        menu_name: [
          { required: true, message: '本字段必填', trigger: 'blur' },
        ],
        icon_class: [
          { required: true, message: '本字段必填', trigger: 'blur' },
        ],
        order_code: [
          { required: true, message: '本字段必填', trigger: 'blur' },
          { pattern: /^\d+(\.\d+)?$/, message: '请输入大于等于0的数字', trigger: 'blur'}
        ],
        url:[
          { validator: validateByLevel, trigger: 'blur' }
        ],
        fid:[
          { validator: validateByLevel, trigger: 'blur' }
        ],
      }
    }
  }
}
