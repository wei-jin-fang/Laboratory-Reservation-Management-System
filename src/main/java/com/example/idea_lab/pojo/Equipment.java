package com.example.idea_lab.pojo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Equipment {
    private Long id;
    private String name;
    private String model;
    private String status;
    private LocalDate purchaseDate;
    private LocalDate warrantyUntil;
    private String location;
    private LocalDate lastMaintenance;

    // Getters and Setters
}