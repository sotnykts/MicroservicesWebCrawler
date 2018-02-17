package com.j2core.sts.webcrawler.dao.model.userdto;

import java.util.List;

/**
 * Created by sts on 1/20/17.
 */

/**
 * Object for save all user's information
 */
public class SecurityToken {

    private UserData userData;              // user's data

    private List<RolesGroup> permission;    // user's permission

    public SecurityToken(){

    }

    public SecurityToken(UserData userData, List<RolesGroup> permission) {
        this.userData = userData;
        this.permission = permission;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<RolesGroup> getPermission() {
        return permission;
    }

    public void setPermission(List<RolesGroup> permission) {

        this.permission = permission;
    }

    @Override
    public String toString() {
        return "SecurityToken{" +
                "userData=" + userData +
                ", permission=" + permission +
                '}';
    }


    /**
     * The method create string with all user's roles in the application
     *
     * @return   string with user's roles
     */
    public String toPermissionString(){

        StringBuilder stringBuilder = new StringBuilder();
        for (RolesGroup role : permission){

            stringBuilder.append(" ").append(role.getGroupName()).append(" - ").append(role.getDescription()).append(";").append("\n");

        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecurityToken token = (SecurityToken) o;

        return userData.equals(token.userData);

    }

    @Override
    public int hashCode() {
        return userData.hashCode();
    }
}
