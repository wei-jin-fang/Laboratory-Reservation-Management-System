package com.example.idea_lab.mapper;


import com.example.idea_lab.pojo.LabSchedule;
import com.example.idea_lab.pojo.ecahrtStatus.WeeklyUsageDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LabScheduleMapper {
    List<LabSchedule> selectWeeklyScheduleByLabIdAndWeek(Integer labId, Integer week);
    LabSchedule findScheduleByLabAndTime(@Param("labId") Integer labId,
                                         @Param("semesterWeek") Integer semesterWeek,
                                         @Param("weekday") Integer weekday,
                                         @Param("timeslot") Integer timeslot);
    @Select("SELECT * FROM lab_schedule WHERE teacher_name = #{teacherName}")
    List<LabSchedule> findSchedulesByTeacher(String teacherName);

    @Delete("DELETE FROM lab_schedule WHERE id = #{id}")
    void cancelSchedule(Integer id);

    @Select("SELECT lab_id AS labId, COUNT(*) AS usageCount FROM lab_schedule GROUP BY lab_id")
    List<Map<String, Object>> countLabUsagePerSemester();

    @Select("SELECT semester_week AS semesterWeek, COUNT(*) AS usageCount FROM lab_schedule WHERE lab_id = #{labId} GROUP BY semester_week")
    List<Map<String, Object>> countWeeklyUsageByLab(int labId);
}

