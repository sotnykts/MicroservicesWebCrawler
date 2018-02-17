package com.j2core.sts.webcrawler.dao.model.userdto;

import javax.persistence.*;

/**
 * Created by sts on 5/25/17.
 */
@Entity
@Table(name = "UserData")
public class UserData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;                  // user's id

    @Column(name = "login", unique = true, length = 65535)
    private String login;                // user's login

    @Column(name = "userName", length = 65535)
    private String userName;             // user's name

    @Column(name = "password", length = 65535)
    private String password;             // user's password

    public UserData(){

    }

    public UserData(String login, String userName, String password){

        this.login = login;
        this.userName = userName;
        this.password = password;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (userId != userData.userId) return false;
        if (login != null ? !login.equals(userData.login) : userData.login != null) return false;
        if (userName != null ? !userName.equals(userData.userName) : userData.userName != null) return false;
        return password != null ? password.equals(userData.password) : userData.password == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
