package com.example.idea_lab.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class LabApply {

    private Integer id;

    private Integer labId;
    private Integer semesterWeek;
    private Integer weekday;
    private Integer timeslot;
    private String courseName;
    private String teacherName;
    private String status;

    // Getters and Setters
}