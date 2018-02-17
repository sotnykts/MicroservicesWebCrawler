package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.userdto.UserData;
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
 * The class is default implement interface UserDataDao
 */
@Repository
public class DefaultUserDataDao implements UserDataDao {

    private final static Logger LOGGER = Logger.getLogger(DefaultUserDataDao.class); // class for save logs information
    private EntityManager entityManager;


    @Autowired
    public DefaultUserDataDao(EntityManagerFactory entityManagerFactory) {

        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public UserData get(int userId) {

        try{

            UserData userData = entityManager.find(UserData.class, userId);

            entityManager.clear();
            return userData;

        }catch (Exception ex){

            LOGGER.error(" Get user exception" + ex);
            entityManager.clear();
            return null;

        }
    }

    @Override
    public UserData get(String login) {

        UserData user;

        try {

            user = (UserData) entityManager.createQuery("select e from UserData e where e.login = :login").
                    setParameter("login", login).getSingleResult();

            entityManager.clear();
            return user;

        }catch (Exception ex){

            LOGGER.error(" Get user exception" + ex);
            entityManager.clear();
            return null;
        }

    }

    @Override
    public List<UserData> findUser(String userName) {

        List<UserData> users;

        try {

            users = entityManager.createQuery("select e from UserData e where e.userName = :userName").
                    setParameter("userName", userName).getResultList();

            entityManager.clear();
            return users;

        }catch (Exception ex){

            LOGGER.error(" Find user exception" + ex);
            entityManager.clear();
            return null;

        }
    }

    @Override
    public int add(UserData user) {

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

            entityManager.clear();
            return user.getUserId();

        }catch (Exception ex){

            LOGGER.error(" Add user exception" + ex);
            entityManager.clear();
            return -1;

        }
    }

    @Override
    public boolean update(UserData user) {

        boolean result = false;

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();

            result = true;

        }catch (Exception ex){

            LOGGER.error(" Update user exception" + ex);
        }

        entityManager.clear();
        return result;
    }

    @Override
    public boolean delete(int userId) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from UserData e where e.userId = :userId").
                    setParameter("userId", userId).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete user exception" + ex);
            entityManager.clear();
            return false;

        }
    }

    @Override
    public boolean delete(String login) {

        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("delete  from UserData e where e.login = :login").
                    setParameter("login", login).executeUpdate();
            entityManager.getTransaction().commit();

            entityManager.clear();
            return true;

        }catch (Exception ex){

            LOGGER.error(" Delete user exception" + ex);
            entityManager.clear();
            return false;

        }
    }
}
