<template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 100px">
            <div style="font-size: 25px;font-weight: bold">注册新用户</div>
            <div style="font-size: 14px;color: grey">欢迎注册我们的学习平台，请在下方填写相关信息</div>
        </div>
        <div style="margin-top: 50px">
            <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                <el-form-item prop="username">
                    <el-input v-model="form.username" :maxlength="12" type="text" placeholder="学号"
                    @blur="checkUsername">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="realname">
                    <el-input v-model="form.realname" :maxlength="255" type="text" placeholder="真实姓名">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="identity">
                    <el-select v-model="form.identity" placeholder="请选择身份">
                        <el-option label="学生" value="student"></el-option>
                        <el-option label="老师" value="teacher"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.password" :maxlength="16" type="password" placeholder="密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password_repeat">
                    <el-input v-model="form.password_repeat" :maxlength="16" type="password" placeholder="重复密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="email">
                    <el-input v-model="form.email" type="email" placeholder="电子邮件地址">
                        <template #prefix>
                            <el-icon><Message /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="phone">
                    <el-row :gutter="10" style="width: 100%">
                        <el-col :span="17">
                            <el-input v-model="form.phone" :maxlength="11" type="text" placeholder="请输入手机号">
                                <template #prefix>
                                    <el-icon><EditPen /></el-icon>
                                </template>
                            </el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
            </el-form>
        </div>
        <div style="margin-top: 80px">
            <el-button style="width: 270px" type="warning" @click="register" plain>立即注册</el-button>
        </div>
        <div style="margin-top: 20px">
            <span style="font-size: 14px;line-height: 15px;color: grey">已有账号? </span>
            <el-link type="primary" style="translate: 0 -2px" @click="router.push('/')">立即登录</el-link>
        </div>
    </div>
</template>

<script setup>
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post,get,getall,postall} from "@/net";

const form = reactive({
    username: '',
    realname: '',        // 新增真实姓名字段
    identity: '',        // 新增身份字段，学生或老师
    password: '',
    password_repeat: '',
    email: '',
    phone: ''
})

const validateUsername = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入学号'))
    } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
        callback(new Error('学号不能包含特殊字符，只能是数字'))
    } else {
        callback()
    }
}
const validatePhone = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入手机号'))
    } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
        callback(new Error('手机号不能包含特殊字符，只能是数字'))
    } else {
        callback()
    }
}

const validateIdentity = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请选择身份'))
    } else {
        callback()
    }
}

const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
}


const rules = {
    username: [
        { validator: validateUsername, trigger: ['blur', 'change'] },
        { pattern: /^\d{12}$/, message: '学号必须是12位数字', trigger: ['blur', 'change'] }
    ],
    realname: [
        { required: true, message: '请输入真实姓名', trigger: ['blur', 'change'] }
    ],
    identity: [
        { validator: validateIdentity, trigger: ['blur', 'change'] }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 16, message: '密码的长度必须在6-16个字符之间', trigger: ['blur', 'change'] }
    ],
    password_repeat: [
        { validator: validatePassword, trigger: ['blur', 'change'] },
    ],
    email: [
        { required: true, message: '请输入邮件地址', trigger: 'blur' },
        {type: 'email', message: '请输入合法的电子邮件地址', trigger: ['blur', 'change']}
    ],
    phone: [
        { validator: validatePhone, trigger: ['blur', 'change'] },
        { pattern: /^\d{11}$/, message: '手机号必须是11位数字', trigger: ['blur', 'change'] }
    ],
}

const formRef = ref()
const isEmailValid = ref(false)
const coldTime = ref(0)

const onValidate = (prop, isValid) => {
    if(prop === 'email')
        isEmailValid.value = isValid
}

// 212219607230
// 用于学号检查的函数
// 用于学号检查的函数
const checkUsername = () => {
    // 正则表达式检查学号是否为12位数字
    if (!/^\d{12}$/.test(form.username)) {
        ElMessage.error('学号必须是12位数字');
        return;  // 如果学号不符合要求，直接返回，不做后端检查
    }

    // 调用自定义的 get 函数，向后端发送请求
    get(`/user/check-username?username=${form.username}`,
        (message, status) => {
            // 成功时的处理逻辑
            if (status === 'exists') {
                
                ElMessage.error("学号已存在");
                
            } else if (status === 'available') {
                ElMessage.success("学号可用");
                
            }
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
}



// 212219607230
const register = () => {
    formRef.value.validate((isValid) => {
        
        if(isValid) 
        {
            post('/user/register', {
                username: form.username,
                realname: form.realname,   // 真实姓名
                level: form.identity,   // 身份（学生或老师）
                password: form.password,
                email: form.email,
                phone: form.phone
            }, 
            (message, status) => {
                router.push("/")
                console.log("注册成功，响应：", message, status);
                ElMessage.success(message);
                
            }, (error) => {
                console.error("请求发生错误:", error);
                ElMessage.error("注册请求出现错误，请稍后重试");
            });
        } 
        else 
        {
            ElMessage.warning('请完整填写注册表单内容！')
        }
    })
}

</script>

<style scoped>

</style>