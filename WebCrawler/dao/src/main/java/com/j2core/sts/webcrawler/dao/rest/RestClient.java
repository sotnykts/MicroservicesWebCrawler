package com.j2core.sts.webcrawler.dao.rest;

import com.j2core.sts.webcrawler.dao.model.dto.FinalInformationDto;
import com.j2core.sts.webcrawler.dao.model.dto.TaskResultDto;
import com.j2core.sts.webcrawler.dao.model.informationdto.NodeData;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.dao.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.dao.model.userdto.UserData;

import java.util.List;

/**
 * Created by sts on 8/15/17.
 */

/**
 * The interface for work with REST client
 */
public interface RestClient {

    /**
     * The method get security token with user's information from DB
     *
     * @param user user's Data
     * @return security token with user's information witch need for authentication user in system
     */
    SecurityToken getSecurityToken(UserData user);

    /**
     * The method get node's data
     *
     * @param nodeId        node's Id
     * @param token         security token for authentication user in system
     * @return node's data
     * @throws Exception
     */
    NodeData getNodeData(int nodeId, SecurityToken token) throws Exception;

    /**
     * The method get node's status
     *
     * @param nodeId       node's Id
     * @param token        security token for authentication user in system
     * @return  1 if node's status - true, and 0 if node's status - false
     */
    Integer getNodeStatus(int nodeId, SecurityToken token);

    /**
     * The method change work node's flag
     *
     * @param nodeId    node's Id
     * @param status    work node's status value
     * @param token     security token for authentication user in system
     * @return  change work node's flag is successfully or not
     */
    boolean changeWorkFlag(int nodeId, int status, SecurityToken token);

    /**
     * The method create new node
     *
     * @param nodeName   name for new node
     * @param token      security token for authentication user in system
     * @return  value new node'd Id
     */
    Integer createNode(String nodeName, SecurityToken token);

    /**
     * The method get new Task for work ( URLs for analyse its content)
     *
     * @param nodeId        attached node's Id
     * @param amountTask    amount task for work
     * @param token         security token for authentication user in system
     * @return   collection with URL's information
     */
    List<UrlData> getTask(int nodeId, int amountTask, SecurityToken token);

    /**
     * The method save task's result
     *
     * @param taskResult   analyse task's result (page information, words from page, new URLs from page, etc.)
     * @return  save result successfully or not
     */
    boolean saveTaskResult(TaskResultDto taskResult);

    /**
     * The method final save tasks information
     *
     * @param finalInfo  information for final save (processed tasks, processes tasks, not processes task, etc.)
     * @param nodeId     attached node's Id
     * @return  final save successfully or not
     */
    boolean finalSaveInformation(FinalInformationDto finalInfo, int nodeId);

}
