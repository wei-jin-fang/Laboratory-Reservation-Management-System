package com.example.idea_lab.service;

import com.example.idea_lab.pojo.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> getAllEquipments();
    Equipment getEquipmentById(Long id);
    void createEquipment(Equipment equipment);
    void updateEquipment(Equipment equipment);
    void deleteEquipment(Long id);
    List<Equipment> getAvailableEquipments();
    void updateEquipmentStatus(Long equipmentId, String status);
}