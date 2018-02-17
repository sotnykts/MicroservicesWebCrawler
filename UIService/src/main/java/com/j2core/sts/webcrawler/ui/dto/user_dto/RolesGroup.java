package com.j2core.sts.webcrawler.ui.dto.user_dto;

import javax.persistence.*;

/**
 * Object for save user's roles in the application
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

    /**
     * Constructor
     *
     * @param groupName       role group's name
     * @param description     role group's description
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesGroup role = (RolesGroup) o;

        if (groupId != role.groupId) return false;
        return groupName != null ? groupName.equals(role.groupName) : role.groupName == null;

    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
