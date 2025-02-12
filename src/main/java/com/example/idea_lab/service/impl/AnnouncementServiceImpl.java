package com.example.idea_lab.service.impl;

import com.example.idea_lab.mapper.AnnouncementMapper;
import com.example.idea_lab.pojo.Announcement;
import com.example.idea_lab.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementMapper announcementMapper;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.getAll();
    }

    @Override
    public void createAnnouncement(Announcement announcement) {
        announcement.setPublishDate(LocalDateTime.now()); // 设置当前时间为发布时间
        announcementMapper.insert(announcement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementMapper.deleteById(id);
    }
}