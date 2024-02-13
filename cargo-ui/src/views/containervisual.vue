<!--
 * @Author: Victorzl
 * @Date: 2024-01-20 16:15:02
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-20 17:12:40
 * @Description: 请填写简介
-->
<template>
  <div class="containervisual">
    <!-- 在这里嵌入 HTML 文件 -->
    <iframe :src="htmlFilePath" width="100%" height="100%"></iframe>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 替换为你的 HTML 文件路径
      htmlFilePath: '/containerviusal/index.html',
    };
  },
};
</script>

<style>
/* 可以在这里添加样式 */
.containervisual {
  width: 100%;
  height: 100%;
}
iframe {
  height: 100vh;
}
</style>
