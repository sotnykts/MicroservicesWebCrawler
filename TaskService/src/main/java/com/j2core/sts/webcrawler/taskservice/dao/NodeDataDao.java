package com.j2core.sts.webcrawler.taskservice.dao;


import com.j2core.sts.webcrawler.taskservice.model.informationdto.NodeData;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with nodeData's table from database.
 */
public interface NodeDataDao {

    /**
     * The method add new node in to the DB
     *
     * @param nodeName    node's name
     * @return  object with node's information
     */
    NodeData addNode(String nodeName);

    /**
     * The method get node's information
     *
     * @param nodeId     node's id witch information need get
     * @return    node's information  or null if this nodeId does not exist
     */
    NodeData get(int nodeId);

    /**
     * The method set stop flag for node
     *
     * @param nodeId   node's id
     * @param value    new value for flag
     * @return   change value flag is successfully or not
     */
    boolean setStopFlag(int nodeId, boolean value);

    /**
     * The method set work status for node
     *
     * @param nodeId   node's id
     * @param status   new value for status
     * @return change value is successfully or not
     */
    boolean setStatus(int nodeId, boolean status);

    /**
     * The method get node stop flag's value
     *
     * @param nodeId   node's id
     * @return value node's stop flag
     */
    boolean getStopFlag(int nodeId);

    /**
     * The method get node's work status
     *
     * @param nodeId   node's id
     * @return node's work status value
     */
    boolean getStatus(int nodeId);

}
