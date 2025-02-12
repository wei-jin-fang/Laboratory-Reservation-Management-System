package com.example.idea_lab.service;

import com.example.idea_lab.pojo.EquipmentBorrow;

import java.util.List;

public interface EquipmentBorrowService {
    List<EquipmentBorrow> getAllBorrowRecords();
    List<EquipmentBorrow> getBorrowRecordsByEquipmentId(Long equipmentId);
    void borrowEquipment(EquipmentBorrow borrowRecord);
    void returnEquipment(Long id);
    List<EquipmentBorrow> getBorrowRecordsByUser(String borrowedBy);

}