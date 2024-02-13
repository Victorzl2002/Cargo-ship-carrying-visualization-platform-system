<!--
 * @Author: Victorzl
 * @Date: 2023-12-30 16:24:51
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-20 12:57:00
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
      <el-form-item label="货物名称" prop="cargoName">
        <el-input
          v-model="queryParams.cargoName"
          placeholder="请输入货物名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货物状态" prop="cargoStatus">
        <el-input
          v-model="queryParams.cargoType"
          placeholder="请输入货物状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
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
        <el-button type="primary" plain size="mini" @click="allocate"
          >分配货物</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['cargoinfo:cargoinfo:add']"
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
          v-hasPermi="['cargoinfo:cargoinfo:edit']"
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
          v-hasPermi="['cargoinfo:cargoinfo:remove']"
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
          v-hasPermi="['cargoinfo:cargoinfo:export']"
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
      :data="cargoinfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="货物id" align="center" prop="cargoId" />
      <el-table-column label="货物名称" align="center" prop="cargoName" />
      <el-table-column label="货物类型" align="center" prop="cargoType" />
      <el-table-column label="货物重量(kg)" align="center" prop="cargoWeight" />
      <el-table-column
        label="货物体积(立方米)"
        align="center"
        prop="cargoVolume"
      />
      <el-table-column label="货物状态" align="center" prop="cargoStatus" />
      <el-table-column
        label="货物图片"
        align="center"
        prop="cargoImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview :src="scope.row.cargoImage" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="用户id" align="center" prop="userId" />
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
            v-hasPermi="['cargoinfo:cargoinfo:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cargoinfo:cargoinfo:remove']"
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

    <!-- 添加或修改货物管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="货物名称" prop="cargoName">
          <el-input v-model="form.cargoName" placeholder="请输入货物名称" />
        </el-form-item>
        <el-form-item label="货物类型" prop="cargoType">
          <el-input v-model="form.cargoType" placeholder="请输入货物类型" />
        </el-form-item>
        <el-form-item label="货物重量" prop="cargoWeight">
          <el-input v-model="form.cargoWeight" placeholder="请输入货物重量" />
        </el-form-item>
        <el-form-item label="货物体积" prop="cargoVolume">
          <el-input v-model="form.cargoVolume" placeholder="请输入货物体积" />
        </el-form-item>
        <el-form-item label="货物状态" prop="cargoStatus">
          <el-input v-model="form.cargoStatus" placeholder="请输入货物类型" />
        </el-form-item>
        <el-form-item label="货物图片" prop="cargoImage">
          <image-upload v-model="form.cargoImage" />
        </el-form-item>
        <el-form-item label="外键关联用户表" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入外键关联用户表" />
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
import { listCargoinfo, getCargoinfo, delCargoinfo, allocateCargo, addCargoinfo, updateCargoinfo } from "@/api/cargoinfo/cargoinfo";

export default {
  name: "Cargoinfo",
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
      // 货物管理表格数据
      cargoinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cargoName: null,
        cargoType: null,
        cargoWeight: null,
        cargoVolume: null,
        cargoStatus: null,
        cargoImage: null,
        userId: null
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
    /** 查询货物管理列表 */
    getList() {
      this.loading = true;
      listCargoinfo(this.queryParams).then(response => {
        this.cargoinfoList = response.rows;
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
        cargoId: null,
        cargoName: null,
        cargoType: null,
        cargoWeight: null,
        cargoVolume: null,
        cargoStatus: null,
        cargoImage: null,
        userId: null
      };
      this.resetForm("form");
    },
    allocate() {
      allocateCargo().then(res => {
        console.log(res);
      })
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
      this.ids = selection.map(item => item.cargoId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加货物管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cargoId = row.cargoId || this.ids
      getCargoinfo(cargoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改货物管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cargoId != null) {
            updateCargoinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCargoinfo(this.form).then(response => {
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
      const cargoIds = row.cargoId || this.ids;
      this.$modal.confirm('是否确认删除货物管理编号为"' + cargoIds + '"的数据项？').then(function () {
        return delCargoinfo(cargoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cargoinfo/cargoinfo/export', {
        ...this.queryParams
      }, `cargoinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
