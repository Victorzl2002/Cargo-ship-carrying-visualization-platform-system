<!--
 * @Author: Victorzl
 * @Date: 2024-01-06 20:06:21
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-02-07 11:56:50
 * @Description: 请填写简介
-->
<template>
  <div id="dangerf">
    <div class="title">危险品识别</div>
    <div class="imagebar">
      <div class="beforehandle">
        <el-button type="primary" class="butt" @click="handle"
          >上传<i class="el-icon-upload el-icon--right"></i
        ></el-button>
        <div class="imageinner">
          <el-image
            style="width: 100%; height: 100%"
            :src="Src"
            ref="portrait"
            v-if="Src"
            :preview-src-list="SrcList"
          ></el-image>
          <input
            type="file"
            accept="image/png, image/jpeg"
            ref="Input"
            v-show="false"
            @change="changeImage($event)"
          />
        </div>
      </div>
      <div class="jiaohu">
        <el-button
          round
          style="color: black background-color: blue "
          @click="rebuild"
          >识别</el-button
        >
      </div>
      <div class="afterhandle">
        <el-button type="primary" class="butt" :loading="isload" @click="load"
          >下载</el-button
        >
        <div class="imageinner">
          <el-image
            style="width: 100%; height: 100%"
            :src="src"
            ref="portrait"
            v-if="src"
            :preview-src-list="srclist"
          ></el-image>
        </div>
      </div>
    </div>
    <div class="description">
      <p>危险品识别功能能够帮助用户快速识别上传的图片中是否包含危险品。</p>
      <p>使用方法：</p>
      <ol>
        <li>点击上传按钮，选择您想要识别的图片。</li>
        <li>点击识别按钮，系统将自动进行危险品识别。</li>
        <li>识别完成后，您可以点击下载按钮获取识别结果。</li>
      </ol>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'app',
  data() {
    return {
      isShow: true,
      isshow: true,
      isload: false,
      Src: '',
      src: '',
      SrcList: [],
      srclist: [],
      name: ''
    }
  },
  methods: {
    handle() {
      this.$refs.Input.click()
      this.isShow = !this.isShow
    },
    changeImage() {
      this.isshow = !this.isshow
      function getFileUrl(file) {
        let url
        const agent = navigator.userAgent
        if (agent.indexOf('MSIE') >= 1 || agent.indexOf('NET') != -1) {
          url = window.URL.createObjectURL(file)
        } else if (agent.indexOf('Firefox') > 0) {
          url = window.URL.createObjectURL(file)
        } else if (agent.indexOf('Chrome') > 0) {
          url = window.webkitURL.createObjectURL(file)
        }
        return url
      }
      const file = this.$refs.Input.files[0]
      const url = getFileUrl(file)
      this.Src = url
      this.SrcList.push(this.Src)
    },
    load() {
      this.isload = !this.isload
      function download(href, name) {
        const eleLink = document.createElement('a')
        eleLink.download = name
        eleLink.href = href
        eleLink.click()
        eleLink.remove()
      }
      function downloadByBlob(url, name) {
        const image = new Image()
        image.setAttribute('crossOrigin', 'anonymous')
        image.src = url
        image.onload = () => {
          const canvas = document.createElement('canvas')
          canvas.width = image.width
          canvas.height = image.height
          const ctx = canvas.getContext('2d')
          ctx.drawImage(image, 0, 0, image.width, image.height)
          canvas.toBlob((blob) => {
            const url = URL.createObjectURL(blob)
            download(url)
            // 用完释放URL对象
            URL.revokeObjectURL(url, name)
          })
        }
      }
      downloadByBlob(this.src)
      this.isload = !this.isload
    },
    rebuild() {
      const formData = new FormData()
      formData.append('name', this.$refs.Input.files[0])
      const url = 'http://127.0.0.1:5000/danger'
      axios.post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data; boundary=--------------------------823765295301561537730103',
          'Access-Control-Allow-Origin': '*'
        },
        responseType: 'blob'
      }).then(res => {
        const Url = URL.createObjectURL(res.data)
        this.src = Url
        this.srclist.push(this.src)
      })
    }
  }
}

</script>

<style lang="scss">
#dangerf {
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
  background: url("/img/bg.png") no-repeat center center;
  .title {
    width: 100%;
    height: 50px;
    line-height: 50px;
    color: white;
    z-index: 100;
    text-align: center;
    font-size: 26px;
    font-weight: 800;
  }
  .description {
    font-size: 16px;
    color: #e0e6ed; /* 描述文字颜色 */
    width: 90%;
    margin: 9.25em auto;
    padding: 20px; /* 添加内边距 */
    border: 2px solid #ccc; /* 添加外边框 */
    border-radius: 10px; /* 添加边框圆角 */
    p {
      margin-bottom: 10px;
    }

    ol {
      margin-left: 20px;

      li {
        margin-bottom: 5px;
      }
    }
  }
  em,
  i {
    font-style: normal;
  }

  /*去掉列表前面的小点*/
  li {
    list-style: none;
  }

  /*让button 按钮 变成小手*/
  button {
    cursor: pointer;
  }

  /*取消链接的下划线*/
  a {
    color: #666;
    text-decoration: none;
  }
  .header {
    margin: 0 auto;
    width: 18.75rem;
    height: 1.875rem;
    text-align: center;
    line-height: 1.875rem;
    background: url("/img/bg.png") no-repeat center center;
    color: #74adc0;
  }
  .imagebar {
    margin: 3rem auto 0 auto;
    width: 100%;
    height: 25rem;
    display: flex;
    justify-content: center;
    .beforehandle,
    .afterhandle {
      position: relative;
      .imageinner {
        margin-top: 5.2rem;
        width: 31rem;
        height: 18rem;
        background-color: transparent;
        text-align: center;
        line-height: 10rem;
        background-image: url("/img/chart-bg.png"); /* Add your background image URL here */
        border: 2px solid #ccc; /* Example border */
        border-radius: 10px; /* Example border radius */
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
      }
      .butt {
        margin-left: 50%;
        transform: translateX(-50%);
        position: absolute;
        bottom: -15%;
      }
    }
    .jiaohu {
      button {
        margin-top: 14rem;
        margin-left: 7rem;
        margin-right: 7rem;
      }
    }
  }
}
</style>
