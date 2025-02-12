<!-- LabDetails.vue -->
<template>
    <div class="lab-info-container">
      <el-card shadow="hover">
        <h1>实验室信息</h1>
        <el-divider></el-divider>
  
        <div v-if="labInfo" class="lab-info">
          <el-row :gutter="20">
            <el-col :span="6">
              <el-card shadow="hover" class="info-panel fixed-height-panel">
                <h3>位置</h3>
                <p><strong>{{ labInfo.location }}</strong></p>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card shadow="hover" class="info-panel fixed-height-panel">
                <h3>负责人</h3>
                <p><strong>{{ labInfo.manager }}</strong></p>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card shadow="hover" class="info-panel fixed-height-panel">
                <h3>工位数</h3>
                <p><strong>{{ labInfo.workstationCount }}</strong></p>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card shadow="hover" class="info-panel fixed-height-panel">
                <h3>开放状态</h3>
                <p><strong>{{ labInfo.openStatus }}</strong></p>
              </el-card>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-top: 20px;">
            <el-col :span="24">
              <el-card shadow="hover" class="info-panel" style="height: 300px;">
                <h3>实验室照片</h3>
                <div class="img-container">
                  <img v-for="(path, index) in photoPaths" :key="index" :src="path" alt="实验室照片" class="lab-image">
                </div>


              </el-card>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-top: 20px;">
            <el-col :span="24">
              <el-card shadow="hover" class="info-panel">
                <h3>描述</h3>
                <p>{{ labInfo.description }}</p>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: {
      labId: {
        type: Number,
        required: true
      }
    },
    data() {
      return {
        labInfo: null,
        photoPaths: [] // 新变量用于存储图片路径数组
      };
    },
    mounted() {
      this.fetchLabInfo();
    },
    methods: {
      fetchLabInfo() {
        axios.get(`/labs/${this.labId}`)
          .then(response => {
            this.labInfo = response.data;
            this.photoPaths = this.labInfo.photoPath ? this.labInfo.photoPath.split(',') : [];
            console.log(this.photoPaths)
          })
          .catch(error => {
            console.error('Error fetching lab info:', error);
          });
      }
    }
  };
  </script>
  
  <style scoped>
  .lab-info-container {
    max-width: 100%;
    margin: 30px auto;
  }
  .info-panel {
    border: 1px solid #ebeef5;
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 10px;
    transition: box-shadow 0.3s ease;
    text-align: center;
  }
  .info-panel:hover {
    box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.2);
  }
  .fixed-height-panel {
    height: 100px;
  }

/* 容器样式 */
.img-container {
  display: flex;
  align-items: center; /* 垂直居中子元素 */
  height: 250px; /* 容器高度 */
}

/* 图片样式 */
.img-container img {
  margin-left: 10px;
  height: 90%; /* 图片高度为容器的90% */
  max-width: 50%; /* 图片最大宽度为容器的50% */
}
  </style>
  