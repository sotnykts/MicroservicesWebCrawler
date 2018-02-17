package com.j2core.sts.webcrawler.taskservice.service;

import com.j2core.sts.webcrawler.taskservice.controller.TaskController;
import com.j2core.sts.webcrawler.taskservice.dao.PermissionDao;
import com.j2core.sts.webcrawler.taskservice.dao.UserDataDao;
import com.j2core.sts.webcrawler.taskservice.model.userdto.RolesGroup;
import com.j2core.sts.webcrawler.taskservice.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.taskservice.model.userdto.UserData;
import com.j2core.sts.webcrawler.taskservice.utils.CryptographerUserPassword;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */
/**
 * The class is AuthenticationService interface implementation
 */
@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private UserDataDao userDataDao;
    private PermissionDao permissionDao;
    private final static Logger LOGGER = Logger.getLogger(TaskController.class);


    @Autowired
    public DefaultAuthenticationService(UserDataDao userDataDao, PermissionDao permissionDao) {

        this.userDataDao = userDataDao;
        this.permissionDao = permissionDao;
    }


    public SecurityToken getSecurityToken(String login, String userPassword) {

        SecurityToken token = null;
        UserData user = userDataDao.get(login);

        if (user != null) {

            String password = CryptographerUserPassword.getSecurePassword(login + userPassword);
            if (user.getPassword().equalsIgnoreCase(password)) {

                List<RolesGroup> permission = permissionDao.findAllUserRole(user);

                token = new SecurityToken();
                token.setUserData(user);
                token.setPermission(permission);

            }
        }

        return token;
    }

    public boolean equalsSecurityInfo(SecurityToken token) {

        boolean result = false;

        if (token != null) {

            UserData userData = userDataDao.get(token.getUserData().getUserId());

            if (userData != null) {
                if (token.getUserData().getLogin().equalsIgnoreCase(userData.getLogin())) {

                    if (token.getUserData().getPassword().equalsIgnoreCase(userData.getPassword()) && token.getPermission().size() > 0) {

                        result = true;
                    }

                }
            }
        }

        return result;
    }
}
