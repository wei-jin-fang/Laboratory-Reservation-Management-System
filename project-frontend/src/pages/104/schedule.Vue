<template>
  <div>
    <lab-schedule :lab-id="104"></lab-schedule>
  </div>
</template>

<script>
import LabSchedule from '@/components/index/components/LabSchedule.vue';

export default {
  components: {
    LabSchedule
  }
}
</script>




<!-- <template>
  <div class="table-container">
    <h1>实验室课程表</h1>
    <select v-model="selectedWeek" @change="fetchSchedules">
      <option v-for="week in weeks" :key="week" :value="week">
        第 {{ week }} 周
      </option>
    </select>
    <table class="table-bordered">
      <thead>
        <tr>
          <th>时间段</th>
          <th v-for="day in days" :key="`header-${day}`">
            星期{{ day }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="timeslot in timeSlots" :key="timeslot.id">
          <td>{{ timeslot.id }}</td>
          <td v-for="day in days" :key="`day-${day}-slot-${timeslot.id}`">
            <div v-if="getCourse(day, timeslot.id)">
              <strong>{{ getCourse(day, timeslot.id).courseName }}</strong><br>
              {{ getCourse(day, timeslot.id).teacherName }}
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
import { ElSelect, ElOption, ElTable, ElTableColumn, ElMessage } from 'element-plus';
import { post, get, getall, postall } from "@/net"; // 使用您提供的导入方式

export default {
  components: {
    ElSelect, ElOption, ElTable, ElTableColumn, ElMessage
  },
  data() {
    return {
      weeks: Array.from({ length: 52 }, (_, i) => i + 1),
      schedules: [],
      days: [1, 2, 3, 4, 5, 6, 7], // 代表星期一到星期日
      timeSlots: Array.from({ length: 5 }, (_, i) => ({ id: i + 1 })), // 创建5个时间段
      selectedWeek: 1 // 默认显示第一周的数据
    };
  },
  methods: {
    fetchSchedules() {
      // 使用您提供的get方法请求数据
      get(`/lab-schedules/week-schedule?labId=103&week=${this.selectedWeek}`,
      (message, status) => {
          if(status=="404"){
            this.schedules = [];
          }else{
            console.log(13)
            console.log(message)
            this.schedules = message;
          }  
          
        },
        (failureMessage) => {
          ElMessage.warning(`校验失败：${failureMessage}`);
        },
        (error) => {
          console.error("请求错误:", error);
          ElMessage.error("请求出现错误，请稍后重试");
        });
    },
    getCourse(day, timeslot) {
      return this.schedules.find(s => s.weekday === day && s.timeslot === timeslot);
    },
    indexMethod(index) {
      return index + 1; // 用于el-table-column type="index"显示时间段
    }
  },
  created() {
    this.fetchSchedules(); // 组件创建时获取初始周次的课程表数据
  }
};
</script>


<style scoped>
.table-container {
  width: 100%; /* 调整容器宽度 */
  overflow-x: auto; /* 超出部分显示滚动条 */
  height: 100%;
}

table.table-bordered {
  width: 100%; /* 表格宽度全屏 */
  border-collapse: collapse; /* 边框合并 */
  height: 80%;
}

table.table-bordered th,
table.table-bordered td {
  border: 1px solid #ccc; /* 边框颜色 */
  padding: 8px; /* 内边距 */
  text-align: center; /* 文字居中 */
}

.select-container {
  margin-bottom: 10px; /* 下拉菜单和表格的间距 */
}

select {
  margin-top: 5px;
  margin-bottom: 5px;
  width: 100%; /* 使下拉框宽度充满其容器宽度 */
  padding: 8px 12px; /* 内边距 */
  border: 1px solid #ccc; /* 边框颜色 */
  border-radius: 4px; /* 边框圆角 */
  background-color: white; /* 背景色 */
  font-size: 16px; /* 文字大小 */
  color: #333; /* 文字颜色 */
  cursor: pointer; /* 鼠标悬停时的指针类型 */

  /* 当下拉选择框获得焦点时的样式 */
  outline: none; /* 移除焦点时的外框 */
  box-shadow: 0 0 5px rgba(81, 203, 238, 1); /* 添加阴影以增强视觉效果 */
}

select:hover {
  border-color: #888; /* 鼠标悬停时改变边框颜色 */
}

select:focus {
  border-color: #105db6; /* 聚焦时边框颜色 */
  box-shadow: 0 0 6px rgba(16, 93, 182, 0.5); /* 聚焦时添加阴影 */
}

select:disabled {
  background-color: #eee; /* 禁用状态的背景颜色 */
  color: #666; /* 禁用状态的文字颜色 */
}
</style> -->








<!-- <template>
  <div>
    <h1>实验室课程表</h1>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>时间段</th>
          <th>星期一</th>
          <th>星期二</th>
          <th>星期三</th>
          <th>星期四</th>
          <th>星期五</th>
          <th>星期六</th>
          <th>星期日</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="timeslot in 5" :key="timeslot">
          <td>{{ timeslot }}</td>
          <td v-for="day in 7" :key="day">
            <div v-if="getCourse(day, timeslot)">
              <strong>{{ getCourse(day, timeslot).courseName }}</strong>
              <br>
              {{ getCourse(day, timeslot).teacherName }}
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {post,get,getall,postall} from "@/net";
import { ElMessage } from 'element-plus'; // 确保你已经安装并在项目中全局注册了 Element Plus
import { ElTable, ElTableColumn, ElMessage } from 'element-plus';
export default {
  data() {
    return {
      schedules: [], // 从后端获取的课程表数据
    };
  },
    methods: {
      fetchSchedules() {
        // axios.get(`http://localhost:8080/lab-schedules/week-schedule`, { params: { labId: 103, week: 2 }})
        //   .then(response => {
        //     console.log(123)
        //     this.schedules = response.data;

        //   })
        //   .catch(error => console.error('Error:', error));
        // 调用自定义的 get 函数，向后端发送请求
        get(`http://localhost:8080/lab-schedules/week-schedule?labId=${103}&week=2`,
            (message, status) => {
                console.log(13)
                console.log(message)
                this.schedules = message;
            },
            (failureMessage) => {
                // 失败时的处理逻辑
                ElMessage.warning(`校验失败：${failureMessage}`);
            },
            (error) => {
                // 网络或其他错误处理
                console.error("请求错误:", error);
                ElMessage.error("请求出现错误，请稍后重试");
            }
        );
      },
    getCourse(day, timeslot) {
      return this.schedules.find(s => s.weekday === day && s.timeslot === timeslot);
    },
    get(url, onSuccess, onError) {
      fetch(url)
        .then(response => response.json())
        .then(data => onSuccess(data))
        .catch(error => onError(error));
    }
  },
  created() {
    this.fetchSchedules(); // 组件创建时获取课程表数据
  }
};
</script>

<style scoped>
.table {
  width: 100%;
  text-align: center;
}
</style> -->
















