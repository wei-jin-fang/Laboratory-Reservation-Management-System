package com.example.idea_lab.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class LabInfo {

    private Integer id;

    private Integer labId;

    private String location;

    private String manager;
    private Integer workstationCount;
    private String photoPath;
    private String description;
    private String openStatus;

    // Getters and Setters
}