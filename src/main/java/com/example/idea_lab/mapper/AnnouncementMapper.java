package com.example.idea_lab.mapper;

import com.example.idea_lab.pojo.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    @Select("SELECT * FROM announcement")
    List<Announcement> getAll();

    @Insert("INSERT INTO announcement (title, content, publish_date, created_by, status) VALUES (#{title}, #{content}, #{publishDate}, #{createdBy}, #{status})")
    void insert(Announcement announcement);

    @Delete("DELETE FROM announcement WHERE id = #{id}")
    void deleteById(Long id);
}