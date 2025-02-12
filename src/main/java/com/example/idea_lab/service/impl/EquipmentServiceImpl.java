package com.example.idea_lab.service.impl;

import com.example.idea_lab.mapper.EquipmentMapper;
import com.example.idea_lab.pojo.Equipment;
import com.example.idea_lab.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;
    @Override
    public void updateEquipmentStatus(Long equipmentId, String status) {
        equipmentMapper.updateEquipmentStatus(equipmentId, status);
    }
    @Override
    public List<Equipment> getAvailableEquipments() {
        return equipmentMapper.getAvailableEquipments();
    }
    @Override
    public List<Equipment> getAllEquipments() {
        return equipmentMapper.getAll();
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentMapper.getById(id);
    }

    @Override
    public void createEquipment(Equipment equipment) {
        equipment.setPurchaseDate(LocalDate.now()); // 设置当前时间为发布时间
        equipmentMapper.insert(equipment);
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        equipmentMapper.update(equipment);
    }

    @Override
    public void deleteEquipment(Long id) {
        equipmentMapper.delete(id);
    }
}