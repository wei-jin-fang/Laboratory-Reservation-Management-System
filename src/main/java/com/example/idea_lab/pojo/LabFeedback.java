package com.example.idea_lab.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LabFeedback {
    private Integer id;
    private String teacherName;
    private LocalDateTime feedbackTime;
    private String feedbackText;
    private Integer labId;
    private String adminResponse;

    // Getters and Setters
}