package com.j2core.sts.webcrawler.taskservice.service;

import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The interface for work with authentication service.
 */
public interface AuthenticationService {

    /**
     * The method get Security token with user's information for his authentication in system.
     *
     * @param login       user's login
     * @param password    user's password
     * @return Security token object with user's information
     */
    SecurityToken getSecurityToken(String login, String password);

    /**
     * The method compares user's information from security token and user's information in DB
     *
     * @param securityToken  user's information
     * @return   compares or not user's information from security token and information in DB
     */
    boolean equalsSecurityInfo(SecurityToken securityToken);
}
