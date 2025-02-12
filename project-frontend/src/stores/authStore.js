// import { defineStore } from 'pinia';

// export const useAuthStore = defineStore('auth', {
//     state: () => ({
//         user: null,  // 用来存储当前登录用户信息
//         isAuthenticated: false  // 是否已登录的标志
//     }),
//     actions: {
//         // 保存用户信息
//         setUser(user) {
//             this.user = user;
//             this.isAuthenticated = true;  // 标记为已登录
//         },
//         // 清除用户信息（登出）
//         logout() {
//             this.user = null;
//             this.isAuthenticated = false;
//         }
//     }
// });
import { defineStore } from 'pinia';
import { get } from "@/net";

export const useAuthStore = defineStore('auth', {
    state: () => ({
        user: JSON.parse(localStorage.getItem('user')) || null,
        isAuthenticated: !!localStorage.getItem('user'),
        menus: JSON.parse(localStorage.getItem('menus')) || [],  // 从 localStorage 恢复菜单信息
    }),
    actions: {
        setUser(user) {
            this.user = user;
            this.isAuthenticated = true;
            localStorage.setItem('user', JSON.stringify(user));
            this.fetchMenus(user.level);
        },
        fetchMenus(level) {
            get(`/user/menus?level=${level}`,
            (message, status) => {
                this.menus = message;
                localStorage.setItem('menus', JSON.stringify(message));  // 将菜单数据保存到 localStorage
            },
            (failureMessage) => {
                console.warn(`获取菜单失败：${JSON.stringify(failureMessage)}`);
            },
            (error) => {
                console.error("请求错误:", error);
            });
        },
        logout() {
            this.user = null;
            this.isAuthenticated = false;
            this.menus = [];
            localStorage.removeItem('user');
            localStorage.removeItem('menus');  // 移除菜单数据
        },
        initialize() {
            const user = JSON.parse(localStorage.getItem('user'));
            if (user) {
                this.user = user;
                this.isAuthenticated = true;
                this.fetchMenus(user.level);  // 如果用户已登录，重新获取菜单数据
            }
        }
    }
});

 