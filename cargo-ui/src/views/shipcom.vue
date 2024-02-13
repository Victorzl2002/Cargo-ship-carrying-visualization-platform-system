<!--
 * @Author: Victorzl
 * @Date: 2023-12-30 16:21:54
 * @LastEditors: Victorzl
 * @LastEditTime: 2023-12-30 16:52:57
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
      <el-form-item label="船舶公司名称" prop="conpanyName">
        <el-input
          v-model="queryParams.conpanyName"
          placeholder="请输入船舶公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司电话" prop="companyPhone">
        <el-input
          v-model="queryParams.companyPhone"
          placeholder="请输入公司电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司邮箱" prop="companyEmail">
        <el-input
          v-model="queryParams.companyEmail"
          placeholder="请输入公司邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人姓名" prop="contactPerson">
        <el-input
          v-model="queryParams.contactPerson"
          placeholder="请输入联系人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人邮箱" prop="contactEmail">
        <el-input
          v-model="queryParams.contactEmail"
          placeholder="请输入联系人邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司描述" prop="companyDescription">
        <el-input
          v-model="queryParams.companyDescription"
          placeholder="请输入公司描述"
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
          v-hasPermi="['shipcompany:shipcompany:add']"
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
          v-hasPermi="['shipcompany:shipcompany:edit']"
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
          v-hasPermi="['shipcompany:shipcompany:remove']"
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
          v-hasPermi="['shipcompany:shipcompany:export']"
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
      :data="shipcompanyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="船舶公司id" align="center" prop="companyId" />
      <el-table-column label="船舶公司名称" align="center" prop="conpanyName" />
      <el-table-column label="公司地址" align="center" prop="companyAddress" />
      <el-table-column label="公司电话" align="center" prop="companyPhone" />
      <el-table-column label="公司邮箱" align="center" prop="companyEmail" />
      <el-table-column label="联系人姓名" align="center" prop="contactPerson" />
      <el-table-column label="联系人邮箱" align="center" prop="contactEmail" />
      <el-table-column
        label="公司管理船舶数量"
        align="center"
        prop="vesselsManaged"
      />
      <el-table-column
        label="公司描述"
        align="center"
        prop="companyDescription"
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
            v-hasPermi="['shipcompany:shipcompany:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shipcompany:shipcompany:remove']"
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

    <!-- 添加或修改shipcompany对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="船舶公司名称" prop="conpanyName">
          <el-input
            v-model="form.conpanyName"
            placeholder="请输入船舶公司名称"
          />
        </el-form-item>
        <el-form-item label="公司地址" prop="companyAddress">
          <el-input
            v-model="form.companyAddress"
            placeholder="请输入公司地址"
          />
        </el-form-item>
        <el-form-item label="公司电话" prop="companyPhone">
          <el-input v-model="form.companyPhone" placeholder="请输入公司电话" />
        </el-form-item>
        <el-form-item label="公司邮箱" prop="companyEmail">
          <el-input v-model="form.companyEmail" placeholder="请输入公司邮箱" />
        </el-form-item>
        <el-form-item label="联系人姓名" prop="contactPerson">
          <el-input
            v-model="form.contactPerson"
            placeholder="请输入联系人姓名"
          />
        </el-form-item>
        <el-form-item label="联系人邮箱" prop="contactEmail">
          <el-input
            v-model="form.contactEmail"
            placeholder="请输入联系人邮箱"
          />
        </el-form-item>
        <el-form-item label="公司管理船舶数量" prop="vesselsManaged">
          <el-input
            v-model="form.vesselsManaged"
            placeholder="请输入公司管理船舶数量"
          />
        </el-form-item>
        <el-form-item label="公司描述" prop="companyDescription">
          <el-input
            v-model="form.companyDescription"
            placeholder="请输入公司描述"
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
import { listShipcompany, getShipcompany, delShipcompany, addShipcompany, updateShipcompany } from "@/api/shipcompany/shipcompany";

export default {
  name: "Shipcompany",
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
      // shipcompany表格数据
      shipcompanyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        conpanyName: null,
        companyAddress: null,
        companyPhone: null,
        companyEmail: null,
        contactPerson: null,
        contactEmail: null,
        vesselsManaged: null,
        companyDescription: null
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
    /** 查询shipcompany列表 */
    getList() {
      this.loading = true;
      listShipcompany(this.queryParams).then(response => {
        this.shipcompanyList = response.rows;
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
        companyId: null,
        conpanyName: null,
        companyAddress: null,
        companyPhone: null,
        companyEmail: null,
        contactPerson: null,
        contactEmail: null,
        vesselsManaged: null,
        companyDescription: null
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
      this.ids = selection.map(item => item.companyId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加shipcompany";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const companyId = row.companyId || this.ids
      getShipcompany(companyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改shipcompany";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.companyId != null) {
            updateShipcompany(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addShipcompany(this.form).then(response => {
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
      const companyIds = row.companyId || this.ids;
      this.$modal.confirm('是否确认删除shipcompany编号为"' + companyIds + '"的数据项？').then(function () {
        return delShipcompany(companyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shipcompany/shipcompany/export', {
        ...this.queryParams
      }, `shipcompany_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
