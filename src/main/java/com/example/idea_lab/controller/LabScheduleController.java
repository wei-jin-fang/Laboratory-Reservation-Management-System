package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.service.LabScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lab-schedules")
public class LabScheduleController {
    @Autowired
    private LabScheduleService service;

    @GetMapping("/week-schedule")
    public ResponseEntity<Map<String, Object>> getWeeklySchedule(@RequestParam Integer labId, @RequestParam Integer week) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<LabSchedule> weeklySchedule = service.getWeeklySchedule(labId, week);
            if (weeklySchedule.isEmpty()) {
                response.put("message", "No schedules found for the specified lab and week.");
                response.put("status", "404");
                response.put("success", "true");
                return ResponseEntity.ok(response); // 返回200 OK，但消息表示没有数据
            }
            response.put("message", weeklySchedule); // 将查询到的数据作为消息内容
            response.put("status", "success");
            response.put("success", "true");
            return ResponseEntity.ok(response); // 返回200 OK 与详细的课表数据
        } catch (Exception e) {
            response.put("message", "An error occurred: " + e.getMessage());
            response.put("status", "error");
            response.put("success", "false");
            return ResponseEntity.internalServerError().body(response); // 返回500 Internal Server Error 与错误消息
        }
    }

    @GetMapping("/get-courses")
    public ResponseEntity<List<LabSchedule>> getCoursesByTeacher(@RequestParam String teacherName) {
        List<LabSchedule> schedules = service.findSchedulesByTeacher(teacherName);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestBody Map<String, Object> payload) {
        try {
            Integer courseId = Integer.valueOf(payload.get("courseId").toString());
            service.cancelSchedule(courseId);
            return ResponseEntity.ok("Booking canceled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error cancelling booking: " + e.getMessage());
        }
    }
}