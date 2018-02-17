package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.userdto.RolesGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default implement interface RolesGroupDao
 */
@Repository
public class DefaultRolesGroupDao implements RolesGroupDao {

    private final static Logger LOGGER = Logger.getLogger(DefaultRolesGroupDao.class); // class for save logs information
    private EntityManager entityManager;


    @Autowired
    public DefaultRolesGroupDao(EntityManagerFactory entityManagerFactory) {

        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<RolesGroup> getAll(){

        try {
            List<RolesGroup> result = entityManager.createQuery("select e from RolesGroup e").getResultList();

            entityManager.clear();
            return result;

        }catch (Exception ex){

            LOGGER.error(" Get All Roles group exception" + ex);
            entityManager.clear();
            return null;
        }
    }

    @Override
    public RolesGroup get(int groupId) {

        try {
            RolesGroup rolesGroup = entityManager.find(RolesGroup.class, groupId);

            entityManager.clear();
            return rolesGroup;

        }catch (Exception ex){

            LOGGER.error(" Get Roles group exception" + ex);
            entityManager.clear();
            return null;
        }
    }

    @Override
    public RolesGroup get(String groupName) {

        RolesGroup group;

        try {

            group = (RolesGroup) entityManager.createQuery("select e from RolesGroup e where e.groupName = :groupName").
                    setParameter("groupName", groupName).getSingleResult();

            entityManager.clear();
            return group;

        }catch (Exception ex){

            LOGGER.error(" Get Roles group exception" + ex);
            entityManager.clear();
            return null;
        }
    }

    @Override
    public boolean add(RolesGroup group) {

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(group);
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Add Roles group exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean update(RolesGroup group) {

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(group);
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Update Roles group exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean delete(String groupName) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from RolesGroup e where e.groupName = :groupName").
                    setParameter("groupName", groupName).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete Roles group exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean delete(int groupId) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from RolesGroup e where e.groupId = :groupId").
                    setParameter("groupId", groupId).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete Roles group exception" + ex);
            entityManager.clear();
            return false;

        }
    }
}
