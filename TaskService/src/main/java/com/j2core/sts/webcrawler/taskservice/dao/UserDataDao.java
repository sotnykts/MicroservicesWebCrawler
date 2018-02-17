package com.j2core.sts.webcrawler.taskservice.dao;


import com.j2core.sts.webcrawler.taskservice.model.userdto.UserData;

import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with UserData's table from database.
 */
public interface UserDataDao {

    /**
     * The method get user's data
     *
     * @param userId   user's id
     * @return user's information
     */
    UserData get(int userId);

    /**
     * The method get user's data
     *
     * @param login   user's login
     * @return user's information
     */
    UserData get(String login);

    /**
     * The method find user
     *
     * @param userName   user's name
     * @return   user collection witch have user name
     */
    List<UserData> findUser(String userName);

    /**
     * The method add new user
     *
     * @param user   information about new user
     * @return  new user's id
     */
    int add(UserData user);

    /**
     * The method update user's data
     *
     * @param user    new user's data
     * @return update was successfully or not
     */
    boolean update(UserData user);

    /**
     * The method delete user's data
     *
     * @param userId   user's id
     * @return delete was successfully or not
     */
    boolean delete(int userId);

    /**
     * The method delete user's data
     *
     * @param login  user's login
     * @return  delete was successfully or not
     */
    boolean delete(String login);
}
