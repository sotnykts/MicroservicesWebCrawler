package com.j2core.sts.webcrawler.crawler;

import com.j2core.sts.webcrawler.crawler.pagehandler.PageAnalyser;
import com.j2core.sts.webcrawler.dao.model.dto.TaskResultDto;
import com.j2core.sts.webcrawler.dao.model.informationdto.PageInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.ResultingInformation;
import com.j2core.sts.webcrawler.dao.model.informationdto.URLStatus;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.dao.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.dao.model.userdto.UserData;
import com.j2core.sts.webcrawler.dao.rest.DefaultRestClient;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by sts on 8/17/17.
 */
public class SaveResultingInformation {

    @Test
    public void testSaveResultingInformation(){

        String login = "sts";
        String password = "admin";

        DefaultRestClient restClient = new DefaultRestClient();
        PageAnalyser analyser = new PageAnalyser(5, 5);

        SecurityToken securityToken = restClient.getSecurityToken(new UserData(login, "Sotnyk", password));

        String url = "http://www.pcworld.com";
        int nodeId = 1709;

        UrlData urlData = new UrlData(url, 2, URLStatus.PROCESSES);
        urlData.setNodeId(nodeId);
        urlData.setUrlId(81);

        ResultingInformation information = analyser.analysePageInformation(urlData);

//        Assert.assertNotNull(information);

        boolean tmp = restClient.saveTaskResult(new TaskResultDto(securityToken, information));

        Assert.assertTrue(tmp);

    }
}
