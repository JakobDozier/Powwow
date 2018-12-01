package com.example.jakobdozier.powwow;

public class GroupProfile {
    String description;
    String name;
    long members;

    public String getGroupDescription() {
        return description;
    }

    public void setGroupDescription(String groupDescription) {
        this.description = groupDescription;
    }

    public String getGroupName() {
        return name;
    }

    public void setGroupName(String groupName) {
        this.name = groupName;
    }

    public long getMembers() {
        return members;
    }

    public void setMembers(long members) {
        this.members = members;
    }
}
