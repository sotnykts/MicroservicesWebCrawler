package com.j2core.sts.webcrawler.ui.dao.statistic;

import com.j2core.sts.webcrawler.ui.dao.WorkerDB;
import com.j2core.sts.webcrawler.ui.dto.url_dto.UrlStatistic;
import com.j2core.sts.webcrawler.ui.dto.user_dto.RolesGroup;
import com.j2core.sts.webcrawler.ui.dto.user_dto.UserData;
import com.j2core.sts.webcrawler.ui.dto.user_dto.UserStatistic;
import com.j2core.sts.webcrawler.ui.service.ObjectEntityManagerFactory;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.LinkedList;
import java.util.List;

/**
 * The class get information for user, url, and node's statistics
 */
public class StatisticDAOWorker extends WorkerDB{

    private final static Logger LOGGER = Logger.getLogger(StatisticDAOWorker.class); // class for save logs information
    private final EntityManagerFactory entityManagerFactory = ObjectEntityManagerFactory.getEntityManagerEntity();  // object for connect to the DB

    /**
     * Constructor
     */
    public StatisticDAOWorker(){

    }

    /**
     * The method get all user's information
     *
     * @return list with all user's information
     */
    public List<UserStatistic> getUserStatistic(){

        List<UserStatistic> userStatisticList = new LinkedList<UserStatistic>();
        List<UserData> userData = getUsers();

        assert userData != null;
        for (UserData user : userData){

            UserStatistic userStatistic = new UserStatistic(user);
            userStatistic.setUserId(user.getUserId());
            userStatistic.setUserRole(createRolesString(findUserRole(user)));
            userStatisticList.add(userStatistic);
        }

        return userStatisticList;
    }


    /**
     * The method create string with roles in the application for user
     *
     * @param rolesGroups   list with user's roles
     * @return  string with information
     */
    private String createRolesString(List<RolesGroup> rolesGroups){

        StringBuilder string = new StringBuilder();
        for (RolesGroup role : rolesGroups){

            string.append(role.getGroupName()).append(", ");

        }
        return string.toString();
    }


    /**
     * The method get all users data
     *
     * @return list with user's data
     */
    private List<UserData> getUsers(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<UserData> userData = null;

        try {

            userData = entityManager.createQuery("select e from UserData e").getResultList();

        } catch (Exception ex) {

            LOGGER.error(ex);
            return null;

        } finally {
            entityManager.close();
        }

        return userData;
    }


    /**
     * The method get all roles in the application
     *
     * @return list with roles
     */
    public List<RolesGroup> getRolesGroup(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RolesGroup> roles = null;

        try {

            roles = entityManager.createQuery("select e from RolesGroup e").getResultList();

        } catch (Exception ex) {

            LOGGER.error(ex);
            return null;

        } finally {
            entityManager.close();
        }

        return roles;

    }


    /**
     * The method get URL's statistics
     *
     * @return  object with URL's statistics
     */
    public UrlStatistic getUrlStatistic(){

        UrlStatistic statistic = new UrlStatistic();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            long amountUrl = (Long) entityManager.createQuery("select count (e) from UrlData e").getSingleResult();
            long notProcessedUrl = (Long) entityManager.createQuery("select count (e) from UrlData e where e.status = 0").getSingleResult();
            long processedUrl = (Long) entityManager.createQuery("select count (e) from UrlData e where e.status = 2").getSingleResult();
            long processesUrl = amountUrl - notProcessedUrl - processedUrl;

            statistic.setAmountUrl(amountUrl);
            statistic.setNotProcessedUrl(notProcessedUrl);
            statistic.setProcessedUrl(processedUrl);
            statistic.setProcessesUrl(processesUrl);

        }catch (Exception ex){

            LOGGER.error(ex);
            return null;

        }finally {
            entityManager.close();
        }

        return statistic;
    }

}
