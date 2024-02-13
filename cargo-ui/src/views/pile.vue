<!--
 * @Author: Victorzl
 * @Date: 2024-01-24 17:26:27
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-24 17:41:02
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
      <el-form-item label="货堆x坐标" prop="pileX">
        <el-input
          v-model="queryParams.pileX"
          placeholder="请输入货堆x坐标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货堆y坐标" prop="pileY">
        <el-input
          v-model="queryParams.pileY"
          placeholder="请输入货堆y坐标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货堆容量" prop="pileCapacity">
        <el-input
          v-model="queryParams.pileCapacity"
          placeholder="请输入货堆容量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前堆积数量" prop="pileCurrentnum">
        <el-input
          v-model="queryParams.pileCurrentnum"
          placeholder="请输入当前堆积数量"
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
          v-hasPermi="['pile:pile:add']"
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
          v-hasPermi="['pile:pile:edit']"
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
          v-hasPermi="['pile:pile:remove']"
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
          v-hasPermi="['pile:pile:export']"
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
      :data="pileList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="货堆id" align="center" prop="pileId" />
      <el-table-column label="货堆x坐标" align="center" prop="pileX" />
      <el-table-column label="货堆y坐标" align="center" prop="pileY" />
      <el-table-column label="货堆容量" align="center" prop="pileCapacity" />
      <el-table-column
        label="当前堆积数量"
        align="center"
        prop="pileCurrentnum"
      />
      <el-table-column label="货堆状态" align="center" prop="pileStatus" />
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
            v-hasPermi="['pile:pile:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pile:pile:remove']"
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

    <!-- 添加或修改货堆管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="货堆x坐标" prop="pileX">
          <el-input v-model="form.pileX" placeholder="请输入货堆x坐标" />
        </el-form-item>
        <el-form-item label="货堆y坐标" prop="pileY">
          <el-input v-model="form.pileY" placeholder="请输入货堆y坐标" />
        </el-form-item>
        <el-form-item label="货堆容量" prop="pileCapacity">
          <el-input v-model="form.pileCapacity" placeholder="请输入货堆容量" />
        </el-form-item>
        <el-form-item label="当前堆积数量" prop="pileCurrentnum">
          <el-input
            v-model="form.pileCurrentnum"
            placeholder="请输入当前堆积数量"
          />
        </el-form-item>
        <el-form-item label="货堆状态" prop="pileStatus">
          <el-input v-model="form.pileStatus" placeholder="请输入货堆状态" />
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
import { listPile, getPile, delPile, addPile, updatePile } from "@/api/pile/pile";

export default {
  name: "Pile",
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
      // 货堆管理表格数据
      pileList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pileX: null,
        pileY: null,
        pileCapacity: null,
        pileCurrentnum: null,
        pileStatus: null
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
    /** 查询货堆管理列表 */
    getList() {
      this.loading = true;
      listPile(this.queryParams).then(response => {
        this.pileList = response.rows;
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
        pileId: null,
        pileX: null,
        pileY: null,
        pileCapacity: null,
        pileCurrentnum: null,
        pileStatus: null
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
      this.ids = selection.map(item => item.pileId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加货堆管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pileId = row.pileId || this.ids
      getPile(pileId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改货堆管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pileId != null) {
            updatePile(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPile(this.form).then(response => {
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
      const pileIds = row.pileId || this.ids;
      this.$modal.confirm('是否确认删除货堆管理编号为"' + pileIds + '"的数据项？').then(function () {
        return delPile(pileIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pile/pile/export', {
        ...this.queryParams
      }, `pile_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
