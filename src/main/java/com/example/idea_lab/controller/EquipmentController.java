package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.Equipment;
import com.example.idea_lab.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;






    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    @PostMapping
    public void createEquipment(@RequestBody Equipment equipment) {
        equipmentService.createEquipment(equipment);
    }

    @PutMapping("/{id}")
    public void updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        equipment.setId(id);
        equipmentService.updateEquipment(equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }
}