<!--
 * @Author: Victorzl
 * @Date: 2024-01-18 16:00:21
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-20 12:55:06
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
      <el-form-item label="船舱id" prop="cabinId">
        <el-input
          v-model="queryParams.cabinId"
          placeholder="请输入船舱id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="集装箱状态" prop="containerStatus">
        <el-input
          v-model="queryParams.containerStatus"
          placeholder="请输入集装箱状态"
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
          v-hasPermi="['container:container:add']"
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
          v-hasPermi="['container:container:edit']"
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
          v-hasPermi="['container:container:remove']"
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
          v-hasPermi="['container:container:export']"
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
      :data="containerList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="集装箱id" align="center" prop="containerId" />
      <el-table-column label="集装箱类型" align="center" prop="containerType" />
      <el-table-column
        label="集装箱现在重量"
        align="center"
        prop="currentWeight"
      />
      <el-table-column
        label="集装箱现在容积"
        align="center"
        prop="currentVolume"
      />
      <el-table-column label="最大承重" align="center" prop="maxWeight" />
      <el-table-column label="最大容器" align="center" prop="maxVolume" />
      <el-table-column
        label="集装箱状态"
        align="center"
        prop="containerStatus"
      />
      <!-- <el-table-column
        label="集装箱温度"
        align="center"
        prop="containerTemperature"
      />
      <el-table-column
        label="集装箱湿度"
        align="center"
        prop="containerHumidity"
      />
      <el-table-column
        label="集装箱经度"
        align="center"
        prop="containerLongitude"
      />
      <el-table-column
        label="集装箱纬度"
        align="center"
        prop="containerLatitude"
      />
      <el-table-column label="货轮id" align="center" prop="shipId" />
      <el-table-column label="船舱id" align="center" prop="cabinId" /> -->
      <el-table-column
        label="集装箱图片"
        align="center"
        prop="containerImage"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview
            :src="scope.row.containerImage"
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
            v-hasPermi="['container:container:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['container:container:remove']"
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

    <!-- 添加或修改集装箱管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="集装箱类型" prop="containerType">
          <el-input
            v-model="form.containerType"
            placeholder="请输入集装箱类型"
          />
        </el-form-item>
        <el-form-item label="集装箱现在重量" prop="currentWeight">
          <el-input
            v-model="form.currentWeight"
            placeholder="请输入集装箱现在重量"
          />
        </el-form-item>
        <el-form-item label="集装箱现在容积" prop="currentVolume">
          <el-input
            v-model="form.currentVolume"
            placeholder="请输入集装箱现在容积"
          />
        </el-form-item>
        <el-form-item label="最大承重" prop="maxWeight">
          <el-input v-model="form.maxWeight" placeholder="请输入最大承重" />
        </el-form-item>
        <el-form-item label="最大容器" prop="maxVolume">
          <el-input v-model="form.maxVolume" placeholder="请输入最大容器" />
        </el-form-item>

        <el-form-item label="集装箱状态" prop="containerStatus">
          <el-input
            v-model="form.containerStatus"
            placeholder="请输入集装箱状态"
          />
        </el-form-item>
        <!-- <el-form-item label="集装箱温度" prop="containerTemperature">
          <el-input
            v-model="form.containerTemperature"
            placeholder="请输入集装箱温度"
          />
        </el-form-item>
        <el-form-item label="集装箱湿度" prop="containerHumidity">
          <el-input
            v-model="form.containerHumidity"
            placeholder="请输入集装箱湿度"
          />
        </el-form-item>
        <el-form-item label="集装箱经度" prop="containerLongitude">
          <el-input
            v-model="form.containerLongitude"
            placeholder="请输入集装箱经度"
          />
        </el-form-item>
        <el-form-item label="集装箱纬度" prop="containerLatitude">
          <el-input
            v-model="form.containerLatitude"
            placeholder="请输入集装箱纬度"
          />
        </el-form-item>
        <el-form-item label="货轮id" prop="shipId">
          <el-input v-model="form.shipId" placeholder="请输入货轮id" />
        </el-form-item>
        <el-form-item label="船舱id" prop="cabinId">
          <el-input v-model="form.cabinId" placeholder="请输入船舱id" />
        </el-form-item> -->
        <el-form-item label="集装箱图片" prop="containerImage">
          <image-upload v-model="form.containerImage" />
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
import { listContainer1, getContainer, delContainer, addContainer1, updateContainer } from "@/api/containerinfo/container";

export default {
  name: "Container",
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
      // 集装箱管理表格数据
      containerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        containerType: null,
        currentWeight: null,
        currentVolume: null,
        maxWeight: null,
        maxVolume: null,
        containerStatus: null,
        containerTemperature: null,
        containerHumidity: null,
        containerLongitude: null,
        containerLatitude: null,
        shipId: null,
        cabinId: null,
        containerImage: null
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
    /** 查询集装箱管理列表 */
    getList() {
      this.loading = true;
      listContainer1(this.queryParams).then(response => {
        this.containerList = response.rows;
        console.log(this.containerList);
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
        containerId: null,
        containerType: null,
        currentWeight: null,
        currentVolume: null,
        maxWeight: null,
        maxVolume: null,
        containerLocation: null,
        containerStatus: null,
        containerTemperature: null,
        containerHumidity: null,
        containerLongitude: null,
        containerLatitude: null,
        shipId: null,
        cabinId: null,
        containerImage: null
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
      this.ids = selection.map(item => item.containerId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加集装箱管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const containerId = row.containerId || this.ids
      getContainer(containerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改集装箱管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.containerId != null) {
            updateContainer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContainer1(this.form).then(response => {
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
      const containerIds = row.containerId || this.ids;
      this.$modal.confirm('是否确认删除集装箱管理编号为"' + containerIds + '"的数据项？').then(function () {
        return delContainer(containerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('container/container/export', {
        ...this.queryParams
      }, `container_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
