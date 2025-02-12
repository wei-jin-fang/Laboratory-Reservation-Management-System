package com.example.idea_lab.pojo.ecahrtStatus;

public class WeeklyUsageDTO {
    private int semesterWeek;
    private long usageCount;

    public WeeklyUsageDTO(int semesterWeek, long usageCount) {
        this.semesterWeek = semesterWeek;
        this.usageCount = usageCount;
    }

    // Getters and Setters
    public int getSemesterWeek() {
        return semesterWeek;
    }

    public void setSemesterWeek(int semesterWeek) {
        this.semesterWeek = semesterWeek;
    }

    public long getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(long usageCount) {
        this.usageCount = usageCount;
    }
}