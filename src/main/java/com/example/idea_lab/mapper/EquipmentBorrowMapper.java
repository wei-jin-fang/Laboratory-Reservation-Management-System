package com.example.idea_lab.mapper;


import com.example.idea_lab.pojo.EquipmentBorrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentBorrowMapper {

    @Select("SELECT * FROM equipment_borrow WHERE equipment_id = #{equipmentId}")
    List<EquipmentBorrow> getBorrowRecordsByEquipmentId(Long equipmentId);

    @Insert("INSERT INTO equipment_borrow (equipment_id, borrowed_by, borrow_date, status) " +
            "VALUES (#{equipmentId}, #{borrowedBy}, #{borrowDate}, #{status})")
    void insertBorrowRecord(EquipmentBorrow borrowRecord);

    @Update("UPDATE equipment_borrow SET return_date = #{returnDate}, status = #{status} WHERE equipment_id = #{id}")
    void updateBorrowRecord(EquipmentBorrow borrowRecord);

    @Select("SELECT * FROM equipment_borrow")
    List<EquipmentBorrow> getAllBorrowRecords();
    @Select("SELECT * FROM equipment_borrow WHERE borrowed_by = #{borrowedBy}")
    List<EquipmentBorrow> getBorrowRecordsByUser(@Param("borrowedBy") String borrowedBy);
}