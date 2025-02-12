<template>
    <div class="f-header">
        <span class="logo">
            <el-icon class="mr-1"><School /></el-icon>
            实验室管理系统
        </span>
        <!-- <el-icon class="icon-btn"><fold /></el-icon> -->
        <el-tooltip effect="dark" content="刷新" placement="bottom">
            <el-icon class="icon-btn" @click="handleRefresh"><refresh /></el-icon>
        </el-tooltip>

        <div class="ml-auto flex items-center">
            <el-tooltip effect="dark" content="全屏" placement="bottom">
                <el-icon class="icon-btn">
                  
                </el-icon>
            </el-tooltip>
            <el-dropdown class="dropdown" @command="handleCommand">
                <span class="flex items-center text-light-50">
                  <el-avatar class="mr-2" :size="25" src="/image.png" />
                    {{ user.username }}
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="rePassword">修改密码</el-dropdown-item>
                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>

    <!-- <el-drawer v-model="showDrawer" title="修改密码" size="45%" :close-on-click-modal="false">
        <el-form ref="formRef" :rules="rules" :model="form" label-width="80px" size="small">
            <el-form-item prop="oldpassword" label="旧密码">
                <el-input v-model="form.oldpassword" placeholder="请输入旧密码"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="新密码">
                <el-input type="password" v-model="form.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
            <el-form-item prop="repassword" label="确认密码">
                <el-input type="password" v-model="form.repassword" placeholder="请输入确认密码" show-password></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit" :loading="loading">提交</el-button>
            </el-form-item>
        </el-form>
    </el-drawer> -->
    <form-drawer ref="formDrawerRef" title="修改密码" destroyOnClose @submit="onSubmit">
        <el-form ref="formRef" :rules="rules" :model="form" label-width="80px" size="small">
            <el-form-item prop="oldpassword" label="旧密码">
                <el-input v-model="form.oldpassword" placeholder="请输入旧密码"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="新密码">
                <el-input type="password" v-model="form.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
            <el-form-item prop="repassword" label="确认密码">
                <el-input type="password" v-model="form.repassword" placeholder="请输入确认密码" show-password></el-input>
            </el-form-item>
        </el-form>
    </form-drawer>
</template>

<script setup>
  import { ref, reactive } from 'vue'
  import FormDrawer from '@/components/FormDrawer.vue'
  import { useAuthStore } from '@/stores/authStore'; 
  import { showModal,toast } from "@/composables/util"
  const store = useAuthStore();
  const { user, isAuthenticated } = store;
  import router from "@/router";
  console.log(user)
  import {get, post} from "@/net";
  // 修改密码
const showDrawer = ref(false)
const formDrawerRef = ref(null)
// do not use same name with ref
const form = reactive({
    oldpassword:"",
    password: "",
    repassword:""
})

const rules = {
    oldpassword: [
        {
            required: true,
            message: '旧密码不能为空',
            trigger: 'blur'
        },
    ],
    password: [
        {
            required: true,
            message: '新密码不能为空',
            trigger: 'blur'
        },
    ],
    repassword: [
        {
            required: true,
            message: '确认密码不能为空',
            trigger: 'blur'
        },
    ]
}

const formRef = ref(null)
const loading = ref(false)
function updatepassword(form){
  const payload = {
        username: user.username,  // 从 store 中获取用户 ID
        oldpassword: form.oldpassword,
        password: form.password,
        repassword: form.repassword
    };

    // 校验新密码是否一致
    if (form.password !== form.repassword) {
        ElMessage.error("新密码和确认密码不一致");
        return;
    }
    // 发送请求修改密码
    post('/user/change-password', payload, 
        (message, status) => {
            if(status="true"){
              ElMessage.success(message);
            //   loading.value = false
              formDrawerRef.value.showLoading()
              showDrawer.value = false;  // 关闭密码修改面板
              logout();
            }else{
              ElMessage.error(message);
            //   loading.value = false
              formDrawerRef.value.hideLoading()
            }
        },
        (message, status) => {
            ElMessage.error(message);

        }
    );

}
const onSubmit = () => {
    formRef.value.validate((valid) => {
        if (!valid) {
            return false
        }
        loading.value = true   
        updatepassword(form)
    })
}



  const handleRefresh = ()=>location.reload()
  const handleCommand = (c)=>{
        switch (c) {
            case "logout":
            console.log("退出登录");
                handleLogout()
                break;
            case "rePassword":
                // showDrawer.value = true
                formDrawerRef.value.open()
                break;
        }
    }

    function handleLogout(){
        showModal("是否要退出登录？").then(res=>{
            logout()
            .finally(()=>{
              toast("退出登录成功")  
            })
        })
    }

  const logout = () => {
    return new Promise((resolve) => {
        store.logout();
        router.push('/');  // 跳转回登录页面
        resolve();  // 确保 logout 操作后返回 Promise
    });
};

</script>

<style>
.f-header{
    @apply flex items-center bg-indigo-700 text-light-50 fixed top-0 left-0 right-0;
    height: 64px;
}

.logo{
    width: 250px;
    @apply flex justify-center items-center text-xl font-thin; 
}

.icon-btn{
    @apply flex justify-center items-center;
    width: 42px;
    height: 64px;
    cursor: pointer;
}

.icon-btn:hover{
    @apply bg-indigo-600;
}

.f-header .dropdown{
    height: 64px;
    cursor: pointer;
    @apply flex justify-center items-center mx-5;
}
</style>