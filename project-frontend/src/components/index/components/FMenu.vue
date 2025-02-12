<template>
    <!-- <div class="f-menu">
        <el-menu :default-active="defaultActive"  default-active="2" class="border-0" @select="handleSelect" :collapse-transition="false">

            <template v-for="(item,index) in asideMenus" :key="index">
                <el-sub-menu v-if="item.child && item.child.length > 0" :index="item.name">
                    <template #title>
                        <el-icon>
                            <component :is="item.icon"></component>
                        </el-icon>
                        <span>{{ item.name }}</span>
                    </template>
                    <el-menu-item v-for="(item2,index2) in item.child" :key="index2" :index="item2.frontpath">
                        <el-icon>
                            <component :is="item2.icon"></component>
                        </el-icon>
                        <span>{{ item2.name }}</span>
                    </el-menu-item>
                </el-sub-menu>

                <el-menu-item v-else :index="item.frontpath">
                    <el-icon>
                         <component :is="item.icon"></component>
                    </el-icon>
                    <span>{{ item.name }}</span>
                </el-menu-item>
            </template>
        </el-menu>
    </div> -->
    <div class="f-menu">
      <el-menu :default-active="defaultActive" class="border-0" @select="handleSelect" :collapse-transition="false">
        <template v-for="(item, index) in asideMenus" :key="index">
          <el-sub-menu v-if="item.child && item.child.length > 0" :index="item.name">
            <template #title>
              <el-icon><component :is="item.icon"></component></el-icon>
              <span>{{ item.name }}</span>
            </template>
            <template v-for="(child1, index1) in item.child" :key="index1">
              <el-sub-menu v-if="child1.child && child1.child.length > 0" :index="child1.name">
                <template #title>
                  <el-icon><component :is="child1.icon"></component></el-icon>
                  <span>{{ child1.name }}</span>
                </template>
                <el-menu-item v-for="(child2, index2) in child1.child" :key="index2" :index="child2.frontpath">
                  <el-icon><component :is="child2.icon"></component></el-icon>
                  <span>{{ child2.name }}</span>
                </el-menu-item>
              </el-sub-menu>
              <el-menu-item v-else :index="child1.frontpath">
                <el-icon><component :is="child1.icon"></component></el-icon>
                <span>{{ child1.name }}</span>
              </el-menu-item>
            </template>
          </el-sub-menu>
          <el-menu-item v-else :index="item.frontpath">
            <el-icon><component :is="item.icon"></component></el-icon>
            <span>{{ item.name }}</span>
          </el-menu-item>
        </template>
      </el-menu>
</div>

</template>
<script setup>

import { computed,ref } from 'vue';
import { useRouter,useRoute } from 'vue-router';
const route = useRoute()
const router = useRouter()
const defaultActive = ref(route.path)
import { useAuthStore } from '@/stores/authStore'; 
const store = useAuthStore();
const { user, isAuthenticated,menus} = store;
// 动态绑定模型
console.log(menus)
const asideMenus = computed(() => store.menus); 

const handleSelect = (e)=>{

    router.push(e)
}
</script>
<style>
.f-menu {
    width: 250px;
    top: 64px;
    bottom: 0;
    left: 0;
    overflow: auto;
    @apply shadow-md fixed bg-light-50;
}
</style>