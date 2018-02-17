package com.j2core.sts.webcrawler.crawler;

import com.j2core.sts.webcrawler.crawler.worker.Coordinator;
import com.j2core.sts.webcrawler.dao.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.dao.model.userdto.UserData;
import com.j2core.sts.webcrawler.dao.rest.DefaultRestClient;
import com.j2core.sts.webcrawler.dao.rest.RestClient;
import org.apache.log4j.Logger;

/**
 * Created by sts on 8/16/17.
 */
public class Application {

    private final static Logger LOGGER = Logger.getLogger(Application.class);         // class for save logs information

    public static void main(String[] args) {

        String login = "sts";
        String password = "admin";
        int amountThreads = 3;
        int amountTransitionUrls = 5;

        RestClient restClient = new DefaultRestClient();

        SecurityToken securityToken = restClient.getSecurityToken(new UserData(login, "Sotnyk", password));

        if (securityToken != null) {

            Coordinator coordinator = new Coordinator(securityToken, amountThreads, amountTransitionUrls, "Coordinator", restClient);
            Thread threadCoordinator = new Thread(coordinator);

            threadCoordinator.start();

            try {
                threadCoordinator.join();
            } catch (InterruptedException e) {
                LOGGER.error(" Sorry, some thing wrong.");
            }
        }

        LOGGER.info("stop main");

    }
}
