package com.j2core.sts.webcrawler.ui.dto.user_dto;

/**
 * Object for save user's data and roles in the application
 */
public class UserStatistic extends UserData {

    private String userRole;    // user's roles in the application

    public UserStatistic (){

        super();
    }

    /**
     * Constructor
     *
     * @param userData   user's data
     */
    public UserStatistic(UserData userData){

        super(userData.getLogin(), userData.getUserName(), userData.getPassword());

    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
