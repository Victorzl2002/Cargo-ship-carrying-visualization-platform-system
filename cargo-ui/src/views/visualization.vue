<!--
 * @Author: Victorzl
 * @Date: 2024-01-21 20:04:56
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-02-13 09:59:28
 * @Description: 请填写简介
-->
<!--
 * @Author: Victorzl
 * @Date: 2023-09-22 20:27:04
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-02-12 11:35:34
 * @Description: 智慧港口可视化平台（Web 3D）
-->
<template>
  <div class="main">
    <div class="header">
      <h1 class="header-title">智慧港口3D可视化平台</h1>
    </div>
    <div class="wrapper">
      <div class="content">
        <div id="three">
          <!-- <button id="down">下载</button> -->
          <div class="progress-bar" v-if="loading">
            <div
              class="progress"
              :style="{ width: `${loadingProgress}%` }"
            ></div>
          </div>
          <div class="loading-status" v-if="loading">
            <p class="loading-text">模型加载中。。。</p>
          </div>
        </div>

        <div class="col col-l" :class="{ move1: isshow }">
          <div class="xpanel-wrapper xpanel-wrapper-40">
            <div class="xpanel xpanel-l-t">
              <div class="title">港口人员统计</div>
              <div v-if="!loading">
                <div
                  id="myChart"
                  :style="{ width: '23rem', height: '18.75rem' }"
                ></div>
              </div>
            </div>
          </div>
          <div class="xpanel-wrapper xpanel-wrapper-60">
            <div class="xpanel xpanel-l-b">
              <div class="title">实时港口监测地图</div>
              <div class="containervisual" v-if="!loading">
                <!-- 在这里嵌入 HTML 文件 -->
                <iframe :src="htmlFilePath" width="100%" height="100%"></iframe>
              </div>
            </div>
          </div>
        </div>
        <div class="col col-r" :class="{ move: isshow }">
          <div class="xpanel-wrapper xpanel-wrapper-25">
            <div class="xpanel xpanel-r-t">
              <div class="title">港口概况</div>
              <div class="danger_depart_box" v-if="!loading">
                <div class="d_title">2023年完成货物吞吐量2.2亿吨</div>
                <ul class="danger_depart">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/pic_ico_01.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">区域面积</li>
                  <li class="data data01">652</li>
                </ul>
                <ul class="danger_depart">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/pic_ico_02.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">岸线总长度</li>
                  <li class="data data01">8650m</li>
                </ul>
                <ul class="danger_depart danger_depart01">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/pic_ico_03.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">前沿水深</li>
                  <li class="data data01">150m</li>
                </ul>
                <ul class="danger_depart danger_depart01">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/pic_ico_04.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">泊位总数</li>
                  <li class="data data01">52</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="xpanel-wrapper xpanel-wrapper-30">
            <div class="xpanel xpanel-r-m">
              <div class="title">实时天气</div>
              <div class="danger_depart_box" v-if="!loading">
                <div class="d_title">天气数据</div>
                <ul class="danger_depart">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/天气.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">天气</li>
                  <li class="data data01">多云</li>
                </ul>
                <ul class="danger_depart">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/空气质量.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">空气质量</li>
                  <li class="data data01">22</li>
                </ul>
                <ul class="danger_depart danger_depart01">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/温度.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">温度</li>
                  <li class="data data01">13</li>
                </ul>
                <ul class="danger_depart danger_depart01">
                  <li
                    class="danger_ico"
                    style="
                      background-image: url(img/湿度.png);
                      background-position-x: 0px;
                    "
                  ></li>
                  <li class="data_name">湿度</li>
                  <li class="data data01">85</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="xpanel-wrapper xpanel-wrapper-45">
            <div class="xpanel xpanel-r-b">
              <div class="title">能源消耗分布</div>
              <div v-if="!loading">
                <div
                  id="myChart1"
                  :style="{ width: '21rem', height: '300px', margin: '15px' }"
                ></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-b">
          <button id="" @click.stop="showinfo">综合态势</button>
          <button id="" @click.stop="changeeyecar">货车寻址</button>
          <button id="" @click.stop="changeeyecontainer">集装箱调度</button>
          <button id="down">实时拍照</button>
        </div>
        <!-- <div id="tag">标签内容</div> -->
        <div
          v-if="showInfo"
          class="info-box"
          :style="{ top: infoBoxTop + 'px', left: infoBoxLeft + 'px' }"
        >
          <p>{{ selectedObject.name }}</p>
          <p>
            Position: {{ selectedObject.position.x }},
            {{ selectedObject.position.y }}, {{ selectedObject.position.z }}
          </p>
          <!-- 其他信息 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as THREE from 'three'
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls'
import { TrackballControls } from 'three/examples/jsm/controls/TrackballControls'
import { EffectComposer } from 'three/examples/jsm/postprocessing/EffectComposer'
import { RenderPass } from 'three/examples/jsm/postprocessing/RenderPass'
import { OutlinePass } from 'three/examples/jsm/postprocessing/OutlinePass.js'
import * as TWEEN from '@tweenjs/tween.js';
// 引入CSS2渲染器CSS2DRenderer和CSS2模型对象CSS2DObject
import { CSS2DRenderer, CSS2DObject } from 'three/examples/jsm/renderers/CSS2DRenderer.js';

import { color } from 'echarts'


export default {
  data() {
    return {
      scene: null,
      camera: null,
      renderer: null,
      models: null,
      composer: null,
      outlinePass: null,
      mixer: null,
      clock: new THREE.Clock(),
      animations: null,
      loading: true,
      loadingProgress: 0,
      loadingStatus: '',
      htmlFilePath: '/moniterviusal/index.html',
      isshow: false,
      shouldStop: false,
      targetPosition: null,
      prevTruckX: 0, // 初始化为0，假设卡车起始位置在 X 轴上
      prevTruckZ: 0,// 初始化为0，假设卡车起始位置在 Z 轴上
      showInfo: false, // 控制信息框显示
      selectedObject: null, // 存储选中的模型对象
      infoBoxTop: 0, // 信息框的 top 坐标
      infoBoxLeft: 0 // 信息框的 left 坐标
    }
  },
  mounted() {
    // Initialize the scene, camera, and renderer
    this.initScene()
    this.initCamera()
    this.initRenderer()
    this.initPostProcessing()
    this.setupOutlinePass();
    // Add lights to the scene
    this.addLights()

    // Load the 3D model asynchronously
    this.loadModelAsync('/cargo1.glb').then(() => {
      // Add coordinate axes helper
      // this.addAxesHelper();
      this.drawLine();

      // Add trackball controls for camera manipulation
      this.initTrackballControls();
      // Start the animation loop
      this.animate();
    });
    window.onresize = () => {
      // 重置渲染器输出画布canvas尺寸
      this.renderer.setSize(window.innerWidth, window.innerHeight);
      // 全屏情况下：设置观察范围长宽比aspect为窗口宽高比
      this.camera.aspect = window.innerWidth / window.innerHeight;
      // 渲染器执行render方法的时候会读取相机对象的投影矩阵属性projectionMatrix
      // 但是不会每渲染一帧，就通过相机的属性计算投影矩阵(节约计算资源)
      // 如果相机的一些属性发生了变化，需要执行updateProjectionMatrix ()方法更新相机的投影矩阵
      this.camera.updateProjectionMatrix();
    };
    document.getElementById('down').addEventListener('click', () => {
      const link = document.createElement('a');
      const canvas = this.renderer.domElement;
      link.href = canvas.toDataURL("image/png");
      link.download = "3d模型.png";
      link.click();
    })
    this.outlinePass.selectedObjects = [];
    document.addEventListener('click', (event) => this.onDocumentClick(event), false);
  },
  methods: {
    //初始化场景
    initScene() {
      this.scene = new THREE.Scene()
    },
    //初始化相机
    initCamera() {
      this.camera = new THREE.PerspectiveCamera(
        75,
        window.innerWidth / window.innerHeight,
        0.001,
        100
      )
      this.camera.position.set(0, 5, -10)
      this.camera.lookAt(0, 0, 0)
      this.scene.add(this.camera)
    },
    //初始化环境渲染
    initRenderer() {
      this.renderer = new THREE.WebGLRenderer({
        alpha: true,
        antialias: true,
        preserveDrawingBuffer: true,
        logarithmicDepthBuffer: true
      })
      this.renderer.setPixelRatio(window.devicePixelRatio)
      this.renderer.setSize(window.innerWidth, window.innerHeight)
      document.getElementById('three').appendChild(this.renderer.domElement)
      this.renderer.setClearColor(0xffffff, 0)
      this.renderer.outputEncoding = THREE.sRGBEncoding;
    },
    //增加灯光
    addLights() {
      const ambientLight = new THREE.AmbientLight(0xffffff, 2.5)
      this.scene.add(ambientLight)
      const directionalLight = new THREE.DirectionalLight(0xffffff, 3)
      directionalLight.castShadow = true
      directionalLight.shadow.mapSize.height = 512 * 2
      directionalLight.shadow.mapSize.width = 512 * 2
      directionalLight.shadow.bias = 0.05
      directionalLight.shadow.normalBias = 0.05
      this.scene.add(directionalLight)
    },
    //初始化模型加载
    loadModelAsync(modelPath) {
      const loader = new GLTFLoader()
      const timeoutId = setTimeout(() => {
        reject(new Error('Model loading timed out.'));
      }, 50000); // 设置5秒超时时间，单位是毫秒
      return new Promise((resolve, reject) => {
        loader.load(
          modelPath,
          (gltf) => {
            const model = gltf.scene;
            this.models = model
            // 创建一个数组来保存每个子网格的原始材质
            const originalMaterials = [];
            // 遍历所有子网格，将每个子网格的材质添加到数组中
            model.traverse((obj) => {
              if (obj.isMesh) {
                originalMaterials.push(obj.material);
              }
            });
            // 然后你可以根据需要对每个材质进行操作，例如替换为新的MeshPhysicalMaterial材质
            originalMaterials.forEach((originalMaterial, index) => {
              const newMaterial = new THREE.MeshPhysicalMaterial({
                map: originalMaterial.map,
                color: originalMaterial.color,
                metalness: originalMaterial.metalness,
                roughness: originalMaterial.roughness,
                transparent: originalMaterial.transparent,
                opacity: originalMaterial.opacity,
                envMapIntensity: originalMaterial.envMapIntensity,
                side: originalMaterial.side,
                clearcoat: 1.0,
                clearcoatRoughness: 0.1,
                name: 'newMaterial' + index
              });
              model.traverse((obj) => {
                if (obj.isMesh && obj.material === originalMaterial) {
                  obj.material = newMaterial;
                }
              });
            });
            clearTimeout(timeoutId); // 清除超时定时器
            const mesh = gltf.scene
            mesh.scale.set(0.01, 0.01, 0.01)
            this.scene.add(mesh)
            this.animation = gltf.animations
            if (gltf.animations && gltf.animations.length > 0) {
              this.mixer = new THREE.AnimationMixer(mesh)
              gltf.animations.forEach((clip) => {
                const action = this.mixer.clipAction(clip)
                action.play()
                action.clampWhenFinished = true
                this.mixer.timeScale = 0.2;
              })
            }
            this.loading = false; // 模型加载完成，隐藏进度条
            // 显示加载成功提示
            resolve();
          },
          (xhr) => {
            // Progress callback
            const percentComplete = (xhr.loaded / xhr.total) * 100;
            this.loadingProgress = Math.round(percentComplete);
            this.loadingStatus = '加载成功，进入中...';
            // 在一段时间后重置提示
            setTimeout(() => {
              this.loadingStatus = '';
            }, 2000); // 设置2秒后重置提示
          },
          (error) => {
            // Error callback
            this.loading = false;
            reject(error);
          }
        );
      });
    },
    //增加坐标轴
    addAxesHelper() {
      const axesHelper = new THREE.AxesHelper(100)
      this.scene.add(axesHelper)
    },
    //初始化一个旋转器
    initTrackballControls() {
      this.controls = new TrackballControls(this.camera, this.renderer.domElement);
      this.controls.rotateSpeed = 1.0;  // 旋转速度
      this.controls.zoomSpeed = 1.2;    // 缩放速度
      this.controls.panSpeed = 0.8;     // 平移速度
      this.controls.dynamicDampingFactor = 0.3; // 阻尼因子
      // this.controls.maxDistance = 10
      // this.controls.minDistance = 1
      this.controls.minPolarAngle = Math.PI / 2; // 最小仰角
      this.controls.maxPolarAngle = Math.PI / 2; // 最大仰角
      this.controls.minAzimuthAngle = -Math.PI / 4; // 最小水平角度
      this.controls.maxAzimuthAngle = Math.PI / 4; // 最大水平角度
    },
    //动画加载器
    animate() {
      const clock = new THREE.Clock();
      const animateLoop = () => {
        requestAnimationFrame(animateLoop);
        TWEEN.update();
        // 更新 TrackballControls
        this.controls.update();
        // 获取当前缩放值
        // Update the animation mixer
        if (this.mixer) {
          const delta = clock.getDelta();
          this.mixer.update(delta);
        }
        this.renderer.render(this.scene, this.camera);
      };
      animateLoop();
    },
    setupOutlinePass() {
      this.outlinePass = new OutlinePass(new THREE.Vector2(window.innerWidth, window.innerHeight), this.scene, this.camera);
      // 设置 OutlinePass 的参数
      this.outlinePass.edgeStrength = 5; // 轮廓线的强度
      this.outlinePass.edgeGlow = 0.8; // 轮廓线的光晕强度
      this.outlinePass.edgeThickness = 2; // 轮廓线的厚度
      this.outlinePass.pulsePeriod = 0; // 轮廓线的脉动周期，设置为 0 可以关闭脉动效

    },
    //点击模型
    onDocumentClick(event) {
      const mouse = new THREE.Vector2();
      const raycaster = new THREE.Raycaster();
      // 计算鼠标坐标
      mouse.x = (event.clientX / this.renderer.domElement.clientWidth) * 2 - 1;
      mouse.y = -(event.clientY / this.renderer.domElement.clientHeight) * 2 + 1;
      // 设置射线投射的原点和方向
      raycaster.setFromCamera(mouse, this.camera);
      // 检测交点
      const intersects = raycaster.intersectObjects([this.models], true);
      console.log(intersects);

      if (intersects.length > 0) {
        // 处理选择
        this.selectedObject = intersects[0].object;
        console.log(intersects[0]);
        // 计算信息框位置
        const point = intersects[0].point.clone(); // 拷贝交点坐标
        point.applyMatrix4(this.selectedObject.matrixWorld); // 将坐标转换为世界坐标系
        const screenPos = this.worldToScreen(point); // 将世界坐标系转换为屏幕坐标系
        this.infoBoxTop = screenPos.y;
        this.infoBoxLeft = screenPos.x;

        // 显示信息框
        this.showInfo = true;
      }

      // if (intersects.length > 0) {
      //   // 处理选择，例如为所选对象添加轮廓效果
      //   const selectedObject = intersects[0].object;
      //   this.selectedObject = selectedObject;
      //   // 切换所选对象的材质颜色为黄色
      //   // 切换所选对象的轮廓效果
      //   this.toggleOutlineEffect(selectedObject);
      // }
    },
    worldToScreen(position) {
      const vector = position.clone();
      vector.project(this.camera); // 将世界坐标转换为裁剪空间坐标
      const width = this.renderer.domElement.offsetWidth;
      const height = this.renderer.domElement.offsetHeight;
      vector.x = (vector.x + 1) / 2 * width;
      vector.y = -(vector.y - 1) / 2 * height;
      return { x: vector.x, y: vector.y };
    },
    // 切换所选对象的轮廓效果的函数
    toggleOutlineEffect(selectedObject) {
      if (!this.outlinePass) {
        console.error("OutlinePass is not initialized.");
        return;
      }
      if (this.outlinePass.selectedObjects.includes(selectedObject)) {
        // 从选择中移除对象
        this.outlinePass.selectedObjects = this.outlinePass.selectedObjects.filter(obj => obj !== selectedObject);
      } else {
        // 将对象添加到选择中
        this.outlinePass.selectedObjects.push(selectedObject);
      }
      // 更新效果通道
      this.composer.render(); // 假设您使用了 EffectComposer 来渲染场景
    },
    // 初始化后期处理
    initPostProcessing() {
      const composer = new EffectComposer(this.renderer);
      const renderPass = new RenderPass(this.scene, this.camera);
      composer.addPass(renderPass);
      const outlinePass = new OutlinePass(new THREE.Vector2(window.innerWidth, window.innerHeight), this.scene, this.camera);
      outlinePass.visibleEdgeColor.set('#ffffff');
      outlinePass.hiddenEdgeColor.set('#000000');
      outlinePass.edgeStrength = 5;
      outlinePass.edgeGlow = 0.5;
      outlinePass.edgeThickness = 2;
      composer.addPass(outlinePass);
      this.composer = composer;
      this.outlinePass = outlinePass;
    },
    showModelInfo(modelId) {
    },
    changeeyecontainer() {
      // 定义目标相机位置和焦点位置
      const targetPosition = new THREE.Vector3(-2.5, 0.5, 0); // 新的相机位置
      const targetLookAt = new THREE.Vector3(0, 0, 0); // 新的焦点位置
      // 创建 Tween 对象来控制相机位置的动画过渡
      new TWEEN.Tween(this.camera.position)
        .to(targetPosition, 1000) // 持续时间为1秒
        .easing(TWEEN.Easing.Quadratic.InOut) // 使用缓动函数控制动画过渡
        .onUpdate(() => {
          // 在动画更新时调用，以便让相机在动画过程中实时更新位置
          this.camera.lookAt(targetLookAt); // 更新相机焦点位置
        })
        .start(); // 启动动画
      // 创建 Tween 对象来控制相机焦点的动画过渡
      new TWEEN.Tween(this.controls.target)
        .to(targetLookAt, 1000) // 持续时间为1秒
        .easing(TWEEN.Easing.Quadratic.InOut) // 使用缓动函数控制动画过渡
        .start(); // 启动动画
    },
    changeeyecar() {
      this.shouldStop = !this.shouldStop;
      const animateLoop = () => {
        // 使用 traverse 方法遍历所有子模型
        this.models.traverse((child) => {
          // 检查每个子模型的名称是否匹配目标名称
          if (child.name === 'visualcar') {
            const truckPosition = new THREE.Vector3();
            child.getWorldPosition(truckPosition);

            // 设置相机的位置为卡车位置加上适当的偏移量
            const offsetX = 0.1;
            const offsetY = 0.08;
            const offsetZ = 0.1;
            if (truckPosition.x > this.prevTruckX && this.prevTruckX != 0) {
              // 卡车向正方向移动，做相应的处理
              this.camera.position.set(truckPosition.x - offsetX, truckPosition.y + offsetY, truckPosition.z);
              this.targetPosition = new THREE.Vector3(truckPosition.x + offsetX, truckPosition.y, truckPosition.z);
            } else if (truckPosition.x < this.prevTruckX) {
              // 卡车向负方向移动，做相应的处理
              this.camera.position.set(truckPosition.x + offsetX, truckPosition.y + offsetY, truckPosition.z);
              this.targetPosition = new THREE.Vector3(truckPosition.x - offsetX, truckPosition.y, truckPosition.z);
            }
            else if (truckPosition.z > this.prevTruckZ && this.prevTruckZ != 0) {
              // 卡车向正方向移动，做相应的处理
              this.camera.position.set(truckPosition.x, truckPosition.y + offsetY, truckPosition.z - offsetZ);
              this.targetPosition = new THREE.Vector3(truckPosition.x, truckPosition.y, truckPosition.z + offsetZ);
            } else if (truckPosition.z < this.prevTruckZ && this.prevTruckZ != 0) {
              // 卡车向负方向移动，做相应的处理
              this.camera.position.set(truckPosition.x, truckPosition.y + offsetY, truckPosition.z + offsetZ);
              this.targetPosition = new THREE.Vector3(truckPosition.x, truckPosition.y, truckPosition.z - offsetZ);
            }
            this.prevTruckX = truckPosition.x;
            this.prevTruckZ = truckPosition.z;
            this.controls.target.copy(this.targetPosition);
            // 更新控制器的状态
            this.controls.update();
            this.camera.lookAt(truckPosition);
            return;
          }
        });
        if (this.shouldStop == true) {
          // 继续下一帧动画
          requestAnimationFrame(animateLoop);
        }
      };
      // 开始动画循环
      animateLoop();
    },
    showinfo() {
      this.isshow = !this.isshow
      const targetPosition = new THREE.Vector3(0, 1, -2.5); // 新的相机位置
      const targetLookAt = new THREE.Vector3(0, 0, 4); // 新的焦点位置
      // 创建 Tween 对象来控制相机位置的动画过渡
      new TWEEN.Tween(this.camera.position)
        .to(targetPosition, 1000) // 持续时间为1秒
        .easing(TWEEN.Easing.Quadratic.InOut) // 使用缓动函数控制动画过渡
        .onUpdate(() => {
          // 在动画更新时调用，以便让相机在动画过程中实时更新位置
          this.camera.lookAt(targetLookAt); // 更新相机焦点位置
        })
        .start(); // 启动动画
      // 创建 Tween 对象来控制相机焦点的动画过渡
      new TWEEN.Tween(this.controls.target)
        .to(targetLookAt, 1000) // 持续时间为1秒
        .easing(TWEEN.Easing.Quadratic.InOut) // 使用缓动函数控制动画过渡
        .start(); // 启动动画
    },
    drawLine() {
      // 基于刚刚准备好的 DOM 容器，初始化 EChart 实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      let myChart1 = this.$echarts.init(document.getElementById('myChart1'))
      // 绘制图表
      myChart.setOption({
        textStyle: {
          color: 'white' // 修改文字颜色为
        },
        title: {
          text: '活跃指数',
          textStyle: {
            color: 'white' // 修改标题文字颜色为白色
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['码头人员', '装配部']
        },
        grid: {
          left: '1%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['非常活跃', '活跃', '一般', '差'],
          axisLabel: { color: 'white' }
        },
        yAxis: {
          type: 'value',
          axisLabel: { color: 'white' }
        },
        series: [
          {
            name: '码头人员',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134],
            itemStyle: { color: 'blue' },
            color: "blue"
          },
          {
            name: '装配部',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234],
            itemStyle: { color: 'red' }
          }
        ]
      });

      myChart1.setOption({
        backgroundColor: '',
        title: {
          text: '能源分布情况',
          left: 'center',
          top: 20,
          textStyle: {
            color: '#ccc'
          }
        },
        tooltip: {
          trigger: 'item'
        },
        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: '能源分类',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: [
              { value: 335, name: '供暖消耗' },
              { value: 310, name: '制冷消耗' },
              { value: 274, name: '水泵消耗' },
              { value: 235, name: '通风消耗' }
            ].sort(function (a, b) {
              return a.value - b.value;
            }),
            roseType: 'radius',
            label: {
              color: 'rgba(255, 255, 255, 0.7)'
            },
            labelLine: {
              lineStyle: {
                color: 'rgba(255, 255, 255, 0.3)'
              },
              smooth: 0.2,
              length: 10,
              length2: 20
            },
            itemStyle: {
              color: '#6bbeff',
              shadowBlur: 200,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
              return Math.random() * 200;
            }
          }
        ]
      });
    }
  }
}


</script>

<style scoped>
html,
body {
  width: 100%;
  height: 100%;
  font-family: "microsoft yahei", arial, sans-serif;
  background-color: #0b0f34;
  overflow-x: hidden;
  overflow-y: auto;
}
body {
  margin: 0 auto;
  min-width: 375px;
  max-width: 1920px;
}
header {
  position: relative;
  height: 42px;
  box-sizing: border-box;
}
.main {
  background-color: #acc4ff;
}
.header-title {
  margin: 0;
  padding: 0;
  line-height: 50px;
  text-align: center;
  font-size: 24px;
  font-weight: 400;
  color: white;
}
.wrapper {
  position: absolute;
  top: 11px;
  bottom: 1px;
  left: 1px;
  right: 1px;
  padding: 10px 10px 0 10px;
  min-height: 500px;
  background: url("../../public/img/wrapper-bg.png") no-repeat;
  background-size: 100% 100%;
  box-sizing: border-box;
  overflow: hidden;
}
/* PC */
@media (max-width: 1919px) {
  .header {
    height: 36px;
  }
  .header-title {
    line-height: 26px;
    font-size: 16px;
  }
  .wrapper {
    top: 7px;
  }
}
/* Mobile */
@media (max-width: 1279px) {
  .header-title {
    max-width: 96%;
  }
  .wrapper {
    background: none;
  }
}

/********** Content **********/
.content {
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  position: relative;
  padding: 25px 15px;
  height: 97%;
  min-height: 100%;
  box-sizing: border-box;
  overflow: hidden;
}
.col {
  margin: 0 10px;
  height: 100%;
}
.col-r {
  position: absolute;
  left: -4px;
  width: 21%;
}

.col-b {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.col-b button {
  flex: 1;
  width: 100px;
  height: 40px;
  line-height: 40px;
  margin-right: 20px;
  /* 添加背景颜色和边框 */
  background-color: rgba(52, 152, 219, 0.5);
  /* background-color: #3498db; 替换为你想要的背景颜色 */
  border: 1px solid #2980b9; /* 替换为你想要的边框颜色 */

  /* 设置文字颜色 */
  color: #ffffff; /* 替换为你想要的文字颜色 */

  /* 添加圆角和阴影效果 */
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

  /* 设置鼠标悬停时的样式 */
  transition: background-color 0.3s ease;
}
.col-b button:hover {
  background-color: #2980b9; /* 鼠标悬停时的背景颜色 */
}

.col-l {
  position: absolute;
  right: -9px;
  width: 21%;
}
.move {
  animation: move 1s linear forwards;
}
@keyframes move {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-1000px);
  }
}
.move1 {
  animation: move1 1s linear forwards;
}
@keyframes move1 {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(1000px);
  }
}
.move2 {
  animation: move2 1s linear forwards;
}
@keyframes move2 {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateY(1000px);
  }
}

#three {
  position: absolute;
  top: 25px;
  bottom: 50px;
  left: 5px;
  right: 20px;
}
/* PC */
@media (max-width: 1919px) {
  .content {
    padding: 5px 0;
  }
}
/* Mobile */
@media (max-width: 1279px) {
  .content {
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
  }
  .col {
    margin: 5px 0;
  }
  .col-l,
  .col-c,
  .col-r {
    -webkit-flex: none;
    -ms-flex: none;
    flex: none;
  }
}
.xpanel-wrapper {
  padding-bottom: 10px;
  box-sizing: border-box;
}
.xpanel-wrapper {
  height: 100%;
}
.xpanel-wrapper-25 {
  height: 25%;
}
.xpanel-wrapper-30 {
  height: 30%;
}
.xpanel-wrapper-40 {
  height: 40%;
}
.xpanel-wrapper-45 {
  height: 45%;
}
.xpanel-wrapper-60 {
  height: 60%;
}
.xpanel-wrapper-75 {
  height: 75%;
}
.xpanel {
  height: 100%;
  min-height: 100px;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  box-sizing: border-box;
}
.xpanel-l-t {
  background-image: url("../../public/img/panel-l-t.png");
  background: linear-gradient(to left, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0.2));
}
.xpanel-l-b {
  background-image: url("../../public/img/panel-l-b.png");
  /* background-color: rgba(0, 0, 0, 0.7); */
  background: linear-gradient(to left, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0.2));
}
.xpanel-r-t {
  background-image: url("../../public/img/panel-r-t.png");
  background: linear-gradient(to right, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0.2));
}
.xpanel-r-m {
  background-image: url("../../public/img/panel-r-m.png");
  background: linear-gradient(to right, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0.2));
}
.xpanel-r-b {
  background-image: url("../../public/img/panel-r-b.png");
  background: linear-gradient(to right, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0.2));
}
.xpanel .title {
  padding-left: 18px;
  height: 44px;
  line-height: 44px;
  font-size: 16px;
  font-weight: normal;
  color: #fff;
  background-image: url("../../public/img/title-bg.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.xpanel .title-long {
  background-image: url("../../public/img/title-bg-long.png");
}
/* PC */
@media (max-width: 1919px) {
  .xpanel .title {
    padding-left: 20px;
    height: 36px;
    line-height: 36px;
    font-size: 20px;
  }
}

/* tool */
.fill-h {
  height: 100% !important;
  min-height: 100% !important;
}
.no-margin {
  margin: 0 !important;
}
.no-padding {
  padding: 0 !important;
}
.no-bg {
  background: none !important;
}
.no-border {
  border: 0 !important;
}

/* scrollbar */
::-webkit-scrollbar {
  width: 0;
  height: 0;
}
::-webkit-scrollbar-track {
  background-color: transparent;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background-color: rgba(0, 0, 0, 0.3);
}

.progress-bar {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 300px;
  height: 20px;
  background-color: #ddd;
  border-radius: 10px;
  overflow: hidden;
}

.progress {
  height: 100%;
  background-color: #4caf50;
  transition: width 0.3s ease;
}
.loading-status {
  position: absolute;
  top: 60%; /* 调整垂直位置，根据实际需要调整 */
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.loading-text {
  font-size: 18px;
  color: blue;
}

#labels-container {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 1; /* Make sure labels are above the 3D scene */
  color: white;
  font-size: 16px;
}
.danger_contain_box {
  width: 100%;
  height: 38%;
  padding-left: 10px;
}
.danger_depart_box {
  height: 40%;
}
.danger_depart {
  float: left;
  width: 50%;
  height: 50%;
  margin-bottom: 0px;
  padding-left: 4%;
  list-style-type: none;
}
.danger_depart01 {
  /*background-image: url("../img/leftbg01.png");*/
  /*background-repeat: no-repeat;*/
  /*background-position: center;*/
  /*background-size: 78%;*/
  /*background-position-y:54% ;*/
  padding-top: 3%;
}
.danger_ico {
  height: 100%;
  background-size: 56%;
  width: 28%;
  background-repeat: no-repeat;
  float: left;
}
.data_name {
  color: white;
  font-weight: lighter;
  font-size: 12px;
  float: left;
  width: 72%;
  text-indent: 0.1em;
  cursor: pointer;
}
.data {
  color: white;
  font-size: 12px;
  font-family: data_Number;
}
.data01 {
  color: #00ffd2;
  float: left;
  width: 36%;
  text-indent: 0.1em;
  font-weight: 700;
}

.containervisual {
  width: 100%;
  height: 100%;
}
.d_title {
  height: 35px;
  line-height: 35px;
  border-bottom: 1px solid white;
  color: white;
  text-indent: 5px;
}
.d_content {
  width: 65px;
  height: 24px;
  margin-left: 20px;
}
.info-box {
  position: absolute;
  background: linear-gradient(to right, #4facfe, #00f2fe); /* 渐变背景 */
  border-radius: 10px; /* 边框圆角 */
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  padding: 20px; /* 内边距 */
  color: #333333; /* 文本颜色 */
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif; /* 字体 */
  animation: fadeIn 0.5s ease-in-out; /* 渐现动画 */
}

.info-box p {
  margin: 0; /* 段落边距 */
  line-height: 1.5; /* 行高 */
}

.info-box p:first-child {
  font-size: 18px; /* 第一个段落字体大小 */
  font-weight: bold; /* 第一个段落加粗 */
}

.info-box p + p {
  margin-top: 10px; /* 第二个段落上边距 */
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
