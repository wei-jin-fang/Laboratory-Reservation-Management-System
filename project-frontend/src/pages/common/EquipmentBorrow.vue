<template>
  <div>
    <!-- 可借用设备展示 -->
    <h1 class="heading">借用</h1>
    <hr class="divider"/>
    <el-table :data="availableEquipments" style="width: 100%">
      <el-table-column prop="id" label="设备id" width="180"></el-table-column>
      <el-table-column prop="name" label="设备名称" width="180"></el-table-column>
      <el-table-column prop="model" label="型号" width="180"></el-table-column>
      <el-table-column prop="location" label="存放位置" width="180"></el-table-column>
      <el-table-column label="操作" width="150">
        <template v-slot="scope">
          <el-button type="primary" size="small" @click="openBorrowModal(scope.row)">借用</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 借用设备弹窗 -->
    <div v-if="modalVisible" class="modal-overlay">
      <div class="modal-content">
        <h3>借用设备</h3>
        <el-form :model="borrowForm">
          <el-form-item label="借用人" required>
            <el-input v-model="borrowForm.borrowedBy" disabled></el-input>
          </el-form-item>
          <el-form-item label="设备名称">
            <el-input v-model="borrowForm.equipmentName" disabled></el-input>
          </el-form-item>
        </el-form>
        <div class="dialog-footer">
          <el-button @click="closeBorrowModal">取消</el-button>
          <el-button type="primary" @click="submitBorrow">提交</el-button>
        </div>
      </div>
    </div>

    <!-- 我的借用记录 -->
    <h1 class="heading">我的借用</h1>
    <hr class="divider"/>
    <el-table :data="myBorrows" style="width: 100%">
      <el-table-column prop="equipmentId" label="设备ID" width="180"></el-table-column>
      <el-table-column prop="borrowDate" label="借用日期" width="180"></el-table-column>
      <el-table-column prop="status" label="状态" width="120"></el-table-column>
      <el-table-column label="操作" width="150">
        <template v-slot="scope">
          <el-button v-if="scope.row.status === 'BORROWED'" type="danger" size="small" 
          @click="returnEquipment(scope.row.id, scope.row.equipmentId)"
          >退还</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useAuthStore } from "@/stores/authStore"; 

export default {
  setup() {
    const store = useAuthStore();
    const { user } = store;
    const availableEquipments = ref([]);
    const modalVisible = ref(false);
    const myBorrows = ref([]); // 用户的借用记录

    // 借用表单，默认借用人设置为 store 中的用户真实姓名
    const borrowForm = ref({
      equipmentId: null,
      equipmentName: "",
      borrowedBy: user.realname || ""
    });

    // 获取可用设备
    const fetchAvailableEquipments = () => {
      axios.get('/equipment-borrow/available').then((response) => {
        console.log(response.data)
        availableEquipments.value = response.data;
      });
    };

    // 获取我的借用记录
  const fetchMyBorrows = () => {
    axios
      .post('/equipment-borrow/my-borrows', { borrowedBy: user.realname })
      .then((response) => {
        console.log(response.data)
        myBorrows.value = response.data;
      });
  };

    // 打开借用弹窗
    const openBorrowModal = (equipment) => {
      modalVisible.value = true;
      borrowForm.value.equipmentId = equipment.id;
      borrowForm.value.equipmentName = equipment.name;
      document.body.style.overflow = "hidden";
    };

    // 关闭借用弹窗
    const closeBorrowModal = () => {
      modalVisible.value = false;
      document.body.style.overflow = "";
    };

    // 提交借用申请
    const submitBorrow = () => {
      axios.post('/equipment-borrow/borrow', borrowForm.value).then(() => {
        closeBorrowModal();
        fetchAvailableEquipments(); // 刷新可用设备列表
        fetchMyBorrows(); // 刷新我的借用记录
      });
    };

    // 退还设备
    const returnEquipment = (id,equipmentId) => {
      // <el-table-column prop="equipmentId" label="设备ID" width="180"></el-table-column>
      console.log(equipmentId)
      axios.post(`/equipment-borrow/return/${equipmentId}`).then(() => {
        fetchAvailableEquipments(); // 设备退还后更新可用设备列表
        fetchMyBorrows(); // 刷新我的借用记录
      });
    };

    // 页面加载时获取可用设备和我的借用记录
    onMounted(() => {
      fetchAvailableEquipments();
      fetchMyBorrows();
    });

    return {
      availableEquipments,
      modalVisible,
      borrowForm,
      myBorrows,
      openBorrowModal,
      closeBorrowModal,
      submitBorrow,
      returnEquipment
    };
  }
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

.heading {
  font-size: 24px;
  color: #2c3e50;
  margin-bottom: 10px;
}

.divider {
  border: none;
  height: 3px;
  background-image: linear-gradient(to right, #e6ebec, #c4e0e5);
  margin-bottom: 20px;
}
</style>
