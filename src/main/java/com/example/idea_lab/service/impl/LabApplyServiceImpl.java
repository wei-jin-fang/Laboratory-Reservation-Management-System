package com.example.idea_lab.service.impl;


import com.example.idea_lab.mapper.LabApplyMapper;
import com.example.idea_lab.mapper.LabScheduleMapper;
import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.service.LabApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LabApplyServiceImpl implements LabApplyService {
    @Autowired
    private LabScheduleMapper scheduleMapper;
    @Autowired
    private LabApplyMapper applyMapper;

    @Transactional
    @Override
    public LabSchedule applyForLab(LabSchedule apply) {
        // 检查是否存在在该时段的预约
        LabSchedule existing = scheduleMapper.findScheduleByLabAndTime(apply.getLabId(), apply.getSemesterWeek(), apply.getWeekday(), apply.getTimeslot());

        if (existing != null) {
            throw new IllegalStateException("This time slot in lab " + apply.getLabId() + " is already booked.");
        }else {
            System.out.println("没有课");
        }

        applyMapper.insertLabApplication(apply);
        return apply;
    }
}
