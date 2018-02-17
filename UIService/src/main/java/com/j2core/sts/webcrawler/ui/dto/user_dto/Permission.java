package com.j2core.sts.webcrawler.ui.dto.user_dto;

import javax.persistence.*;

/**
 * Object for save information about roles which user have
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

    /**
     * Constructor
     */
    public Permission(){

    }

    /**
     * Constructor
     *
     * @param userData       user's data
     * @param rolesGroup     user role's group
     */
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
