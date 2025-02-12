package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.MenuItem;
import com.example.idea_lab.pojo.User;
import com.example.idea_lab.service.MenuService;
import com.example.idea_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public ResponseEntity<Map<String, Object>> getMenusByUsername(@RequestParam String level) {
        Map<String, Object> response = new HashMap<>();

        // 假设你从数据库或其他服务获取用户角色，这里用简单的判断来模拟


        List<MenuItem> menus = menuService.getMenuByRole(level);
        response.put("success", "true");
        response.put("message", menus);
        response.put("status", "true");
        System.out.println(menus);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody Map<String, String> passwordData) {
        String username = passwordData.get("username");  // 假设前端传递了 userId
        String oldPassword = passwordData.get("oldpassword");
        String newPassword = passwordData.get("password");
        System.out.println(username);
        Map<String, Object> response = new HashMap<>();
        boolean isChanged = userService.changePassword((username), oldPassword, newPassword);

        if (isChanged) {
            response.put("success", "true");
            response.put("message", "密码修改成功");
            response.put("status", "true");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", "false");
            response.put("message", "旧密码不正确");
            response.put("status", "false");
            return ResponseEntity.ok(response);
        }
    }


    @GetMapping("/check-username")
    public ResponseEntity<Map<String, String>> checkUsername(@RequestParam String username) {
        System.out.println("检查冲突");
        boolean exists = userService.isUsernameExists(username);

        Map<String, String> response = new HashMap<>();
        if (exists) {
        response.put("success", "true");
        response.put("status", "exists");  // 学号已存在
        response.put("message", "学号已存在");
        }else{
            response.put("success", "true");
            response.put("status", "available");
            response.put("message", "学号可用");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        System.out.println(user);
        Map<String, Object> response = new HashMap<>();
        // 你可以在此处添加更多校验逻辑
        try {
            userService.register(user);
            response.put("success", "true");
            response.put("status", "ok");  // 学号已存在
            response.put("message", "注册成功");
            return ResponseEntity.ok(response);  // 返回200 OK状态和JSON格式的响应
        }catch (Exception e){
            System.out.println(e);
            response.put("success", "false");
            response.put("status", "no");  // 学号已存在
            response.put("message", "注册失败");
            return ResponseEntity.ok(response);  // 返回200 OK状态和JSON格式的响应
        }


    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        boolean remember = Boolean.parseBoolean(loginData.get("remember"));

        Map<String, Object> response = new HashMap<>();
        User user = userService.authenticate(username, password);
        System.out.println(user);

        if (user != null) {
            response.put("message", user);
            response.put("status", "success");
            response.put("success", "true");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "用户名或密码错误");
            response.put("status", "error");
            response.put("success", "false");
            return ResponseEntity.ok(response);
        }
    }



}
//请你仿照上面内容，根据下面前端代码，
// 与后端controller给出给出实验室信息与管理系统的用户登录流程内容，与文本类型的流程图
//请你根据上面内容，根据前端代码， 与后端controller给出给出实验室信息与管理系统的用户注册流程内容尽可能详细，与文本类型的流程图