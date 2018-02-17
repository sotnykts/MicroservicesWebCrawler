package com.j2core.sts.webcrawler.ui.dao.user;

import com.j2core.sts.webcrawler.ui.dao.WorkerDB;
import com.j2core.sts.webcrawler.ui.dto.user_dto.RolesGroup;
import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;
import com.j2core.sts.webcrawler.ui.dto.user_dto.UserData;
import com.j2core.sts.webcrawler.ui.service.CryptographerUserPassword;
import com.j2core.sts.webcrawler.ui.service.ObjectEntityManagerFactory;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by sts on 4/14/17.
 */

/**
 * The class get and change user's information
 */
public class UserDAOWorker extends WorkerDB{

    private final static Logger LOGGER = Logger.getLogger(UserDAOWorker.class); // class for save logs information
    private final EntityManagerFactory entityManagerFactory = ObjectEntityManagerFactory.getEntityManagerEntity();  // object for connect to the DB

    public UserDAOWorker(){

    }


    /**
     * The method get all user's information
     *
     * @param login   user's login
     * @param pass    user's password
     * @return        user's information
     */
    public SecurityToken getSecurityToken(String login, String pass) {

        SecurityToken token = null;
        UserData userData;
        String password = CryptographerUserPassword.getSecurePassword(login + pass);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            userData = (UserData) entityManager.createQuery("select e from UserData e where e.login = :login and e.password = :password").
                    setParameter("login", login).setParameter("password", password).getSingleResult();

            token = new SecurityToken();
            token.setUserData(userData);

            List<RolesGroup> groupList = findUserRole(userData);

            if (groupList != null) {
                token.setPermission(groupList);
            }

        } catch (NoResultException ex) {
            LOGGER.error(ex);
            return token;
        } finally {
            entityManager.close();
        }

        return token;

    }


    /**
     * The method change user's data
     *
     * @param userData       old user's data
     * @param newUserName    new user's name
     * @param newLogin       new user's login
     * @param newPass        new user's password
     * @return     change user data successfully or not
     */
    public boolean changeUserInformation(UserData userData, String newUserName, String newLogin, String newPass) {

        String password = CryptographerUserPassword.getSecurePassword(newLogin + newPass);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            userData.setUserName(newUserName);
            userData.setLogin(newLogin);
            userData.setPassword(password);
            entityManager.merge(userData);

            entityManager.getTransaction().commit();

        } catch (Exception ex) {

            return false;

        } finally {
            entityManager.close();
        }
        return true;

    }

}
