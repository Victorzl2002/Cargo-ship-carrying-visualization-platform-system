<!--
 * @Author: Victorzl
 * @Date: 2024-04-07 18:40:06
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-04-07 19:53:20
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
      <el-form-item label="终端位置" prop="terminalLoacation">
        <el-input
          v-model="queryParams.terminalLoacation"
          placeholder="请输入终端位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="安装日期" prop="installationDate">
        <el-date-picker
          clearable
          v-model="queryParams.installationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择安装日期"
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
          v-hasPermi="['terminalmanage:terminalmanage:add']"
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
          v-hasPermi="['terminalmanage:terminalmanage:edit']"
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
          v-hasPermi="['terminalmanage:terminalmanage:remove']"
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
          v-hasPermi="['terminalmanage:terminalmanage:export']"
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
      :data="terminalmanageList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="终端编号" align="center" prop="terminalId" />
      <el-table-column label="终端类型" align="center" prop="terminalType" />
      <el-table-column label="终端状态" align="center" prop="terminalStatus" />
      <el-table-column
        label="终端位置"
        align="center"
        prop="terminalLoacation"
      />
      <el-table-column
        label="安装日期"
        align="center"
        prop="installationDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.installationDate, "{y}-{m}-{d}")
          }}</span>
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
            v-hasPermi="['terminalmanage:terminalmanage:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['terminalmanage:terminalmanage:remove']"
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

    <!-- 添加或修改terminalmanage对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="终端Id" prop="terminalId">
          <el-input v-model="form.terminalId" placeholder="请输入终端Id" />
        </el-form-item>
        <el-form-item label="终端类型" prop="terminalType">
          <el-input v-model="form.terminalType" placeholder="请输入终端类型" />
        </el-form-item>
        <el-form-item label="终端状态" prop="terminalStatus">
          <el-input
            v-model="form.terminalStatus"
            placeholder="请输入终端状态"
          />
        </el-form-item>
        <el-form-item label="终端位置" prop="terminalLoacation">
          <el-input
            v-model="form.terminalLoacation"
            placeholder="请输入终端位置"
          />
        </el-form-item>
        <el-form-item label="安装日期" prop="installationDate">
          <el-date-picker
            clearable
            v-model="form.installationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择安装日期"
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
import { listTerminalmanage, getTerminalmanage, delTerminalmanage, addTerminalmanage, updateTerminalmanage } from "@/api/terminalmanage/terminalmanage";

export default {
  name: "Terminalmanage",
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
      // terminalmanage表格数据
      terminalmanageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        terminalId: null,
        terminalType: null,
        terminalStatus: null,
        terminalLoacation: null,
        installationDate: null
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
    /** 查询terminalmanage列表 */
    getList() {
      this.loading = true;
      listTerminalmanage(this.queryParams).then(response => {
        this.terminalmanageList = response.rows;
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
        terminalId: null,
        terminalType: null,
        terminalStatus: null,
        terminalLoacation: null,
        installationDate: null
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
      this.ids = selection.map(item => item.terminalId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加terminalmanage";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const terminalId = row.terminalId || this.ids
      getTerminalmanage(terminalId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改terminalmanage";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          let id = this.form.terminalId
          getTerminalmanage(id).then(response => {
            if (response.data != null) {
              updateTerminalmanage(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addTerminalmanage(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          });

        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const terminalIds = row.terminalId || this.ids;
      this.$modal.confirm('是否确认删除terminalmanage编号为"' + terminalIds + '"的数据项？').then(function () {
        return delTerminalmanage(terminalIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('terminalmanage/terminalmanage/export', {
        ...this.queryParams
      }, `terminalmanage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
