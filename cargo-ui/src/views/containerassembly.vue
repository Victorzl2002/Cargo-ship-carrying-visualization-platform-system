<!--
 * @Author: Victorzl
 * @Date: 2024-02-12 17:10:40
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-02-12 17:10:44
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
      <el-form-item label="航运计划id" prop="scheduleId">
        <el-input
          v-model="queryParams.scheduleId"
          placeholder="请输入航运计划id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="集装箱id" prop="containerId">
        <el-input
          v-model="queryParams.containerId"
          placeholder="请输入集装箱id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船箱位id" prop="slotId">
        <el-input
          v-model="queryParams.slotId"
          placeholder="请输入船箱位id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="装配时间" prop="assemblyTime">
        <el-date-picker
          clearable
          v-model="queryParams.assemblyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择装配时间"
        >
        </el-date-picker>
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
          v-hasPermi="['containerassembly:containerassembly:add']"
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
          v-hasPermi="['containerassembly:containerassembly:edit']"
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
          v-hasPermi="['containerassembly:containerassembly:remove']"
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
          v-hasPermi="['containerassembly:containerassembly:export']"
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
      :data="containerassemblyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="装配id" align="center" prop="assemblyId" />
      <el-table-column label="航运计划id" align="center" prop="scheduleId" />
      <el-table-column label="集装箱id" align="center" prop="containerId" />
      <el-table-column label="船箱位id" align="center" prop="slotId" />
      <el-table-column
        label="装配时间"
        align="center"
        prop="assemblyTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.assemblyTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['containerassembly:containerassembly:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['containerassembly:containerassembly:remove']"
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

    <!-- 添加或修改集装箱装配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="航运计划id" prop="scheduleId">
          <el-input v-model="form.scheduleId" placeholder="请输入航运计划id" />
        </el-form-item>
        <el-form-item label="集装箱id" prop="containerId">
          <el-input v-model="form.containerId" placeholder="请输入集装箱id" />
        </el-form-item>
        <el-form-item label="船箱位id" prop="slotId">
          <el-input v-model="form.slotId" placeholder="请输入船箱位id" />
        </el-form-item>
        <el-form-item label="装配时间" prop="assemblyTime">
          <el-date-picker
            clearable
            v-model="form.assemblyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择装配时间"
          >
          </el-date-picker>
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
import { listContainerassembly, getContainerassembly, delContainerassembly, addContainerassembly, updateContainerassembly } from "@/api/containerassembly/containerassembly";

export default {
  name: "Containerassembly",
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
      // 集装箱装配表格数据
      containerassemblyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scheduleId: null,
        containerId: null,
        slotId: null,
        assemblyTime: null
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
    /** 查询集装箱装配列表 */
    getList() {
      this.loading = true;
      listContainerassembly(this.queryParams).then(response => {
        this.containerassemblyList = response.rows;
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
        assemblyId: null,
        scheduleId: null,
        containerId: null,
        slotId: null,
        assemblyTime: null
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
      this.ids = selection.map(item => item.assemblyId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加集装箱装配";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const assemblyId = row.assemblyId || this.ids
      getContainerassembly(assemblyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改集装箱装配";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.assemblyId != null) {
            updateContainerassembly(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContainerassembly(this.form).then(response => {
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
      const assemblyIds = row.assemblyId || this.ids;
      this.$modal.confirm('是否确认删除集装箱装配编号为"' + assemblyIds + '"的数据项？').then(function () {
        return delContainerassembly(assemblyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('containerassembly/containerassembly/export', {
        ...this.queryParams
      }, `containerassembly_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
