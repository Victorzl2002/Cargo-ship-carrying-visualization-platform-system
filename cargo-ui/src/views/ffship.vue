<!--
 * @Author: Victorzl
 * @Date: 2023-12-30 16:30:48
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-16 12:34:40
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
      <el-form-item label="船舶名称" prop="shipName">
        <el-input
          v-model="queryParams.shipName"
          placeholder="请输入船舶名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="吃水" prop="shipDraft">
        <el-input
          v-model="queryParams.shipDraft"
          placeholder="请输入吃水"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="净重" prop="shipNetweight">
        <el-input
          v-model="queryParams.shipNetweight"
          placeholder="请输入净重"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总吨" prop="shipWeight">
        <el-input
          v-model="queryParams.shipWeight"
          placeholder="请输入总吨"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="MMSI" prop="shipMmsi">
        <el-input
          v-model="queryParams.shipMmsi"
          placeholder="请输入MMSI"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船籍港" prop="shipPortofregistry">
        <el-input
          v-model="queryParams.shipPortofregistry"
          placeholder="请输入船籍港"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="呼号" prop="shipCallsign">
        <el-input
          v-model="queryParams.shipCallsign"
          placeholder="请输入呼号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国籍" prop="shipNationality">
        <el-input
          v-model="queryParams.shipNationality"
          placeholder="请输入国籍"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="IMO号" prop="shipImo">
        <el-input
          v-model="queryParams.shipImo"
          placeholder="请输入IMO号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船长" prop="shipLength">
        <el-input
          v-model="queryParams.shipLength"
          placeholder="请输入船长"
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
          v-hasPermi="['ship:ship:add']"
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
          v-hasPermi="['ship:ship:edit']"
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
          v-hasPermi="['ship:ship:remove']"
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
          v-hasPermi="['ship:ship:export']"
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
      :data="shipList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="货轮id" align="center" prop="shipId" />
      <el-table-column label="船舶名称" align="center" prop="shipName" />
      <el-table-column label="船舶类型" align="center" prop="shipType" />
      <el-table-column label="船舶箱位" align="center" prop="shipBox" />
      <el-table-column label="吃水" align="center" prop="shipDraft" />
      <el-table-column label="净重" align="center" prop="shipNetweight" />
      <el-table-column label="总吨" align="center" prop="shipWeight" />
      <el-table-column label="MMSI" align="center" prop="shipMmsi" />
      <el-table-column
        label="船籍港"
        align="center"
        prop="shipPortofregistry"
      />
      <el-table-column label="呼号" align="center" prop="shipCallsign" />
      <el-table-column label="国籍" align="center" prop="shipNationality" />
      <el-table-column label="IMO号" align="center" prop="shipImo" />
      <el-table-column label="船长" align="center" prop="shipLength" />
      <el-table-column
        label="船舶图片"
        align="center"
        prop="shipImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview :src="scope.row.shipImage" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="船舶状态" align="center" prop="shipStatus" />
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
            v-hasPermi="['ship:ship:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ship:ship:remove']"
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

    <!-- 添加或修改船舶管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="船舶名称" prop="shipName">
          <el-input v-model="form.shipName" placeholder="请输入船舶名称" />
        </el-form-item>
        <el-form-item label="船舶类型" prop="shipType">
          <el-input v-model="form.shipType" placeholder="请输入船舶类型" />
        </el-form-item>
        <el-form-item label="船舶箱位" prop="shipBox">
          <el-input v-model="form.shipBox" placeholder="请输入船舶箱位" />
        </el-form-item>
        <el-form-item label="吃水" prop="shipDraft">
          <el-input v-model="form.shipDraft" placeholder="请输入吃水" />
        </el-form-item>
        <el-form-item label="净重" prop="shipNetweight">
          <el-input v-model="form.shipNetweight" placeholder="请输入净重" />
        </el-form-item>
        <el-form-item label="总吨" prop="shipWeight">
          <el-input v-model="form.shipWeight" placeholder="请输入总吨" />
        </el-form-item>
        <el-form-item label="MMSI" prop="shipMmsi">
          <el-input v-model="form.shipMmsi" placeholder="请输入MMSI" />
        </el-form-item>
        <el-form-item label="船籍港" prop="shipPortofregistry">
          <el-input
            v-model="form.shipPortofregistry"
            placeholder="请输入船籍港"
          />
        </el-form-item>
        <el-form-item label="呼号" prop="shipCallsign">
          <el-input v-model="form.shipCallsign" placeholder="请输入呼号" />
        </el-form-item>
        <el-form-item label="国籍" prop="shipNationality">
          <el-input v-model="form.shipNationality" placeholder="请输入国籍" />
        </el-form-item>
        <el-form-item label="IMO号" prop="shipImo">
          <el-input v-model="form.shipImo" placeholder="请输入IMO号" />
        </el-form-item>
        <el-form-item label="船长" prop="shipLength">
          <el-input v-model="form.shipLength" placeholder="请输入船长" />
        </el-form-item>
        <el-form-item label="船舶图片" prop="shipImage">
          <image-upload v-model="form.shipImage" />
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
import { listShip1, getShip, delShip, addShip, updateShip } from "@/api/ship/ship";

export default {
  name: "Ship",
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
      // 船舶管理表格数据
      shipList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyId: 1,
        shipName: null,
        shipType: null,
        shipBox: null,
        shipDraft: null,
        shipNetweight: null,
        shipWeight: null,
        shipMmsi: null,
        shipPortofregistry: null,
        shipCallsign: null,
        shipNationality: null,
        shipImo: null,
        shipLength: null,
        shipImage: null,
        shipStatus: null
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询船舶管理列表 */
    getList() {
      this.loading = true;
      listShip1(this.queryParams).then(response => {
        this.shipList = response.rows;
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
        shipId: null,
        companyId: 1,
        shipName: null,
        shipType: null,
        shipDraft: null,
        shipNetweight: null,
        shipWeight: null,
        shipMmsi: null,
        shipPortofregistry: null,
        shipCallsign: null,
        shipNationality: null,
        shipImo: null,
        shipLength: null,
        shipImage: null,
        shipStatus: null
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
      this.ids = selection.map(item => item.shipId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加船舶管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const shipId = row.shipId || this.ids
      getShip(shipId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改船舶管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.shipId != null) {
            updateShip(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addShip(this.form).then(response => {
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
      const shipIds = row.shipId || this.ids;
      this.$modal.confirm('是否确认删除船舶管理编号为"' + shipIds + '"的数据项？').then(function () {
        return delShip(shipIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ship/ship/export', {
        ...this.queryParams
      }, `ship_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
