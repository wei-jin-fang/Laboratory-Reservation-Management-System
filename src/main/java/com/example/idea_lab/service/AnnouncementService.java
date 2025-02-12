package com.example.idea_lab.service;

import com.example.idea_lab.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();
    void createAnnouncement(Announcement announcement);
    void deleteAnnouncement(Long id);
}