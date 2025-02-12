package com.example.idea_lab.controller;

import com.example.idea_lab.pojo.LabInfo;
import com.example.idea_lab.service.LabInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/labs")
public class LabInfoController {
    @Autowired
    private LabInfoService labInfoService;

    @GetMapping("/{labId}")
    public LabInfo getLabInfo(@PathVariable int labId) {
        return labInfoService.getLabInfoById(labId);
    }
}