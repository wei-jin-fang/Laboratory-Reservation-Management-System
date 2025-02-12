package com.example.idea_lab.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Announcement {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishDate;
    private String createdBy;
    private String status;
    private LocalDateTime lastUpdated;

    // Getters and Setters
}