<template>
    <el-container>
      <el-header id="f-header">
        <f-header id="123"></f-header>
      </el-header>
      <el-container>
        <el-aside>
            <f-menu id="f-menu"></f-menu>
        </el-aside>
        <el-main>
            <!-- <f-tag-list></f-tag-list> -->
        <router-view></router-view>

          
        </el-main>
      </el-container>
    </el-container>
</template>

  
<script setup>

import FHeader from './components/FHeader.vue'
import FMenu from './components/FMenu.vue'
import FTagList from './components/FTagList.vue'
import { useAuthStore } from '@/stores/authStore';  // 假设 authStore 位于 stores 目录
import {ElMessage} from "element-plus";
import router from "@/router";
const store = useAuthStore();
  
  // 通过解构从 store 中获取用户信息和登录状态
  const { user, isAuthenticated } = store;
  const logout = () => {
    store.logout();
    router.push('/');  // 跳转回登录页面
};

</script>

<style scoped>
  .el-container {
  height: 100%; /* 使其高度充满整个父元素 */
  min-height: 100%; /* 确保最小高度也是100% */
}
.el-main{
  height: 100%; /* 使其高度充满整个父元素 */
  min-height: 100%; /* 确保最小高度也是100% */
}
</style>