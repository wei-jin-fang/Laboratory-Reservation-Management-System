        <!-- 
            写一个实验室事务反馈
            要求前端有如下内容需要传递给后端:
            1.反馈老师姓名，从store获取
            2.反馈时间前端获取
            3.反馈问题，以文本框形式获取
            4.反馈实验室ID
            5.管理员反馈信息（默认为空，后续由管理员端处理完成显示）
            给我给出前后端代码，前端使用Vue3+ElementUI Plus，后端SpringBoot3+Mybaits
            后端三层架构，service里要有接口和实现类

            你还需要给我建表语句，记得表要有id字段，我刚才忘记说了
            --><template>
  <div class="feedback-container">
    <h1 class="heading">实验室事务反馈</h1>
      <hr class="divider"/>
    <el-form @submit.native.prevent="submitFeedback" label-position="right" label-width="100px">
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="反馈时间">
            <el-date-picker v-model="feedback.feedbackTime" type="datetime" placeholder="选择日期和时间" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="反馈内容">
            <el-input v-model="feedback.feedbackText" type="textarea" rows="4" placeholder="请输入反馈内容" style="width: 100%;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="实验室ID">
            <el-input v-model.number="feedback.labId" placeholder="输入实验室ID" style="width: 100%;"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item>
            <el-button type="primary" native-type="submit">提交反馈</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <h1 class="heading">我的反馈</h1>
      <hr class="divider"/>
      <!-- 添加一个我的反馈，要求
            后端传递 const store = useAuthStore();
    const { user, isAuthenticated } = store;
    console.log(user.realname);
    通过user.realname
    进行查询该账户下得所有反馈，以表格的形式（ElementUI PLUS）进行展示
    该区域页面加载，以及每次提交反馈后，这个地方都要刷新目前属于本账户的的反馈
        用ElementUI进行表格化展示，这个功能要给出前后端交互代码，
        然后后端返回查询lab_feedback返回的内容进行表格化展示
      -->
      <el-table :data="myFeedbacks" style="width: 100%">
      <el-table-column prop="feedbackTime" label="时间" width="180"></el-table-column>
      <el-table-column prop="feedbackText" label="反馈内容"></el-table-column>
      <el-table-column prop="labId" label="实验室ID" width="120"></el-table-column>
      <el-table-column prop="adminResponse" label="管理员回复"></el-table-column>
    </el-table>

  </div>
</template>
<script>
// import { useStore } from 'vuex';
import axios from 'axios';
import { useAuthStore } from '@/stores/authStore'; 
export default {
  data() {
    const store = useAuthStore();
    const { user, isAuthenticated } = store;
    console.log(user.realname);
    return {
      feedback: {
        teacherName: user.realname, // Assuming the teacher's name is stored in Vuex
        feedbackTime: new Date(),
        feedbackText: '',
        labId: null,
        adminResponse: ''
      },
      myFeedbacks: [],
      feedbackSubmitted: false  // Status flag to track feedback submissions
    };
  },mounted() {
      this.fetchMyFeedbacks();
    },
    watch: {
    feedbackSubmitted: function(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.fetchMyFeedbacks();  // Refresh the feedback list on submission
      }
    }
  },
  methods: {
    submitFeedback() {
      axios.post('/feedback/submit', this.feedback)
        .then(() => {
          this.$message.success('Feedback submitted successfully');
          this.feedbackSubmitted = !this.feedbackSubmitted; // Toggle the state to trigger the watcher
        
        })
        .catch(error => {
          this.$message.error('Error submitting feedback: ' + error.message);
        });
    },
    fetchMyFeedbacks() {
      const store = useAuthStore();
      const { user } = store;
      axios.get(`/feedback/my-feedback`, { params: { teacherName: user.realname }})
        .then(response => {
          this.myFeedbacks = response.data;
        })
        .catch(error => {
          console.error('Error fetching my feedbacks:', error);
        });
    }
    
  }
};
</script>

<style scoped>

    
.feedback-container .heading {
  font-size: 24px;
  color: #2c3e50; /* 深蓝灰色 */
  margin-bottom: 10px;
}

.feedback-container .divider {
  border: none;
  height: 3px;
  background-image: linear-gradient(to right, #e6ebec, #c4e0e5); /* 渐变效果 */
  margin-bottom: 20px;
}
</style>