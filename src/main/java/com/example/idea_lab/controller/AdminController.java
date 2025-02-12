package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.LabFeedback;
import com.example.idea_lab.pojo.User;
import com.example.idea_lab.service.LabFeedbackService;
import com.example.idea_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private LabFeedbackService feedbackService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());

    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody User user) {
        userService.updatePassword(user.getId(), user.getPassword());
        return ResponseEntity.ok("Password updated successfully");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    @GetMapping("/feedback_all")
    public ResponseEntity<List<LabFeedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @PostMapping("/feedback_respond")
    public ResponseEntity<?> respondToFeedback(@RequestBody LabFeedback feedback) {
        feedbackService.respondToFeedback(feedback.getId(), feedback.getAdminResponse());
        return ResponseEntity.ok("Response saved successfully");
    }
}