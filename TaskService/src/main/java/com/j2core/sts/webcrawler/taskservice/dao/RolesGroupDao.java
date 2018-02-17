package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.userdto.RolesGroup;

import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with rolesGroup's table from database.
 */
public interface RolesGroupDao {

    /**
     * The method get collection with all roles group in system
     *
     * @return    collection with roles group in system
     */
    List getAll();

    /**
     * The method get role's group information
     *
     * @param groupId   group's id
     * @return role's group information
     */
    RolesGroup get(int groupId);

    /**
     * The method get role's group information
     *
     * @param groupName     group's name
     * @return  role's group information
     */
    RolesGroup get(String groupName);

    /**
     * The method add new role's group in the system
     *
     * @param group  information about new role's group
     * @return  add was successfully or not
     */
    boolean add(RolesGroup group);

    /**
     * The method update role's information
     *
     * @param group  new role's information
     * @return  update was successfully or not
     */
    boolean update(RolesGroup group);

    /**
     * The method delete role's group from system
     *
     * @param groupName   group's name
     * @return delete was successfully or not
     */
    boolean delete(String groupName);

    /**
     * The method delete role's group
     *
     * @param groupId   role's group id
     * @return delete was successfully or not
     */
    boolean delete(int groupId);
}
