        <!-- 写一个用户管理功能，要求
        页面加载时候读取后端数据库 accountinfo 表
        实体类如下
        public class User {
            private String id;
            private String username;
            private String password;
            private String email;
            private String level;
            private String phone;
            private String realname;
        使用ElementUI PLUS 进行前端展示
        要求每一行都有一个修改密码和删除用户按钮
        给我给出前后端代码，前端使用Vue3+ElementUI Plus，后端SpringBoot3+Mybaits
            后端三层架构，service里要有接口和实现类

        -->
<template>
    <div>
        <el-table :data="users" style="width: 100%">
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="email" label="电子邮件"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="realname" label="真实姓名"></el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
            <el-button type="primary" @click="updateUserPassword(scope.row)">修改密码</el-button>
            <el-button type="danger" @click="deleteUser(scope.row.id)">删除用户</el-button>
            </template>
        </el-table-column>
        </el-table>
    </div>
    </template>
    
    <script>
    import axios from 'axios';
    
    export default {
    data() {
        return {
        users: []
        };
    },
    methods: {
        fetchUsers() {
        axios.get('/admin/').then(response => {
            this.users = response.data;
        });
        },
        updateUserPassword(user) {
        // 弹窗获取新密码然后调用 API 更新密码
        this.$prompt('请输入新密码', '密码更新', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /^[a-zA-Z0-9]{6,20}$/,
            inputErrorMessage: '密码长度应在6到20个字符之间'
        }).then(({ value }) => {
            axios.post('/admin/update-password', { ...user, password: value }).then(() => {
            this.$message.success('密码更新成功');
            }).catch(() => {
            this.$message.error('密码更新失败');
            });
        }).catch(() => {});
        },
        deleteUser(id) {
        this.$confirm('确认删除此用户?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            axios.delete(`/admin/delete/${id}`).then(() => {
            this.fetchUsers();  // 重新加载用户列表
            this.$message.success('用户删除成功');
            }).catch(() => {
            this.$message.error('用户删除失败');
            });
        }).catch(() => {});
        }
    },
    created() {
        this.fetchUsers();
    }
    };
    </script>
                  
