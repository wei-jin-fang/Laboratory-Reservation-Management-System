package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.ecahrtStatus.LabUsageStats;
import com.example.idea_lab.pojo.ecahrtStatus.WeeklyUsageDTO;
import com.example.idea_lab.service.LabScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lab-stats")
public class LabEchartsController {

    @Autowired
    private LabScheduleService labScheduleService;

    // 获取所有实验室这个学期的使用次数
    @GetMapping("/total-usage")
    public ResponseEntity<List<LabUsageStats>> getTotalLabUsage() {
        List<LabUsageStats> usageStats = labScheduleService.countLabUsagePerSemester();
        return ResponseEntity.ok(usageStats);
    }

    // 获取指定实验室每周的使用次数
    @GetMapping("/weekly-usage/{labId}")
    public ResponseEntity<List<WeeklyUsageDTO>> getWeeklyUsageByLab(@PathVariable int labId) {
        List<WeeklyUsageDTO> weeklyUsage = labScheduleService.countWeeklyUsageByLab(labId);
        return ResponseEntity.ok(weeklyUsage);
    }
}