                    <template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 150px">
            <div style="font-size: 25px;font-weight: bold">登录</div>
            <div style="font-size: 14px;color: grey">在进入系统之前请先输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 50px">
            <el-input v-model="form.username" type="text" placeholder="学号/工号">
                <template #prefix>
                    <el-icon><User /></el-icon>
                </template>
            </el-input>
            <el-input v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码">
                <template #prefix>
                    <el-icon><Lock /></el-icon>
                </template>
            </el-input>
        </div>
        <el-row style="margin-top: 5px">
            <el-col :span="12" style="text-align: left">
                <el-checkbox v-model="form.remember" label="记住我"/>
            </el-col>
            <!-- <el-col :span="12" style="text-align: right">
                <el-link @click="router.push('/forget')">忘记密码？</el-link>
            </el-col> -->
        </el-row>
        <div style="margin-top: 40px">
            <el-button @click="login()" style="width: 270px" type="success" plain>立即登录</el-button>
        </div>
        <el-divider>
            <span style="color: grey;font-size: 13px">没有账号</span>
        </el-divider>
        <div>
            <el-button style="width: 270px" @click="router.push('/register')" type="warning" plain>注册账号</el-button>
        </div>
    </div>
</template>

<script setup>
import {User, Lock} from '@element-plus/icons-vue'
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {get, post} from "@/net";
import router from "@/router";
// import {useStore} from "@/stores/authStore";
import { useAuthStore } from '@/stores/authStore';  // 假设 authStore 位于 stores 目录



const form = reactive({
    username: '',
    password: '',
    remember: false
})

// 212219607230

const store = useAuthStore()
const login = () => {
    if(!form.username || !form.password) {
        ElMessage.warning('请填写用户名和密码！')
    } else {
        post('/user/login', {
            username: form.username,
            password: form.password,
            remember: form.remember
        },(message, status) => {
                // store.setUser(message);  // 保存用户信息
                // console.log(status)
            if(status=="success"){
                store.setUser(message);  // 保存用户信息
                // 跳转到主页
                router.push("/index/index")
                    .then(() => {
                        console.log("跳转成功");
                        
                        console.log(message)

                    })
                    .catch((error) => {
                        console.error("跳转失败：", error);
                    });
                // console.log("注册成功，响应：", message, status);
                ElMessage.success(status);
            }else{
                ElMessage.error(message);
            }
                
            }, (error) => {
                console.error("请求发生错误:", error);
                ElMessage.error("注册请求出现错误，请稍后重试");
            });
    }
}
</script>

<style scoped>

</style>