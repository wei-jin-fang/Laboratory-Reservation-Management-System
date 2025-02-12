package com.example.idea_lab.mapper;

import com.example.idea_lab.pojo.LabFeedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LabFeedbackMapper {
    @Insert("INSERT INTO lab_feedback (teacher_name, feedback_time, feedback_text, lab_id, admin_response) " +
            "VALUES (#{teacherName}, #{feedbackTime}, #{feedbackText}, #{labId}, #{adminResponse})")
    void insertFeedback(LabFeedback feedback);

    @Select("SELECT * FROM lab_feedback WHERE teacher_name = #{teacherName}")
    List<LabFeedback> findAllByTeacherName(String teacherName);

    @Select("SELECT * FROM lab_feedback")
    List<LabFeedback> findAllFeedbacks();

    @Update("UPDATE lab_feedback SET admin_response = #{adminResponse} WHERE id = #{id}")
    void updateAdminResponse(Integer id, String adminResponse);
}