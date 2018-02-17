package com.j2core.sts.webcrawler.taskservice.controller;

import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.taskservice.model.userdto.UserData;
import com.j2core.sts.webcrawler.taskservice.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The controller class for work with authentication's requests
 */
@RestController
@RequestMapping("/TaskService/API/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    private final static Logger LOGGER = Logger.getLogger(AuthenticationController.class);


    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public SecurityToken get(@RequestBody UserData data){

        SecurityToken token;

        token = authenticationService.getSecurityToken(data.getLogin(), data.getPassword());

        return token;
    }

}
