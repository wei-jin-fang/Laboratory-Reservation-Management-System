package com.example.idea_lab.pojo;

import lombok.Data;

@Data

public class LabSchedule {
    private Integer id;
    private Integer labId;
    private Integer semesterWeek;
    private Integer weekday;
    private Integer timeslot;
    private String courseName;
    private String teacherName;

    // Getters and Setters
}
