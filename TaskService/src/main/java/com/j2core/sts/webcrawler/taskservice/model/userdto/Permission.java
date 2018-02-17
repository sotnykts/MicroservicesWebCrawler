package com.j2core.sts.webcrawler.taskservice.model.userdto;

import javax.persistence.*;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep information about roles group for user
 */
@Entity
@Table(name = "Permission")
public class Permission {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "permissionId")
    private int permissionId;            // permission id

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserData userData;          // user's data

    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false)
    private RolesGroup rolesGroup;       // user's role group


    public Permission(){

    }

    public Permission(UserData userData, RolesGroup rolesGroup){

        this.userData = userData;
        this.rolesGroup = rolesGroup;

    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public RolesGroup getRolesGroup() {
        return rolesGroup;
    }

    public void setRolesGroup(RolesGroup rolesGroup) {
        this.rolesGroup = rolesGroup;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", userData=" + userData +
                ", rolesGroup=" + rolesGroup +
                '}';
    }
}
