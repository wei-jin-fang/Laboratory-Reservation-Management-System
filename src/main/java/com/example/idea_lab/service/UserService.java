package com.example.idea_lab.service;

import com.example.idea_lab.pojo.User;

import java.util.List;

public interface UserService {
    void register(User user);

    // 检查用户名是否已存在
     boolean isUsernameExists(String username);
     User authenticate(String username, String password);
    boolean changePassword(String username, String oldPassword, String newPassword);

    List<User> getAllUsers();
    void updatePassword(String id, String newPassword);
    void deleteUser(String id);

}
