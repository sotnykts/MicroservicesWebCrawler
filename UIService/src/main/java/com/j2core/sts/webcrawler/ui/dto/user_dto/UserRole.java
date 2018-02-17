package com.j2core.sts.webcrawler.ui.dto.user_dto;

/**
 * Enum user's roles in the application
 */
public enum UserRole {

    GUEST("Guest", 3),
    USER("User", 2),
    ADMIN("Admin", 1);

    private final String roleName;       // role's name
    private final int roleId;            // role's id

    UserRole(String roleName, int roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    /**
     * The method find role id
     *
     * @param name    role's name
     * @return        role's id
     */
    public static int fineRoleId(String name){

        for(UserRole role : UserRole.values()){

            if (name.equals(role.getRoleName())){
                return role.getRoleId();
            }
        }
        return -1;
    }
}
