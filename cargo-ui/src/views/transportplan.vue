<!--
 * @Author: Victorzl
 * @Date: 2024-04-07 13:45:00
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-04-07 13:45:03
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
      <el-form-item label="运输货物id" prop="cargoId">
        <el-input
          v-model="queryParams.cargoId"
          placeholder="请输入运输货物id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="起始地点" prop="startLocation">
        <el-input
          v-model="queryParams.startLocation"
          placeholder="请输入起始地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目的地" prop="endLocation">
        <el-input
          v-model="queryParams.endLocation"
          placeholder="请输入目的地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计发车时间" prop="departureTime">
        <el-date-picker
          clearable
          v-model="queryParams.departureTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预计发车时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预计到达时间" prop="arrivalTime">
        <el-date-picker
          clearable
          v-model="queryParams.arrivalTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预计到达时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="货车司机id" prop="driverId">
        <el-input
          v-model="queryParams.driverId"
          placeholder="请输入货车司机id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货车车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入货车车牌号"
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
          v-hasPermi="['transplan:transplan:add']"
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
          v-hasPermi="['transplan:transplan:edit']"
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
          v-hasPermi="['transplan:transplan:remove']"
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
          v-hasPermi="['transplan:transplan:export']"
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
      :data="transplanList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="运输计划id" align="center" prop="planId" />
      <el-table-column label="运输货物id" align="center" prop="cargoId" />
      <el-table-column label="起始地点" align="center" prop="startLocation" />
      <el-table-column label="目的地" align="center" prop="endLocation" />
      <el-table-column
        label="预计发车时间"
        align="center"
        prop="departureTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.departureTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="预计到达时间"
        align="center"
        prop="arrivalTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.arrivalTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="货车司机id" align="center" prop="driverId" />
      <el-table-column label="货车车牌号" align="center" prop="licensePlate" />
      <el-table-column
        label="运输状态"
        align="center"
        prop="transportationStatus"
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
            v-hasPermi="['transplan:transplan:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['transplan:transplan:remove']"
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

    <!-- 添加或修改transplan对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="运输货物id" prop="cargoId">
          <el-input v-model="form.cargoId" placeholder="请输入运输货物id" />
        </el-form-item>
        <el-form-item label="起始地点" prop="startLocation">
          <el-input v-model="form.startLocation" placeholder="请输入起始地点" />
        </el-form-item>
        <el-form-item label="目的地" prop="endLocation">
          <el-input v-model="form.endLocation" placeholder="请输入目的地" />
        </el-form-item>
        <el-form-item label="预计发车时间" prop="departureTime">
          <el-date-picker
            clearable
            v-model="form.departureTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计发车时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计到达时间" prop="arrivalTime">
          <el-date-picker
            clearable
            v-model="form.arrivalTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计到达时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="货车司机id" prop="driverId">
          <el-input v-model="form.driverId" placeholder="请输入货车司机id" />
        </el-form-item>
        <el-form-item label="货车车牌号" prop="licensePlate">
          <el-input
            v-model="form.licensePlate"
            placeholder="请输入货车车牌号"
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
import { listTransplan, getTransplan, delTransplan, addTransplan, updateTransplan } from "@/api/transplan/transplan";

export default {
  name: "Transplan",
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
      // transplan表格数据
      transplanList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cargoId: null,
        startLocation: null,
        endLocation: null,
        departureTime: null,
        arrivalTime: null,
        driverId: null,
        licensePlate: null,
        transportationStatus: null
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
    /** 查询transplan列表 */
    getList() {
      this.loading = true;
      listTransplan(this.queryParams).then(response => {
        this.transplanList = response.rows;
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
        planId: null,
        cargoId: null,
        startLocation: null,
        endLocation: null,
        departureTime: null,
        arrivalTime: null,
        driverId: null,
        licensePlate: null,
        transportationStatus: null
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
      this.ids = selection.map(item => item.planId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加transplan";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const planId = row.planId || this.ids
      getTransplan(planId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改transplan";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.planId != null) {
            updateTransplan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTransplan(this.form).then(response => {
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
      const planIds = row.planId || this.ids;
      this.$modal.confirm('是否确认删除transplan编号为"' + planIds + '"的数据项？').then(function () {
        return delTransplan(planIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('transplan/transplan/export', {
        ...this.queryParams
      }, `transplan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
