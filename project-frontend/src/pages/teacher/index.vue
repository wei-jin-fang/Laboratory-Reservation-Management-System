<!-- <template>
    <div class="tour-container">

      <div style="margin-top: 50px;" id="v-step-0" class="tour-step">1</div>
      <div class="v-step-1 tour-step">2</div>
      <div data-v-step="2" class="tour-step">3</div>
    

      <v-tour name="myTour" :steps="steps"></v-tour>
    </div>
  </template>
  
  <script ts>
  export default {
    name: 'my-tour',
    data() {
      return {
        steps: [
          {
            target: '#v-step-0', 
            header: {
              title: '👋 Get Started',
            },
            content: `<strong>Discover Vue Tour</strong> with a fully customizable experience!`,
          },
          {
            target: '.v-step-1',
            content: '<strong>Vue Tour</strong> is an awesome plugin made with Vue.js!',
          },
          {
            target: '[data-v-step="2"]',
            content: `Try it, you\'ll love it!<br>You can fully customize the steps with <strong>HTML</strong> and more.`,
            params: {
              placement: 'top', 
            },
          }
        ]
      }
    },
    mounted() {
      this.$tours['myTour'].start()
    }
  }
  </script>
  
  <style scoped>

  .v-tour__step {
    background-color: #f7f9fc; 
    color: #333;
    border-radius: 10px; 
    padding: 20px; 
    font-size: 16px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2); 
    max-width: 400px; 
  }
  
  .v-tour__header {
    font-weight: bold;
    font-size: 18px; 
    color: #409eff; 
    margin-bottom: 10px;
    border-bottom: 2px solid #409eff; 
    padding-bottom: 8px;
  }
  
  .v-tour__content {
    margin-top: 10px;
    font-size: 15px;
    line-height: 1.6; 
    color: #555;
  }
  
  .v-tour__step strong {
    color: #409eff; 
  }
  

  .v-tour__actions button {
    background-color: #409eff;
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
  }
  
  .v-tour__actions button:hover {
    background-color: #66b1ff;
  }
  
  .v-tour__actions button:disabled {
    background-color: #dcdcdc;
    cursor: not-allowed;
  }
  </style>
   -->


   <template>
    <el-button type="primary" @click="open = true">点我了解如何使用</el-button>
    
    <el-divider />
    
      <!-- 公告展示组件 -->
  <div>
    <AnnouncementDisplay />
  </div>


    <el-space>

    </el-space>
  
    <!-- 引导步骤定义 -->
    <el-tour v-model="open">
      <!-- 第一步：介绍 f-header -->
      <el-tour-step target="#f-header" title="Header" description="这是导航栏目，最右边下拉包括个人信息">
      </el-tour-step>
  
      <!-- 第二步：介绍 f-menu -->
      <el-tour-step target="#f-menu" 
      placement="right"
      title="Menu" 
      description="这个是侧边栏，包含所有功能">
      </el-tour-step>
      <!-- 介绍一级菜单 -->
      <el-tour-step target=".el-sub-menu__title" 
            placement="right"
      title="Menu" 
      description="这个是一级菜单，试试点击下拉">
    </el-tour-step>
    <!-- 介绍二级菜单 -->
    <el-tour-step target=".el-sub-menu" 
            placement="right"
      title="Menu" 
      description="这个是二级级菜单，试试点击下拉">
    </el-tour-step>
    <!-- 介绍三级级菜单 -->
    <el-tour-step target=".el-menu-item" title="Menu" 
    description="这个里面包括该三级菜单的内容">
    </el-tour-step>
      
    </el-tour>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue'
  import { MoreFilled } from '@element-plus/icons-vue'
  import type { ButtonInstance } from 'element-plus'
  import AnnouncementDisplay from '../../components/index/components/gonggaotem.vue'

  

  // 定义 ref 以获取按钮 DOM 引用
  const ref1 = ref<ButtonInstance>()
  const ref2 = ref<ButtonInstance>()
  const ref3 = ref<ButtonInstance>()
  
  // 控制引导的开关
  const open = ref(false)
  </script>
