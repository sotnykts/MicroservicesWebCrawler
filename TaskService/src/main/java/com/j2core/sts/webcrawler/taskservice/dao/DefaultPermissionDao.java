package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.userdto.Permission;
import com.j2core.sts.webcrawler.taskservice.model.userdto.RolesGroup;
import com.j2core.sts.webcrawler.taskservice.model.userdto.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.LinkedList;
import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default implement interface PermissionDao
 */
@Repository
public class DefaultPermissionDao implements PermissionDao {


    private final static Logger LOGGER = Logger.getLogger(DefaultPermissionDao.class); // class for save logs information
    private EntityManager entityManager;


    @Autowired
    public DefaultPermissionDao(EntityManagerFactory entityManagerFactory) {

        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<UserData> findAllUser(RolesGroup role) {

        List<UserData> result = null;

        try {

            List<Permission> permissionList = entityManager.createQuery("select e from Permission e where e.rolesGroup = :rolesGroup").
                    setParameter("rolesGroup", role).getResultList();

            if (permissionList.size() > 0){

                result = new LinkedList<UserData>();

                for (Permission permission : permissionList){

                    result.add(permission.getUserData());

                }
            }
        }catch (Exception ex){

            LOGGER.error(" Find all user permission exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public List<RolesGroup> findAllUserRole(UserData user) {

        List<RolesGroup> result = new LinkedList<RolesGroup>();

        try {

            List<Permission> permissionList = entityManager.createQuery("select e from Permission e where e.userData = :userData").
                    setParameter("userData", user).getResultList();

            if (permissionList.size() > 0) {

                for (Permission permission : permissionList) {

                    result.add(permission.getRolesGroup());

                }
            }
        }catch (Exception ex){

            LOGGER.error(" Find all user role permission exception" + ex);
            entityManager.clear();
            return null;
        }

        entityManager.clear();
        return result;

    }

    @Override
    public boolean addPermission(UserData user, RolesGroup role) {

        boolean result = false;
        Permission permission = new Permission(user, role);

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(permission);
            entityManager.getTransaction().commit();

            result = true;

        }catch (Exception ex){

            LOGGER.error(" Add permission exception" + ex);

        }

        entityManager.clear();
        return result;
    }

    @Override
    public boolean addAllPermission(UserData user, List<RolesGroup> rolesList) {

        try {

            for (RolesGroup group : rolesList){

                if (!addPermission(user, group)){

                    return false;
                }
            }
            return true;

        }catch (Exception ex){

            LOGGER.error(" Add all permission exception" + ex);
            return false;
        }

    }

    @Override
    public boolean deletePermission(UserData user, RolesGroup role) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from Permission e where e.userData.userId = :userId and e.rolesGroup.groupId = :groupId").
                    setParameter("userId", user.getUserId()).setParameter("groupId", role.getGroupId()).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete permission exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean deleteAllPermission(UserData user) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from Permission e where e.userData.userId = :userId").
                    setParameter("userId", user.getUserId()).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.flush();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete all permission exception" + ex);
            entityManager.clear();
            return false;

        }
    }
}
