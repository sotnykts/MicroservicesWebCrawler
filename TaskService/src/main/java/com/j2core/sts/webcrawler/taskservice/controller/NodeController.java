package com.j2core.sts.webcrawler.taskservice.controller;

import com.j2core.sts.webcrawler.taskservice.model.informationdto.NodeData;
import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.taskservice.service.AuthenticationService;
import com.j2core.sts.webcrawler.taskservice.service.NodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The controller class for work with node's requests
 */
@RestController
@RequestMapping("/TaskService/API/node")
@Scope("request")
public class NodeController {

    private final static Logger LOGGER = Logger.getLogger(NodeController.class);
    private NodeService nodeService;
    private AuthenticationService authService;

    @Autowired
    public NodeController(NodeService nodeService, AuthenticationService authenticationService) {

        this.nodeService = nodeService;
        this.authService = authenticationService;

    }

    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/new/{nodeName}", method = RequestMethod.POST)
    public Integer createNode(@PathVariable("nodeName") String nodeName, @RequestBody SecurityToken token){

        if (authService.equalsSecurityInfo(token)){

            return nodeService.createNewNode(nodeName);

        }else return -1;

    }


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/flag/{nodeId}/{status:[0-1]}", method = RequestMethod.POST)
    public boolean changeWorkFlag(@PathVariable("nodeId") int  nodeId, @PathVariable("status") int status, @RequestBody SecurityToken token){

        boolean result = false;

        if (authService.equalsSecurityInfo(token)) {

            boolean workFlag;

            workFlag = status == 1;

            result =  nodeService.changeWorkNodeFlag(nodeId, workFlag);
        }
        return result;
    }


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/status/{nodeId}", method = RequestMethod.POST)
    public Integer getNodeStatus(@PathVariable("nodeId") int nodeId, @RequestBody SecurityToken token){

        if (authService.equalsSecurityInfo(token)) {

            boolean result = nodeService.getNodeStatus(nodeId);
            if (result){
                return 1;
            }else {
                return 0;
            }
        }else return -1;
    }


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/get/{nodeId}", method = RequestMethod.POST)
    public NodeData getNodeData(@PathVariable("nodeId") int nodeId, @RequestBody SecurityToken token) {

        if (authService.equalsSecurityInfo(token)) {

            return nodeService.get(nodeId);

        } else {

            return null;
        }
    }
}
