package com.j2core.sts.webcrawler.taskservice.controller;

import com.j2core.sts.webcrawler.taskservice.model.dto.FinalInformationDto;
import com.j2core.sts.webcrawler.taskservice.model.dto.TaskResultDto;
import com.j2core.sts.webcrawler.taskservice.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.taskservice.service.AuthenticationService;
import com.j2core.sts.webcrawler.taskservice.service.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import java.util.*;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The controller class for work with task's requests
 */
@RestController
@RequestMapping("/TaskService/API/task")
public class TaskController {

    private final static Logger LOGGER = Logger.getLogger(TaskController.class);
    private TaskService taskService;
    private AuthenticationService authService;

    @Autowired
    public TaskController(TaskService taskService, AuthenticationService authenticationService) {

        this.taskService = taskService;
        this.authService = authenticationService;

    }


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/get/{nodeId}/{amountTask}", method = RequestMethod.POST)
    public List<UrlData> getTasks(@PathVariable("nodeId") int  nodeId, @PathVariable("amountTask") int amountTask, @RequestBody SecurityToken token){

        if (authService.equalsSecurityInfo(token)){

            return taskService.getNotProcessesTask(amountTask, nodeId);

        }else return null;
    }


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/saveResult", method = RequestMethod.POST)
    public boolean saveTaskResult(@RequestBody TaskResultDto resultDto) {

        return authService.equalsSecurityInfo(resultDto.getToken()) && taskService.addTaskResult(resultDto.getResult());

    }


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/finalSave/{nodeId}", method = RequestMethod.POST)
    public boolean finalSaveTask(@PathVariable("nodeId") int nodeId, @RequestBody FinalInformationDto finalDto) {

        boolean result = false;

        if (authService.equalsSecurityInfo(finalDto.getToken())) {
            result = taskService.finalSaveInformation(finalDto.getProcessedTask(), finalDto.getProcessesTask(),
                    finalDto.getNotProcessesTask(), nodeId);

        }

        return result;
    }


}
