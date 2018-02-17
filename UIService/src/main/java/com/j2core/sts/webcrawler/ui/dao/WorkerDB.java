package com.j2core.sts.webcrawler.ui.dao;

import com.j2core.sts.webcrawler.ui.dto.node_dto.AllNodeStatistic;
import com.j2core.sts.webcrawler.ui.dto.node_dto.NodeData;
import com.j2core.sts.webcrawler.ui.dto.node_dto.NodeStatistic;
import com.j2core.sts.webcrawler.ui.dto.user_dto.*;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

/**
 * The class work with DB. Class get information about node's statistics and find user's roles in the application
 */
public class WorkerDB {

    private final static Logger LOGGER = Logger.getLogger(WorkerDB.class); // class for save logs information
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("webCrawler"); // object for connect to the DB

    /**
     * Constructor
     */
    public WorkerDB() {
    }


    /**
     * The method find user's roles in the application
     *
     * @param user user's data
     * @return   list with user'a roles in the application
     */
    protected List<RolesGroup> findUserRole(UserData user){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RolesGroup> groupList = null;

        try {

            List<Permission> permissionList = entityManager.createQuery("select e from Permission e where e.userData = :userId").
                    setParameter("userId", user).getResultList();

            if (!permissionList.isEmpty()){

                groupList = new LinkedList<RolesGroup>();
                for (Permission permission : permissionList){

                    groupList.add(entityManager.find(RolesGroup.class, permission.getRolesGroup().getGroupId()));

                }

            }
        } catch (Exception ex) {

            LOGGER.error(ex);
            return null;

        } finally {
            entityManager.close();
        }

        return groupList;
    }


    /**
     * The method get statistics about all nodes
     *
     * @return object with statistic's information
     */
    public AllNodeStatistic getNodesStatistics (){

        AllNodeStatistic allNodeStatistic = new AllNodeStatistic();
        List<NodeData> nodeDataList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            nodeDataList = entityManager.createQuery("select e from NodeData e").getResultList();
            Long stoppedNode = (Long) entityManager.createQuery("select count (e) from NodeData e where e.statusWork = false ").getSingleResult();

            allNodeStatistic.setNodeDataList(nodeDataList);
            allNodeStatistic.setStoppedNode(stoppedNode);
            allNodeStatistic.setStartedNode(nodeDataList.size());

        }catch (Exception ex){

            LOGGER.error(ex);
            return null;

        }finally {
            entityManager.close();
        }

        return allNodeStatistic;
    }


    /**
     * The method get node statistic with its URL's statistic
     *
     * @param nodeId  node's id
     * @return  object with statistics
     */
    public NodeStatistic getNodeStatistic(int nodeId){

        NodeStatistic nodeStatistic = new NodeStatistic();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            NodeData nodeData = entityManager.find(NodeData.class, nodeId);
            if (nodeData == null ){

                throw new Exception();
            }else {

                nodeStatistic.setStartTime(nodeData.getStartUnixTime());
                nodeStatistic.setStopTime(nodeData.getStopUnixTime());

                Long processesUrl = (Long) entityManager.createQuery("select count (e) from UrlData e where e.status = 1 and e.nodeId = :nodeId").
                        setParameter("nodeId", nodeId).getSingleResult();
                Long processedUrl = (Long) entityManager.createQuery("select count (e) from UrlData e where e.status = 2 and e.nodeId = :nodeId").
                        setParameter("nodeId", nodeId).getSingleResult();

                nodeStatistic.setAmountProcessesUrl(processesUrl);
                nodeStatistic.setAmountProcessedUrl(processedUrl);
            }

        }catch (Exception ex){

            LOGGER.error(ex);
            return null;

        }finally {
            entityManager.close();
        }
        return nodeStatistic;
    }

}
