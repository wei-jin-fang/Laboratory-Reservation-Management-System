<script setup>
import {get} from "@/net";
import {useStore} from "@/stores";
import router from "@/router";

const store = useStore()

// if(store.auth.user == null) {
//     // get('/api/user/me', (message) => {
//     //     store.auth.user = message
//     //     router.push('/index')
//     // }, () => {
//     //     store.auth.user = null
//     // })
//     router.push('/index')
// }
</script>

<template>
   
  <router-view/>
</template>

<style scoped>

</style>





















<!-- 

 <div>
      <el-input v-model="input" style="width: 240px" placeholder="Please input" >

        <template #suffix>
        <el-icon class="el-input__icon"><user /></el-icon>
      </template>

      </el-input>
    </div>

-->
