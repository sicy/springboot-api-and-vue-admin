<template>
  <el-row v-loading="loading">
    <el-row class="search-form-row">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="showAddDialog">新增分组</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row>
      <el-table :data="itemList" style="width: 100%">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column align="left" prop="group_name" label="分组名称"></el-table-column>
        <el-table-column align="left" prop="group_desc" label="分组说明"></el-table-column>
        <el-table-column align="left" label="操作" width="300px">
          <template slot-scope="scope">
            <el-button type="primary" @click="showModifyDialog(scope.row)">修改</el-button>
            <el-button type="success" @click="showConfigDialog(scope.row.id)">配置菜单</el-button>
            <el-button type="danger" @click="deleteGroup(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>


    <el-dialog :title="formTitle" :visible.sync="addFormVisible" width="30%">
      <el-form :model="addForm" :rules="addRules" ref="addForm" label-width="100px">
        <el-form-item label="分组名称" prop="group_name">
          <el-input v-model="addForm.group_name" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
        <el-form-item label="分组描述" prop="group_desc">
          <el-input v-model="addForm.group_desc" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveMenuOrModify" :loading="saving">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="配置菜单" :visible.sync="configVisible" width="40%" v-loading="configLoading" @close="cleanSelected">
      <div style="max-height: 400px;overflow-y: scroll;">
        <el-tree :data="menuList" show-checkbox default-expand-all node-key="id" ref="tree" highlight-current :props="defaultProps">
        </el-tree>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="configVisible = false">取 消</el-button>
        <el-button type="primary" @click="configMenu" :loading="saving">确 定</el-button>
      </div>
    </el-dialog>

  </el-row>
</template>

<script>
  import sysgroup from './sysgroup'

  export default sysgroup;

</script>

<style>

</style>
