<!--
 * @Author: Victorzl
 * @Date: 2023-12-30 16:27:35
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-17 18:34:49
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
      <el-form-item label="货轮id" prop="shipId">
        <el-input
          v-model="queryParams.shipId"
          placeholder="请输入货轮id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="航线id" prop="routeId">
        <el-input
          v-model="queryParams.routeId"
          placeholder="请输入航线id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="终点港口" prop="endingPort">
        <el-input
          v-model="queryParams.endingPort"
          placeholder="请输入终点港口"
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['routeschedule:routeschedule:export']"
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
      :data="routescheduleList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="航线计划id"
        align="center"
        prop="routescheduleId"
      />
      <el-table-column label="货轮id" align="center" prop="shipId" />
      <el-table-column label="航线id" align="center" prop="routeId" />
      <el-table-column label="出发港口" align="center" prop="departurePort" />
      <el-table-column label="终点港口" align="center" prop="endingPort" />
      <el-table-column
        label="预计到港时间"
        align="center"
        prop="startTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.startTime, "{y}-{m}-{d} {hh}:{ii}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="预计出港时间"
        align="center"
        prop="endTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.endTime, "{y}-{m}-{d} {hh}:{ii}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="航线距离(海里)"
        align="center"
        prop="routeDistance"
      />
      <el-table-column
        label="预计航行时间(小时)"
        align="center"
        prop="estimatedDuration"
      />
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listRouteschedule2, getRouteschedule, delRouteschedule, addRouteschedule, updateRouteschedule } from "@/api/routeschedule/routeschedule";

export default {
  name: "Routeschedule",
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
      // 航运计划表格数据
      routescheduleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        shipId: null,
        routeId: null,
        departurePort: null,
        endingPort: null,
        startTime: null,
        endTime: null,
        routeDistance: null,
        estimatedDuration: null
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
    /** 查询航运计划列表 */
    getList() {
      this.loading = true;
      listRouteschedule2(this.queryParams).then(response => {
        this.routescheduleList = response.rows;
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
        routescheduleId: null,
        shipId: null,
        routeId: null,
        departurePort: null,
        endingPort: null,
        startTime: null,
        routeDistance: null,
        estimatedDuration: null
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
      this.ids = selection.map(item => item.routescheduleId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加航运计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const routescheduleId = row.routescheduleId || this.ids
      getRouteschedule(routescheduleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改航运计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.routescheduleId != null) {
            updateRouteschedule(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRouteschedule(this.form).then(response => {
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
      const routescheduleIds = row.routescheduleId || this.ids;
      this.$modal.confirm('是否确认删除航运计划编号为"' + routescheduleIds + '"的数据项？').then(function () {
        return delRouteschedule(routescheduleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('routeschedule/routeschedule/export', {
        ...this.queryParams
      }, `routeschedule_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
