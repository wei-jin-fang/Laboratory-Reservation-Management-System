package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.Equipment;
import com.example.idea_lab.pojo.EquipmentBorrow;
import com.example.idea_lab.service.EquipmentBorrowService;
import com.example.idea_lab.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipment-borrow")
public class EquipmentBorrowController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentBorrowService equipmentBorrowService;

    // 获取所有可用设备
    @GetMapping("/available")
    public List<Equipment> getAvailableEquipments() {
        System.out.println("查询可用设备");
        return equipmentService.getAvailableEquipments();
    }

    // 借用设备
    @PostMapping("/borrow")
    public void borrowEquipment(@RequestBody EquipmentBorrow borrowRecord) {
        System.out.println("开始借"+borrowRecord.getEquipmentId());
        equipmentBorrowService.borrowEquipment(borrowRecord);

    }

    // 获取借用记录
    @GetMapping("/borrow-records")
    public List<EquipmentBorrow> getBorrowRecords() {
        return equipmentBorrowService.getAllBorrowRecords();
    }
    // 获取当前用户的借用记录
    @PostMapping("/my-borrows")
    public List<EquipmentBorrow> getMyBorrowRecords(@RequestBody Map<String, String> request) {
        String borrowedBy = request.get("borrowedBy");

        return equipmentBorrowService.getBorrowRecordsByUser(borrowedBy);
    }
    // 退还设备
    @PostMapping("/return/{id}")
    public void returnEquipment(@PathVariable Long id) {
//        System.out.println(id);
        equipmentBorrowService.returnEquipment(id);
    }
}