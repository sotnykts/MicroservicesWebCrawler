package com.j2core.sts.webcrawler.taskservice.service;

import com.j2core.sts.webcrawler.taskservice.controller.TaskController;
import com.j2core.sts.webcrawler.taskservice.dao.NodeDataDao;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.NodeData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by Sotnyk Tetiana.
 */
/**
 * The class is NodeService interface implementation
 */
@Service
public class DefaultNodeService implements NodeService {

    private NodeDataDao nodeDataDao;
    private final static Logger LOGGER = Logger.getLogger(TaskController.class);


    @Autowired
    public DefaultNodeService(NodeDataDao nodeDataDao) {
        this.nodeDataDao = nodeDataDao;
    }

    public int createNewNode(String nodeName) {

        NodeData nodeData = nodeDataDao.addNode(nodeName);

        return nodeData.getNodeId();

    }


    public boolean changeWorkNodeFlag(int nodeId, boolean status) {

        return nodeDataDao.setStatus(nodeId, status);

    }

    public boolean getNodeStatus(int nodeId) {

        NodeData nodeData = nodeDataDao.get(nodeId);

        return nodeData.isStatusWork();

    }

    public NodeData get(int nodeId){

        return nodeDataDao.get(nodeId);
    }

    public boolean changeNodeStatus(int nodeId){

        return nodeDataDao.setStatus(nodeId, false);

    }
}
