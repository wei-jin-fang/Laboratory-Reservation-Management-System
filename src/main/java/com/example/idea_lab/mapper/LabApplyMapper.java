package com.example.idea_lab.mapper;


import com.example.idea_lab.pojo.LabSchedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabApplyMapper {
    void insertLabApplication(LabSchedule application);
}