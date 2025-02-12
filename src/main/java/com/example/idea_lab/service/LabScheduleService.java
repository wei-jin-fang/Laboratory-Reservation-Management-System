package com.example.idea_lab.service;

import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.pojo.ecahrtStatus.LabUsageStats;
import com.example.idea_lab.pojo.ecahrtStatus.WeeklyUsageDTO;

import java.util.List;
import java.util.Map;

public interface LabScheduleService {
    List<LabSchedule> getWeeklySchedule(Integer labId, Integer week);
    public List<LabSchedule> findSchedulesByTeacher(String teacherName);
    public void cancelSchedule(Integer id);

    List<LabUsageStats> countLabUsagePerSemester();

    public List<WeeklyUsageDTO> countWeeklyUsageByLab(int labId);
}