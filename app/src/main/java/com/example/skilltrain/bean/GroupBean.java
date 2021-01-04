package com.example.skilltrain.bean;

public class GroupBean {
    String groupName, groupCategory, groupDoor;


    public GroupBean(String groupName, String groupCategory, String groupDoor) {
        this.groupName = groupName;
        this.groupCategory = groupCategory;
        this.groupDoor = groupDoor;

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCategory() {
        return groupCategory;
    }

    public void setGroupCategory(String groupCategory) {
        this.groupCategory = groupCategory;
    }

    public String getGroupDoor() {
        return groupDoor;
    }

    public void setGroupDoor(String groupDoor) {
        this.groupDoor = groupDoor;
    }
}
