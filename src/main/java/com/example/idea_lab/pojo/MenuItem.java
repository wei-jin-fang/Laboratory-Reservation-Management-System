package com.example.idea_lab.pojo;


import java.util.List;

public class MenuItem {
    private String name;
    private String icon;
    private String frontpath;
    private List<MenuItem> child;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", frontpath='" + frontpath + '\'' +
                ", child=" + child +
                '}';
    }

    public String getFrontpath() {
        return frontpath;
    }

    public void setFrontpath(String frontpath) {
        this.frontpath = frontpath;
    }

    public List<MenuItem> getChild() {
        return child;
    }

    public void setChild(List<MenuItem> child) {
        this.child = child;
    }
}