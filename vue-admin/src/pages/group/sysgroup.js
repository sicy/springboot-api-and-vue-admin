export default {
  name: 'sysgroup',
  mounted: function(){
    this.query();
    this.queryMenusInTree();
  },
  created() {
    this.defaultData = JSON.parse(JSON.stringify(this.addForm));
  },
  methods:{
    //查询所有分组
    query() {
      this.loading = true;
      let url = '/api/group/querys';
      this.$ajax.post(url, {}, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.itemList = response.data;
        this.loading = false;
      });
    },
    //查询菜单树
    queryMenusInTree(){
      this.$ajax.post('/api/menu/queryMenusInTree', {}, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.menuList = response.data;
      });
    },
    //查询分组包含的菜单
    queryGroupAuth(){
      this.configLoading = true;
      this.$ajax.post('/api/group/queryGroupAuth', {groupId: this.currentId}, (response) => {
        if(!response.success){
          this.$message.error(response.msg);
        }
        this.groupMenus = [];
        for(let a of response.data){
          this.groupMenus.push(a.menu_id);
        }
        this.configLoading = false;
        this.configVisible = true;
        setTimeout(()=>{
          this.$refs.tree.setCheckedKeys(this.groupMenus);
        }, 200);
      });
    },
    //删除
    deleteGroup(id){
      this.$confirm('确定删除该分组吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.post('/api/group/delete', {id: id}, (response) => {
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
    //修改和保存
    saveMenuOrModify(){
      this.saving = true;
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          let params = this.addForm;
          let url = '';
          if(!!this.addForm.id){
            //存在ID表示 修改状态
            url = "/api/group/update";
          }else{
            url = "/api/group/save";
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
    //配置菜单
    configMenu(){
      //被选中的菜单ID 只包含二级
      let selectMenuId = this.$refs.tree.getCheckedKeys(true);
      let menuIds = '';
      for(let mid of selectMenuId){
        menuIds += mid + ',';
      }
      this.$confirm('确定为该分组分配菜单吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.post('/api/group/saveGroupAuth', {menuIds: menuIds, groupId: this.currentId}, (response) => {
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
    },

    //显示增加窗口
    showAddDialog(){
      this.formTitle = '新增';
      this.addForm.saving = false;
      if(this.$refs['addForm']){
        //reset之后将重置掉所有验证提示
        this.$refs['addForm'].resetFields();
      }
      this.addFormVisible = true;
    },
    //显示修改窗口
    showModifyDialog(row){
      this.formTitle = '修改';
      this.addForm.saving = false;
      if(this.$refs['addForm']){
        //reset之后将重置掉所有验证提示
        this.$refs['addForm'].resetFields();
      }
      this.addForm = Object.assign(this.addForm, row);
      this.addFormVisible = true;
    },
    //显示配置菜单窗口
    showConfigDialog(id){
      this.currentId = id;
      this.queryGroupAuth();
    },
  },
  data (){
    return {
      //是否在加载标记
      loading: false,
      configLoading: false,
      saving: false,
      itemList:[],
      addFormVisible: false,
      configVisible: false,
      addForm:{
        group_name: '',
        group_desc: '',
      },
      formTitle: '新增',
      addRules:{
        group_name: [
          { required: true, message: '本字段必填', trigger: 'blur' },
          { max: 50, message: '最长限制50个字符', trigger: 'blur' },
          { pattern: /^[\u4E00-\u9FA5A-Za-z0-9]+$/, message: '不能包含特殊字符!', trigger: 'blur'}
        ],
        group_desc: [
          { required: true, message: '本字段必填', trigger: 'blur' },
          { max: 50, message: '最长限制50个字符', trigger: 'blur' },
          { pattern: /^[\u4E00-\u9FA5A-Za-z0-9]+$/, message: '不能包含特殊字符!', trigger: 'blur'}
        ]
      },
      //当前配置的分组ID
      currentId: null,
      //菜单
      menuList:[],
      defaultProps: {
        children: 'chlid_list',
        label: 'menu_name'
      },
      groupMenus:[],
    }
  }
}
