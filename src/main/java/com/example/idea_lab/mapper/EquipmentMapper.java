package com.example.idea_lab.mapper;

import com.example.idea_lab.pojo.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    @Update("UPDATE equipment SET status = #{status} WHERE id = #{id}")
    void updateEquipmentStatus(@Param("id") Long id, @Param("status") String status);
    // 获取所有状态为 "AVAILABLE" 的设备
    @Select("SELECT * FROM equipment WHERE status = 'AVAILABLE'")
    List<Equipment> getAvailableEquipments();
    @Select("SELECT * FROM equipment")
    List<Equipment> getAll();

    @Select("SELECT * FROM equipment WHERE id = #{id}")
    Equipment getById(Long id);

    @Insert("INSERT INTO equipment (name, model, status, purchase_date, warranty_until, location, last_maintenance) " +
            "VALUES (#{name}, #{model}, #{status}, #{purchaseDate}, #{warrantyUntil}, #{location}, #{lastMaintenance})")
    void insert(Equipment equipment);

    @Update("UPDATE equipment SET name=#{name}, model=#{model}, status=#{status}, purchase_date=#{purchaseDate}, " +
            "warranty_until=#{warrantyUntil}, location=#{location}, last_maintenance=#{lastMaintenance} WHERE id=#{id}")
    void update(Equipment equipment);

    @Delete("DELETE FROM equipment WHERE id = #{id}")
    void delete(Long id);
}