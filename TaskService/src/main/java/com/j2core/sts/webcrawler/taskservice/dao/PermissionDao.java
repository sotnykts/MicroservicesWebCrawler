package com.j2core.sts.webcrawler.taskservice.dao;


import com.j2core.sts.webcrawler.taskservice.model.userdto.RolesGroup;
import com.j2core.sts.webcrawler.taskservice.model.userdto.UserData;

import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with Permission's table from database.
 */
public interface PermissionDao {

    /**
     * The method find all users with role
     *
     * @param role    role's group witch need find
     * @return   collection with users witch have this role
     */
    List findAllUser(RolesGroup role);

    /**
     * The method find all user's role in system
     *
     * @param user    user's data
     * @return  collection with user's roles in system
     */
    List<RolesGroup> findAllUserRole(UserData user);

    /**
     * The method add new permission for user
     *
     * @param user   user's data
     * @param role   role's data
     * @return add was successfully or not
     */
    boolean addPermission(UserData user, RolesGroup role);

    /**
     * The method add role's collection for user
     *
     * @param user       user's data
     * @param rolesList  role's collection for user
     * @return  add was successfully or not
     */
    boolean addAllPermission(UserData user, List<RolesGroup> rolesList);

    /**
     * The method delete permission with user's role
     *
     * @param user  user's data
     * @param role  user's role witch need delete
     * @return  delete permission was successfully or not
     */
    boolean deletePermission(UserData user, RolesGroup role);

    /**
     * The method delete all permission for user
     *
     * @param user     user'a data
     * @return delete was successfully or not
     */
    boolean deleteAllPermission(UserData user);

}
