package com.example.idea_lab.service;

import com.example.idea_lab.pojo.MenuItem;

import java.util.List;

public interface MenuService {
    MenuItem getLabInfoMenu();
    MenuItem getInfoManagementMenu();
     List<MenuItem> getMenuByRole(String role);
}
