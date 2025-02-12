        <!-- 写一个管理员事务查看回复功能，要求
        页面加载时候读取后端数据库 lab_feedback 表
        实体类如下
        @Data
        public class LabFeedback {
            private Integer id;
            private String teacherName;
            private LocalDateTime feedbackTime;
            private String feedbackText;
            private Integer labId;
            private String adminResponse;

        使用ElementUI PLUS 进行前端展示
        要求每一行都有一个回复按钮
        用户点击回复可以填写文本，然后插入到数据库中
        给我给出前后端代码，前端使用Vue3+ElementUI Plus，后端SpringBoot3+Mybaits
            后端三层架构，service里要有接口和实现类
        -->
        <template>
            <div>
              <el-table :data="feedbacks" style="width: 100%">
                <el-table-column prop="teacherName" label="教师姓名"></el-table-column>
                <el-table-column prop="feedbackTime" label="反馈时间" width="180" ></el-table-column>
                <el-table-column prop="feedbackText" label="反馈内容"></el-table-column>
                <el-table-column prop="labId" label="实验室ID"></el-table-column>
                <el-table-column prop="adminResponse" label="管理员回复"></el-table-column>
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button type="primary" @click="openModal(scope.row)">回复</el-button>
                  </template>
                </el-table-column>
              </el-table>
          
<!-- 自定义模态框 -->
<div v-if="modalVisible" class="modal-overlay" @click="closeModal">
      <div class="modal-content" @click.stop>
        <h3 style="margin-bottom: 5px;">回复反馈</h3>
        <el-input type="textarea" v-model="currentResponse" rows="4" placeholder="请输入回复内容"></el-input>
        <div class="modal-actions" style="margin-top: 5px;">
          <el-button @click="closeModal">取消</el-button>
          <el-button type="primary" @click="submitResponse">提交回复</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      feedbacks: [],
      modalVisible: false,
      currentResponse: '',
      currentFeedbackId: null
    };
  },
  methods: {
    fetchFeedbacks() {
      axios.get('/admin/feedback_all').then(response => {
        this.feedbacks = response.data;
      });
    },
    openModal(feedback) {
      this.currentFeedbackId = feedback.id;
      this.currentResponse = feedback.adminResponse || '';
      this.modalVisible = true;
      document.body.style.overflow = 'hidden'; // 禁止背景滚动
    },
    closeModal() {
      this.modalVisible = false;
      document.body.style.overflow = ''; // 恢复背景滚动
    },
    submitResponse() {
      axios.post('/admin/feedback_respond', { id: this.currentFeedbackId, adminResponse: this.currentResponse })
        .then(() => {
          this.$message.success('回复已保存');
          this.closeModal();
          this.fetchFeedbacks();
        })
        .catch(error => {
          this.$message.error('回复保存失败: ' + error.message);
        });
    }
  },
  created() {
    this.fetchFeedbacks();
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
  cursor: auto;
}
</style>