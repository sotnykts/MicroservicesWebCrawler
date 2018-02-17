package com.j2core.sts.webcrawler.taskservice.model.userdto;

import javax.persistence.*;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep information about roles group for application
 */
@Entity
@Table(name = "RolesGroup")
public class RolesGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "groupId")
    private int groupId;                // role's group id

    @Column(name = "groupName", unique = true, length = 65535)
    private String groupName;           // role's group name

    @Column(name = "description", length = 65535)
    private String description;         // role's group description

    public RolesGroup() {

    }

    public RolesGroup(String groupName, String description){

        this.groupName = groupName;
        this.description = description;

    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RolesGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
