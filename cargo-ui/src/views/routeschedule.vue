<!--
 * @Author: Victorzl
 * @Date: 2023-12-30 16:27:35
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-17 17:54:51
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
      <el-form-item label="货代id" prop="hdcompanyId">
        <el-input
          v-model="queryParams.hdcompanyId"
          placeholder="请输入货代公司id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出发港口" prop="departurePort">
        <el-input
          v-model="queryParams.departurePort"
          placeholder="请输入出发港口"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['routeschedule:routeschedule:add']"
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
          v-hasPermi="['routeschedule:routeschedule:edit']"
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
          v-hasPermi="['routeschedule:routeschedule:remove']"
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
      <el-table-column label="货代公司id" align="center" prop="hdcompanyId" />
      <el-table-column label="出发港口" align="center" prop="departurePort" />
      <el-table-column label="终点港口" align="center" prop="endingPort" />
      <el-table-column label="计划箱位" align="center" prop="scheduleBox" />
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
            v-hasPermi="['routeschedule:routeschedule:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['routeschedule:routeschedule:remove']"
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

    <!-- 添加或修改航运计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="货轮id" prop="shipId">
          <el-input v-model="form.shipId" placeholder="请输入货轮id" />
        </el-form-item>
        <el-form-item label="航线id" prop="routeId">
          <el-input v-model="form.routeId" placeholder="请输入航线id" />
        </el-form-item>
        <el-form-item label="货代公司id" prop="hdcompanyId">
          <el-input v-model="form.hdcompanyId" placeholder="请输入航线id" />
        </el-form-item>
        <el-form-item label="出发港口" prop="departurePort">
          <el-input v-model="form.departurePort" placeholder="请输入出发港口" />
        </el-form-item>
        <el-form-item label="终点港口" prop="endingPort">
          <el-input v-model="form.endingPort" placeholder="请输入终点港口" />
        </el-form-item>
        <el-form-item label="计划箱位" prop="scheduleBox">
          <el-input v-model="form.scheduleBox" placeholder="请输入计划箱位" />
        </el-form-item>
        <el-form-item label="预计到港时间" prop="startTime">
          <el-date-picker
            clearable
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm"
            placeholder="请选择预计到港时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计出港时间" prop="endTime">
          <el-date-picker
            clearable
            v-model="form.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm"
            placeholder="请选择预计出港时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="航线距离" prop="routeDistance">
          <el-input v-model="form.routeDistance" placeholder="请输入航线距离" />
        </el-form-item>
        <el-form-item label="预计航行时间" prop="estimatedDuration">
          <el-input
            v-model="form.estimatedDuration"
            placeholder="请输入预计航行时间"
          />
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
import { listRouteschedule, getRouteschedule, delRouteschedule, addRouteschedule, updateRouteschedule } from "@/api/routeschedule/routeschedule";

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
        hdcompanyId: null,
        departurePort: null,
        endingPort: null,
        scheduleBox: null,
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
      listRouteschedule(this.queryParams).then(response => {
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
        hdcompanyId: null,
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
