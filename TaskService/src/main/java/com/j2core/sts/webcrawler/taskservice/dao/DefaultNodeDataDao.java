package com.j2core.sts.webcrawler.taskservice.dao;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.NodeData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default implement interface NodeDataDao
 */
@Repository
public class DefaultNodeDataDao implements NodeDataDao {

    private final static Logger LOGGER = Logger.getLogger(DefaultNodeDataDao.class); // class for save logs information

    private EntityManager entityManager;

    @Autowired
    public DefaultNodeDataDao(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public NodeData addNode(String nodeName) {

        NodeData nodeData = new NodeData(nodeName);

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(nodeData);
            entityManager.getTransaction().commit();

        }catch (Exception ex){

            LOGGER.error(" Get node exception" + ex);
            entityManager.clear();
            return null;

        }

        entityManager.clear();
        return nodeData;
    }

    @Override
    public NodeData get(int nodeId){

        NodeData nodeData = null;

        try {

            nodeData = entityManager.find(NodeData.class, nodeId);

        }catch (Exception ex){

            LOGGER.error(" Get node exception" + ex);

        }

        entityManager.clear();
        return nodeData;
    }

    @Override
    public boolean setStopFlag(int nodeId, boolean value) {

        NodeData nodeData;

        try {

            nodeData = entityManager.find(NodeData.class, nodeId);
            nodeData.setStopFlag(value);

            entityManager.getTransaction().begin();
            entityManager.merge(nodeData);
            entityManager.getTransaction().commit();

        }catch (Exception ex){

            LOGGER.error(" Set stop flag node exception" + ex);
            entityManager.clear();
            return false;

        }

        entityManager.clear();
        return true;
    }

    @Override
    public boolean setStatus(int nodeId, boolean status) {

        NodeData nodeData;

        try {

            nodeData = entityManager.find(NodeData.class, nodeId);
            nodeData.setStatusWork(status);
            nodeData.setStopUnixTime(System.currentTimeMillis());

            entityManager.getTransaction().begin();
            entityManager.merge(nodeData);
            entityManager.getTransaction().commit();

        }catch (Exception ex){

            LOGGER.error(" Set status node exception" + ex);
            entityManager.clear();
            return false;

        }

        entityManager.clear();
        return true;
    }

    @Override
    public boolean getStopFlag(int nodeId) {

        try {
            NodeData nodeData = entityManager.find(NodeData.class, nodeId);

            entityManager.clear();
            return nodeData.isStopFlag();

        }catch (Exception ex){

            LOGGER.error(" Get stop flag node exception" + ex);
            entityManager.clear();
            return true;
        }

    }

    @Override
    public boolean getStatus(int nodeId) {

        try {
            NodeData nodeData = entityManager.find(NodeData.class, nodeId);

            entityManager.clear();
            return nodeData.isStatusWork();

        }catch (Exception ex){

            LOGGER.error(" Get status node exception" + ex);
            entityManager.clear();
            return false;
        }
    }
}
