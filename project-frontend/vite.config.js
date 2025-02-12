import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import WindiCSS from 'vite-plugin-windicss'
// https://vitejs.dev/config/


export default defineConfig({
  plugins: [
      WindiCSS(),
      vue(),
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
   // 新增 server 配置
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // 后端服务器的地址
        changeOrigin: true,  // 是否需要改变请求头中的 host
        rewrite: (path) => path.replace(/^\/api/, '')  // 可选：将 '/api' 替换为后端需要的路径
      }
    }
  }
})


