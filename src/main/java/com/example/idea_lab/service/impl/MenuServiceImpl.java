package com.example.idea_lab.service.impl;

import com.example.idea_lab.pojo.MenuItem;
import com.example.idea_lab.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public MenuItem getLabInfoMenu() {
        MenuItem labInfo = new MenuItem();
        labInfo.setName("实验室信息Home");
        labInfo.setIcon("help");

        List<MenuItem> labInfoChildren = new ArrayList<>();

        // 实验室 103 配置
        MenuItem lab103 = new MenuItem();
        lab103.setName("103");
        lab103.setIcon("home-filled");
        lab103.setFrontpath("/menu/onezerothree");

        List<MenuItem> lab103Children = new ArrayList<>();
        MenuItem lab103Equipment = new MenuItem();
        lab103Equipment.setName("实验室介绍");
        lab103Equipment.setIcon("home-filled");
        lab103Equipment.setFrontpath("/menu/onezerothree/information");

        lab103Children.add(lab103Equipment);

        MenuItem lab103Schedule = new MenuItem();
        lab103Schedule.setName("课表信息");
        lab103Schedule.setIcon("calendar");
        lab103Schedule.setFrontpath("/menu/onezerothree/schedule");
        lab103Children.add(lab103Schedule);

        lab103.setChild(lab103Children);

        // 实验室 102 配置
        MenuItem lab102 = new MenuItem();
        lab102.setName("102");
        lab102.setIcon("home-filled");
        lab102.setFrontpath("/menu/onezerotwo");

        List<MenuItem> lab102Children = new ArrayList<>();
        MenuItem lab102Equipment = new MenuItem();
        lab102Equipment.setName("实验室介绍");
        lab102Equipment.setIcon("home-filled");
        lab102Equipment.setFrontpath("/menu/onezerotwo/information");

        lab102Children.add(lab102Equipment);

        MenuItem lab102Schedule = new MenuItem();
        lab102Schedule.setName("课表信息");
        lab102Schedule.setIcon("calendar");
        lab102Schedule.setFrontpath("/menu/onezerotwo/schedule");
        lab102Children.add(lab102Schedule);

        lab102.setChild(lab102Children);

        // 实验室 104 配置
        MenuItem lab104 = new MenuItem();
        lab104.setName("104");
        lab104.setIcon("home-filled");
        lab104.setFrontpath("/menu/onezerofour");

        List<MenuItem> lab104Children = new ArrayList<>();
        MenuItem lab104Equipment = new MenuItem();
        lab104Equipment.setName("实验室介绍");
        lab104Equipment.setIcon("home-filled");
        lab104Equipment.setFrontpath("/menu/onezerofour/information");

        lab104Children.add(lab104Equipment);

        MenuItem lab104Schedule = new MenuItem();
        lab104Schedule.setName("课表信息");
        lab104Schedule.setIcon("calendar");
        lab104Schedule.setFrontpath("/menu/onezerofour/schedule");
        lab104Children.add(lab104Schedule);

        lab104.setChild(lab104Children);

        // 将所有实验室菜单添加到总菜单
        labInfoChildren.add(lab103);
        labInfoChildren.add(lab102);
        labInfoChildren.add(lab104);

        labInfo.setChild(labInfoChildren);
        return labInfo;
    }
    // 返回信息管理菜单
    public MenuItem getInfoManagementMenu() {
        MenuItem infoManagement = new MenuItem();
        infoManagement.setName("教师服务管理");
        infoManagement.setIcon("shopping-bag");

        List<MenuItem> infoManagementChildren = new ArrayList<>();

        // 预定实验室管理
        MenuItem bookingList = new MenuItem();
        bookingList.setName("预定实验室管理");
        bookingList.setIcon("Edit");
        bookingList.setFrontpath("/teacher/applylab");

        infoManagementChildren.add(bookingList);

        // 实验室事务反馈
        MenuItem labAffairsFeedback = new MenuItem();
        labAffairsFeedback.setName("实验室事务反馈");
        labAffairsFeedback.setIcon("message");
        labAffairsFeedback.setFrontpath("/teacher/labfeedback");
        infoManagementChildren.add(labAffairsFeedback);

        // 实验室事务反馈
        MenuItem getequipmentsFeedback = new MenuItem();
        getequipmentsFeedback.setName("实验室设备预约操作");
        getequipmentsFeedback.setIcon("message");
        getequipmentsFeedback.setFrontpath("/common/EquipmentBorrow");
        infoManagementChildren.add(getequipmentsFeedback);


        // 将子菜单列表设置到教师服务管理菜单项
        infoManagement.setChild(infoManagementChildren);

        return infoManagement;
    }

    // 返回管理员管理菜单
    // 仿照我上面代码风格，要求管理员管理菜单，名字是管理员管理
//    有两个一级菜单，第一个是用户管理，第二个是实验室事务处理
    public MenuItem getAdminManagementMenu() {
        MenuItem adminManagement = new MenuItem();
        adminManagement.setName("管理员管理");
        adminManagement.setIcon("setting");

        List<MenuItem> adminManagementChildren = new ArrayList<>();

        // 用户管理
        MenuItem userManager = new MenuItem();
        userManager.setName("用户管理");
        userManager.setIcon("user");
        userManager.setFrontpath("/admin/usermanagement");
        adminManagementChildren.add(userManager);

        // 实验室事务处理
        MenuItem labAffairsManagement = new MenuItem();
        labAffairsManagement.setName("实验室事务处理");
        labAffairsManagement.setIcon("message-box");
        labAffairsManagement.setFrontpath("/admin/labaffairs");
        adminManagementChildren.add(labAffairsManagement);

        // 实验室数据统计
        MenuItem labStatsManagement = new MenuItem();
        labStatsManagement.setName("实验室数据统计");
        labStatsManagement.setIcon("Stopwatch");
        labStatsManagement.setFrontpath("/admin/labstats");
        adminManagementChildren.add(labStatsManagement);

        // 实验室数据统计
        MenuItem gonggaoManagement = new MenuItem();
        gonggaoManagement.setName("实验室公告发布");
        gonggaoManagement.setIcon("Stopwatch");
        gonggaoManagement.setFrontpath("/admin/gonggao");
        adminManagementChildren.add(gonggaoManagement);

        // 将子菜单列表设置到管理员管理菜单项
        adminManagement.setChild(adminManagementChildren);

        // 实验室数据统计
        MenuItem equipment = new MenuItem();
        equipment.setName("实验室设备管理");
        equipment.setIcon("message");
        equipment.setFrontpath("/teacher/quipment");
        adminManagementChildren.add(equipment);

        // 将子菜单列表设置到管理员管理菜单项
        adminManagement.setChild(adminManagementChildren);





        return adminManagement;
    }


    // 根据角色返回不同菜单
    public List<MenuItem> getMenuByRole(String role) {
        List<MenuItem> menuList = new ArrayList<>();

        // 所有角色都有实验室信息Home菜单
        menuList.add(getLabInfoMenu());

        // 如果角色是 teacher，增加信息管理菜单
        if ("teacher".equals(role)) {
            menuList.add(getInfoManagementMenu());
        }
        else if("admin".equals(role)) {
            menuList.add(getInfoManagementMenu());
            menuList.add(getAdminManagementMenu());
        }


        return menuList;
    }
}