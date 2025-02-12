package com.example.idea_lab.service.impl;

import com.example.idea_lab.mapper.LabScheduleMapper;
import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.pojo.ecahrtStatus.LabUsageStats;
import com.example.idea_lab.pojo.ecahrtStatus.WeeklyUsageDTO;
import com.example.idea_lab.service.LabScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabScheduleServiceImpl implements LabScheduleService {
    @Autowired
    private LabScheduleMapper mapper;

    @Override
    public List<LabSchedule> getWeeklySchedule(Integer labId, Integer week) {

        return mapper.selectWeeklyScheduleByLabIdAndWeek(labId, week);
    }

    public List<LabSchedule> findSchedulesByTeacher(String teacherName) {
        return mapper.findSchedulesByTeacher(teacherName);
    }

    public void cancelSchedule(Integer id) {
        mapper.cancelSchedule(id);
    }

    @Override
    public List<LabUsageStats> countLabUsagePerSemester() {
        List<Map<String, Object>> results = mapper.countLabUsagePerSemester();
        List<LabUsageStats> statsList = new ArrayList<>();
        for (Map<String, Object> result : results) {
            LabUsageStats stats = new LabUsageStats();
            stats.setLabId(Integer.parseInt(result.get("labId").toString()));
            stats.setUsageCount(Long.parseLong(result.get("usageCount").toString()));
            statsList.add(stats);
        }
        return statsList;
    }

    @Override
    public List<WeeklyUsageDTO> countWeeklyUsageByLab(int labId) {
        List<Map<String, Object>> results = mapper.countWeeklyUsageByLab(labId);
        List<WeeklyUsageDTO> statsList = new ArrayList<>();
        for (Map<String, Object> result : results) {
            int week = (Integer) result.get("semesterWeek");
            long count = (Long) result.get("usageCount");
            statsList.add(new WeeklyUsageDTO(week, count));
        }
        return statsList;
    }
}