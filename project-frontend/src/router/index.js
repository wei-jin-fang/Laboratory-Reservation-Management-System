import { createRouter, createWebHistory } from 'vue-router'
import {useStore} from "@/stores";
import GoodList from '@/pages/teacher/list.vue'
import room102 from '@/pages/teacher/102.vue'
import room103 from '@/pages/teacher/103.vue'
import applylab from '@/pages/teacher/applylab.vue'
import EquipmentBorrow from '@/pages/common/EquipmentBorrow.vue'
import quipment from '@/pages/admin/quipment.vue'
import labfeedback from '@/pages/teacher/labfeedback.vue'
import labaffairs from '@/pages/admin/labaffairs.vue'
import labstats from '@/pages/admin/labstats.vue'
import gonggao from '@/pages/admin/gonggao.vue'
import usermanagement from '@/pages/admin/usermanagement.vue'
import index from '@/pages/teacher/index.vue'
import information103 from '@/pages/103/information.vue'
import schedule103 from '@/pages/103/schedule.vue'
import information102 from '@/pages/102/information.vue'
import schedule102 from '@/pages/102/schedule.vue'
import information104 from '@/pages/104/information.vue'
import schedule104 from '@/pages/104/schedule.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: () => import('@/views/WelcomeView.vue'),
      children: [
        {
          path: '',
          name: 'welcome-login',
          component: () => import('@/components/welcome/LoginPage.vue')
        }, {
          path: 'register',
          name: 'welcome-register',
          component: () => import('@/components/welcome/RegisterPage.vue')
        }, {
          path: 'forget',
          name: 'welcome-forget',
          component: () => import('@/components/welcome/ForgetPage.vue')
        },
       
      ]
 },  
    {
      path: '/index',
      name: 'index',
      component: () => import('@/components/index/admin.vue'),
      children:[
      {
        path:"/menu/onezerothree/information",
        component:information103,
        meta:{
            title:"103"
        }, 
    },{
      path:"/menu/onezerothree/schedule",
      component:schedule103,
      meta:{
          title:"103"
      }, 
    }
    
    
    ,{
      path:"/index/index",
      component:index,
      meta:{
          title:"index"
      }
    },{
      path:"/menu/onezerotwo",
      component:room102,
      meta:{
          title:"102"
      }
    },{
      path:"/menu/onezerotwo/information",
      component:information102,
      meta:{
          title:"103"
      }, 
  },{
    path:"/menu/onezerotwo/schedule",
    component:schedule102,
    meta:{
        title:"103"
    }, 
  },{
    path:"/menu/onezerofour/information",
    component:information104,
    meta:{
        title:"104"
    }, 
},{
  path:"/menu/onezerofour/schedule",
  component:schedule104,
  meta:{
      title:"104"
  }, 
}
,{
  path:"/teacher/applylab",
  component:applylab,
  meta:{
      title:"商品管理"
  }
},{
  path:"/teacher/quipment",
  component:quipment,
  meta:{
      title:"商品管理"
  }
},{
  path:"/teacher/labfeedback",
  component:labfeedback,
  meta:{
      title:"实验室反馈"
  }
},{
  path:"/admin/usermanagement",
  component:usermanagement,
  meta:{
      title:"商品管理"
  }
},{
  path:"/admin/labaffairs",
  component:labaffairs,
  meta:{
      title:"实验室反馈"
  }
},{
  path:"/admin/labstats",
  component:labstats,
  meta:{
      title:"实验室反馈"
  }
},{
  path:"/admin/gonggao",
  component:gonggao,
  meta:{
      title:"实验室反馈"
  }
},{
  path:"/common/EquipmentBorrow",
  component:EquipmentBorrow,
  meta:{
      title:"实验室反馈"
  }
}


]








    }
    ,
    // 这里捕获所有未定义的路由，重定向到 404 页面
    {
      path: '/:pathMatch(.*)*', // 捕获所有未匹配的路由
      name: 'not-found',
      component: () => import('@/views/NotFoundView.vue') // 404 页面组件
    }
  ]
})


// beforeEach 路由守卫（router.beforeEach）的作用是在每次路由切换之前拦截路由
// router.beforeEach((to, from, next) => {
//   const store = useStore(); // 获取 Pinia store，用于检查用户的登录状态

//   // 检查用户是否已登录，并且目标路由是否是 welcome 页面的子页面（登录、注册、忘记密码）
//   if (store.auth.user != null && to.name.startsWith('welcome-')) {
//     next('/index'); // 如果用户已登录，且目标是 welcome 页面相关路由，则重定向到 /index
//   } 
//   // 如果用户未登录并且试图访问 /index 开头的路由
//   else if (store.auth.user == null && to.fullPath.startsWith('/index')) {
//     next('/'); // 重定向到登录页面（/），因为用户未登录
//   }
//   // 如果没有匹配的路由（通常意味着 404）
//   else if (to.matched.length === 0) {
//     next('/index'); // 重定向到 /index 页面
//   }
//   // 正常情况下允许导航到目标路由
//   else {
//     next(); // 继续导航，允许用户访问该路由
//   }
// });




export default router
