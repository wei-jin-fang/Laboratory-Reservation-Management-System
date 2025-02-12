<template>
    <div>
      <el-table :data="announcements" style="width: 100%">
        <el-table-column prop="title" label="标题" width="180"></el-table-column>
        <el-table-column prop="content" label="内容"></el-table-column>
        <el-table-column prop="publishDate" label="发布时间" width="180"></el-table-column>
        <el-table-column prop="createdBy" label="创建人" width="120"></el-table-column>
      </el-table>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { ref, onMounted } from 'vue';
  
  export default {
    name: 'AnnouncementDisplay',
    setup() {
      const announcements = ref([]);
  
      const fetchAnnouncements = () => {
        axios.get('/announcements').then((response) => {
          announcements.value = response.data;
        });
      };
  
      onMounted(() => {
        fetchAnnouncements();
      });
  
      return {
        announcements,
      };
    },
  };
  </script>
  
  <style scoped>
  /* 样式可以根据需要定制 */
  </style>
  