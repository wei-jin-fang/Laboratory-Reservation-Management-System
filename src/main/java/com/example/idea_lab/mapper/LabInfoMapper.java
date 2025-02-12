package com.example.idea_lab.mapper;

import com.example.idea_lab.pojo.LabInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LabInfoMapper {
    @Select("SELECT * FROM LabInfo WHERE lab_id = #{labId}")
    LabInfo findByLabId(int labId);
}