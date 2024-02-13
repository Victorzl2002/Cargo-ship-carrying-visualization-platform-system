<!--
 * @Author: Victorzl
 * @Date: 2024-01-22 13:21:47
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-23 10:12:22
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
      <el-form-item label="危险品名称" prop="dangerName">
        <el-input
          v-model="queryParams.dangerName"
          placeholder="请输入危险品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="危险品代码" prop="dangerNum">
        <el-input
          v-model="queryParams.dangerNum"
          placeholder="请输入危险品代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应急措施" prop="dangerAction">
        <el-input
          v-model="queryParams.dangerAction"
          placeholder="请输入应急措施"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查方法" prop="checkWay">
        <el-input
          v-model="queryParams.checkWay"
          placeholder="请输入检查方法"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注意事项" prop="securityNotes">
        <el-input
          v-model="queryParams.securityNotes"
          placeholder="请输入注意事项"
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
          v-hasPermi="['dangerknowledge:dangerknowledge:add']"
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
          v-hasPermi="['dangerknowledge:dangerknowledge:edit']"
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
          v-hasPermi="['dangerknowledge:dangerknowledge:remove']"
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
          v-hasPermi="['dangerknowledge:dangerknowledge:export']"
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
      :data="dangerknowledgeList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="dangerId" />
      <el-table-column label="危险品名称" align="center" prop="dangerName" />
      <el-table-column label="危险品分类" align="center" prop="dangerType" />
      <el-table-column label="危险品代码" align="center" prop="dangerNum" />
      <el-table-column label="应急措施" align="center" prop="dangerAction" />
      <el-table-column label="检查方法" align="center" prop="checkWay" />
      <el-table-column label="注意事项" align="center" prop="securityNotes" />
      <el-table-column
        label="危险品图片"
        align="center"
        prop="dangerImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview
            :src="scope.row.dangerImage"
            :width="50"
            :height="50"
          />
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
            v-hasPermi="['dangerknowledge:dangerknowledge:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['dangerknowledge:dangerknowledge:remove']"
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

    <!-- 添加或修改危险品知识库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="危险品名称" prop="dangerName">
          <el-input v-model="form.dangerName" placeholder="请输入危险品名称" />
        </el-form-item>
        <el-form-item label="危险品分类" prop="dangerType">
          <el-input v-model="form.dangerType" placeholder="请输入危险品分类" />
        </el-form-item>
        <el-form-item label="危险品代码" prop="dangerNum">
          <el-input v-model="form.dangerNum" placeholder="请输入危险品代码" />
        </el-form-item>
        <el-form-item label="应急措施" prop="dangerAction">
          <el-input v-model="form.dangerAction" placeholder="请输入应急措施" />
        </el-form-item>
        <el-form-item label="检查方法" prop="checkWay">
          <el-input v-model="form.checkWay" placeholder="请输入检查方法" />
        </el-form-item>
        <el-form-item label="注意事项" prop="securityNotes">
          <el-input v-model="form.securityNotes" placeholder="请输入注意事项" />
        </el-form-item>
        <el-form-item label="危险品图片" prop="dangerImage">
          <image-upload v-model="form.dangerImage" />
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
import { listDangerknowledge, getDangerknowledge, delDangerknowledge, addDangerknowledge, updateDangerknowledge } from "@/api/dangerknowledge/dangerknowledge";

export default {
  name: "Dangerknowledge",
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
      // 危险品知识库表格数据
      dangerknowledgeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dangerName: null,
        dangerType: null,
        dangerNum: null,
        dangerAction: null,
        checkWay: null,
        securityNotes: null,
        dangerImage: null
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
    /** 查询危险品知识库列表 */
    getList() {
      this.loading = true;
      listDangerknowledge(this.queryParams).then(response => {
        this.dangerknowledgeList = response.rows;
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
        dangerId: null,
        dangerName: null,
        dangerType: null,
        dangerNum: null,
        dangerAction: null,
        checkWay: null,
        securityNotes: null,
        dangerImage: null
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
      this.ids = selection.map(item => item.dangerId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加危险品知识库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dangerId = row.dangerId || this.ids
      getDangerknowledge(dangerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改危险品知识库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dangerId != null) {
            updateDangerknowledge(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDangerknowledge(this.form).then(response => {
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
      const dangerIds = row.dangerId || this.ids;
      this.$modal.confirm('是否确认删除危险品知识库编号为"' + dangerIds + '"的数据项？').then(function () {
        return delDangerknowledge(dangerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('dangerknowledge/dangerknowledge/export', {
        ...this.queryParams
      }, `dangerknowledge_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
