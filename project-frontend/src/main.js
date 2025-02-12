import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'virtual:windi.css'
import Vue3Tour from 'vue3-tour'

 

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
axios.defaults.baseURL = 'http://localhost:8080'

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.use(Vue3Tour)
app.mount('#app')
