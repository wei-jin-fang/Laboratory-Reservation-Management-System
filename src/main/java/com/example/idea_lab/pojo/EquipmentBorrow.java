package com.example.idea_lab.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquipmentBorrow {
    private Long id;
    private Long equipmentId;
    private String borrowedBy;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private String status;

    // Getters and Setters
}