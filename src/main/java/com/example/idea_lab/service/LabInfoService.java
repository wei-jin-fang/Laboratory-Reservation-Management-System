package com.example.idea_lab.service;

import com.example.idea_lab.mapper.LabInfoMapper;
import com.example.idea_lab.pojo.LabInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface LabInfoService {


    public LabInfo getLabInfoById(int labId) ;
}