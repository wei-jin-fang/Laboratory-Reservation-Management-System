<!-- <template>
  <div>待开发</div>
  <div>
      欢迎{{store.auth.user.username}}进入到学习平台
  </div>
  <div>
      <el-button @click="logout()" type="danger" plain>退出登录</el-button>
  </div>
</template>

<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
 -->

 <template>
    <div>
      <!-- <p v-if="isAuthenticated">欢迎回来, {{ user.username }}!</p>
      
      <p v-else>您还没有登录，请先登录。</p> -->


      <indexheader />

<!-- 
      <div>
        <el-button @click="logout()" type="danger" plain>退出登录</el-button>
      </div> -->
    </div>
    


  


  </template>
  
<script setup>
  import indexheader from '@/components/index/admin.vue'
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
  
