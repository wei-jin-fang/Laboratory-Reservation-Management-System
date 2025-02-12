<template>
    <div>
      <el-button type="primary" @click="openCreateModal">新建设备</el-button>
  
      <el-table :data="equipments" style="width: 100%">
        <el-table-column prop="name" label="设备名称" width="180"></el-table-column>
        <el-table-column prop="model" label="型号" width="180"></el-table-column>
        <el-table-column prop="status" label="状态" width="180"></el-table-column>
        <el-table-column prop="purchaseDate" label="购买日期" width="180"></el-table-column>
        <el-table-column prop="location" label="存放位置" width="180"></el-table-column>
        <el-table-column label="操作" width="150">
          <template v-slot="scope">
            <el-button type="danger" size="small" @click="deleteEquipment(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 自定义弹窗 -->
      <div v-if="modalVisible" class="modal-overlay">
        <div class="modal-content">
          <h3>新建设备</h3>
          <el-form :model="newEquipment">
            <el-form-item label="设备名称" required>
              <el-input v-model="newEquipment.name"></el-input>
            </el-form-item>
            <el-form-item label="型号" required>
              <el-input v-model="newEquipment.model"></el-input>
            </el-form-item>
            <el-form-item label="存放位置" required>
              <el-input v-model="newEquipment.location"></el-input>
            </el-form-item>
            <!-- 新增状态选择 -->
            <el-form-item label="设备状态" required>
              <el-select v-model="newEquipment.status" placeholder="请选择设备状态">
                <el-option label="可用" value="AVAILABLE"></el-option>
                <el-option label="借出" value="BORROWED"></el-option>
                <el-option label="维修中" value="UNDER_MAINTENANCE"></el-option>
                <el-option label="报废" value="DISCARDED"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div class="dialog-footer">
            <el-button @click="closeModal">取消</el-button>
            <el-button type="primary" @click="submitEquipment">提交</el-button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from "vue";
  import axios from "axios";
  
  export default {
    setup() {
      const equipments = ref([]);
      const modalVisible = ref(false);
      const newEquipment = ref({
        name: '',
        model: '',
        location: '',
        status: '' // 新增的状态字段
      });
  
      const fetchEquipments = () => {
        axios.get('/equipment').then((response) => {
          equipments.value = response.data;
        });
      };
  
      const openCreateModal = () => {
        modalVisible.value = true;
        newEquipment.value = { name: '', model: '', location: '', status: '' }; // 初始化状态
        document.body.style.overflow = 'hidden'; // 禁止背景滚动
      };
  
      const closeModal = () => {
        modalVisible.value = false;
        document.body.style.overflow = ''; // 恢复背景滚动
      };
  
      const submitEquipment = () => {
        axios.post('/equipment', newEquipment.value).then(() => {
          closeModal();
          fetchEquipments();
        });
      };
  
      const deleteEquipment = (id) => {
        axios.delete(`/equipment/${id}`).then(() => {
          fetchEquipments();
        });
      };
  
      onMounted(() => {
        fetchEquipments();
      });
  
      return {
        equipments,
        modalVisible,
        newEquipment,
        openCreateModal,
        closeModal,
        submitEquipment,
        deleteEquipment,
      };
    },
  };
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  
  .modal-content {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 500px;
    position: relative;
  }
  
  .dialog-footer {
    text-align: right;
    margin-top: 20px;
  }
  </style>
  