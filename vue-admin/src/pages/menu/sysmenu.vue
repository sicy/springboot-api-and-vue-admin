<template>
  <el-row v-loading="loading">
    <el-row class="search-form-row">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="showAddDialog">新增菜单</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row>
      <el-table :data="itemList" style="width: 100%">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column align="left" prop="menu_name" label="菜单名称"></el-table-column>
        <el-table-column align="left" label="级别">
          <template slot-scope="scope">
            <span v-show="scope.row.level == 0" style="color: red;">一级</span>
            <span v-show="scope.row.level == 1">二级</span>
          </template>
        </el-table-column>
        <el-table-column align="left" prop="fname" label="父菜单"></el-table-column>
        <el-table-column align="left" prop="url" label="URL"></el-table-column>
        <el-table-column align="left" label="样式">
          <template slot-scope="scope">
            <i :class="scope.row.icon_class"></i>
            <span>{{ scope.row.icon_class }}</span>
          </template>
        </el-table-column>
        <el-table-column align="left" label="操作" width="300px">
          <template slot-scope="scope">
            <el-button type="primary" @click="showModifyDialog(scope.row)">修改</el-button>
            <el-button type="danger" @click="deleteMenu(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>


    <el-dialog :title="formTitle" :visible.sync="addFormVisible" width="30%">
      <el-form :model="addForm" :rules="addRules" ref="addForm" label-width="100px">
        <el-form-item label="菜单名称" prop="menu_name">
          <el-input v-model="addForm.menu_name" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
        <el-form-item label="级别" prop="level">
          <el-radio-group v-model="addForm.level" @change="changeLevel" :disabled="!!addForm.id">
            <el-radio label="0" border>一级</el-radio>
            <el-radio label="1" border>二级</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="父菜单" prop="fid" v-show="addForm.level == 1" >
          <el-select placeholder="请选择" style="width: 90%;" v-model="addForm.fid">
            <el-option v-for="item in firstMenuList" :key="item.id" :label="item.menu_name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="URL" prop="url" v-show="addForm.level == 1">
          <el-input v-model="addForm.url" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
        <el-form-item label="样式" prop="icon_class">
          <el-input v-model="addForm.icon_class" auto-complete="off" style="width: 90%;"></el-input>
          <el-button type="text" @click="openFontWebpage">点击查询样式</el-button>
        </el-form-item>
        <el-form-item label="排序" prop="order_code">
          <el-input v-model="addForm.order_code" auto-complete="off" style="width: 90%;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveMenuOrModify" :loading="saving">确 定</el-button>
      </div>
    </el-dialog>

  </el-row>
</template>

<script>
  import sysmenu from './sysmenu'

  export default sysmenu;

</script>

<style>

</style>
