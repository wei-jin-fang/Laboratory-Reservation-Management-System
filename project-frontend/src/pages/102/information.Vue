<!-- <template>
  <div class="lab-info-container">
    <el-card shadow="hover">
      <h1>实验室信息</h1>
      <el-divider></el-divider>

      <div v-if="labInfo" class="lab-info">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-card shadow="hover" class="info-panel">
              <h3>位置</h3>
              <p>{{ labInfo.location }}</p>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover" class="info-panel">
              <h3>负责人</h3>
              <p>{{ labInfo.manager }}</p>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="12">
            <el-card shadow="hover" class="info-panel">
              <h3>工位数</h3>
              <p>{{ labInfo.workstationCount }}</p>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover" class="info-panel">
              <h3>开放状态</h3>
              <p>{{ labInfo.openStatus }}</p>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="24">
            <el-card shadow="hover" class="info-panel">
              <h3>照片路径</h3>
              <p>{{ labInfo.photoPath }}</p>
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
  data() {
    return {
      labInfo: null
    };
  },
  mounted() {
    this.fetchLabInfo();
  },
  methods: {
    fetchLabInfo() {
      axios.get(`/labs/102`)
        .then(response => {
          this.labInfo = response.data;
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
  margin: 50px auto;
}
.info-panel {
  border: 1px solid #ebeef5;
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 20px;
  transition: box-shadow 0.3s ease;
}
.info-panel:hover {
  box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.2);
}
</style> -->

<!-- 
<template>
  <div class="lab-info-container">
    <el-card shadow="hover">
      <h1>实验室信息</h1>
      <el-divider></el-divider>

      <div v-if="labInfo" class="lab-info">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-card shadow="hover" class="info-panel enhanced-panel fixed-height-panel">
              <h3>位置</h3>
              <p>{{ labInfo.location }}</p>
              <p class="info-detail">详细地址</p>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover" class="info-panel enhanced-panel fixed-height-panel">
              <h3>负责人</h3>
              <p>{{ labInfo.manager }}</p>
              <p class="info-detail">负责人姓名</p>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover" class="info-panel enhanced-panel fixed-height-panel">
              <h3>工位数</h3>
              <p>{{ labInfo.workstationCount }}</p>
              <p class="info-detail">总工位数</p>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover" class="info-panel enhanced-panel fixed-height-panel">
              <h3>开放状态</h3>
              <p>{{ labInfo.openStatus }}</p>
              <p class="info-detail">当前状态</p>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="24">
            <el-card shadow="hover" class="info-panel enhanced-panel">
              <h3>照片路径</h3>
              <p>{{ labInfo.photoPath }}</p>
              <p class="info-detail">照片文件路径</p>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="24">
            <el-card shadow="hover" class="info-panel enhanced-panel">
              <h3>描述</h3>
              <p>{{ labInfo.description }}</p>
              <p class="info-detail">实验室描述信息</p>
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
  data() {
    return {
      labInfo: null
    };
  },
  mounted() {
    this.fetchLabInfo();
  },
  methods: {
    fetchLabInfo() {
      axios.get(`/labs/102`)
        .then(response => {
          this.labInfo = response.data;
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
  margin: 50px auto;
}
.info-panel {
  border: 1px solid #ebeef5;
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 20px;
  transition: box-shadow 0.3s ease;
  text-align: center;
}
.info-panel:hover {
  box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.2);
}
.enhanced-panel {
  background-color: #f5f7fa;
  font-weight: bold;
}
.info-detail {
  font-size: 14px;
  color: #909399;
  margin-top: 10px;
}
.fixed-height-panel {
  height: 200px;
}
</style> -->

<template>
  <div>
    <lab-details :lab-id="102"></lab-details>
  </div>
</template>

<script>
import LabDetails from '@/components/index/components/LabDetails.vue';
export default {
  components: {
    LabDetails
  }
}
</script>
