package com.example.idea_lab.service.impl;

import com.example.idea_lab.mapper.EquipmentBorrowMapper;
import com.example.idea_lab.pojo.EquipmentBorrow;
import com.example.idea_lab.service.EquipmentBorrowService;
import com.example.idea_lab.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentBorrowServiceImpl implements EquipmentBorrowService {

    @Autowired
    private EquipmentBorrowMapper equipmentBorrowMapper;
    @Autowired



    private EquipmentService equipmentService;  // 引入 EquipmentService
     @Override
    public List<EquipmentBorrow> getBorrowRecordsByUser(String borrowedBy) {
        return equipmentBorrowMapper.getBorrowRecordsByUser(borrowedBy);
    }
    @Override
    public List<EquipmentBorrow> getAllBorrowRecords() {
        return equipmentBorrowMapper.getAllBorrowRecords();
    }

    @Override
    public List<EquipmentBorrow> getBorrowRecordsByEquipmentId(Long equipmentId) {
        return equipmentBorrowMapper.getBorrowRecordsByEquipmentId(equipmentId);
    }

    @Override
    public void borrowEquipment(EquipmentBorrow borrowRecord) {
        borrowRecord.setBorrowDate(LocalDateTime.now());
        borrowRecord.setStatus("BORROWED");
        equipmentBorrowMapper.insertBorrowRecord(borrowRecord);

        // 更新设备状态为 BORROWED
        equipmentService.updateEquipmentStatus(borrowRecord.getEquipmentId(), "BORROWED");
    }

    @Override
    public void returnEquipment(Long id) {
        EquipmentBorrow borrowRecord = new EquipmentBorrow();
        borrowRecord.setId(id);
        borrowRecord.setReturnDate(LocalDateTime.now());
        borrowRecord.setStatus("RETURNED");
        equipmentBorrowMapper.updateBorrowRecord(borrowRecord);

        // 更新设备状态为 AVAILABLE (假设归还时状态变回 "AVAILABLE")
//        这个地方id出现问题
        equipmentService.updateEquipmentStatus(id, "AVAILABLE");
    }
}