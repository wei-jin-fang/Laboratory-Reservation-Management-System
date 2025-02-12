<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1">
            <el-image style="width: 100%;height: 100%" fit="cover"
                      src="./login.jpg"/>
        </div>
        <div class="welcome-title">
            <div style="font-size: 60px;font-weight: bold">实验室预约与管理系统</div>
            <!-- <div style="font-size: 20px;margin-top: 10px">江苏科技大学</div>
            <div style="font-size: 20px;margin-top: 5px">张家港校区电气与信息工程学院</div> -->
        </div>
        <div style="width: 400px;background-color: white;z-index: 1">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear" mode="out-in">
                    <component :is="Component" style="height: 100%"/>
                </transition>
            </router-view>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.welcome-title {
    position: absolute;
    top: 30px;
    left: 30px;
    color: white;
    text-shadow: 0 0 10px black;
}
</style>