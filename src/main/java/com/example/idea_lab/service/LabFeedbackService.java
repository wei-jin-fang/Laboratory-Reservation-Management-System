package com.example.idea_lab.service;

import com.example.idea_lab.pojo.LabFeedback;

import java.util.List;

public interface LabFeedbackService {
    void submitFeedback(LabFeedback feedback);
    public List<LabFeedback> getFeedbackByTeacherName(String teacherName);
    List<LabFeedback> getAllFeedbacks();
    void respondToFeedback(Integer id, String response);
}
