package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.LabApply;
import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.service.impl.LabApplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab-apply")
public class LabApplyController {

    @Autowired
    private LabApplyServiceImpl service;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForLab(@RequestBody LabSchedule apply) {
        try {

            LabSchedule newApply = service.applyForLab(apply);
            System.out.println(newApply);
            return ResponseEntity.ok(newApply);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}