package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.LabFeedback;
import com.example.idea_lab.service.LabFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class LabFeedbackController {
    @Autowired
    private LabFeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitFeedback(@RequestBody LabFeedback feedback) {
        feedbackService.submitFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully");
    }

    @GetMapping("/my-feedback")
    public ResponseEntity<List<LabFeedback>> getMyFeedback(@RequestParam String teacherName) {
        List<LabFeedback> feedbacks = feedbackService.getFeedbackByTeacherName(teacherName);
        return ResponseEntity.ok(feedbacks);
    }
}