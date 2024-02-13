<!--
 * @Author: Victorzl
 * @Date: 2024-01-15 18:45:19
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-15 18:47:05
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
      <el-form-item label="名称" prop="ffcompanyName">
        <el-input
          v-model="queryParams.ffcompanyName"
          placeholder="请输入货代公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册地点" prop="ffregisteredLocation">
        <el-input
          v-model="queryParams.ffregisteredLocation"
          placeholder="请输入货代公司注册地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系地址" prop="ffcontactAddress">
        <el-input
          v-model="queryParams.ffcontactAddress"
          placeholder="请输入货代公司联系地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="ffcontactPhone">
        <el-input
          v-model="queryParams.ffcontactPhone"
          placeholder="请输入货代公司联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电子邮件" prop="ffEmail">
        <el-input
          v-model="queryParams.ffEmail"
          placeholder="请输入货代公司电子邮件"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网站地址" prop="ffWebsite">
        <el-input
          v-model="queryParams.ffWebsite"
          placeholder="请输入货代公司网站地址"
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
          v-hasPermi="['ffcompany:ffcompany:add']"
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
          v-hasPermi="['ffcompany:ffcompany:edit']"
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
          v-hasPermi="['ffcompany:ffcompany:remove']"
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
          v-hasPermi="['ffcompany:ffcompany:export']"
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
      :data="ffcompanyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="货代公司id" align="center" prop="ffcompanyId" />
      <el-table-column
        label="货代公司名称"
        align="center"
        prop="ffcompanyName"
      />
      <el-table-column
        label="货代公司注册地点"
        align="center"
        prop="ffregisteredLocation"
      />
      <el-table-column
        label="货代公司联系地址"
        align="center"
        prop="ffcontactAddress"
      />
      <el-table-column
        label="货代公司联系电话"
        align="center"
        prop="ffcontactPhone"
      />
      <el-table-column label="货代公司电子邮件" align="center" prop="ffEmail" />
      <el-table-column
        label="货代公司网站地址"
        align="center"
        prop="ffWebsite"
      />
      <el-table-column
        label="公司实景图片"
        align="center"
        prop="ffImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview :src="scope.row.ffImage" :width="50" :height="50" />
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
            v-hasPermi="['ffcompany:ffcompany:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ffcompany:ffcompany:remove']"
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

    <!-- 添加或修改货代公司管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="货代公司名称" prop="ffcompanyName">
          <el-input
            v-model="form.ffcompanyName"
            placeholder="请输入货代公司名称"
          />
        </el-form-item>
        <el-form-item label="货代公司注册地点" prop="ffregisteredLocation">
          <el-input
            v-model="form.ffregisteredLocation"
            placeholder="请输入货代公司注册地点"
          />
        </el-form-item>
        <el-form-item label="货代公司联系地址" prop="ffcontactAddress">
          <el-input
            v-model="form.ffcontactAddress"
            placeholder="请输入货代公司联系地址"
          />
        </el-form-item>
        <el-form-item label="货代公司联系电话" prop="ffcontactPhone">
          <el-input
            v-model="form.ffcontactPhone"
            placeholder="请输入货代公司联系电话"
          />
        </el-form-item>
        <el-form-item label="货代公司电子邮件" prop="ffEmail">
          <el-input
            v-model="form.ffEmail"
            placeholder="请输入货代公司电子邮件"
          />
        </el-form-item>
        <el-form-item label="货代公司网站地址" prop="ffWebsite">
          <el-input
            v-model="form.ffWebsite"
            placeholder="请输入货代公司网站地址"
          />
        </el-form-item>
        <el-form-item label="公司实景图片" prop="ffImage">
          <image-upload v-model="form.ffImage" />
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
import { listFfcompany, getFfcompany, delFfcompany, addFfcompany, updateFfcompany } from "@/api/ffcompany/ffcompany";

export default {
  name: "Ffcompany",
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
      // 货代公司管理表格数据
      ffcompanyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ffcompanyName: null,
        ffregisteredLocation: null,
        ffcontactAddress: null,
        ffcontactPhone: null,
        ffEmail: null,
        ffWebsite: null,
        ffImage: null
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
    /** 查询货代公司管理列表 */
    getList() {
      this.loading = true;
      listFfcompany(this.queryParams).then(response => {
        this.ffcompanyList = response.rows;
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
        ffcompanyId: null,
        ffcompanyName: null,
        ffregisteredLocation: null,
        ffcontactAddress: null,
        ffcontactPhone: null,
        ffEmail: null,
        ffWebsite: null,
        ffImage: null
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
      this.ids = selection.map(item => item.ffcompanyId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加货代公司管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ffcompanyId = row.ffcompanyId || this.ids
      getFfcompany(ffcompanyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改货代公司管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ffcompanyId != null) {
            updateFfcompany(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFfcompany(this.form).then(response => {
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
      const ffcompanyIds = row.ffcompanyId || this.ids;
      this.$modal.confirm('是否确认删除货代公司管理编号为"' + ffcompanyIds + '"的数据项？').then(function () {
        return delFfcompany(ffcompanyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ffcompany/ffcompany/export', {
        ...this.queryParams
      }, `ffcompany_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
