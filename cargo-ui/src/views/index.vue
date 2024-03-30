<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning" style="font-size: 28px">
          面向智慧航运的智能港口数字孪生一体化平台
        </blockquote>
        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        面向智慧航运的智能港口数字孪生一体化平台是为了打破信息孤岛，提高港口装卸效率、优化船舶装载方案和增强港口管理而设计的智能解决方案。该平台利用先进的计算机技术和数据分析算法，通过整合和分析大量的实时数据，为港口和船舶运营商提供全面的装载管理服务。
        以下是该平台的主要功能和特点：
        <br />1.装载计划优化：基于船舶特性、港口设备和货物信息等因素，利用智能算法优化装载计划，提高装卸效率，减少装卸时间和成本。
        <br />2.实时监控和调度：通过传感器、摄像头技术，对港口装卸过程中的船舶、货物、设备和人员进行实时监控和调度，确保装卸作业顺利进行，并及时响应异常情况。
        <br />3.船舶信息管理：集成和管理船舶相关信息，包括船舶规格、载重能力、航线计划等，为装载计划提供准确的数据支持。
        <br />4.货物跟踪和管理：利用物联网和传感器技术，实时跟踪货物的位置和状态，并提供货物管理功能，包括货物进出港登记、库存管理等。
        <br />5.数据分析和预测：通过对装卸数据的分析和挖掘，提供各种关键指标和报表，帮助决策者进行数据驱动的决策，并利用机器学习算法进行装载方案的预测和优化。
        <br />6.接口集成和扩展性：该平台支持与其他港口管理系统、船舶管理系统和物流平台的接口集成，实现信息共享和业务流程的无缝对接，同时也具有良好的扩展性，可以根据需要进行功能定制和拓展。
        <br />7.通过使用面向智慧航运的智能港口数字孪生一体化平台，对港口和船舶运营商可以实现装卸作业的精细化管理和优化，提高装卸效率、减少操作风险，并为整个港口物流链提供更高的可靠性和效益。
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <img src="../assets/images/show.jpg" alt="Image" />
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="博客地址">
              <a href="https://blog.victorzl.top/" target="_blank"
                >Victorzl个人博客</a
              >
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v1.0.0 - 2023-12-20">
              系统构建
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.6"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

