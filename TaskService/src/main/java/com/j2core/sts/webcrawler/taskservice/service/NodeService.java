package com.j2core.sts.webcrawler.taskservice.service;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.NodeData;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with node's service.
 */
public interface NodeService {

    /**
     * The method create new node
     *
     * @param nodeName  node's name
     * @return  int new node's Id
     */
    int createNewNode(String nodeName);

    /**
     * The method change work node's flag
     *
     * @param nodeId  node's Id
     * @param status  status for work node's flag
     * @return  change successfully or not
     */
    boolean changeWorkNodeFlag(int nodeId, boolean status);

    /**
     * The method get node's status
     *
     * @param nodeId  node's Id
     * @return  node's status status
     */
    boolean getNodeStatus(int nodeId);

    /**
     * The method get node's information
     *
     * @param nodeId  node's Id
     * @return  node's information
     */
    NodeData get(int nodeId);

    /**
     * The method change node's work status
     *
     * @param nodeId node's Id
     * @return change successfully or not
     */
    boolean changeNodeStatus(int nodeId);
}
