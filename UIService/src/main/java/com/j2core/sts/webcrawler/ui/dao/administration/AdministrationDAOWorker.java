package com.j2core.sts.webcrawler.ui.dao.administration;

import com.j2core.sts.webcrawler.ui.dao.WorkerDB;
import com.j2core.sts.webcrawler.ui.dto.node_dto.AllNodeStatistic;
import com.j2core.sts.webcrawler.ui.dto.node_dto.NodeData;
import com.j2core.sts.webcrawler.ui.dto.node_dto.NodeStatus;
import com.j2core.sts.webcrawler.ui.dto.url_dto.UrlData;
import com.j2core.sts.webcrawler.ui.dto.user_dto.*;
import com.j2core.sts.webcrawler.ui.service.CryptographerUserPassword;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

/**
 * The class add new user, change user's data, delete user, and stop node.
 */
public class AdministrationDAOWorker extends WorkerDB {

    private final static Logger LOGGER = Logger.getLogger(AdministrationDAOWorker.class); // class for save logs information
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("webCrawler");  // object for connect to the DB

    /**
     * Constructor
     */
    public AdministrationDAOWorker(){

    }

    /**
     * The method add new user to the DB
     *
     * @param name    user's name
     * @param login   user's login
     * @param pass    user's password
     * @param roles   user's roles in the application
     * @return   add new user successfully or not
     */
    public boolean addNewUser(String name, String login, String pass, String[] roles){

        UserData userData = new UserData(login, name, pass);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(userData);

            for (String role : roles){

                int roleId = UserRole.fineRoleId(role);
                RolesGroup rolesGroup = entityManager.find(RolesGroup.class, roleId);

                Permission permission = new Permission(userData, rolesGroup);

                entityManager.persist(permission);

            }

            entityManager.getTransaction().commit();

        } catch (Exception ex) {

            LOGGER.error(ex);
            return false;

        } finally {
            entityManager.close();
        }

        return true;
    }


    /**
     * The method find user's information and create security token with user's information
     *
     * @param userId   user's id
     * @param login    user's login
     * @param name     user's name
     * @return         all user's information
     */
    public SecurityToken findUserInformation(String userId, String login, String name){

        SecurityToken userToken = null;

        UserData user = findUser(userId, login, name);

        if (user != null){

            userToken = new SecurityToken();
            userToken.setUserData(user);

            List<RolesGroup> groupList = findUserRole(user);

            if (groupList != null) {
                userToken.setPermission(groupList);
            }

        }

        return userToken;
    }


    /**
     * The method find user information by one parameter
     *
     * @param userId   user's id
     * @param login    user's login
     * @param name     user's name
     * @return         user's data
     */
    private UserData findUser(String userId, String login, String name){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserData user = null;

        try {
            if (userId.length() > 0){

                int id = Integer.parseInt(userId);

                user = entityManager.find(UserData.class, id);

            }else if(login.length() > 0){

                user = (UserData) entityManager.createQuery("select e from UserData e where e.login = :login").
                        setParameter("login", login).getSingleResult();

            }else if (name.length() > 0){

                user = (UserData) entityManager.createQuery("select e from UserData e where e.userName = :userName").
                        setParameter("userName", name).getSingleResult();

            }
        } catch (Exception ex) {

            LOGGER.error(ex);
            return null;

        } finally {
            entityManager.close();
        }

        return user;

    }


    /**
     * The method delete user from DB
     *
     * @param userId     user's id
     * @return  delete user successfully or not
     */
    public boolean deleteUser(int userId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            UserData user = entityManager.find(UserData.class, userId);

            entityManager.getTransaction().begin();

            // delete user's permission
            entityManager.createQuery("delete from Permission e where e.userData = :userId").
                    setParameter("userId", user).executeUpdate();

            // delete user's data
            entityManager.remove(user);

            entityManager.getTransaction().commit();

        } catch (Exception ex) {

            LOGGER.error(ex);
            return false;

        } finally {
            entityManager.close();
        }

        return true;

    }


    /**
     * The method change user's information
     *
     * @param userId       user's id
     * @param newName      new user's name
     * @param newLogin     new user's login
     * @param newPass      new user's password
     * @param newRoles     new user's roles in the application
     * @return             new Security token with new user's information
     */
    public SecurityToken adminChangeUserInformation(int userId, String newName, String newLogin, String newPass, String[] newRoles){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        SecurityToken userToken = new SecurityToken();
        // find all user's information
        UserData userData = entityManager.find(UserData.class, userId);

        try {

            entityManager.getTransaction().begin();

            // change user's name, if new user's name isn't null
            if (newName.length() > 0) userData.setUserName(newName);

            // change user's login, if new user's login isn't null
            if (newLogin.length() > 0) {

                userData.setLogin(newLogin);
                // change user's security password
                userData.setPassword(CryptographerUserPassword.getSecurePassword(newLogin + newPass));

            }

            entityManager.merge(userData);

            entityManager.getTransaction().commit();

            // save new user's information in to the new security token
            userToken.setUserData(userData);

            // change user's roles in app
            List<RolesGroup> permission = changeUserRole(entityManager, userData, newRoles);
            if (permission == null){
                throw new Exception();
            }else {
                // save new user's roles in new security token
                userToken.setPermission(permission);
            }

        } catch (Exception ex) {

            LOGGER.error(ex);
            return null;

        } finally {
            entityManager.close();
        }

        // return new security token with new user's information
        return userToken;

    }


    /**
     * The method change user's roles in the application
     *
     * @param entityManager     object for work with DB
     * @param userData          user's data
     * @param roles             new user's roles
     * @return    list with new user's roles
     */
    private List<RolesGroup> changeUserRole(EntityManager entityManager, UserData userData, String[] roles){

        List<RolesGroup>  newRoles = new LinkedList<RolesGroup>();

        try {

            entityManager.getTransaction().begin();

            // get old user's permissions
            List<Permission> userPermission  = entityManager.createQuery("select e from Permission e where e.userData = :userId").
                    setParameter("userId", userData).getResultList();

            // if amount new roles is not null
            if (roles.length > 0){

                Permission permission;
                RolesGroup role;
                int roleId;
                int index = 0;
                while (index < roles.length){

                    // if amount old user's role is not null
                    if (!userPermission.isEmpty()){

                        // change old to the new user's role
                        permission = userPermission.remove(0);
                        roleId = UserRole.fineRoleId(roles[index]);
                        role = entityManager.find(RolesGroup.class, roleId);

                        permission.setUserData(userData);
                        permission.setRolesGroup(role);

                        entityManager.merge(permission);

                        newRoles.add(role);

                    }else {

                        // add new user's role
                        roleId = UserRole.fineRoleId(roles[index]);
                        role = entityManager.find(RolesGroup.class, roleId);
                        permission = new Permission(userData, role);

                        entityManager.persist(permission);
                    }

                    index++;

                }
                if (!userPermission.isEmpty()){

                    for (Permission perm : userPermission){

                        entityManager.remove(perm);

                    }
                }
            }else {

                for (Permission perm : userPermission){

                    // delete old user's role
                    entityManager.remove(perm);

                }
            }

            entityManager.getTransaction().commit();

        }catch (Exception ex){

            LOGGER.error(ex);
            return null;
        }

        return newRoles;
    }


    /**
     * The method stop node
     *
     * @param nodeId   node's id which need stop
     * @return  stop node successfully or not
     */
    public boolean stopNode(int nodeId){

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            NodeData nodeData = entityManager.find(NodeData.class, nodeId);

            nodeData.setStoppedFlag(true);

            entityManager.getTransaction().begin();

            entityManager.merge(nodeData);

            entityManager.getTransaction().commit();

        }catch (Exception ex){
            LOGGER.error(ex);
            return false;
        }finally {
            entityManager.close();
        }

        return true;

    }


    /**
     * The method get status all node
     *
     * @return   list with node's status
     */
    public List<NodeStatus> getNodeStatus(){

        List<NodeStatus> nodeStatusList = new LinkedList<NodeStatus>();
        AllNodeStatistic nodes = getNodesStatistics();

        for (NodeData nodeData : nodes.getNodeDataList()){

            NodeStatus nodeStatus = new NodeStatus(nodeData);
            nodeStatus.setNodeStatus(getStatus(nodeData));

            nodeStatusList.add(nodeStatus);

        }

        return nodeStatusList;
    }


    /**
     * The method get node's status
     *
     * @param nodeData   node's data
     * @return  node's status
     */
    private int getStatus(NodeData nodeData){

        int status = 1;
        UrlData urlData = null;

        if (nodeData.getStopUnixTime() > 0){

            status = -1;

        }else {

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            long time = System.currentTimeMillis();
            long expTime = time - 3600000;

            try {

                // find url with status change time bigger at exp. time
                urlData = (UrlData) entityManager.createQuery("select e from UrlData e where e.nodeId = :nodeId and e.statusChangeUnixTime > :statusChangeUnixTime").
                        setParameter("nodeId", nodeData.getNodeId()).setParameter("statusChangeUnixTime", expTime).getSingleResult();

            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            finally {
                entityManager.close();
            }
            // if node not have url with status change time bigger at exp. time - node's work status is false (not work)
            if (urlData == null) {

                status = 0;
            }
        }
        return status;
    }
}
