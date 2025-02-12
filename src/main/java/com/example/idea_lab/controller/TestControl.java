//package com.example.idea_lab.controller;
//
//import com.example.idea_lab.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class TestControl {
//
//    @RequestMapping("/login")
//    public String Login(@RequestBody User user){
//        if (user.getUsername()!=null){
//            return "success";
//        }
//        else {
//            return "false";
//        }
//    }
//    @RequestMapping("/register")
//    public String Register(@RequestBody User user){
//        userService.Adduser(user);
//        return "success";
//    }
//}