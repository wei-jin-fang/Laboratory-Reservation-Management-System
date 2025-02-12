package com.example.idea_lab.service.impl;


import com.example.idea_lab.mapper.LabInfoMapper;
import com.example.idea_lab.pojo.LabInfo;
import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.service.LabInfoService;
import com.example.idea_lab.service.LabScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LabInfoServiceImpl implements LabInfoService {
    @Autowired
    private LabInfoMapper labInfoMapper;
    @Override
    public LabInfo getLabInfoById(int labId) {
        return labInfoMapper.findByLabId(labId);
    }
}