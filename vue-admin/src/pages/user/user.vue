<template>
  <el-row v-loading="loading">
    <!--<el-row class="search-form-row" style="padding: 10px;">用户管理</el-row>-->
    <el-row class="search-form-row">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="showAddDialog">新增用户</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row>
      <el-table :data="itemList" style="width: 100%">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column align="left" prop="user_name" label="用户名"></el-table-column>
        <el-table-column align="left" prop="real_name" label="姓名"></el-table-column>
        <el-table-column align="left" prop="group_name" label="所属分组"></el-table-column>
        <el-table-column align="left" prop="expire_time" label="有效期"></el-table-column>
        <el-table-column align="left" label="操作" width="300px">
          <template slot-scope="scope">
            <el-button size="mini" type="success" @click="showModifyDialog(scope.row)">修改</el-button>
            <el-button size="mini" type="primary" @click="showConfigAuth(scope.row)">配置权限</el-button>
            <el-button size="mini" type="danger" @click="deleteUser(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="text-align: right;margin-top: 10px">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageInfo.currentPage"
        :page-sizes="[10, 20, 50]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageInfo.totalNum">
      </el-pagination>
    </el-row>

    <el-dialog :title="formTitle" :visible.sync="addFormVisible" width="30%">
      <el-form :model="addForm" :rules="addRules" ref="addForm" label-width="100px">
        <el-form-item label="用户名" prop="user_name">
          <el-input v-model="addForm.user_name" auto-complete="off" style="width: 90%;" :disabled="!!addForm.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="real_name">
          <el-input v-model="addForm.real_name" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" v-show="!addForm.id">
          <el-input v-model="addForm.password" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
        <el-form-item label="所属分组" prop="group_id">
          <!-- 这里 下拉框里绑定的值是一个对象  必须在select里设置 value-key="id" -->
          <el-select v-model="addForm.group_id" value-key="id" placeholder="请选择分组" style="width: 90%;" :disabled="!!addForm.id">
            <el-option :value="cc.id" :key="cc.id" v-for="cc in groupList" :label="cc.group_name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="有效期" prop="expire_time">
          <el-date-picker type="date" placeholder="选择日期" v-model="addForm.expire_time" style="width: 90%;"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateUser" :loading="saving">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="配置权限" :visible.sync="configVisible" width="40%" v-loading="configLoading" @close="cleanSelected">
      <div style="max-height: 300px;">
        <el-tree :data="menuList" show-checkbox default-expand-all node-key="id" ref="tree" highlight-current :props="defaultProps">
        </el-tree>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="configVisible = false">取 消</el-button>
        <el-button type="primary" @click="configAuth" :loading="saving">确 定</el-button>
      </div>
    </el-dialog>

  </el-row>
</template>

<script>
  import user from './user'

  export default user;

</script>

<style>
  .search-form-row{
    text-align: left;
  }

</style>
