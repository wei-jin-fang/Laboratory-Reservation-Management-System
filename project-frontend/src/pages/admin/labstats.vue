<!-- 写一个实验室数据统计可视化工程，要求
        页面加载时候读取后端数据库 lab_schedule 表
        实体类如下
        @Data

public class LabSchedule {
    private Integer id;
    private Integer labId;
    private Integer semesterWeek;
    private Integer weekday;
    private Integer timeslot;
    private String courseName;
    private String teacherName;

    // Getters and Setters
}


        使用ElementUI PLUS +Ecahrts进行前端展示
        要求至少一个功能，
        例如
        （1）统计这个学期每个实验室使用次数，数据格式（实验室ID，使用次数）
        （2）统计这个学期某一个实验室，（用于选择）每一周使用次数（哪一周，使用次数）
    
        给我给出前后端代码，前端使用Vue3+ElementUI Plus，后端SpringBoot3+Mybaits
            后端三层架构，service里要有接口和实现类

        -->
    <template>
        <div>
            <h1 style="font-family: 'Roboto', sans-serif; font-size: 26px; background: linear-gradient(to right, #4facfe 0%, #00f2fe 100%); -webkit-background-clip: text; color: transparent; margin-bottom: 20px;">
            下拉选择实验室查看详细数据
            </h1>
          <el-select v-model="selectedLabId" 
          style="margin-bottom: 100px;"
          placeholder="选择实验室" @change="fetchWeeklyUsage">
            <el-option v-for="lab in labs" :key="lab" :label="`实验室 ${lab}`" :value="lab"></el-option>
          </el-select>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <div ref="totalUsageChart" style="width: 650px; height: 400px; margin-right: 20px;"></div>
            <div ref="weeklyUsageChart" style="width: 600px; height: 400px;"></div>
        </div>

        </div>
      </template>
      
      <script>
      import * as echarts from 'echarts';
      import axios from 'axios';
      
      export default {
        data() {
          return {
            selectedLabId: null,
            labs: [101, 102, 103, 104],
            totalUsageChartInstance: null,
            weeklyUsageChartInstance: null
          };
        },
        methods: {
          fetchTotalUsage() {
            axios.get('/lab-stats/total-usage').then(response => {
            
              this.initTotalUsageChart(response.data);
            });
          },
          fetchWeeklyUsage() {
            axios.get(`/lab-stats/weekly-usage/${this.selectedLabId}`).then(response => {
              this.initWeeklyUsageChart(response.data);
            });
          },
        //   initTotalUsageChart(data) {
        //     const chartDom = this.$refs.totalUsageChart;
        //     this.totalUsageChartInstance = echarts.init(chartDom);
        //     data.sort((a, b) => a.labId - b.labId);
        //     // 提取实验室ID作为X轴数据
        //     const labIds = data.map(item => item.labId);
        //     // 提取使用次数作为Y轴数据
        //     const usageCounts = data.map(item => item.usageCount);

        //     const options = {
        //         title: { text: '每个实验室本学期使用次数' },
        //         tooltip: {
        //         trigger: 'axis',
        //         axisPointer: { type: 'shadow' }
        //         },
        //         xAxis: {
        //         type: 'category',
        //         data: labIds,
        //         name: '实验室ID',
        //         axisLabel: { rotate: 45, interval: 0 }  // 如果标签太多可以轻微旋转标签或者使标签间隔显示
        //         },
        //         yAxis: {
        //         type: 'value',
        //         name: '使用次数'
        //         },
        //         series: [{
        //         data: usageCounts,
        //         type: 'bar',
        //         barWidth: '60%',  // 控制条形的宽度
        //         label: {
        //             show: true,
        //             position: 'top'
        //         }
        //         }]
        //     };

        //     this.totalUsageChartInstance.setOption(options);
        //     },
            
        initTotalUsageChart(data) {
            const chartDom = this.$refs.totalUsageChart;
            this.totalUsageChartInstance = echarts.init(chartDom);
            
            // 对数据按实验室ID排序
            data.sort((a, b) => a.labId - b.labId);

            // 提取实验室ID和使用次数，转换为饼图所需的格式
            const pieData = data.map(item => ({
                name: `实验室 ${item.labId}`,  // 实验室ID作为名称
                value: item.usageCount           // 使用次数作为值
            }));

            const options = {
                title: { text: '每个实验室本学期使用次数', left: 'center' },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'  // 显示提示：{a}为系列名，{b}为数据名，{c}为数值，{d}为百分比
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [{
                    name: '使用次数',
                    type: 'pie',
                    radius: '50%',  // 控制饼图的半径大小
                    data: pieData,  // 数据
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },
                    label: {
                        show: true,
                        formatter: '{b}: {c} ({d}%)'  // 标签显示格式：实验室ID、使用次数、百分比
                    }
                }]
            };

            this.totalUsageChartInstance.setOption(options);
        },

        
        initWeeklyUsageChart(data) {
                console.log(data);
                
                // 生成1到16周的完整周次
                const totalWeeks = 16;
                const completeData = [];
                
                // 创建一个字典来存储现有数据，方便后续查找
                const dataMap = {};
                data.forEach(item => {
                    dataMap[item.semesterWeek] = item.usageCount;
                });

                // 生成1到16周的数据，如果某周没有数据则默认为0
                for (let week = 1; week <= totalWeeks; week++) {
                    completeData.push({
                        semesterWeek: week,
                        usageCount: dataMap[week] || 0 // 如果没有该周的数据，使用0作为默认值
                    });
                }

                // 处理 completeData，使其符合 echarts 的需求
                const xAxisData = completeData.map(item => `第${item.semesterWeek}周`); // 横坐标为周次
                const seriesData = completeData.map(item => item.usageCount); // 纵坐标为使用次数

                const chartDom = this.$refs.weeklyUsageChart;
                this.weeklyUsageChartInstance = echarts.init(chartDom);
                
                const options = {
                    title: { text: `实验室 ${this.selectedLabId} 每周使用次数` },
                    tooltip: {},
                    xAxis: { 
                        type: 'category', 
                        data: xAxisData  // 横坐标使用周次
                    },
                    yAxis: { 
                        type: 'value'  // 纵坐标使用次数
                    },
                    series: [{
                        data: seriesData, // 纵坐标为使用次数
                        type: 'line'
                    }]
                };

                this.weeklyUsageChartInstance.setOption(options);
            }
            },

        mounted() {
          this.fetchTotalUsage();
        }
      };
      </script>
      
      <style scoped>
      </style>
      <!-- https://chatgpt.com/c/67088365-67e4-8003-8848-0d6b9ee14aab -->