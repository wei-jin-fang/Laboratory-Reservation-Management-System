package com.example.idea_lab.service.impl;

import com.example.idea_lab.mapper.LabFeedbackMapper;
import com.example.idea_lab.pojo.LabFeedback;
import com.example.idea_lab.service.LabFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabFeedbackServiceImpl implements LabFeedbackService {
    @Autowired
    private LabFeedbackMapper feedbackMapper;

    @Override
    public void submitFeedback(LabFeedback feedback) {
        feedbackMapper.insertFeedback(feedback);
    }

    @Override
    public List<LabFeedback> getFeedbackByTeacherName(String teacherName) {
        return feedbackMapper.findAllByTeacherName(teacherName);
    }
    @Override
    public List<LabFeedback> getAllFeedbacks() {
        return feedbackMapper.findAllFeedbacks();
    }

    @Override
    public void respondToFeedback(Integer id, String response) {
        feedbackMapper.updateAdminResponse(id, response);
    }
}