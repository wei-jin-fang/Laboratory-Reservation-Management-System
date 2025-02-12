<template>
    <div class="lab-booking">
      <h1 class="heading">预定实验室</h1>
      <hr class="divider"/>
  
      <el-form @submit.native.prevent="applyForLab" ref="form" label-position="right" label-width="180px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="实验室ID">
              <el-select v-model.number="applyForm.labId" placeholder="Select a lab">
                <el-option v-for="item in labs" :key="item" :label="`Lab ${item}`" :value="item"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学期周次">
              <el-select v-model.number="applyForm.semesterWeek" placeholder="Select week">
                <el-option v-for="week in weeks" :key="week" :label="`第 ${week}周`" :value="week"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="星期">
              <el-select v-model.number="applyForm.weekday" placeholder="Select day">
                <el-option v-for="day in weekdays" :key="day" :label="`周 ${day}`" :value="day"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="时间段">
              <el-select v-model.number="applyForm.timeslot" placeholder="Select slot">
                <el-option v-for="slot in timeSlots" :key="slot" :label="`第 ${slot}大节课`" :value="slot"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="课程名称">
          <el-input v-model="applyForm.courseName"></el-input>
        </el-form-item>
        <!-- <el-form-item label="教师姓名">
          <el-input v-model="applyForm.teacherName"></el-input>
        </el-form-item> -->
        <el-form-item>
          <el-button type="primary" native-type="submit">申请</el-button>
        </el-form-item>
      </el-form>
  
      <h1 class="heading">我的课程</h1>
      <hr class="divider"/>

      <!-- 
        制作一个区域，该区域页面加载，以及每次申请成功后，这个地方都要刷新目前属于本账户的已经预定的课程
        用ElementUI进行表格化展示，这个功能要给出前后端交互代码，向后端传递teacher_name.这个属性从store中获取
        然后后端返回查询lab_schedule返回的内容进行表格化展示
        ，要求加一个取消预定按钮，进行与后端进行交互（先写好前端，留出与后端交互接口）
      -->
      <el-table :data="courses" style="width: 100%">
      <el-table-column prop="labId" label="实验室ID"></el-table-column>
      <el-table-column prop="semesterWeek" label="周次"></el-table-column>
      <el-table-column prop="weekday" label="星期"></el-table-column>
      <el-table-column prop="timeslot" label="时间段"></el-table-column>
      <el-table-column prop="courseName" label="课程名称"></el-table-column>
      <el-table-column prop="teacherName" label="教师名称"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="danger" size="mini" @click="cancelBooking(scope.$index, scope.row)">取消预定</el-button>
        </template>
      </el-table-column>
    </el-table> 

    </div>
  </template>
  

  
  <script>
  import axios from 'axios';
  import { ref, reactive } from 'vue'
  import { useAuthStore } from '@/stores/authStore'; 
  import { showModal, toast } from "@/composables/util"

  export default {
    data() {
      const store = useAuthStore();
      const { user, isAuthenticated } = store;
      console.log(user.realname);
      return {
        applyForm: {
          labId: '',
          semesterWeek: '',
          weekday: '',
          timeslot: '',
          courseName: '',
          teacherName: user.realname
        },
        labs: [101, 102, 103, 104],  // 假设这些是可用的实验室
        weeks: Array.from({ length: 16 }, (_, i) => i + 1),  // 一学期有16周
        weekdays: Array.from({ length: 7 }, (_, i) => i + 1),  // 一周有7天
        timeSlots: [1, 2, 3, 4, 5],  // 假设一天有5个时间段
        courses: []
      };
    },
    // 将 mounted 放在 methods 之外
    mounted() {
      this.fetchCourses();
    },
    methods: {
      fetchCourses() {
        // console.log("获取课程信息");
        const store = useAuthStore();
        const { user, isAuthenticated } = store;
        console.log(user.realname);
        const teacherName = user.realname
        axios.get('/lab-schedules/get-courses', { params: { teacherName: teacherName } })
          .then(response => {
            this.courses = response.data;
            console.log(this.courses)
          })
          .catch(error => {
            console.error('Error fetching courses:', error);
          });
      },

      applyForLab() {
        axios.post('/lab-apply/apply', this.applyForm)
          .then(response => {
            this.$message.success('预定成功');
            this.fetchCourses();  // 重新获取课程，刷新表格
          })
          .catch(error => {
            this.$message.error(error.response.data);
          });
      },

      cancelBooking(index, row) {
        console.log(row.id)
        axios.post('/lab-schedules/cancel', { courseId: row.id })
          .then(response => {
            this.$message.success('预定已取消');
            this.courses.splice(index, 1);  // 从列表中移除已取消的课程
          })
          .catch(error => {
            this.$message.error('取消预定失败');
            console.error('Error cancelling booking:', error);
          });
      }
    }
  };
</script>

<style scoped>
.lab-booking .heading {
  font-size: 24px;
  color: #2c3e50; /* 深蓝灰色 */
  margin-bottom: 10px;
}

.lab-booking .divider {
  border: none;
  height: 3px;
  background-image: linear-gradient(to right, #e6ebec, #c4e0e5); /* 渐变效果 */
  margin-bottom: 20px;
}

.el-form-item {
  margin-right: 20px; /* 调整表单项之间的右侧间距 */
}

.el-select {
  width: 100%; /* 确保下拉框充满其列宽 */
}
</style>
