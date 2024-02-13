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
      <el-form-item label="识别描述" prop="dangerDescrition">
        <el-input
          v-model="queryParams.dangerDescrition"
          placeholder="请输入识别描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="识别时间" prop="dangerTime">
        <el-date-picker
          clearable
          v-model="queryParams.dangerTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择识别时间"
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
          v-hasPermi="['dangerinfo:dangerinfo:add']"
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
          v-hasPermi="['dangerinfo:dangerinfo:edit']"
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
          v-hasPermi="['dangerinfo:dangerinfo:remove']"
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
          v-hasPermi="['dangerinfo:dangerinfo:export']"
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
      :data="dangerinfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="危险品识别id" align="center" prop="dangerId" />
      <el-table-column
        label="识别前图片"
        align="center"
        prop="beforeImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview
            :src="scope.row.beforeImage"
            :width="50"
            :height="50"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="识别结果图片"
        align="center"
        prop="afterImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview :src="scope.row.afterImage" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column
        label="识别描述"
        align="center"
        prop="dangerDescrition"
      />
      <el-table-column label="识别状态" align="center" prop="dangerStatus" />
      <el-table-column
        label="识别时间"
        align="center"
        prop="dangerTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.dangerTime, "{y}-{m}-{d} {hh}:{ii}")
          }}</span>
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
            v-hasPermi="['dangerinfo:dangerinfo:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['dangerinfo:dangerinfo:remove']"
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

    <!-- 添加或修改危险品识别信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="识别前图片" prop="beforeImage">
          <image-upload v-model="form.beforeImage" />
        </el-form-item>
        <el-form-item label="识别结果图片" prop="afterImage">
          <image-upload v-model="form.afterImage" />
        </el-form-item>
        <el-form-item label="识别描述" prop="dangerDescrition">
          <el-input
            v-model="form.dangerDescrition"
            placeholder="请输入识别描述"
          />
        </el-form-item>
        <el-form-item label="识别时间" prop="dangerTime">
          <el-date-picker
            clearable
            v-model="form.dangerTime"
            type="datetime"
            value-format="yyyy-MM-dd"
            placeholder="请选择识别时间"
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
import { listDangerinfo, getDangerinfo, delDangerinfo, addDangerinfo, updateDangerinfo } from "@/api/dangerinfo/dangerinfo";

export default {
  name: "Dangerinfo",
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
      // 危险品识别信息管理表格数据
      dangerinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        beforeImage: null,
        afterImage: null,
        dangerDescrition: null,
        dangerStatus: null,
        dangerTime: null
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
    /** 查询危险品识别信息管理列表 */
    getList() {
      this.loading = true;
      listDangerinfo(this.queryParams).then(response => {
        this.dangerinfoList = response.rows;
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
        beforeImage: null,
        afterImage: null,
        dangerDescrition: null,
        dangerStatus: null,
        dangerTime: null
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
      this.title = "添加危险品识别信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dangerId = row.dangerId || this.ids
      getDangerinfo(dangerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改危险品识别信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dangerId != null) {
            updateDangerinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDangerinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除危险品识别信息管理编号为"' + dangerIds + '"的数据项？').then(function () {
        return delDangerinfo(dangerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('dangerinfo/dangerinfo/export', {
        ...this.queryParams
      }, `dangerinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
