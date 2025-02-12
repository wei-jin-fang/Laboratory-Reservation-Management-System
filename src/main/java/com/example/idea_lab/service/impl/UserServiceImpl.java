package com.example.idea_lab.service.impl;

import com.example.idea_lab.mapper.UserMapper;
import com.example.idea_lab.pojo.User;
import com.example.idea_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 修改密码的业务逻辑
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        // 获取用户信息
        User user = userMapper.findById(username);

        if (user != null && user.getPassword().equals(oldPassword)) {
            // 旧密码正确，更新为新密码
            userMapper.updatePassword(username, newPassword);
            return true;
        }
        // 旧密码不正确，返回 false
        return false;
    }

    // 用户认证逻辑
    @Override
    public User authenticate(String username, String password) {
        // 通过用户名和密码直接查询数据库
        return userMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public void register(User user) {
        // 注册时你可以添加密码加密等逻辑
        userMapper.register(user);
    }
    @Override
    // 检查用户名是否已存在
    public boolean isUsernameExists(String username) {
        return userMapper.countByUsername(username) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public void updatePassword(String id, String newPassword) {
        userMapper.updateUserPassword(id, newPassword);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }
}
