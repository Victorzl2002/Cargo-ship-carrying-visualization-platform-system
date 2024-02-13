<!--
 * @Author: Victorzl
 * @Date: 2024-01-19 21:20:19
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-19 21:20:23
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
      <el-form-item label="空间利用率" prop="rate">
        <el-input
          v-model="queryParams.rate"
          placeholder="请输入空间利用率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录时间" prop="createdate">
        <el-date-picker
          clearable
          v-model="queryParams.createdate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录时间"
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
          v-hasPermi="['spacenote:spacenote:add']"
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
          v-hasPermi="['spacenote:spacenote:edit']"
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
          v-hasPermi="['spacenote:spacenote:remove']"
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
          v-hasPermi="['spacenote:spacenote:export']"
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
      :data="spacenoteList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="集装箱空间利用率记录id"
        align="center"
        prop="spaceutilizationrateId"
      />
      <el-table-column label="航运计划id" align="center" prop="scheduleId" />
      <el-table-column label="集装箱id" align="center" prop="containerId" />
      <el-table-column label="空间利用率" align="center" prop="rate" />
      <el-table-column
        label="记录时间"
        align="center"
        prop="createdate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdate, "{y}-{m}-{d}") }}</span>
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
            v-hasPermi="['spacenote:spacenote:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['spacenote:spacenote:remove']"
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

    <!-- 添加或修改集装箱空间利用率对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="航运计划id" prop="scheduleId">
          <el-input v-model="form.scheduleId" placeholder="请输入航运计划id" />
        </el-form-item>
        <el-form-item label="集装箱id" prop="containerId">
          <el-input v-model="form.containerId" placeholder="请输入集装箱id" />
        </el-form-item>
        <el-form-item label="空间利用率" prop="rate">
          <el-input v-model="form.rate" placeholder="请输入空间利用率" />
        </el-form-item>
        <el-form-item label="记录时间" prop="createdate">
          <el-date-picker
            clearable
            v-model="form.createdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录时间"
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
import { listSpacenote, getSpacenote, delSpacenote, addSpacenote, updateSpacenote } from "@/api/spacenote/spacenote";

export default {
  name: "Spacenote",
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
      // 集装箱空间利用率表格数据
      spacenoteList: [],
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
        rate: null,
        createdate: null
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
    /** 查询集装箱空间利用率列表 */
    getList() {
      this.loading = true;
      listSpacenote(this.queryParams).then(response => {
        this.spacenoteList = response.rows;
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
        spaceutilizationrateId: null,
        scheduleId: null,
        containerId: null,
        rate: null,
        createdate: null
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
      this.ids = selection.map(item => item.spaceutilizationrateId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加集装箱空间利用率";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const spaceutilizationrateId = row.spaceutilizationrateId || this.ids
      getSpacenote(spaceutilizationrateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改集装箱空间利用率";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.spaceutilizationrateId != null) {
            updateSpacenote(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSpacenote(this.form).then(response => {
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
      const spaceutilizationrateIds = row.spaceutilizationrateId || this.ids;
      this.$modal.confirm('是否确认删除集装箱空间利用率编号为"' + spaceutilizationrateIds + '"的数据项？').then(function () {
        return delSpacenote(spaceutilizationrateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('spacenote/spacenote/export', {
        ...this.queryParams
      }, `spacenote_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
