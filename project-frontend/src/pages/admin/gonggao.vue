<template>
    <div>
      <el-button type="primary" @click="openCreateModal">新建公告</el-button>
  
      <el-table :data="announcements" style="width: 100%">
        <el-table-column prop="title" label="标题" width="180"></el-table-column>
        <el-table-column prop="content" label="内容"></el-table-column>
        <el-table-column prop="publishDate" label="发布时间" width="180"></el-table-column>
        <el-table-column prop="createdBy" label="创建人" width="120"></el-table-column>
        <el-table-column label="操作" width="150">
          <template v-slot="scope">
            <el-button type="danger" size="small" @click="deleteAnnouncement(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 新建公告弹窗 -->
      <div v-if="modalVisible" class="modal-overlay">
        <div class="modal-content">
          <h3>新建公告</h3>
          <el-form :model="newAnnouncement">
            <el-form-item label="标题" required>
              <el-input v-model="newAnnouncement.title"></el-input>
            </el-form-item>
            <el-form-item label="内容" required>
              <el-input type="textarea" v-model="newAnnouncement.content"></el-input>
            </el-form-item>
            <el-form-item label="创建人" required>
              <el-input v-model="newAnnouncement.createdBy"></el-input>
            </el-form-item>
          </el-form>
          <div class="dialog-footer">
            <el-button @click="closeModal">取消</el-button>
            <el-button type="primary" @click="submitAnnouncement">提交</el-button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { ref, onMounted } from 'vue';
  
  export default {
    name: 'Announcement',
    setup() {
      const announcements = ref([]);
      const modalVisible = ref(false);
      const newAnnouncement = ref({
        title: '',
        content: '',
        createdBy: '',
        publishDate: new Date().toISOString(),
        
      });
  
      const fetchAnnouncements = () => {
        axios.get('/announcements').then((response) => {
          announcements.value = response.data;
        });
      };
  
      const openCreateModal = () => {
        modalVisible.value = true;
        newAnnouncement.value = { title: '', content: '', createdBy: '' };
        document.body.style.overflow = 'hidden'; // 禁止背景滚动
      };
  
      const closeModal = () => {
        modalVisible.value = false;
        document.body.style.overflow = ''; // 恢复背景滚动
      };
  
      const submitAnnouncement = () => {
        axios
          .post('/announcements', newAnnouncement.value)
          .then(() => {
            modalVisible.value = false;
            fetchAnnouncements();
          })
          .catch((error) => {
            console.error('公告保存失败', error);
          });
      };
  
      const deleteAnnouncement = (id) => {
        axios.delete(`/announcements/${id}`).then(() => {
          fetchAnnouncements();
        });
      };
  
      onMounted(() => {
        fetchAnnouncements();
      });
  
      return {
        announcements,
        modalVisible,
        newAnnouncement,
        openCreateModal,
        closeModal,
        submitAnnouncement,
        deleteAnnouncement,
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
    cursor: auto;
  }
  
  .dialog-footer {
    text-align: right;
    margin-top: 20px;
  }
  </style>
  