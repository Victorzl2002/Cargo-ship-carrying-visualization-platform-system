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
      <el-form-item label="船舶id" prop="shipId">
        <el-input
          v-model="queryParams.shipId"
          placeholder="请输入船舶id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船箱位的行号" prop="rNumber">
        <el-input
          v-model="queryParams.rNumber"
          placeholder="请输入船箱位的行号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船箱位的层号" prop="tierNumber">
        <el-input
          v-model="queryParams.tierNumber"
          placeholder="请输入船箱位的层号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船箱位的列号" prop="cNumber">
        <el-input
          v-model="queryParams.cNumber"
          placeholder="请输入船箱位的列号"
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
          v-hasPermi="['containerslot:containerslot:add']"
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
          v-hasPermi="['containerslot:containerslot:edit']"
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
          v-hasPermi="['containerslot:containerslot:remove']"
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
          v-hasPermi="['containerslot:containerslot:export']"
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
      :data="containerslotList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="船箱位" align="center" prop="slotId" />
      <el-table-column label="船舶id" align="center" prop="shipId" />
      <el-table-column label="船箱位的行号" align="center" prop="rNumber" />
      <el-table-column label="船箱位的层号" align="center" prop="tierNumber" />
      <el-table-column label="船箱位的列号" align="center" prop="cNumber" />
      <el-table-column label="箱位状态" align="center" prop="status" />
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
            v-hasPermi="['containerslot:containerslot:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['containerslot:containerslot:remove']"
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

    <!-- 添加或修改箱位管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="船舶id" prop="shipId">
          <el-input v-model="form.shipId" placeholder="请输入船舶id" />
        </el-form-item>
        <el-form-item label="船箱位的行号" prop="rNumber">
          <el-input v-model="form.rNumber" placeholder="请输入船箱位的行号" />
        </el-form-item>
        <el-form-item label="船箱位的层号" prop="tierNumber">
          <el-input
            v-model="form.tierNumber"
            placeholder="请输入船箱位的层号"
          />
        </el-form-item>
        <el-form-item label="船箱位的列号" prop="cNumber">
          <el-input v-model="form.cNumber" placeholder="请输入船箱位的列号" />
        </el-form-item>
        <el-form-item label="船箱位的状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态" />
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
import { listContainerslot, getContainerslot, delContainerslot, addContainerslot, updateContainerslot } from "@/api/containerslot/containerslot";

export default {
  name: "Containerslot",
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
      // 箱位管理表格数据
      containerslotList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        shipId: null,
        rNumber: null,
        tierNumber: null,
        cNumber: null,
        status: null
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
    /** 查询箱位管理列表 */
    getList() {
      this.loading = true;
      listContainerslot(this.queryParams).then(response => {
        this.containerslotList = response.rows;
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
        slotId: null,
        shipId: null,
        rNumber: null,
        tierNumber: null,
        cNumber: null,
        status: null
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
      this.ids = selection.map(item => item.slotId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加箱位管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const slotId = row.slotId || this.ids
      getContainerslot(slotId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改箱位管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.slotId != null) {
            updateContainerslot(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContainerslot(this.form).then(response => {
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
      const slotIds = row.slotId || this.ids;
      this.$modal.confirm('是否确认删除箱位管理编号为"' + slotIds + '"的数据项？').then(function () {
        return delContainerslot(slotIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('containerslot/containerslot/export', {
        ...this.queryParams
      }, `containerslot_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
