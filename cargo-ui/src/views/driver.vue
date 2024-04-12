<!--
 * @Author: Victorzl
 * @Date: 2024-04-07 13:44:26
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-04-07 13:44:30
 * @Description: 请填写简介
-->
<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="司机姓名" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入司机姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证" prop="identitiyNumber">
        <el-input
          v-model="queryParams.identitiyNumber"
          placeholder="请输入身份证"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="驾驶证" prop="licenseNumber">
        <el-input
          v-model="queryParams.licenseNumber"
          placeholder="请输入驾驶证"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="driverPhone">
        <el-input
          v-model="queryParams.driverPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属公司" prop="dirverCompany">
        <el-input
          v-model="queryParams.dirverCompany"
          placeholder="请输入所属公司"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['driver:driver:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['driver:driver:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['driver:driver:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['driver:driver:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="driverList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="司机Id" align="center" prop="driverId" />
      <el-table-column label="司机姓名" align="center" prop="driverName" />
      <el-table-column label="身份证" align="center" prop="identitiyNumber" />
      <el-table-column label="驾驶证" align="center" prop="licenseNumber" />
      <el-table-column label="联系电话" align="center" prop="driverPhone" />
      <el-table-column label="驾驶状态" align="center" prop="drivingStatus" />
      <el-table-column label="是否在线" align="center" prop="onlineStatus" />
      <el-table-column label="所属公司" align="center" prop="dirverCompany" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['driver:driver:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['driver:driver:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改driver对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="司机姓名" prop="driverName">
          <el-input v-model="form.driverName" placeholder="请输入司机姓名" />
        </el-form-item>
        <el-form-item label="身份证" prop="identitiyNumber">
          <el-input v-model="form.identitiyNumber" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="驾驶证" prop="licenseNumber">
          <el-input v-model="form.licenseNumber" placeholder="请输入驾驶证" />
        </el-form-item>
        <el-form-item label="联系电话" prop="driverPhone">
          <el-input v-model="form.driverPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="所属公司" prop="dirverCompany">
          <el-input v-model="form.dirverCompany" placeholder="请输入所属公司" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDriver, getDriver, delDriver, addDriver, updateDriver } from "@/api/driver/driver";

export default {
  name: "Driver",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // driver表格数据
      driverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        driverName: null,
        identitiyNumber: null,
        licenseNumber: null,
        driverPhone: null,
        drivingStatus: null,
        onlineStatus: null,
        dirverCompany: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询driver列表 */
    getList() {
      this.loading = true;
      listDriver(this.queryParams).then(response => {
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        driverId: null,
        driverName: null,
        identitiyNumber: null,
        licenseNumber: null,
        driverPhone: null,
        drivingStatus: null,
        onlineStatus: null,
        dirverCompany: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.driverId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加driver";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const driverId = row.driverId || this.ids
      getDriver(driverId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改driver";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.driverId != null) {
            updateDriver(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDriver(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const driverIds = row.driverId || this.ids;
      this.$modal.confirm('是否确认删除driver编号为"' + driverIds + '"的数据项？').then(function () {
        return delDriver(driverIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('driver/driver/export', {
        ...this.queryParams
      }, `driver_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
